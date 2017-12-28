import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MagicPortal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagicPortal extends Actor
{
    //Magic portal that takes Paul to the Robot's castles and then takes him back to the JavaRoom
    GifImage gifImage = new GifImage("MagicPortal.gif");
    /**
     * Act - do whatever the MagicPortal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setImage(gifImage.getCurrentImage());
    }
}