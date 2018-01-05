import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bolt of the object class Robot3. Each bolt that hits Paul takes
 * away 40 health points from Paul
 */
public class Robot3Bolt extends Actor
{
    // Time counter for object image changes
    private int time = 0;
    private int imageNum = 1;
    // Direction of the Robot3Bolt object
    public boolean right = true;
    // True when Robot3Bolt object is initially created
    private boolean initialized = true;
    // True if Robot3Bolt object hits Paul or world edge
    private boolean splat = false;

    public void act() 
    {
        // After Robot3Bolt object is initialized, begin its initial animation
        if(initialized){
            initAnimation();
        }
        else {
            //If this bolt did not splat, it still moves
            if(!splat){
                checkKeys();
            } else {
                checkSplat();
            }
        }
    }

    public void initAnimation()
    {
        time++;
        
        if(time%8 == 0){
            GreenfootImage img = new GreenfootImage("Robot3Bolt-" + imageNum + ".png");
            
            if(right){
                setImage(img);
            }
            else {
                // If the object is going in the left direction, mirror it so image reflects direction
                img.mirrorHorizontally();
                setImage(img);
            }
            
            imageNum++;
        }
        
        // If true, initial animation is complete
        if(imageNum == 4){
            initialized = false;
            imageNum = 1;
        }
    }

    public void checkKeys()
    {
        if(right){
            move(3);
        }
        else {
            move(-3);
        }
    }
    
    // Identical to checkSplat() in BoltRobotBolt
    public void checkSplat()
    {
        /* After Robot3Bolt hits Paul, is at the world edge, or splats, start/continue
           the splat animation */
        if(isTouching(Paul.class) || atWorldEdge() || splat){
            /* True only on first call of when Robot3Bolt hit Paul or when it is at
               world edge (start splat animation) */
            if(!splat){
                splat = true;
                // True only if the bolt hit Paul, then does damage to Paul
                if(isTouching(Paul.class)){
                    doDamage(40);
                }
                imageNum = 1;
            }
            
            time++;
            
            if(time%8 == 0){
                // BoltRobotBolt is removed after splat animation is complete
                if(imageNum ==  3){
                    getWorld().removeObject(this);
                }
                else{
                    setImage("Robot3BoltSplat-" + imageNum + ".png");
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

    // Returns true if a Robot3Bolt object is at the edge of the world
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
