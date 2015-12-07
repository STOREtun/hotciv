package hotciv.variants.SemiCiv;

import hotciv.framework.*;
import hotciv.variants.BetaCiv.BetaAgingStrategy;
import hotciv.variants.DeltaCiv.DeltaWorldStrategy;
import hotciv.variants.EpsilonCiv.EpsilonAttackStrategy;
import hotciv.variants.EpsilonCiv.EpsilonWinnerStrategy;
import hotciv.variants.GammaCiv.GammaUnitActionStrategy;
import hotciv.variants.RandomDie;
import hotciv.variants.SemiCiv.CityStrategies.SemiCivCityFactory;

/**
 * Created by asger on 03/12/15.
 */
public class SemiCivFactory implements Factory {
    @Override
    public WinnerStrategy getWinnerStrategy() {
        return new EpsilonWinnerStrategy();
    }

    @Override
    public WorldAgingStrategy getWorldAgingStrategy() {
        return new BetaAgingStrategy();
    }

    @Override
    public WorldMapStrategy getWorldMapStrategy() {
        return new DeltaWorldStrategy();
    }

    @Override
    public UnitActionStrategy getUnitActionStrategy() {
        return new GammaUnitActionStrategy();
    }

    @Override
    public AttackStrategy getAttackStrategy() {
        return new EpsilonAttackStrategy(new RandomDie());
    }

    @Override
    public CityFactory getCityFactory() {
        return new SemiCivCityFactory();
    }

}
