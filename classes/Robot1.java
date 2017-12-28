import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Robot1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robot1 extends Enemy
{
    //The amount of health this robot has
    private int health;
    private int speed = 1;
    private int imageNum = 1;
    private int time = 0;
    private int vSpeed = 1;
    private int acceleration = 1;
    //True if Robot1 is alive
    private boolean alive = true;
    //True whenever Robot1 gets hit by Paul
    private boolean hurt = false;
    private boolean facingRight;
    private boolean inAir = false;
    //Y and X value of Paul
    private int PaulX;
    private int PaulY;
    //There is 1 out of 10 chances that Robot1 will attack Paul when it is standing next to Paul
    private int attackChance = Greenfoot.getRandomNumber(10)+1;
    //True whenever Paul is attacking
    private boolean attacking = false;
    public Robot1(boolean fR)
    {
        health = 160;
        facingRight = fR;
    }

    /**
     * Act - do whatever the Robot1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //If Robot1 is not hurt, it can move or attack, but if it is hurt, it has to stabilize itself
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
        //If it dies, then the animation for dying starts
        if(!alive){
            dieImage();
        }
    }    

    public void checkKeys()
    {
        //Gets Paul's X and Y values
        PaulX = ((Paul)getWorld().getObjects(Paul.class).get(0)).getX();
        PaulY = ((Paul)getWorld().getObjects(Paul.class).get(0)).getY();
        //If Paul is 40 pixels higher or lower in the Y value, then this robot will move towards Paul
        if(((getY()+30 - PaulY <= 130) && (getY()+30 - PaulY >= 0)) || (isTouching(Paul.class))){
            //If this robot's X value + 30 is less than Paul's, then it will move towards Paul
            if(this.getX()+30 < PaulX){
                moveRight();
            }
            else if(this.getX()-30 > PaulX){
                moveLeft();
            }
            //If this robot is next to Paul, and if attackChance = 1, then it attacks Paul
            else if(attackChance == 1){
                attack();
            }
            //If it is not time for the robot to attack yet, it will just stand
            else{
                standing();
                attackChance = Greenfoot.getRandomNumber(20)+1;
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
            setImage("Robot1Walking-" + imageNum + ".png");
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
            GreenfootImage img = new GreenfootImage("Robot1Walking-" + imageNum + ".png");
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
        GreenfootImage img = new GreenfootImage("Robot1inAir.png");
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
        //If Robot1 is touching the ground or a platform, he will not fall, or else he will.
        if(isTouching(StandingObject.class)){
            vSpeed = 0;
        }
        else{
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
            //If the animation is complete, a new number for the attack is generated and imageNum
            if(imageNum > 4){
                attackChance = Greenfoot.getRandomNumber(20)+1;
                imageNum = 1;
            }
            else if(attackChance != 1){
                attackChance = Greenfoot.getRandomNumber(10)+1;
            }
            else{
                GreenfootImage img = new GreenfootImage("Robot1Attacking-" + imageNum + ".png");
                if(facingRight){
                    setImage(img);
                }
                else{
                    img.mirrorHorizontally();
                    setImage(img);
                }
                //If the robot's image is of him punching/kicking, it does damage
                if(imageNum == 2 || imageNum == 4){
                    doDamage(10);
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
        Greenfoot.playSound("RobotPunch.wav");
        Paul p = ((Paul)getWorld().getObjects(Paul.class).get(0));
        p.takeDamage(damage);
    }

    public void takeDamage(int damage){
        health -= damage;
        //If this robot takes damage, it gets hurt
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
    //This robot can't do anything while stabilizing because it is hurt
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
    //Checks for this robot's health
    public void checkDie()
    {
        //If the value of this robot's health is 0, he dies
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
            else if(time >= 40){
                die();
            }
        }
    }
    //Creates explosion animation and sound when this robot dies
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

