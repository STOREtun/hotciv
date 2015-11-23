package hotciv.variants;

import hotciv.framework.*;

/**
 * Created by sditlev on 23/11/15.
 */
public class BetaManager implements Manager {
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
        return new SimpleWorldWorldStrategy();
    }

    @Override
    public UnitActionStrategy getUnitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }
}
