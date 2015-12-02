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
    private int attackingStrength;
    private int moveLength;
    private int cost;

    private boolean movable;

    public UnitImpl(Player owner, String unitType) {
        this.owner = owner;
        this.unitType = unitType;
        this.movable = true;

        switch (unitType){
            case "archer":
                defensiveStrength = 3;
                attackingStrength = 2;
                cost = 10;
                moveLength = 1;
                break;

            case "settler":
                defensiveStrength = 3;
                attackingStrength = 0;
                cost = 30;
                moveLength = 1;
                break;

            case "legion":
                attackingStrength = 4;
                defensiveStrength = 2;
                cost = 15;
                moveLength = 1;
                break;

            case "chariot":
                attackingStrength = 3;
                defensiveStrength = 1;
                cost = 20;
                moveLength = 1;
                break;
        }

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
        return attackingStrength;
    }

    public boolean isMovable(){
        return movable;
    }

    public int getMoveLength(){
        return moveLength;
    }

    public int getCost(){
        return cost;
    }

    public void fortifyAction(){
        if (!movable) {
            defensiveStrength = defensiveStrength / 2;
        } else {
            defensiveStrength = defensiveStrength * 2;
        }
        movable = !movable;

    }
}
