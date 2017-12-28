import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Color;
/**
 * Write a description of class RobotCastle3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RobotCastle3 extends World
{
    Health h;
    Paul p;
    /**
     * Constructor for objects of class RobotCastle3.
     * 
     */
    public RobotCastle3(Health health, Paul paul)
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        h = health;
        p = paul;
        getBackground().setColor(Color.white);
        getBackground().setFont(new Font("",Font.BOLD,13));
        getBackground().drawString("DEFEAT ALL THE ROBOTS AND THEN ENTER THE CASTLE DOOR TO GO TO THE NEXT LEVEL",140,55);
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
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
        BossDoor bossdoor = new BossDoor();
        addObject(bossdoor,710,132);
        
        Robot3 robot3 = new Robot3(false);
        addObject(robot3,497,542);
        Robot3 robot32 = new Robot3(true);
        addObject(robot32,255,349);
        Robot3 robot33 = new Robot3(true);
        addObject(robot33,30,250);
        Robot3 robot34 = new Robot3(false);
        addObject(robot34,603,156);
        
        addObject(h, 720,584);
        addObject(p, 54, 539);
    }
}
