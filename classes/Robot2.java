import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Robot2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robot2 extends Enemy
{
    private int health;
    private int speed = 2;
    private int imageNum = 1;
    private int time = 0;
    private int vSpeed = 1;
    private int acceleration = 1;
    //True if Robot2 is alive
    private boolean alive = true;
    //True whenever Robot2 gets hit by Paul
    private boolean hurt = false;
    private boolean facingRight;
    private boolean inAir = false;
    //Y and X value of Paul
    private int PaulX;
    private int PaulY;
    //There is 1 out of 7 chances that Robot2 will attack Paul when it is standing next to Paul
    private int attackChance = Greenfoot.getRandomNumber(7)+1;
    //True whenever this robot is attacking Paul
    private boolean attacking = false;
    //True whenever this robot is falling
    private boolean falling = false;
    
    public Robot2(boolean fR)
    {
        health = 250;
        facingRight = fR;
    }

    /**
     * Act - do whatever the Robot2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
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
        if(!alive){
            dieImage();
        }
    }    

    public void checkKeys()
    {
        //Gets Paul's X and Y values
        PaulX = ((Paul)getWorld().getObjects(Paul.class).get(0)).getX();
        PaulY = ((Paul)getWorld().getObjects(Paul.class).get(0)).getY();
        if(((getY()+30 - PaulY <= 130) && (getY()+30 - PaulY >= 0)) || (isTouching(Paul.class))){
            if(this.getX()+30 < PaulX){
                moveRight();
            }
            else if(this.getX()-30 > PaulX){
                moveLeft();
            }
            else if(attackChance == 1){
                attack();
            }
            else{
                standing();
                attackChance = Greenfoot.getRandomNumber(18)+1;
            }
        }
        else{
            standing();
        }
    }

    public void standing()
    {
        attacking = false;
        imageNum = 1;
        GreenfootImage img = new GreenfootImage("Robot2Standing.png");
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
        if(attacking){
            imageNum = 1;
        }
        attacking = false;
        if(!facingRight){
            imageNum = 1;
        }
        facingRight = true;
        time++;
        if(time%10==0){
            setImage("Robot2Walking-" + imageNum + ".png");
            if(imageNum == 4){
                imageNum = 1;
            }
            else{
                imageNum++;
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
        if(time%10==0){
            GreenfootImage img = new GreenfootImage("Robot2Walking-" + imageNum + ".png");
            img.mirrorHorizontally();
            setImage(img);
            if(imageNum == 4){
                imageNum = 1;
            }
            else{
                imageNum++;
            }
        }
        setLocation(getX()-speed, getY());
    }

    public void fall()
    {
        imageNum = 1;
        attacking = false;
        GreenfootImage img = new GreenfootImage("Robot2inAir.png");
        if(facingRight){
            setImage(img);
        }
        else{
            img.mirrorHorizontally();
            setImage(img);
        }
        setLocation(getX(), getY()+vSpeed);
        vSpeed = vSpeed + acceleration;
    }

    public void checkFall()
    {
        //If Robot2 is touching the ground or a platform, it will not fall, but if not, it will.
        if(isTouching(StandingObject.class)){
            vSpeed = 0;
            if(falling){
                setLocation(getX(), getY()+2);
                falling = false;
            }
        }
        else{
            falling = true;
            fall();
        }
    }

    public void attack()
    {
        if(!attacking){
            imageNum = 1;
        }
        attacking = true;
        time++;
        if(time%20==0)
        {  
            //If the animation is complete, a new number for the attack is generated and imageNum resets
            if(imageNum > 4){
                attackChance = Greenfoot.getRandomNumber(18)+1;
                imageNum = 1;
            }
            else if (attackChance != 1){
                attackChance = Greenfoot.getRandomNumber(18)+1;
            }
            else{
                GreenfootImage img = new GreenfootImage("Robot2Attacking-" + imageNum + ".png");
                if(facingRight){
                    setImage(img);
                }
                else{
                    img.mirrorHorizontally();
                    setImage(img);
                }
                if(imageNum == 2){
                    doDamage(15);
                }
                else if(imageNum == 4){
                    doDamage(25);
                }
                //Only increases num when the image animation is not complete
                if(imageNum <= 4){
                    imageNum++;
                }
            }
        }
    }

    public void doDamage(int damage)
    {
        Greenfoot.playSound("UpperCut.wav");
        Paul p = ((Paul)getWorld().getObjects(Paul.class).get(0));
        p.takeDamage(damage);
    }

    public void takeDamage(int damage){
        health -= damage;
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
        GreenfootImage img = new GreenfootImage("Robot2Hit.png");
        if(facingRight){
            setImage(img);
        }
        else{
            img.mirrorHorizontally();
            setImage(img);
        }
    }

    public void stabilize()
    {
        time++;
        if(time%14 == 0){
            hurt = false;
        }
    }

    public int getHealth()
    {
        return health;
    }

    public void checkDie()
    {
        //If the value of this robot's health is 0, it dies
        if(getHealth() <= 0){
            alive = false;
            imageNum = 1;
            time = 0;
        }
    }

    public void dieImage()
    {
        time++;
        if(facingRight){
            setLocation(getX()-1, getY());
        }
        else if(!facingRight){
            setLocation(getX()+1, getY());
        }
        if(time%10==0){
            if(imageNum < 5){
                GreenfootImage img = new GreenfootImage("Robot2Die-" + imageNum + ".png");
                if(facingRight){
                    setImage(img);
                }
                else{
                    img.mirrorHorizontally();
                    setImage(img);
                }
                imageNum++;
            }
            else if(time >= 40){
                die();
            }
        }
    }

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
