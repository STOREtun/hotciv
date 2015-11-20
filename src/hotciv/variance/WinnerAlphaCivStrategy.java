package hotciv.variance;

import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.CityImpl;

import java.util.ArrayList;

/**
 * Created by sditlev on 20/11/15.
 */
public class WinnerAlphaCivStrategy implements WinnerStrategy {

    @Override
    public Player calcWinner(ArrayList<CityImpl> cities) {
        return Player.RED;
    }


}
