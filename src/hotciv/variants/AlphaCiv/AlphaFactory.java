package hotciv.variants.AlphaCiv;

import hotciv.framework.*;

/**
 * Created by sditlev on 23/11/15.
 */
public class AlphaFactory implements Factory {
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
        return new AlphaWorldStrategy();
    }
    @Override
    public UnitActionStrategy getUnitActionStrategy() {
        return null;
    }

    @Override
    public AttackStrategy getAttackStrategy() {
        return new AlphaAttackStrategy();
    }
}
