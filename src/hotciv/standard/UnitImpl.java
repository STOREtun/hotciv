package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by sditlev on 12/11/15.
 */
public class UnitImpl implements Unit {
    private Player owner;
    private String unitType;

    public UnitImpl(Player _owner, String _unitType) {
        this.owner = _owner;
        this.unitType = _unitType;
    }

    @Override
    public String getTypeString() {
        return unitType;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return 0;
    }

    @Override
    public int getDefensiveStrength() {
        return 0;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
