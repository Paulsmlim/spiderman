import greenfoot.*;
import java.awt.Font;
import java.awt.Color;

/**
 * First stage of the Robot Castle which has 3 of the weakest Robot.
 */
public class RobotCastle1 extends World
{    
    Health h;
    Paul p;
    
    /**
     * Constructor for objects of class RobotCastle1
     */
    public RobotCastle1(Health health, Paul paul)
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

        CastleDoor castledoor = new CastleDoor();
        addObject(castledoor,710,132);

        Robot1 robot1 = new Robot1(false);
        addObject(robot1,497,542);
        Robot1 robot12 = new Robot1(true);
        addObject(robot12,255,349);
        Robot1 robot13 = new Robot1(true);
        addObject(robot13,30,250);
        Robot1 robot14 = new Robot1(false);
        addObject(robot14,603,156);
        BoltRobot boltrobot = new BoltRobot();
        addObject(boltrobot, 61, 61);

        addObject(h, 720,584);
        addObject(p, 54, 539);
    }
}
