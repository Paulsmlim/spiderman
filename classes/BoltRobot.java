import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LaserRobot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoltRobot extends Enemy
{
    //This robot is undefeatable. It is part of the world and Paul cannot destroy it
    private int PaulX;
    private int PaulY;
    private int time = 0;
    /**
     * Act - do whatever the LaserRobot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        turnToPaul();
        shoot();
    }    
    //This robot turns to Paul so that it can shoot at him
    public void turnToPaul()
    {
        PaulX = ((Paul)getWorld().getObjects(Paul.class).get(0)).getX();
        PaulY = ((Paul)getWorld().getObjects(Paul.class).get(0)).getY();
        turnTowards(PaulX, PaulY);
    }

    /*
     * Code adopted from program shoot
     */
    public void shoot()
    {
        time++;
        if(time%200 == 0){
            Greenfoot.playSound("BoltRobotShoot.wav");
            BoltRobotBolt boltrobotbolt = new BoltRobotBolt();
            getWorld().addObject(boltrobotbolt, getX(), getY());
            boltrobotbolt.setRotation(getRotation());
            boltrobotbolt.move(5);
        }
    }
}
