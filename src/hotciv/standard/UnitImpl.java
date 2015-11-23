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

    public UnitImpl(Player owner, String unitType) {
        this.owner = owner;
        this.unitType = unitType;
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

    public boolean isMovable(){
        if (fortified){
            return false;
        } else return true;
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
