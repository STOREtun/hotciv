package hotciv.variants.SemiCiv.CityStrategies;

import hotciv.framework.CityFactory;
import hotciv.framework.PopulationStrategy;
import hotciv.framework.WorkforceFocusStrategy;

/**
 * Created by asger on 03/12/15.
 */
public class SemiCivCityFactory implements CityFactory {
    @Override
    public WorkforceFocusStrategy getWorkforceFocusStrategy() {
        return null;
    }

    @Override
    public PopulationStrategy getPopulationStrategy() {
        return new SemiCivPopulationStrategy();
    }
}
