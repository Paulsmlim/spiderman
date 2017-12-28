import greenfoot.*;
import javax.swing.JOptionPane;
/**
 * Write a description of class Paul here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paul extends Health
{
    //Sets up a counter for the number of kills that Paul has
    private Health h;
    //The amount of robots that Paul destroyed
    private int killCount = 0;
    //Speed at which Paul moves horizontally
    private int speed = 2;
    //Speed at which Paul moves vertically
    private int vSpeed = 0;
    //Strength of Paul's legs
    private int jumpStrength = 4;
    //How long Paul was in Air
    private int airTime = 0;
    //True whenever Paul jumped
    private boolean jumped = false;
    //Speed at which Paul falls
    private int acceleration = 1;
    //Number to change image of Paul
    private int imageNum = 1;
    //True whenever Paul is facing the right direction
    private boolean facingRight = true;
    //True whenever Paul is in air
    private boolean inAir = false;
    //True whenever Paul is using his shield
    private int yBeforeJump;
    //The y-value of Paul before he jumps
    private boolean isShielding = false;
    //True whenever Paul is walking
    private boolean isWalking = false;
    //True whenever Paul is shooting webs
    private boolean shooting = false;
    //True whenever Paul is attacking
    private boolean attacking = false;
    //Random number to determine what attack Paul will do when the "e" key is pressed
    private int random = Greenfoot.getRandomNumber(3)+1;
    //Time incrementation to delay image change
    private int time = 0;
    //True if Paul has the USB
    private boolean hasUSB = false;
    //True whenever Paul used the downPlatform() function
    private boolean downPlatform = false;
    Platform p = null;
    //Key that the user presses
    String key;
    private int castleLevel = 0;

    public Paul(Health health)
    {
        h = health;
    }

    /**
     * Act - do whatever the Paul wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkKeys();
        checkFall();
        nextLevel();
        pickUpUSB();
        checkGameWin();
        checkDie();
    } 

    public void checkKeys()
    {
        //This set of if statements makes sure that Paul can't move while using any skills
        //Makes Paul use his shield whenever the "q" key is pressed
        if(Greenfoot.isKeyDown("q")){
            if(!inAir){
                shield();
            }
        }
        else{
            isShielding = false;
            if(Greenfoot.isKeyDown("right")){
                moveRight();
            }
            //Makes Paul move in the left direction whenever the "left" key is pressed
            else if(Greenfoot.isKeyDown("left")){
                moveLeft();
            }
            else{
                if(isWalking){
                    isWalking = false;
                }
                //Makes Paul shoot his web bolt whenever the "w" key is pressed
                if(Greenfoot.isKeyDown("w")){
                    if(!inAir){
                        shootWeb();
                    }
                }
                //Makes Paul attacks whenever the "e" key is pressed
                else if(Greenfoot.isKeyDown("e")){
                    if(!inAir){
                        attack();
                    }
                }
                //If none of the keys are pressed, Paul remains standing idle
                else{
                    standing();
                }
            }
        }
        //Makes Paul jump whenever the "space" key is pressed
        if(Greenfoot.isKeyDown("space")){
            if(!inAir){
                jump();
            }
        }
        //Makes Paul go down a platform whenever the "down" key is pressed
        if(Greenfoot.isKeyDown("down")){
            downPlatform();
        } 
    }

    public void standing()
    {
        imageNum = 1;
        shooting = false;
        attacking = false;
        GreenfootImage img = new GreenfootImage("PaulStanding.png");
        if(facingRight){
            setImage(img);
        }
        else{
            //Flips horizontally the image of Paul standing in the right direction
            img.mirrorHorizontally();
            setImage(img);
        }
    }

    public void moveRight()
    {
        isWalking = true;
        shooting = false;
        attacking = false;
        //If Paul was previously facing left, then the image for Paul running to the right resets
        if(!facingRight){
            imageNum = 1;
        }
        facingRight = true;
        //Increments the amount of times this method was called
        time++;
        //For every 5 times I call this method, Paul changes image
        if(time%10==0){
            if(imageNum > 10){
                imageNum = 1;
            }
            //Sets Paul's image as him running in the right direction
            setImage("PaulRunning-" + imageNum + ".png");
            //Changes the image number for Paul
            if(imageNum <= 10){
                imageNum++;
            }
        }
        setLocation(getX()+speed, getY());
    }

    public void moveLeft()
    {
        isWalking = true;
        shooting = false;
        attacking = false;
        //If Paul was previously facing right, then the image for Paul running to the right resets.
        if(facingRight){
            imageNum = 1;
        }
        facingRight = false;
        //Increments the amount of times this method was called
        time++;
        //For every 5 times I call this method, Paul changes image
        if(time%10==0){
            if(imageNum > 10){
                imageNum = 1;
            }
            //Flips horizontally the original image of Paul running in the right direction
            GreenfootImage img = new GreenfootImage("PaulRunning-" + imageNum + ".png");
            img.mirrorHorizontally();
            setImage(img);
            //Changes the image number for Paul
            if(imageNum <= 10){
                imageNum++;
            }
        }
        setLocation(getX()-speed, getY());
    }

    public void downPlatform()
    {
        //if Paul is touching a platform, he is allowed to move down to a lower platform or the ground
        if(isTouching(Platform.class)){
            downPlatform = true;
            jumped = false;
            //p is the platform that Paul was on before he used the downPlatform() function
            p = ((Platform)(getOneIntersectingObject(Platform.class)));
            vSpeed = 1;
            fall();
        }
    }

    /*
     * Code adopted from Pengu
     */
    public void jump()
    {
        jumped = true;
        yBeforeJump = getY();
        GreenfootImage img = new GreenfootImage("PaulinAir.png");
        if(facingRight){
            setImage(img);
        }
        else{
            img.mirrorHorizontally();
            setImage(img);
        }
        vSpeed = -jumpStrength;
        fall();
    }

    /*
     * Code adopted from Pengu
     */
    public void fall()
    {
        imageNum = 1;
        inAir = true;
        shooting = false;
        attacking = false;
        GreenfootImage img = new GreenfootImage("PaulinAir.png");
        if(facingRight){
            setImage(img);
        }
        else{
            img.mirrorHorizontally();
            setImage(img);
        }

        if(downPlatform){
            if((((Platform)(getOneIntersectingObject(Platform.class))) != p) || isTouching(Ground.class)){
                downPlatform = false;
                p = null;
            }
        }

        setLocation(getX(), getY()+vSpeed);
        //If Paul jumped at least 57 pixels above where he was before he jumped, then he will start descending (falling)
        if(jumped){
            if(yBeforeJump - getY() >= 100){
                vSpeed = 0;
                jumped = false;
            }
        }
        else if(!jumped){
            airTime++;
            if(airTime%3 == 0){
                vSpeed = vSpeed + acceleration;
            }
        }
    }

    /*
     * Code adopted from Pengu
     */
    public void checkFall()
    {
        //If Paul is touching the ground or a platform, he will not fall, or else he will.
        if(isTouching(StandingObject.class) && !downPlatform){
            inAir = false;
            jumped = false;
            speed = 2;
            vSpeed = 0;
        }
        else{
            inAir = true;
            speed = 3;
            fall();
        }
    }

    public void shield()
    {
        //If Paul just activated his shield, then imageNum resets.
        if(!isShielding){
            imageNum = 1;
        }
        //Paul is shielding from attacks so he will take reduced damage from enemies
        isShielding = true;
        shooting = false;
        attacking = false;
        //Increments the amount of times this method was called
        time++;
        //For every 12 times I call this method, Paul changes image
        if(time >= 12){
            time = 0;
            //Sets which direction Paul is facing while using shield
            GreenfootImage img = new GreenfootImage("PaulShielding-" + imageNum + ".png");
            if(facingRight){
                setImage(img);
            }
            else{
                img.mirrorHorizontally();
                setImage(img);
            }
            //Changes the number of the image if the animation is still needed. If not, then keeps image number the same.
            if(imageNum <= 3){
                imageNum++;
            }
            if(imageNum == 3){
                Greenfoot.playSound("Shield.wav");
            }
        }
    }

    public void shootWeb()
    {
        attacking = false;
        if(!shooting){
            imageNum = 1;
        }
        shooting = true;
        //Increments the amount of times this method was called
        time++;
        //Changes Paul's image so that it looks like he is shooting his web bolts
        GreenfootImage img = new GreenfootImage("PaulShooting.png");
        if(facingRight){
            setImage(img);
        }
        else if(!facingRight){
            img.mirrorHorizontally();
            setImage(img);
        }
        //The rate of creating webs is slow because if it was fast, then Paul can just continue to shoot without
        //the enemy being able to do anything
        if(time >= 25){
            time = 0;
            Web web = new Web();
            Greenfoot.playSound("WebShoot.wav");
            GreenfootImage img2 = new GreenfootImage("Web.png");
            //Decides where the web comes out and which direction is shoots
            if(facingRight){
                web.right = true;
                web.setImage(img2);
                getWorld().addObject(web,getX()+30,getY()+10);
            }
            else{
                web.right = false;
                img2.mirrorHorizontally();
                web.setImage(img2);
                getWorld().addObject(web,getX()-25,getY()+10);
            }
        }
    }

    public void attack()
    {
        shooting = false;
        if(!attacking){
            time = 0;
            imageNum = 1;
        }
        attacking = true;
        //There are three possible attacks for Paul
        if(random == 1){
            time++;
            if(time >= 15)
            {  
                time = 0;
                //If the animation is complete, a new number for the attack is generated and imageNum resets
                if(imageNum > 5){
                    random = Greenfoot.getRandomNumber(3)+1;
                    imageNum = 1;
                }
                GreenfootImage img = new GreenfootImage("PaulAttacking1-" + imageNum + ".png");
                if(facingRight){
                    setImage(img);
                }
                else{
                    img.mirrorHorizontally();
                    setImage(img);
                }
                //Damages the enemy when the image of him punching is set
                if(imageNum == 4 && isTouching(Enemy.class)){
                    Greenfoot.playSound("Punch.wav");
                    doDamage(10);
                }
                //Only increases num when the image animation is not complete
                if(imageNum <= 5){
                    imageNum++;
                }
            }
        }
        else if(random == 2){
            time++;
            if(time >= 15)
            {  
                time = 0;
                if(imageNum > 6){
                    random = Greenfoot.getRandomNumber(3)+1;
                    imageNum = 1;
                }
                GreenfootImage img = new GreenfootImage("PaulAttacking2-" + imageNum + ".png");
                if(facingRight){
                    setImage(img);
                }
                else{
                    img.mirrorHorizontally();
                    setImage(img);
                }
                if(imageNum == 4 && isTouching(Enemy.class)){
                    Greenfoot.playSound("UpperCut.wav");
                    doDamage(30);
                }
                if(imageNum <= 6){
                    imageNum++;
                }
            }
        }
        else{
            time++;
            if(time >= 15)
            {  
                time = 0;
                if(imageNum > 4){
                    random = Greenfoot.getRandomNumber(3)+1;
                    imageNum = 1;
                }
                GreenfootImage img = new GreenfootImage("PaulAttacking3-" + imageNum + ".png");
                if(facingRight){
                    setImage(img);
                }
                else{
                    img.mirrorHorizontally();
                    setImage(img);
                }
                if(imageNum == 3 && isTouching(Enemy.class)){
                    Greenfoot.playSound("Kick.wav");
                    doDamage(20);
                }
                if(imageNum <= 4){
                    imageNum++;
                }
            }
        }   
    }
    //Parameter is how much damage Paul's attack does
    public void doDamage(int damage)
    {
        if(getOneIntersectingObject(Robot1.class) != null){
            Robot1 a = ((Robot1)getOneIntersectingObject(Robot1.class));
            a.takeDamage(damage);
        }
        else if(getOneIntersectingObject(Robot2.class) != null){
            Robot2 b = ((Robot2)getOneIntersectingObject(Robot2.class));
            b.takeDamage(damage);
        }
        else if(getOneIntersectingObject(Robot3.class) != null){
            Robot3 c = ((Robot3)getOneIntersectingObject(Robot3.class));
            c.takeDamage(damage);
        }
    }

    public void takeDamage(int damage)
    {
        //If Paul is shielding, he only takes 1/3 of the damage
        if(isShielding){
            h.add(-damage/3);
        }
        else{
            h.add(-damage);
        }
    }

    public double getHealth()
    {
        return h.getValue();
    }

    public void checkDie()
    {
        //If Paul's health is 0, he dies
        if(getHealth() <= 0){
            int l = JOptionPane.showConfirmDialog(null, "Gameover! You were defeated by the Robots.","Paul Died",JOptionPane.OK_CANCEL_OPTION);
            Greenfoot.stop();
        }
    }

    public void addKill()
    {
        killCount++;
    }

    public int getKillCount()
    {
        return killCount;
    }

    public void nextLevel()
    {
        //Changes world depending on how many kills Paul has. He can only get a maximum of 4 extra kills per level except the last level.
        if(Greenfoot.isKeyDown("enter")){
            //Health h = (((Health)getWorld().getObjects(Health.class).get(0)));
            if(isTouching(MagicPortal.class)){
                if(getKillCount() == 0){
                    //Moves Paul and his health from this level to the next level
                    Greenfoot.setWorld(new RobotCastleEntry(h, this));
                }
                else if(getKillCount() == 20 && hasUSB){
                    Greenfoot.setWorld(new ReturnJavaRoom(h, this));
                }
            }
            else if(isTouching(CastleDoor.class)){
                if((getKillCount() == 0) && (castleLevel == 0)){
                    Greenfoot.setWorld(new RobotCastle1(h, this));
                    castleLevel++;
                }
                else if((getKillCount() == 4) && (castleLevel == 1)){
                    Greenfoot.setWorld(new RobotCastle2(h, this));
                    castleLevel++;
                }
                else if((getKillCount() == 8) && (castleLevel == 2)){
                    Greenfoot.setWorld(new RobotCastle3(h, this));
                    castleLevel++;
                }
            }
            else if(isTouching(BossDoor.class) && getKillCount() == 12){
                Greenfoot.setWorld(new RobotCastle4(h, this));
            }
        }
    }

    public void pickUpUSB()
    {
        //If Paul is touching the USB and presses enter, he picks up the USB
        if(isTouching(USB.class) && Greenfoot.isKeyDown("enter")){
            if(getKillCount() == 20){
                hasUSB = true;
                removeTouching(USB.class);
            }
            else{
                int n = JOptionPane.showConfirmDialog(null, "Defeat all the Robots before you pick up the USB!", "",JOptionPane.OK_CANCEL_OPTION);
            }
        }
    }

    public void checkGameWin()
    {
        //If Paul has the USB and talks to Mrs.Nastasi, he wins the game and gets a 100 on the project.
        if((hasUSB) && isTouching(Professor.class) && Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new Victory());
        }
        //If Paul does NOT have the USB and clicks on Mrs.Nastasi, she will yell at him to go find it and hand it in.
        else if (isTouching(Professor.class) && Greenfoot.isKeyDown("enter")){
            int n = JOptionPane.showConfirmDialog(null, "Go get your USB!", "Mrs.Nastasi",JOptionPane.OK_CANCEL_OPTION);
        }
    }
}