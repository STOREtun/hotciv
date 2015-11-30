package hotciv.variants.BetaCiv;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sditlev on 23/11/15.
 */
public class BetaWinnerStrategy implements WinnerStrategy{
    Player firstCityOwner;

    @Override
    public Player calcWinner(GameImpl game) {
        Map<Position, CityImpl> cities = game.getCityMap();

        for (Map.Entry<Position, CityImpl> entry : cities.entrySet()){
            Player cityOwner = entry.getValue().getOwner();
            if (firstCityOwner == null){
                firstCityOwner = cityOwner;
            }
            if (cityOwner.equals(firstCityOwner)){
                firstCityOwner = cityOwner;
            }else return null;
        }
        return firstCityOwner;
    }

    @Override
    public void updateWinCount(Player player, int roundCount) {
        // not used
    }
}
