import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    private int time = 0;
    private int imageNum = 1;
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        changeImage();
        checkRemove();
    }    
    
    public void changeImage()
    {
        if(time%4 == 0){
            setImage("Explosion-" + imageNum + ".png");
            imageNum++;
        }
        time++;
    }
        
    public void checkRemove()
    {
        if(imageNum == 23){
            getWorld().removeObject(this);
        }
    }
}
