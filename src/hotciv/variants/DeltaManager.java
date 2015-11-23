package hotciv.variants;

import hotciv.framework.*;

/**
 * Created by sditlev on 23/11/15.
 */
public class DeltaManager implements Manager {
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
}
