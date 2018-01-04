import greenfoot.*;
import javax.swing.JOptionPane;

/**
 * Game winning screen
 */
public class Victory extends World
{
    public Victory()
    {
        super(800, 600, 1); 
        
        int w = JOptionPane.showConfirmDialog(null, "Congratulations! You helped Paul submit his project and receive a 100!","Professor",JOptionPane.OK_CANCEL_OPTION);
        Greenfoot.playSound("Cheer.wav");
        Greenfoot.stop();
    }
}
