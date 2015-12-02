package hotciv.variants.GammaCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.UnitActionStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by sditlev on 23/11/15.
 */
public class GammaUnitActionStrategy implements UnitActionStrategy{
    @Override
    public void doUnitAction(GameImpl game, Position p) {
        UnitImpl unitActor = (UnitImpl) game.getUnitAt(p);
        String unitType = unitActor.getTypeString();

        if (unitType.equals(GameConstants.ARCHER)){
            unitActor.fortifyAction();
        }else if (unitType.equals(GameConstants.SETTLER)){
            CityImpl city = new CityImpl(unitActor.getOwner());
            game.positionUnitMap.remove(p);
            game.positionCityMap.put(p, city);
        }
    }
}
