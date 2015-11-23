package hotciv.variants;

import hotciv.framework.WorldAgingStrategy;

/**
 * Created by sditlev on 23/11/15.
 */
public class BetaAgingStrategy implements WorldAgingStrategy{
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
