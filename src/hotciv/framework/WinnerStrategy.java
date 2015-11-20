package hotciv.framework;

import hotciv.standard.CityImpl;

import java.util.ArrayList;

/**
 * Created by sditlev on 20/11/15.
 */
public interface WinnerStrategy {

    /** Calculate the world winner.
     @param all cities in list
     @return winner
     */
    public Player calcWinner(ArrayList<CityImpl> cities);
}
