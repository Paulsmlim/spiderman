import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

/**
 * Write a description of class Victory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Victory extends World
{
    /**
     * Constructor for objects of class Victory.
     * 
     */
    public Victory()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        int w = JOptionPane.showConfirmDialog(null, "Congratulations! You helped Paul submit his project and receive a 100!","Mrs.Nastasi",JOptionPane.OK_CANCEL_OPTION);
        Greenfoot.playSound("Cheer.wav");
        Greenfoot.stop();
    }
}
