package hotciv.variants.FractalMap;

import hotciv.framework.Position;
import hotciv.framework.WorldMapStrategy;
import hotciv.standard.TileImpl;

import java.util.Arrays;
import java.util.Map;

import hotciv.standard.WorldMapGenerator;
import thirdparty.ThirdPartyFractalGenerator;

/**
 * Created by sditlev on 13/12/15.
 */
public class FractalMapStrategy implements WorldMapStrategy {
    ThirdPartyFractalGenerator fractalGenerator = new ThirdPartyFractalGenerator();
    WorldMapGenerator mapGenerator = new WorldMapGenerator();

    public String[] fractalStringMapCall(){
        String line;
        String[] layout =  new String[16];

        //Generate string array
        for ( int r = 0; r < 16; r++ ) {
            line = "";
            for ( int c = 0; c < 16; c++ ) {
                line = line + fractalGenerator.getLandscapeAt(r,c);
            }
            layout[r] = line;
        }
        return layout;
    }

    @Override
    public Map<Position, TileImpl> getWorldLayout() {
        return mapGenerator.defineWorld(fractalStringMapCall());
    }
}
