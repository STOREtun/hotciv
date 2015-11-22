package hotciv.variance;

import hotciv.framework.CivType;
import hotciv.framework.Player;
import hotciv.standard.CityImpl;

import java.util.ArrayList;

/**
 * Created by asger on 22/11/15.
 */
public class BetaCiv implements CivType{

    private int worldAge;

    @Override
    public int calcWorldAge(int currentWorldAge) {
        worldAge = currentWorldAge;

        if(isBetween4000BCAnd100BC()){
            return currentWorldAge + 100;
        }

        if(isAroundBirthOfChrist()) {
            switch (worldAge) {
                case -100:
                    return -1;

                case -1:
                    return 1;

                case 1:
                    return 50;
            }
        }

        if(isBetween50And1750())
            return currentWorldAge + 50;

        if(isBetween1750And1900())
            return currentWorldAge + 25;

        if(isBetween1900And1970())
            return currentWorldAge + 5;

        if(isAfter1970())
            return currentWorldAge + 1;

        return 0;
    }

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

        /*
    * Methods to handle the different calculations
    */

    public boolean isBetween4000BCAnd100BC(){
        return (worldAge >= -4000 && worldAge < -100);
    }

    public boolean isAroundBirthOfChrist(){
        return (worldAge >= -100 && worldAge < 50);
    }

    public boolean isBetween50And1750(){
        return (worldAge <= 50 && worldAge < 1750);
    }

    public boolean isBetween1750And1900(){
        return (worldAge <= 1750 && worldAge < 1900);
    }

    public boolean isBetween1900And1970(){
        return (worldAge <= 1900 && worldAge < 1970);
    }

    public boolean isAfter1970(){
        return (worldAge >= 1970);
    }
}
