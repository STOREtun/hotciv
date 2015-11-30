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
   static private HashMap<Player, Integer> playerWinCount = new HashMap<>();

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
    public void upDateWinCount(Player player) {
        if (playerWinCount.containsKey(player)){
            int currentWins = playerWinCount.get(player);
            playerWinCount.replace(player, currentWins+1);
        } else playerWinCount.put(player, 1);
    }
}
