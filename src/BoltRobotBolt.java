import greenfoot.*;

/**
 * Bolt of the object class BoltRobot. Each bolt that hits Paul takes
 * away 50 health points from Paul
 */
public class BoltRobotBolt extends Actor
{
    // Time counter for object image changes
    private int time = 0;
    private int imageNum = 1;
    // True if Bolt hit Paul or world edge
    private boolean splat = false;

    public void act()
    {
        // If BoltRobotBolt didn't hit anything, keep moving; else, start/continue splat animation
        if(!splat){
            checkKeys();
        } else {
            checkSplat();
        }
    }    

    public void checkKeys()
    {
        move(3);
        time++;
        // Image of BoltRobotBolt changes for every 6th call of this function
        if(time%6 == 0){
            setImage("BoltRobotBolt-" + imageNum + ".png");
            // Image animation of BoltRobotBolt resets after 14 images
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
        /* After BoltRobotBolt hits Paul, is at the world edge, or splats, start/continue
           the splat animation */
        if(isTouching(Paul.class) || atWorldEdge() || splat){
            /* True only on first call of when BoltRobotBolt hit Paul or when it is at
               world edge (start splat animation) */
            if(!splat){
                splat = true;
                // True only if the bolt hit Paul, then does damage to Paul
                if(isTouching(Paul.class)){
                    doDamage(50);
                }
                imageNum = 1;
            }
            
            time++;
            
            if(time%4 == 0){
                // BoltRobotBolt is removed after splat animation is complete
                if(imageNum == 5){
                    getWorld().removeObject(this);
                }
                else
                {
                    setImage("BoltRobotBoltSplat-" + imageNum + ".png");
                    imageNum++;
                }
            }
        }
    }

    public void doDamage(int damage)
    {
        Paul p = ((Paul)getOneIntersectingObject(Paul.class));
        //Does damage to Paul
        p.takeDamage(damage);
    }
    
    // Returns true if a BoltRobotBolt object is at the edge of the world
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
