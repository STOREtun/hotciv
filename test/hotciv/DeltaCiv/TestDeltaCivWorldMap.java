package hotciv.DeltaCiv;

import hotciv.framework.*;
import hotciv.standard.TileImpl;
import hotciv.variants.DeltaCiv.DeltaFactory;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
/**
 * Created by sditlev on 23/11/15.
 */
public class TestDeltaCivWorldMap {

    private Factory manager;

    @Before
    public void setupGame(){
        manager = new DeltaFactory();
    }

    @Test
    public void shouldStartWithCorrectWorldLayout() {
        Map<Position, TileImpl> positionSpecialTileMap = manager.getWorldMapStrategy().getWorldLayout();

        String oceanTile = positionSpecialTileMap.get(new Position(0,0)).getTypeString();
        String mountainTile = positionSpecialTileMap.get(new Position(0,5)).getTypeString();

        // delta map layout has ocean on tile (0,0) and mountain on (0,5)

        assertThat("Tiletype is ocean", oceanTile, is("ocean"));
        assertThat("Tiletype is mountain", mountainTile, is("mountain"));
    }
}
