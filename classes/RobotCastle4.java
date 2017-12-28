import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Color;
/**
 * Write a description of class RobotCastle4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RobotCastle4 extends World
{
    Health h;
    Paul p;
    /**
     * Constructor for objects of class RobotCastle4.
     * 
     */
    public RobotCastle4(Health health, Paul paul)
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        h = health;
        p = paul;
        getBackground().setColor(Color.white);
        getBackground().setFont(new Font("",Font.BOLD,12));
        getBackground().drawString("DEFEAT ALL THE ROBOTS, PICK UP PAUL'S USB, AND THEN ENTER THE PORTAL TO RETURN TO THE CLASSROOM!",140,55);
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

        MagicPortal magicportal = new MagicPortal();
        addObject(magicportal, 78,502);
        USB usb = new USB();
        addObject(usb, 745, 170);

        Robot1 robot1 = new Robot1(false);
        addObject(robot1, 494,542);
        Robot3 robot3 = new Robot3(false);
        addObject(robot3,645,542);
        Robot2 robot2 = new Robot2(false);
        addObject(robot2,742,447);
        Robot1 robot12 = new Robot1(true);
        addObject(robot12, 329,349);
        Robot3 robot32 = new Robot3(true);
        addObject(robot32, 98,349);
        Robot2 robot22 = new Robot2(true);
        addObject(robot22, 48,253);
        Robot1 robot13 = new Robot1(false);
        addObject(robot13, 408, 156);
        Robot3 robot33 = new Robot3(false);
        addObject(robot33, 675,156);
        BoltRobot boltrobot = new BoltRobot();
        addObject(boltrobot, 61, 61);

        addObject(h, 720,584);
        addObject(p, 54, 539);
    }
}
