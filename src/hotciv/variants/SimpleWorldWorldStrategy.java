package hotciv.variants;

import hotciv.framework.Position;
import hotciv.framework.WorldMapStrategy;
import hotciv.standard.TileImpl;
import hotciv.standard.WorldMapGenrator;

import java.util.Map;

/**
 * Created by sditlev on 21/11/15.
 */
public class SimpleWorldWorldStrategy implements WorldMapStrategy {

    WorldMapGenrator mapGenrator = new WorldMapGenrator();

    String[] layout =
            new String[] {
                    "ohoooooooooooooo",
                    ".ooooooooooooooo",
                    "ooMooooooooooooo",
                    "oooooooooooooooo",
                    "oooooooooooooooo",
                    "oooooooooooooooo",
                    "oooooooooooooooo",
                    "oooooooooooooooo",
                    "oooooooooooooooo",
                    "oooooooooooooooo",
                    "oooooooooooooooo",
                    "oooooooooooooooo.",
                    "oooooooooooooooo",
                    "oooooooooooooooo",
                    "oooooooooooooooo",
                    "oooooooooooooooo",
            };

    @Override
    public Map<Position, TileImpl> getWorldLayout() {
        return mapGenrator.defineWorld(layout);
    }
}
