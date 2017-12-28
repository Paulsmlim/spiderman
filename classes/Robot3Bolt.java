import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Robot3Bolt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Robot3Bolt extends Actor
{
    public boolean right = true;
    //True if Robot3Bolt was recently called
    private boolean changeImage = true;
    private boolean splat = false;
    private int imageNum = 1;
    private int time = 0;

    /**
     * Act - do whatever the Robot3Bolt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //After this bolt is summoned, it has to change its image. Then it starts moving
        if(changeImage){
            changeImage();
        }
        else{
            //If this bolt did not splat, it still moves
            if(!splat){
                checkKeys();
            }
            checkSplat();
        }
    }

    public void changeImage()
    {
        time++;
        if(time%8 == 0){
            GreenfootImage img = new GreenfootImage("Robot3Bolt-" + imageNum + ".png");
            if(right){
                setImage(img);
            }
            else{
                img.mirrorHorizontally();
                setImage(img);
            }
            imageNum++;
        }
        
        if(imageNum == 4){
            changeImage = false;
            imageNum = 1;
        }
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

    public void checkSplat()
    {
        if(isTouching(Paul.class) || atWorldEdge() || splat){
            //If the bolt just hit Paul, then splat = true and imageNum resets to 1
            if(!splat){
                splat = true;
                //If it splat while touching Paul, then it does damage to Paul
                if(isTouching(Paul.class)){
                    doDamage(40);
                }
                imageNum = 1;
            }
            time++;
            if(time%8 == 0){
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
