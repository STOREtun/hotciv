package hotciv.variants.DeltaCiv;

import hotciv.framework.Position;
import hotciv.framework.WorldMapStrategy;
import hotciv.standard.TileImpl;
import hotciv.standard.WorldMapGenerator;

import java.util.Map;

/**
 * Created by sditlev on 21/11/15.
 */
public class DeltaWorldStrategy implements WorldMapStrategy {

    WorldMapGenerator mapGenrator = new WorldMapGenerator();

    String[] layout =
            new String[] {
                    "...ooMooooo.....",
                    "..ohhoooofffoo..",
                    ".oooooMooo...oo.",
                    ".ooMMMoooo..oooo",
                    "...ofooohhoooo..",
                    ".ofoofooooohhoo.",
                    "...ooo..........",
                    ".ooooo.ooohooM..",
                    ".ooooo.oohooof..",
                    "offfoooo.offoooo",
                    "oooooooo...ooooo",
                    ".ooMMMoooo......",
                    "..ooooooffoooo..",
                    "....ooooooooo...",
                    "..ooohhoo.......",
                    ".....ooooooooo..",
            };

    @Override
    public Map<Position, TileImpl> getWorldLayout() {
        return mapGenrator.defineWorld(layout);
    }
}
