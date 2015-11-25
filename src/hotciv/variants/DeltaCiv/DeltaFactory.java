package hotciv.variants.DeltaCiv;

import hotciv.framework.*;
import hotciv.variants.AlphaCiv.AlphaAgingStrategy;
import hotciv.variants.AlphaCiv.AlphaAttackStrategy;
import hotciv.variants.AlphaCiv.AlphaUnitActionStrategy;
import hotciv.variants.AlphaCiv.AlphaWinnerStrategy;

/**
 * Created by sditlev on 23/11/15.
 */
public class DeltaFactory implements Factory {
    @Override
    public WinnerStrategy getWinnerStrategy() {
        return new AlphaWinnerStrategy();
    }

    @Override
    public WorldAgingStrategy getWorldAgingStrategy() {
        return new AlphaAgingStrategy();
    }

    @Override
    public WorldMapStrategy getWorldMapStrategy() {
        return new DeltaWorldStrategy();
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
