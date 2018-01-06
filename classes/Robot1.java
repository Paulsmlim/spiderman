import greenfoot.*;

/**
 * Weakest robot of all Robots
 * Only spawns in RobotCastle1 and RobotCastle4
 * Melee fighter
 */
public class Robot1 extends Enemy
{
    private int health;
    // Horizontal speed
    private int speed = 1;
    private int imageNum = 1;
    private int time = 0;
    // Vertical speed
    private int vSpeed = 1;
    // Vertical acceleration for falling
    private int acceleration = 1;
    private boolean alive = true;
    // True whenever Robot1 gets hit by Paul
    private boolean hurt = false;
    private boolean facingRight;
    // X and Y value of Paul
    private int PaulX;
    private int PaulY;
    // True whenever Robot1 is attacking Paul
    private boolean attacking = false;
    
    public Robot1(boolean fR)
    {
        health = 160;
        facingRight = fR;
    }
    
    public void act() 
    {
        // If Robot1 is not hurt, it can move or attack; if it is hurt, it has to stabilize itself
        if(!hurt){
            if(alive){
                checkKeys();
                checkFall();
                checkDie();
            }
        }
        else{
            stabilize();
        }
        
        // If dead, death animation starts/continues
        if(!alive){
            deathAnimation();
        }
    }    

    public void checkKeys()
    {
        //Gets Paul's X and Y values
        PaulX = ((Paul)getWorld().getObjects(Paul.class).get(0)).getX();
        PaulY = ((Paul)getWorld().getObjects(Paul.class).get(0)).getY();
        
        /* If Robot1 is at most 100 pixels below Paul and at most 30 pixels above
           Paul, then it will move towards Paul */
        if(((getY() - PaulY <= 100) && (getY() - PaulY >= -30)) || isTouching(Paul.class)){
            // Determines which direction Robot has to face to move towards Paul
            if(this.getX() - PaulX < -30){
                moveRight();
            }
            else if(this.getX() - PaulX > 30){
                moveLeft();
            }
            // True only if Robot is 30 pixels horizontal distance from Paul; attack Paul
            else {
                attack();
            }
        }
        // Robot does not notice Paul, so it stands still
        else {
            standing();
        }
    }

    public void standing()
    {
        attacking = false;
        imageNum = 1;
        GreenfootImage img = new GreenfootImage("Robot1Standing.png");
        if(facingRight){
            setImage(img);
        }
        else{
            img.mirrorHorizontally();
            setImage(img);
        }
    }

    public void moveRight()
    {
        // True only if it was previously attacking Paul but now has to chase Paul
        if(attacking){
            imageNum = 1;
        }
        
        attacking = false;
        
        // True only if it was previously facing/walking left; change direction
        if(!facingRight){
            imageNum = 1;
        }
        
        facingRight = true;
        time++;
        
        // Image of Robot1 changes for every 10th continuous call of this function
        if(time%10 == 0){
            setImage("Robot1Walking-" + imageNum + ".png");
            // Moving animation of Robot1 resets after 4 images
            if(imageNum < 4){
                imageNum++;
            }
            else{
                imageNum = 1;
            }
        }
        
        setLocation(getX()+speed, getY());
    }

    public void moveLeft()
    {
        if(attacking){
            imageNum = 1;
        }
        
        attacking = false;
        
        if(facingRight){
            imageNum = 1;
        }
        
        facingRight = false;
        time++;
        
        if(time%10 == 0){
            GreenfootImage img = new GreenfootImage("Robot1Walking-" + imageNum + ".png");
            img.mirrorHorizontally();
            setImage(img);
            if(imageNum < 4){
                imageNum++;
            }
            else{
                imageNum = 1;
            }
        }
        
        setLocation(getX()-speed, getY());
    }

    public void fall()
    {
        imageNum = 1;
        GreenfootImage img = new GreenfootImage("Robot1inAir.png");
        
        if(facingRight){
            setImage(img);
        }
        else{
            img.mirrorHorizontally();
            setImage(img);
        }
        
        setLocation(getX(), getY()+vSpeed);
        // Update vertical speed so Robot falls faster 
        vSpeed = vSpeed + acceleration;
    }

    public void checkFall()
    {
        // If Robot1 is touching the ground or a platform, he will not fall; else, he falls
        if(!isTouching(StandingObject.class)){
            fall();
        } else if(vSpeed != 0){
            vSpeed = 0;
        }
    }

    public void attack()
    {
        // True only if Robot1 just initiated its attack
        if(!attacking){
            imageNum = 1;
        }
        
        attacking = true;
        time++;
        
        if(time%20==0)
        {
            // True only if completed full attack animation; attack is done
            if(imageNum > 4){
                attacking = false;
                imageNum = 1;
        
            }
            else {
                GreenfootImage img = new GreenfootImage("Robot1Attacking-" + imageNum + ".png");
                
                if(facingRight){
                    setImage(img);
                }
                else{
                    img.mirrorHorizontally();
                    setImage(img);
                }
                
                // If the robot's image is of it punching/kicking, it deals 10 damage to Paul
                if(imageNum == 2 || imageNum == 4){
                    doDamage(10);
                }
                
                imageNum++;
            }
        }
    }

    public void doDamage(int damage)
    {
        Greenfoot.playSound("RobotPunch.wav");
        Paul p = ((Paul)getWorld().getObjects(Paul.class).get(0));
        p.takeDamage(damage);
    }

    public void takeDamage(int damage){
        health -= damage;
        /* If the Robot gets hit by a weak attack (spider web), it will
           only get hurt / need to stabilize 20% of the time */
        if(damage == 5){
            if(Greenfoot.getRandomNumber(5) == 1){
                hurt();
            }
        }
        else{
            hurt();
        }
    }

    public void hurt()
    {
        hurt = true;
        GreenfootImage img = new GreenfootImage("Robot1Hit.png");
        
        if(facingRight){
            setImage(img);
        }
        else{
            img.mirrorHorizontally();
            setImage(img);
        }
    }
    
    // Robot can not do anything while stabilizing because it is hurt
    public void stabilize()
    {
        time++;
        
        if(time%8 == 0){
            hurt = false;
        }
    }

    public int getHealth()
    {
        return health;
    }
    
    // Checks for this robot's health
    public void checkDie()
    {
        if(getHealth() <= 0){
            alive = false;
            imageNum = 1;
            time = 0;
        }
    }

    public void deathAnimation()
    {
        time++;
        
        if(facingRight){
            setLocation(getX()-1, getY());
        }
        else if(!facingRight){
            setLocation(getX()+1, getY());
        }
        
        if(time%10 == 0){
            if(imageNum <= 4){
                GreenfootImage img = new GreenfootImage("Robot1Die-" + imageNum + ".png");
                
                if(facingRight){
                    setImage(img);
                }
                else{
                    img.mirrorHorizontally();
                    setImage(img);
                }
                
                imageNum++;
            }
            else{
                die();
            }
        }
    }
    
    // Explosion animation and sound when Robot1 object dies
    public void die()
    {
        Explosion explosion = new Explosion();
        getWorld().addObject(explosion,getX(),getY()-28);
        Greenfoot.playSound("RobotDie.wav");
        Paul p = (((Paul)getWorld().getObjects(Paul.class).get(0)));
        p.addKill();
        getWorld().removeObject(this);
    }
}

