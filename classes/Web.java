import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Web here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Web extends Actor
{
    //True if the Paul was facing the right direction when he shot this web
    public boolean right = true;
    //True if the web hit something, false if not
    private boolean splat = false;
    private int imageNum = 1;
    private int time = 0;
    /**
     * Act - do whatever the Web wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //If this web did not splat yet, then it keeps moving. If it did hit something, then it stops moving
        //and it removed
        if(!splat){
            checkKeys();
        }
        checkSplat();
    }

    public void checkKeys()
    {
        if(right){
            move(3);
        }
        else{
            move(-3);
        }
    }
    //Checks whether the web should splat. Yes if touching an enemy or atWorldEdge, no if not
    public void checkSplat()
    {
        if(isTouching(Enemy.class) || atWorldEdge() || splat){
            time++;
            if(!splat){
                splat = true;
                //If this web splat while hitting an enemy class, then it does damage
                if(isTouching(Enemy.class)){
                    doDamage(5);
                }
                imageNum = 1;
            }
            if(time%4 == 0){
                //Since there are 3 different images for the web splat, after 3 image changes, the web will be removed
                if(imageNum == 4){
                    getWorld().removeObject(this);
                }
                else{
                    setImage("WebSplat-" + imageNum + ".png");
                    imageNum++;
                }
            }
        }
    }
    //Parameter for how much damage the web does
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
