import greenfoot.*;

/**
 * An ant that collects food.
 * 
 * @author Michael Kölling
 * @version 0.1
 */
public class Ant extends Creature
{
    private static final int MAX_PH_AVAILABLE = 16;
    private static final int TIME_FOLLOWING_TRAIL = 30;
    
    private boolean carryingFood;
    private GreenfootImage image1, image2;
    private int phAvailable, followTrialTimeRemaining;
    
    /**
     * Create an ant with a given home hill. The initial speed is zero (not moving).
     */
    public Ant(AntHill home)
    {
        setHomeHill(home);
        
        phAvailable = MAX_PH_AVAILABLE;
        followTrialTimeRemaining = 0;
        
        image1 = getImage();
        image2 = new GreenfootImage("ant-with-food.gif");
    }

    /**
     * Do what an ant's gotta do.
     */
    public void act()
    {
        status();
    }
    
    private void status()
    {
        if (carryingFood)
        {
            walkTowardsHome();
            handlePheromoneDrop();
            
            if (atHome())
            {
                setImage(image1);
                carryingFood = false;
                getHomeHill().countFood();
            } 
        } else
        {
            searchForFood();
        }
    }
    
    private void checkForFood()
    {
        Food food = (Food) getOneIntersectingObject(Food.class);
        if (food != null) 
        {
            food.removeCrumb();
            carryingFood = true;
            setImage(image2);
        }
    }
    
    private void searchForFood()
    {
        randomWalk();
        checkForFood();
    }
    
    private void handlePheromoneDrop()
    {
        
    }
    
    private void walkTowardsPheromoneCenter()
    {
        
    }
    
    private boolean atHome()
    {
        if (getHomeHill() != null)
        {
            return (Math.abs(getX() - getHomeHill().getX()) < 4) && 
                   (Math.abs(getY() - getHomeHill().getY()) < 4);
        } else
        {
            return false;
        }
    }
    
    private boolean smellsPheromone()
    {
        return false;
    }
}