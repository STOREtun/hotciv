package hotciv.variants.ZetaCiv;

import hotciv.framework.*;
import hotciv.variants.AlphaCiv.AlphaAgingStrategy;
import hotciv.variants.AlphaCiv.AlphaAttackStrategy;
import hotciv.variants.AlphaCiv.AlphaUnitActionStrategy;
import hotciv.variants.AlphaCiv.AlphaWorldStrategy;

/**
 * Created by asger on 30/11/15.
 */
public class ZetaFactory implements Factory {

    @Override
    public WinnerStrategy getWinnerStrategy() {
        return new ZetaWinnerStrategy();
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
    public AttackInterface getAttackStrategy() {
        return new AlphaAttackStrategy();
    }
}
