import greenfoot.*;
import java.awt.Font;
import java.awt.Color;

/**
 * Classroom where Paul returns to after he defeats the Robots and retrieves his USB.
 * He can give the USB to the Professor to win the game
 */
public class ReturnClassroom extends World
{
    Health h;
    Paul p;
    
    /**
     * Constructor for objects of class ReturnClassroom.
     */
    public ReturnClassroom(Health health, Paul paul)
    {
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
     * Create the initial objects and add them to the world.
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
