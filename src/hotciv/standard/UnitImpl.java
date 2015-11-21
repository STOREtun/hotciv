package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by sditlev on 12/11/15.
 */
public class UnitImpl implements Unit {
    private Player owner;
    private String unitType;
    private int defensiveStrength;
    private boolean fortified;

    public UnitImpl(Player _owner, String _unitType) {
        this.owner = _owner;
        this.unitType = _unitType;
        defensiveStrength = 3;
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
        return defensiveStrength;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }

    public void archerFortifyAction(){
        if (fortified) {
            defensiveStrength = defensiveStrength / 2;
        } else {
            defensiveStrength = defensiveStrength * 2;
        }
        fortified = !fortified;

    }
}
