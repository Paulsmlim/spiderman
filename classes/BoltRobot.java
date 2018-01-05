import greenfoot.*;

/**
 * Indestructible Robot that is in every stage of the Robot Castle
 * Shoots bolts that take away 50 health points from Paul if it hits Paul
 */
public class BoltRobot extends Enemy
{
    // Coordinates of Paul used to aim bolts
    private int PaulX;
    private int PaulY;
    private int time = 0;
    
    public void act() 
    {
        turnToPaul();
        shoot();
    }
    
    public void turnToPaul()
    {
        PaulX = ((Paul)getWorld().getObjects(Paul.class).get(0)).getX();
        PaulY = ((Paul)getWorld().getObjects(Paul.class).get(0)).getY();
        turnTowards(PaulX, PaulY);
    }

    public void shoot()
    {
        time++;
        
        // BoltRobot object shoots every 200th time this function is called
        if(time%200 == 0){
            Greenfoot.playSound("BoltRobotShoot.wav");
            BoltRobotBolt boltrobotbolt = new BoltRobotBolt();
            getWorld().addObject(boltrobotbolt, getX(), getY());
            boltrobotbolt.setRotation(getRotation());
            boltrobotbolt.move(5);
        }
    }
}
