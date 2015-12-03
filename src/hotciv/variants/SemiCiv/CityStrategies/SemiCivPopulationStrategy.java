package hotciv.variants.SemiCiv.CityStrategies;

import hotciv.framework.PopulationStrategy;

/**
 * Created by asger on 03/12/15.
 */
public class SemiCivPopulationStrategy implements PopulationStrategy {

    @Override
    public int incrementPopulationIfAccumulatedEnoughFood(int currentPopulation, int currentFood) {
        if(5 + (currentFood) * 3 >= currentPopulation){
            return currentPopulation + 1;
        }else return currentPopulation;
    }
}
