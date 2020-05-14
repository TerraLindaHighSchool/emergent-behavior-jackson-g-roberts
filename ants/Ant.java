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
    private int phAvailable, followTrailTimeRemaining;
    
    /**
     * Create an ant with a given home hill. The initial speed is zero (not moving).
     */
    public Ant(AntHill home)
    {
        setHomeHill(home);
        
        phAvailable = MAX_PH_AVAILABLE;
        followTrailTimeRemaining = 0;
        
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
        if (followTrailTimeRemaining == 0)
        {
            if (smellsPheromone())
            {
                walkTowardsPheromoneCenter();
            } else
            {
                randomWalk();
                if (isAtEdge()) turn(180);
            }
        } else 
        {
            followTrailTimeRemaining--;
            walkAwayFromHome();
        }
        checkForFood();
    }
    
    private void handlePheromoneDrop()
    {
        if (phAvailable == MAX_PH_AVAILABLE)
        {
            getWorld().addObject(new Pheremone((int) (getHomeHill().getX() / 2.6), 127, (int) (getHomeHill().getY() / 2.6)), getX(), getY());
            phAvailable = 0;
        } else
        {
            phAvailable++;
        }
    }
    
    private void walkTowardsPheromoneCenter()
    {
        Actor droplet = getOneIntersectingObject(Pheremone.class);
        if (droplet != null)
        {
            headTowards(droplet);
            if (getX() == droplet.getX() && getY() == droplet.getY())
            {
                followTrailTimeRemaining = TIME_FOLLOWING_TRAIL;
            }
        }
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
        Actor droplet = getOneIntersectingObject(Pheremone.class);
        if (droplet != null)
        {
            return true;
        } else 
        {
            return false;
        }
    }
}