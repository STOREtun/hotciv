package hotciv.variants.AlphaCiv.CityStrategies;

import hotciv.framework.PopulationStrategy;

/**
 * Created by asger on 03/12/15.
 */
public class AlphaCivPopulationStrategy implements PopulationStrategy {
    @Override
    public int incrementPopulationIfAccumulatedEnoughFood(int currentPopulation, int currentFood) {
        return 1; // always returns one
    }
}
