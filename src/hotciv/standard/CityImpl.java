package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

/**
 * Created by sditlev on 02/11/15.
 */
public class CityImpl implements City{

    private Player owner;
    private String production;
    private int productionPoints;


    public CityImpl(Player _owner) {
        this.owner = _owner;
        this.productionPoints = 0;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getProduction() {
        return production;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }

    public int getProductionPoints(){
        return productionPoints;
    }

    public void incrementProductionPoints(){
        productionPoints += 6;
    }

    public void endProduction(){
        productionPoints -= 12;
        production = null;
    }

    public void changeProduction(String unitType){
        production = unitType;
    }

    public void changeOwner(Player player){
        owner = player;
    }

}
