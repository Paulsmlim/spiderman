import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Color;

/**
 * Write a description of class JavaRoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JavaRoom extends World
{
    /**
     * Constructor for objects of class JavaRoom.
     * 
     */
    public JavaRoom()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        prepare();
        getBackground().setColor(Color.black);
        getBackground().setFont(new Font("",Font.PLAIN,12));
        getBackground().drawString("TODAY IS THE FINAL DAY FOR PAUL TO SUBMIT HIS FINAL EXAM PROJECT FOR AP JAVA, BUT WHEN HE ARRIVED AT",63,40);
        getBackground().drawString("SCHOOL THIS MORNING, HE REALIZED THAT THE ROBOTS IN THE CASTLE HAD STOLEN HIS USB WHICH CONTAINS HIS PROJECT!",28,70);
        getBackground().setFont(new Font("",Font.BOLD,13));
        getBackground().drawString("PRESS \"ENTER\" IN FRONT OF THE MAGIC PORTAL TO TELEPORT TO THE ENTRACE OF THE CASTLE!",74,110);
        getBackground().setColor(Color.black);
        getBackground().setFont(new Font("",Font.BOLD,25));
        getBackground().drawString("FINAL EXAM PROJECT",376,245);
        getBackground().drawString("DUE IN 5 MINUTES!!!",385,300);
        getBackground().setColor(Color.black);
        getBackground().setFont(new Font("",Font.PLAIN,10));
        getBackground().drawString("(Press \"Enter\" in front of Mrs.Nastasi to talk to her)",391,340);
        getBackground().setFont(new Font("",Font.PLAIN,11));
        getBackground().drawString("(Use the arrow keys to move)",28,400);
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        Floor floor = new Floor();
        addObject(floor, 395, 545);
        MagicPortal magicportal2 = new MagicPortal();
        addObject(magicportal2, 722, 449);
        MrsNastasi mrsnastasi = new MrsNastasi();
        addObject(mrsnastasi, 391, 469);

        Health h = new Health("Health", 13000);
        Paul paul = new Paul(h);
        addObject(paul, 97, 470);
    }
}
