package hotciv.variants.EpsilonCiv;

import hotciv.framework.*;
import hotciv.variants.AlphaCiv.AlphaAgingStrategy;
import hotciv.variants.AlphaCiv.AlphaUnitActionStrategy;
import hotciv.variants.AlphaCiv.AlphaWorldStrategy;

/**
 * Created by asger on 25/11/15.
 *
 * The class takes a die in order to test the outcome of a battle
 * with either a random- or fake die
 *
 */
public class EpsilonFactory implements Factory {

    private Die die;

    public EpsilonFactory(Die die){
        this.die = die;
    }

    @Override
    public WinnerStrategy getWinnerStrategy() {
        return new EpsilonWinnerStrategy();
    }

    @Override
    public WorldAgingStrategy getWorldAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public WorldMapStrategy getWorldMapStrategy() {
        return new AlphaWorldStrategy();
    }

    @Override
    public UnitActionStrategy getUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }

    @Override
    public AttackStrategy getAttackStrategy() {
        return new EpsilonAttackStrategy(die);
    }

    @Override
    public CityFactory getCityFactory() {
        return null;
    }

}
