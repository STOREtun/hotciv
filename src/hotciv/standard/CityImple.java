package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

/**
 * Created by sditlev on 02/11/15.
 */
public class CityImple implements City{

    public Player owner;

    public CityImple(Player _owner) {
        this.owner = _owner;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String getProduction() {
        return null;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
