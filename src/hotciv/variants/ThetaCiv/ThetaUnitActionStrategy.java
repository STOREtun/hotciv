package hotciv.variants.ThetaCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.UnitActionStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by sditlev on 07/12/15.
 */
public class ThetaUnitActionStrategy implements UnitActionStrategy {
    @Override
    public void doUnitAction(GameImpl game, Position p) {
        UnitImpl unitActor = game.getUnitAt(p);
        String unitType = unitActor.getTypeString();

        if (unitType.equals(GameConstants.ARCHER) || unitType.equals(ThetaCivGameConstant.CHARIOT)){
            unitActor.fortifyAction();
        }else if (unitType.equals(GameConstants.SETTLER)){
            CityImpl city = new CityImpl(unitActor.getOwner(), game.factory.getCityFactory());
            game.positionUnitMap.remove(p);
            game.positionCityMap.put(p, city);
        }
    }
}
