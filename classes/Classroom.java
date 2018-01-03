import greenfoot.*;
import java.awt.Font;
import java.awt.Color;

/**
 * Initial world that explains the objective of the game
 */
public class Classroom extends World
{
    /**
     * Constructor for objects of class Classroom
     */
    public Classroom()
    {    
        // Create 800 x 600 World
        super(800, 600, 1); 
        prepare();
        
        getBackground().setColor(Color.black);
        getBackground().setFont(new Font("",Font.PLAIN,12));
        getBackground().drawString("TODAY IS THE DUE DATE FOR PAUL TO SUBMIT HIS CODE FOR HIS COMPUTER SCIENCE COURSE, BUT WHEN HE ARRIVED AT",45,40);
        getBackground().drawString("HIS PROFESSOR'S OFFICE, HE REALIZED THAT ROBOTS FROM THE ROBOT CASTLE HAD STOLEN HIS USB WHICH CONTAINS HIS CODE.",20,70);
        getBackground().setFont(new Font("",Font.BOLD,13));
        getBackground().drawString("PRESS \"ENTER\" IN FRONT OF THE MAGIC PORTAL TO TELEPORT TO THE ENTRACE OF THE ROBOT CASTLE!",48,110);
        getBackground().setColor(Color.black);
        getBackground().setFont(new Font("",Font.BOLD,25));
        getBackground().drawString("Project Due Today!",395,250);
        getBackground().setColor(Color.black);
        getBackground().setFont(new Font("",Font.PLAIN,10));
        getBackground().drawString("(Press \"Enter\" in front of the professor to talk to her)",385,340);
        getBackground().setFont(new Font("",Font.PLAIN,11));
        getBackground().drawString("(Use the arrow keys to move)",28,400);
    }

    /**
     * Create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Floor floor = new Floor();
        addObject(floor, 395, 545);
        MagicPortal magicportal2 = new MagicPortal();
        addObject(magicportal2, 722, 449);
        Professor professor = new Professor();
        addObject(professor, 391, 469);

        Health h = new Health("Health", 13000);
        Paul paul = new Paul(h);
        addObject(paul, 97, 470);
    }
}
