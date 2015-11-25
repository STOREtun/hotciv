package hotciv.variants.GammaCiv;

import hotciv.framework.*;
import hotciv.variants.AlphaCiv.AlphaAttackStrategy;
import hotciv.variants.AlphaCiv.AlphaWorldStrategy;
import hotciv.variants.BetaCiv.BetaAgingStrategy;
import hotciv.variants.BetaCiv.BetaWinnerStrategy;

/**
 * Created by sditlev on 23/11/15.
 */
public class GammaFactory implements Factory {
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
    public AttackInterface getAttackStrategy() {
        return new AlphaAttackStrategy();
    }
}
