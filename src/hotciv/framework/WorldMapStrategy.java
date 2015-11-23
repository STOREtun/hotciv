package hotciv.framework;

import hotciv.standard.TileImpl;

import java.util.Map;

/**
 * Created by sditlev on 21/11/15.
 */
public interface WorldMapStrategy {
    Map<Position, TileImpl> getWorldLayout();
}
