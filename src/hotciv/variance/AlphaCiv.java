package hotciv.variance;

import hotciv.framework.CivType;
import hotciv.framework.Player;
import hotciv.standard.CityImpl;

import java.util.ArrayList;

/**
 * Created by asger on 22/11/15.
 */
public class AlphaCiv implements CivType{

    @Override
    public int calcWorldAge(int currentWorldAge) {
        return currentWorldAge+100;
    }

    @Override
    public Player calcWinner(ArrayList<CityImpl> cities) {
        return Player.RED;
    }
}
