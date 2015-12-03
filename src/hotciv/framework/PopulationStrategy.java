package hotciv.framework;

/**
 * Created by asger on 03/12/15.
 */
public interface PopulationStrategy {
    int incrementPopulationIfAccumulatedEnoughFood(int currentPopulation, int currentFood);
}
