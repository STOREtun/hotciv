package hotciv.standard;

import hotciv.framework.Tile;

/**
 * Created by sditlev on 02/11/15.
 */
public class TileImple implements Tile {

    public String type;

    public TileImple(String _type) {
        this.type = _type;
    }

    @Override
    public String getTypeString() {
        return type;
    }
}
