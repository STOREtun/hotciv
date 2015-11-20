package hotciv.variance;

import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.CityImpl;

import java.util.ArrayList;

/**
 * Created by sditlev on 20/11/15.
 */
public class WinnerBetaCivStrategy implements WinnerStrategy {

    @Override
    public Player calcWinner(ArrayList<CityImpl> cities) {
        int count = 1;
        Player firstCityOwner = cities.get(0).getOwner();
        while (count < cities.size()){
            Player nextCityOwner = cities.get(count).getOwner();
            if (nextCityOwner.equals(firstCityOwner)){
                count++;
            } else return null;
        }
        return firstCityOwner;
    }
}
