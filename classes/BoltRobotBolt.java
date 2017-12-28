import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BoltRobotBolt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BoltRobotBolt extends Actor
{
    private int time = 0;
    private int imageNum = 1;
    //True if Bolt touched Paul or world edge
    private boolean splat = false;
    /**
     * Act - do whatever the Bolt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(!splat){
            checkKeys();
        }
        checkSplat();
    }    

    public void checkKeys()
    {
        move(3);
        time++;
        if(time%6 == 0){
            setImage("BoltRobotBolt-" + imageNum + ".png");
            if(imageNum < 14){
                imageNum++;
            }
            else{
                imageNum = 1;
            }
        }
    }

    public void checkSplat()
    {
        //After this bolt touches Paul, is atWorldEdge, or splats, then the animation will start
        if(isTouching(Paul.class) || atWorldEdge() || splat){
            //If the bolt just hit Paul, then splat = true and imageNum resets to 1
            if(!splat){
                splat = true;
                //Does damage to Paul if it hit paul and splat at the same time
                if(isTouching(Paul.class)){
                    doDamage(50);
                }
                imageNum = 1;
            }
            time++;
            if(time%4 == 0){
                //Since there are 5 different images for the bolt splat, after 5 image changes, the bolt will be removed
                if(imageNum == 5){
                    getWorld().removeObject(this);
                }
                else{
                    setImage("BoltRobotBoltSplat-" + imageNum + ".png");
                    imageNum++;
                }
            }
        }
    }

    public void doDamage(int damage){
        Paul p = ((Paul)getOneIntersectingObject(Paul.class));
        //Does damage to Paul
        p.takeDamage(damage);
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
