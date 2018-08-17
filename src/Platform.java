import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends StandingObject
{
    boolean right;
    public Platform(boolean r)
    {
        GreenfootImage img = new GreenfootImage("Platform.png");
        if(!r){
            img.mirrorHorizontally();
            setImage(img);
        }
    }

    /**
     * Act - do whatever the Ground wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
