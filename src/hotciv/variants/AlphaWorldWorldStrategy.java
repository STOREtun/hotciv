package hotciv.variants;

import hotciv.framework.Position;
import hotciv.framework.WorldMapStrategy;
import hotciv.standard.TileImpl;
import hotciv.standard.WorldMapGenerator;

import java.util.Map;

/**
 * Created by sditlev on 21/11/15.
 */
public class AlphaWorldWorldStrategy implements WorldMapStrategy {

    WorldMapGenerator mapGenerator = new WorldMapGenerator();

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
        return mapGenerator.defineWorld(layout);
    }
}
