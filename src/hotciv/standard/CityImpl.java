package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.CityFactory;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

import java.util.HashMap;

/**
 * Created by sditlev on 02/11/15.
 */
public class CityImpl implements City{

    private CityFactory factory;

    private Player owner;
    private String unitInProduction;
    private int productionPoints;
    private int size;

    private HashMap<String, Integer> unitCostMap;

    public CityImpl(Player _owner, CityFactory factory) {
        this.owner = _owner;
        this.productionPoints = 0;
        this.factory = factory;
        size = 0;

        unitCostMap = new HashMap<>();
        unitCostMap.put(GameConstants.CHARIOT, 20);
        unitCostMap.put(GameConstants.ARCHER, 10);
        unitCostMap.put(GameConstants.LEGION, 15);
        unitCostMap.put(GameConstants.SETTLER, 20);
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getUnitInProduction() {
        return unitInProduction;
    }

    @Override
    public String getWorkforceFocus() {
        return factory.getWorkforceFocusStrategy().getWorkforceFocus();
    }

    public void setWorkforceFocus(String newWorkforceFocus){
        factory.getWorkforceFocusStrategy().setWorkforceFocus(newWorkforceFocus);
    }

    public int getProductionPoints(){
        return productionPoints;
    }

    public boolean canProduceUnit(){
       if (unitInProduction != null) {
           return unitCostMap.get(unitInProduction) <= productionPoints;
       } else return false;
    }

    public void incrementProductionPoints(){
        productionPoints += 6;
    }

    public void endProduction(){
        productionPoints -= unitCostMap.get(unitInProduction);
        unitInProduction = null;
    }

    public void changeProduction(String unitType){
        unitInProduction = unitType;
    }

    public void changeOwner(Player player){
        owner = player;
    }
}
