import greenfoot.*;
import java.awt.Font;
import java.awt.Color;

/**
 * Second stage of Robot Castle which only has Robot2's that are slightly stronger than Robot1's
 */
public class RobotCastle2 extends World
{
    Health h;
    Paul p;
    
    /** 
     * Constructor for objects of class RobotCastle2
     */
    public RobotCastle2(Health health, Paul paul)
    {
        super(800, 600, 1);
        h = health;
        p = paul;
        
        getBackground().setColor(Color.white);
        getBackground().setFont(new Font("",Font.BOLD,13));
        getBackground().drawString("DEFEAT ALL THE ROBOTS AND THEN ENTER THE CASTLE DOOR TO GO TO THE NEXT LEVEL",140,55);
        prepare();
    }
    
    /**
     * Create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Ground ground = new Ground();
        addObject(ground,436,596);
        Platform platform = new Platform(false);
        addObject(platform,730,480);
        Platform platform2 = new Platform(true);
        addObject(platform2,310,385);
        Platform platform3 = new Platform(false);
        addObject(platform3,26,385);
        Platform platform4 = new Platform(true);
        addObject(platform4,42,285);
        Platform platform5 = new Platform(false);
        addObject(platform5,462,191);
        Platform platform6 = new Platform(true);
        addObject(platform6,710,191);

        BoltRobot boltrobot = new BoltRobot();
        addObject(boltrobot, 61, 61);
        CastleDoor castledoor = new CastleDoor();
        addObject(castledoor,710,132);
        
        Robot2 robot2 = new Robot2(false);
        addObject(robot2,497,542);
        Robot2 robot22 = new Robot2(true);
        addObject(robot22,255,349);
        Robot2 robot23 = new Robot2(true);
        addObject(robot23,30,250);
        Robot2 robot24 = new Robot2(false);
        addObject(robot24,603,156);
        
        addObject(h, 720,584);
        addObject(p, 54, 539);
    }
}
