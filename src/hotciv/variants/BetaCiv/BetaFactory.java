package hotciv.variants.BetaCiv;

import hotciv.framework.*;
import hotciv.variants.AlphaCiv.AlphaAttackStrategy;
import hotciv.variants.AlphaCiv.AlphaUnitActionStrategy;
import hotciv.variants.AlphaCiv.AlphaWorldStrategy;

/**
 * Created by sditlev on 23/11/15.
 */
public class BetaFactory implements Factory {
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
        return new AlphaUnitActionStrategy();
    }

    @Override
    public AttackStrategy getAttackStrategy() {
        return new AlphaAttackStrategy();
    }

    @Override
    public CityFactory getCityFactory() {
        return null;
    }
}
