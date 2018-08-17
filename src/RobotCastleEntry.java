import greenfoot.*;
import java.awt.Font;
import java.awt.Color;

/**
 * Entry to the Robot Castle stages where Paul will fight the Robots and retrieve his USB
 * Contains all instructions/keys to control Paul
 */
public class RobotCastleEntry extends World
{
    Health h;
    Paul p;
    
    /**
     * Constructor for objects of class RobotCastleEntry 
     */
    public RobotCastleEntry(Health health, Paul paul)
    {
        super(800, 600, 1);
        h = health;
        p = paul;
        
        getBackground().setColor(Color.white);
        getBackground().setFont(new Font("",Font.BOLD,20));
        getBackground().drawString("ENTER THE CASTLE AND DEFEAT THE ROBOTS!",164,48);
        getBackground().setFont(new Font("",Font.BOLD,12));
        getBackground().drawString("PRESS \"ENTER\" TO TAKE ACTION (TALK, ENTER DOORS, OR PICK UP OBJECTS)",40,115);
        getBackground().setFont(new Font("",Font.BOLD,12));
        getBackground().drawString("PRESS \"Q\" TO SHIELD (ONLY TAKE 1/3 OF THE DAMAGE WHEN USING SHIELD)",40,155);
        getBackground().drawString("PRESS \"W\" TO SHOOT WEBS",40,195);
        getBackground().drawString("PRESS \"E\" TO ATTACK", 40,235);
        getBackground().drawString("PRESS \"SPACEBAR\" TO JUMP",40,275);
        prepare();
    }
    
    /**
     * Create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Ground ground = new Ground();
        addObject(ground, 500, 642);
        BrokenMagicPortal brokenmagicportal = new BrokenMagicPortal();
        addObject(brokenmagicportal, 78,498);
        Castle castle = new Castle();
        addObject(castle, 649,390);
        CastleDoor castledoor = new CastleDoor();
        addObject(castledoor, 654,526);
        
        addObject(p, 181,542);
    }
}
