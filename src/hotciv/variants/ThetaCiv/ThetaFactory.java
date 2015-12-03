package hotciv.variants.ThetaCiv;

import hotciv.framework.*;
import hotciv.variants.AlphaCiv.AlphaAttackStrategy;
import hotciv.variants.AlphaCiv.AlphaWorldStrategy;
import hotciv.variants.AlphaCiv.CityStrategies.AlphaCityFactory;
import hotciv.variants.BetaCiv.BetaAgingStrategy;
import hotciv.variants.BetaCiv.BetaWinnerStrategy;
import hotciv.variants.GammaCiv.GammaUnitActionStrategy;

/**
 * Created by asger on 03/12/15.
 */
public class ThetaFactory implements Factory {
    @Override
    public WinnerStrategy getWinnerStrategy() {
        return new BetaWinnerStrategy();
    }

    @Override
    public WorldAgingStrategy getWorldAgingStrategy() {
        return new BetaAgingStrategy();
    }

    @Override
    public WorldMapStrategy getWorldMapStrategy() {
        return new AlphaWorldStrategy();
    }

    @Override
    public UnitActionStrategy getUnitActionStrategy() {
        return new GammaUnitActionStrategy();
    }

    @Override
    public AttackStrategy getAttackStrategy() {
        return new AlphaAttackStrategy();
    }

    @Override
    public CityFactory getCityFactory() {
        return new AlphaCityFactory();
    }

}
