import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pheremone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pheremone extends Actor
{
    private GreenfootImage image;
    private static final int MAX_INTENSITY = 180;
    private int intensity;
    
    private int mRed, mGreen, mBlue;
    
    public Pheremone(int red, int green, int blue)
    {
        intensity = MAX_INTENSITY;
        
        mRed = red;
        mGreen = green;
        mBlue = blue;
        
        updateImage();
    }
    
    /**
     * Act - do whatever the Pheremone wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        intensity--;
        if (intensity <= 0)
        {
            getWorld().removeObject(this);
        } else
        {
            if ((intensity % 6) == 0)
            {
                updateImage();
            }
        }
    } 
    
    private void updateImage()
    {
        int size = intensity / 3 + 5;
        image = new GreenfootImage(size + 1, size + 1);
        
        image.setColor(new Color(mRed, mGreen, mBlue));
        image.setTransparency(intensity / 3);
        image.fillOval(0, 0, size, size);
        
        image.setColor(Color.DARK_GRAY);
        image.fillRect(size / 2, size / 2, 2, 2);
        
        setImage(image);
    }
}
