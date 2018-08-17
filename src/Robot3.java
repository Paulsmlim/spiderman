import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Robot3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robot3 extends Enemy
{
    private int health;
    private int speed = 2;
    private int imageNum = 1;
    private int time = 0;
    private int vSpeed = 1;
    private int acceleration = 1;
    //Number of times Robot3 was hit by Paul
    private int timesHit = 0;
    private boolean alive = true;
    //True if Robot3 is currently shooting a bolt
    private boolean shooting = false;
    //True if Robot3 is at the right side of Paul
    private boolean rightOfPaul = true;
    //True if Robot3 can escape
    private boolean escape = false;
    private boolean hurt = false;
    private boolean facingRight;
    private boolean inAir = false;
    private int PaulX;
    private int PaulY;
    //There is 1 out of 120 chances that Robot3 will attack Paul while standing more than 300 pixels away, but the first attack is 100%
    private int attackChance = 1;
    //There is 1 out of 280 chance that Robot3 will turn around and attack Paul while it is walking
    private int turnAttack = Greenfoot.getRandomNumber(280)+1;

    public Robot3(boolean fR)
    {
        health = 250;
        facingRight = fR;
    }

    /**
     * Act - do whatever the Robot3 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(alive){
            if(!hurt){
                checkKeys();
                checkFall();
                checkDie();
            }
            else{
                stabilize();
            }
        }
        else{
            dieImage();
        }
    }

    public void checkKeys()
    {
        PaulX = ((Paul)getWorld().getObjects(Paul.class).get(0)).getX();
        PaulY = ((Paul)getWorld().getObjects(Paul.class).get(0)).getY();
        if(PaulX < getX()){
            rightOfPaul = true;
        }
        else if(PaulX > getX()){
            rightOfPaul = false;
        }
        //If Robot3's Y value and Paul's Y value is at max 125 pixels apart
        if(((getY()+30 - PaulY <= 130) && (getY()+30 - PaulY >= 0)) || (isTouching(Paul.class))){
            //If Robot3 is at least 300 pixels away from Paul OR atWorldEdge while not touching Paul, he tries to shoot
            if((Math.abs(getX() - PaulX) >= 300) || (atWorldEdge() && !isTouching(Paul.class))){
                if(attackChance%20 == 0){
                    attack();
                }
                else{
                    attackChance = Greenfoot.getRandomNumber(120)+1;
                }
            }
            else if(!shooting){
                //If Robot3 is touching Paul, then it will go to the other side after getting hit 2 times
                if(isTouching(Paul.class)){
                    //If Robot3 is at the right side of the world, then it will escape 170 pixels to the left
                    if(getX() > 500){
                        if(escape){
                            //If Robot3 has the ability to escape, then it will escape 170 pixels away from Paul
                            do{
                                moveLeft();
                            }while(Math.abs(getX() - PaulX ) < 200);
                            //After Robot3 escape from Paul, it has to get hit at least 2 times before it can escape again
                            escape = false;
                        }
                        //If escape is false, then it will just try to attack
                        else{
                            attack();
                        }
                    }
                    else if(getX() < 300){
                        //If Robot3 is at the left side of the world, then it will escape 170 pixels to the right
                        if(escape){
                            do{
                                moveRight();
                            }while(Math.abs(getX() - PaulX ) < 200);
                            escape = false;
                        }
                        else{
                            attack();
                        }
                    }
                    //If the Robot is in the middle of world, then it will teleport to the left or right depending on Paul's posiiton
                    else{
                        if(rightOfPaul){
                            if(escape){
                                do{
                                    moveLeft();
                                }while(Math.abs(getX() - PaulX ) < 200);
                                //After Robot3 escape from Paul, it has to get hit at least 2 times before it can escape again
                                escape = false;
                            }
                            //If escape is false, then it will just try to attack
                            else{
                                if(attackChance == 1){
                                    attack();
                                }
                                else{
                                    attackChance = Greenfoot.getRandomNumber(40)+1;
                                    turnAttack = Greenfoot.getRandomNumber(280)+1;
                                }
                            }
                        }
                        else{
                            if(escape){
                                do{
                                    moveRight();
                                }while(Math.abs(getX() - PaulX ) < 200);
                                escape = false;
                            }
                            else{
                                if(attackChance == 1){
                                    attack();
                                }
                                else{
                                    attackChance = Greenfoot.getRandomNumber(40)+1;
                                    turnAttack = Greenfoot.getRandomNumber(280)+1;
                                }
                            }
                        }
                    }
                }
                //If Paul less than 300 pixels away from Robot 3 and is coming in Robot3's direction, Robot3 gets away from Paul
                //However, Robot3 won't run forever, so it will shoot at Paul once a while
                else if(rightOfPaul){
                    if(turnAttack == 1){
                        attack();
                    }
                    else{
                        moveRight();
                        turnAttack = Greenfoot.getRandomNumber(280)+1;
                    }
                }
                else if(!rightOfPaul){
                    if(turnAttack == 1){
                        attack();
                    }
                    else{
                        moveLeft();
                        turnAttack = Greenfoot.getRandomNumber(280)+1;
                    }
                }
            }
            else{
                attack();
            }
        }
        else{
            standing();
        }
    }

    public void standing()
    {
        imageNum = 1;
        shooting = false;
        GreenfootImage img = new GreenfootImage("Robot3Standing.png");
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
        if(shooting){
            imageNum = 1;
        }
        shooting = false;
        if(!facingRight){
            imageNum = 1;
        }
        facingRight = true;
        time++;
        if(time%10==0){
            setImage("Robot3Walking-" + imageNum + ".png");
            if(imageNum >= 4){
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
        if(shooting){
            imageNum = 1;
        }
        shooting = false;
        if(facingRight){
            imageNum = 1;
        }
        facingRight = false;
        time++;
        if(time%10==0){
            GreenfootImage img = new GreenfootImage("Robot3Walking-" + imageNum + ".png");
            img.mirrorHorizontally();
            setImage(img);
            if(imageNum >= 4){
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
        shooting = false;
        GreenfootImage img = new GreenfootImage("Robot3inAir.png");
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
        //If Robot2 is touching the ground or a platform, it will not fall, or else it will.
        if(isTouching(StandingObject.class)){
            vSpeed = 0;
        }
        else{
            fall();
        }
    }

    public void attack()
    {
        //Since this robot turns around to shoot, if he was previously walking, then the direction is reversed.
        if(!shooting){
            imageNum = 1;
        }
        shooting = true;
        time++;
        if(time%20 == 0)
        {  
            //If the animation is complete, a new number for the attack is generated and imageNum resets
            if(imageNum > 5){
                imageNum = 1;
                attackChance = Greenfoot.getRandomNumber(120)+1;
                turnAttack = Greenfoot.getRandomNumber(280)+1;
                shooting = false;
            }
            else{
                GreenfootImage img = new GreenfootImage("Robot3Attacking-" + imageNum + ".png");
                if(rightOfPaul){
                    setImage(img);
                    img.mirrorHorizontally();
                    facingRight = false;
                }
                else{
                    setImage(img);
                    facingRight = true;
                }

                if(imageNum == 3 || imageNum == 5){
                    Robot3Bolt robot3bolt = new Robot3Bolt();
                    Greenfoot.playSound("Robot3Bolt.wav");
                    if(facingRight){
                        robot3bolt.right = true;
                        getWorld().addObject(robot3bolt,getX()+20,getY()-12);
                    }
                    else{
                        robot3bolt.right = false;
                        getWorld().addObject(robot3bolt,getX()-20,getY()-12);
                    }
                }
                if(imageNum <= 5){
                    imageNum++;
                }            
            }
        }
    }

    public void takeDamage(int damage){
        health -= damage;
        //if the Robot3 getes hit from the spider web, there is only 1/5 chance that it will be "hurt"
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
        shooting = false;
        hurt = true;
        timesHit++;
        //Gives Robot3 the ability to escape from Paul if he has been hit 2 times
        if(timesHit == 2){
            escape = true;
            timesHit = 0;
        }

        GreenfootImage img = new GreenfootImage("Robot3Hit.png");
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
        if(time%10 == 0){
            hurt = false;
            imageNum = 1;
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
            setLocation(getX()+1, getY());
        }
        else if(!facingRight){
            setLocation(getX()-1, getY());
        }
        if(time%10==0){
            if(imageNum < 4){
                GreenfootImage img = new GreenfootImage("Robot3Die-" + imageNum + ".png");
                if(facingRight){
                    img.mirrorHorizontally();
                    setImage(img);
                }
                else{
                    setImage(img);
                }
                imageNum++;
            }
            else if(time >= 30){
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

    public boolean atWorldEdge()
    {
        if (getX() <= 5 || getX() >= getWorld().getWidth()-5)
            return true;
        else if (getY() <= 5 || getY() >= getWorld().getHeight()-5) 
            return true;
        else
            return false;
    }
}
