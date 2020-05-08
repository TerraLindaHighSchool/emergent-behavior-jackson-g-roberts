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
    
    private int numOfFoodPiles = 5;

    /**
     * Create a new world. It will be initialised with a few ant hills
     * and food sources
     */
    public AntWorld()
    {
        super(SIZE, SIZE, 1);
        setPaintOrder(Ant.class, AntHill.class);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        AntHill antHill = new AntHill();
        addObject(antHill, 115, 159);
        AntHill antHill2 = new AntHill();
        addObject(antHill2, 321, 503);
        
        for (int i = 0; i < numOfFoodPiles; i++)
        {
            int foodPileX = Greenfoot.getRandomNumber(SIZE);
            int foodPileY = Greenfoot.getRandomNumber(SIZE);
            
            addObject(new Food(), foodPileX, foodPileY);
        }
    }
}
