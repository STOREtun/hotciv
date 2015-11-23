package hotciv.framework;

/**
 * Created by sditlev on 23/11/15.
 */
public interface Manager {

    public WinnerStrategy getWinnerStrategy();

    public WorldAgingStrategy getWorldAgingStrategy();

    public WorldMapStrategy getWorldMapStrategy();

    public UnitActionStrategy getUnitActionStrategy();
}
