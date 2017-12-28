import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * A Health class that allows you to display a numerical value on screen.
 * 
 * The Health is an actor, so you will need to create it, and then add it to
 * the world in Greenfoot.  If you keep a reference to the Health then you
 * can adjust its value.  Here's an example of a world class that
 * displays a health with the number of act cycles that have occurred:
 * 
 * <pre>
 * class CountingWorld
 * {
 *     private Health actHealth;
 *     
 *     public CountingWorld()
 *     {
 *         super(600, 400, 1);
 *         actHealth = new Health("Act Cycles: ");
 *         addObject(actHealth, 100, 100);
 *     }
 *     
 *     public void act()
 *     {
 *         actHealth.setValue(actHealth.getValue() + 1);
 *     }
 * }
 * </pre>
 * 
 * @author Neil Brown and Michael Kölling 
 * @version 1.0
 */
public class Health extends Actor
{
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    private int value;
    private int target;
    private String prefix;
    
    public Health()
    {
        this(new String(), 0);
    }

    /**
     * Create a new health, initialised to 0.
     */
    public Health(String prefix, int t)
    {
        background = getImage();  // get image from class
        value = 0;
        target = t;
        this.prefix = prefix;
        updateImage();
    }
    
    /**
     * Animate the display to count up (or down) to the current target value.
     */
    public void act() 
    {
        if (value < target) {
            value = target;
            updateImage();
        }
        else if (value > target) {
            value-=10;
            updateImage();
        }
    }

    /**
     * Add a new score to the current health value.  This will animate
     * the health over consecutive frames until it reaches the new value.
     */
    public void add(int score)
    {
        target += score;
    }

    /**
     * Return the current health value.
     */
    public int getValue()
    {
        return target;
    }

    /**
     * Set a new health value.  This will not animate the health.
     */
    public void setValue(int newValue)
    {
        target = newValue;
        value = newValue;
        updateImage();
    }
    
    /**
     * Sets a text prefix that should be displayed before
     * the health value (e.g. "Score: ").
     */
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
        updateImage();
    }

    /**
     * Update the image on screen to show the current value.
     */
    private void updateImage()
    {
        GreenfootImage image = new GreenfootImage(background);
        GreenfootImage text = new GreenfootImage(prefix + ": " + value, 22, Color.BLACK, transparent);
        
        if (text.getWidth() > image.getWidth() - 20)
        {
            image.scale(text.getWidth() + 20, image.getHeight());
        }
        
        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                        (image.getHeight()-text.getHeight())/2);
        setImage(image);
    }
}
