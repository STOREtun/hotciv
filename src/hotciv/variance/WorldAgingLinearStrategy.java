package hotciv.variance;

import hotciv.framework.WorldAgingStrategy;

/**
 * Created by asger on 18/11/15.
 *
 * Calculate the world age in a linear way.
 * Adding 100 to the current world age
 */
public class WorldAgingLinearStrategy implements WorldAgingStrategy {

    @Override
    public int calcWorldAge(int currentAge) {
        return currentAge+100;
    }
}
