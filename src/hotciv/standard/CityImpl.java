package hotciv.standard;

import hotciv.framework.*;
import hotciv.variants.ThetaCiv.ThetaCivGameConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sditlev on 02/11/15.
 */
public class CityImpl implements City{

    private Player owner;
    private String unitInProduction;
    private int productionPoints, size, food;
    private CityFactory cityFactory;
    private PopulationStrategy populationStrategy;
    private WorkforceFocusStrategy workforceFocusStrategy;


    private HashMap<String, Integer> unitCostMap, tilesWorkedMap;

    public CityImpl(Player _owner, CityFactory factory) {
        this.owner = _owner;
        this.productionPoints = 0;

        this.cityFactory = factory;
        populationStrategy = cityFactory.getPopulationStrategy();
        workforceFocusStrategy = cityFactory.getWorkforceFocusStrategy();

        size = 1;
        food = 0;

        unitCostMap = new HashMap<>();
        unitCostMap.put(ThetaCivGameConstant.CHARIOT, 20);
        unitCostMap.put(GameConstants.ARCHER, 10);
        unitCostMap.put(GameConstants.LEGION, 15);
        unitCostMap.put(GameConstants.SETTLER, 20);



        tilesWorkedMap = new HashMap<String, Integer>();
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return size;
    }

    /*
    *  the idea is to maximize (depending on workforce focus) either
    *  food or production from the surrounding 8 tiles.
    *
    *  as of now it just increments the population by one.
    *  It should check if enough food have been accumulated before incrementing
    */
    public void growCityIfAccumulatedEnoughFood(List<String> surroundingTiles) {
        size = populationStrategy.incrementPopulationIfAccumulatedEnoughFood(size, food);
    }

    public int getFood(){
        return food;
    }

    @Override
    public String getUnitInProduction() {
        return unitInProduction;
    }

    @Override
    public String getWorkforceFocus() {
        return workforceFocusStrategy.getWorkforceFocus();
    }

    public void setWorkforceFocus(String newWorkforceFocus){
        workforceFocusStrategy.setWorkforceFocus(newWorkforceFocus);
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
