import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * The world where ants live.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class AntWorld extends World
{
    public static final int SIZE = 640;
    
    private static final int NUM_OF_FOOD_PILES = 10;
    private static final int NUM_OF_ANT_HILLS = 2;

    /**
     * Create a new world. It will be initialised with a few ant hills
     * and food sources
     */
    public AntWorld()
    {
        super(SIZE, SIZE, 1);
        setPaintOrder(Counter.class, Ant.class, AntHill.class);
        prepare();
    }
    
    public void act()
    {
        if (getObjects(Food.class).size() == 0 && getObjects(Pheremone.class).size() == 0)
        {
            Greenfoot.stop();
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        for (int i = 0; i < NUM_OF_ANT_HILLS; i++)
        {
            int antHillX = Greenfoot.getRandomNumber(SIZE);
            int antHillY = Greenfoot.getRandomNumber(SIZE);
            
            addObject(new AntHill(), antHillX, antHillY);
        }
        
        for (int i = 0; i < NUM_OF_FOOD_PILES; i++)
        {
            int foodPileX = Greenfoot.getRandomNumber(SIZE);
            int foodPileY = Greenfoot.getRandomNumber(SIZE);
            
            addObject(new Food(), foodPileX, foodPileY);
        }
    }
}
