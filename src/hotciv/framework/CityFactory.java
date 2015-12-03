package hotciv.framework;

/**
 * Created by asger on 03/12/15.
 */
public interface CityFactory {
    WorkforceFocusStrategy getWorkforceFocusStrategy();

    PopulationStrategy getPopulationStrategy();
}
