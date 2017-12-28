import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Color;
/**
 * Write a description of class ReturnJavaRoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ReturnJavaRoom extends World
{
    Health h;
    Paul p;
    /**
     * Constructor for objects of class ReturnJavaRoom.
     * 
     */
    public ReturnJavaRoom(Health health, Paul paul)
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        h = health;
        p = paul;
        getBackground().setColor(Color.black);
        getBackground().setFont(new Font("",Font.BOLD,25));
        getBackground().drawString("FINAL EXAM PROJECT",376,245);
        getBackground().drawString("DUE IN 1 MINUTE!!!",385,315);
        getBackground().setColor(Color.black);
        getBackground().setFont(new Font("",Font.BOLD,17));
        getBackground().drawString("Talk to Mrs. Nastasi to give her the USB!",60,80);
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        Floor floor = new Floor();
        addObject(floor, 395, 545);
        BrokenMagicPortal brokenmagicportal2 = new BrokenMagicPortal();
        addObject(brokenmagicportal2, 85, 418);
        Professor professor = new Professor();
        addObject(professor, 391, 469);
 
        addObject(p, 181, 470);
    }
}
