package hotciv.variants;

import hotciv.framework.*;

/**
 * Created by sditlev on 23/11/15.
 */
public class AlphaManager implements Manager {
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
        return new AlphaWorldWorldStrategy();
    }
    @Override
    public UnitActionStrategy getUnitActionStrategy() {
        return null;
    }
}
