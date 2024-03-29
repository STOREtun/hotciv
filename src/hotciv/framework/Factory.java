package hotciv.framework;


/**
 * Created by sditlev on 23/11/15.
 *
 * This interface handles the different strategies defining the civ type
 *
 */
public interface Factory {

    WinnerStrategy getWinnerStrategy();

    WorldAgingStrategy getWorldAgingStrategy();

    WorldMapStrategy getWorldMapStrategy();

    UnitActionStrategy getUnitActionStrategy();

    AttackStrategy getAttackStrategy();

    CityFactory getCityFactory();
}
