package hotciv.standard;

import hotciv.framework.*;
import hotciv.variance.WorldAgingLinearStrategy;
import javafx.geometry.Pos;

import java.util.*;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {
    //game variables
    private Player currentPlayerInTurn;
    private int worldAge;

    //Maps
    private Map<Position, UnitImpl> positionUnitMap;

    //Create city instance
    private CityImpl city1;
    private CityImpl city2;

    //Create tile instances
    private TileImpl tile1;
    private TileImpl tile2;
    private TileImpl tile3;

    //World age strategy
    private WorldAgingStrategy worldAgingStrategy;

    public GameImpl(WorldAgingStrategy worldAgingStrategy){
        currentPlayerInTurn = Player.RED;
        worldAge = -4000;

        //Create cities
        city1 = new CityImpl(Player.RED);
        city2 = new CityImpl(Player.BLUE);

        //Create tiles
        tile1 = new TileImpl(GameConstants.OCEANS);
        tile2 = new TileImpl(GameConstants.HILLS);
        tile3 = new TileImpl(GameConstants.MOUNTAINS);

        //Create hash maps of units
        positionUnitMap = new HashMap<Position, UnitImpl>();

        //Create units
        UnitImpl redUnit1 = new UnitImpl(Player.RED, GameConstants.ARCHER);
        UnitImpl blueUnit2 = new UnitImpl(Player.BLUE, GameConstants.LEGION);
        UnitImpl redUnit2 = new UnitImpl(Player.RED, GameConstants.SETTLER);

        //add units to unit map
        positionUnitMap.put(new Position(2,0), redUnit1);
        positionUnitMap.put(new Position(3,2), blueUnit2);
        positionUnitMap.put(new Position(4,3), redUnit2);

        //World aging strategy
        this.worldAgingStrategy = worldAgingStrategy;
    }

    public Tile getTileAt( Position p ) {
        if (p.equals(new Position(0, 1))){
            return tile1;
        } else if (p.equals(new Position(1, 0))){
            return tile2;
        } else if(p.equals(new Position(2, 2))){
            return tile3;
        } else {
            return new TileImpl(GameConstants.PLAINS);
        }
    }

    public Unit getUnitAt( Position p ) {
        return positionUnitMap.get(p);
    }

    public CityImpl getCityAt( Position p ) {
        if (p.equals(new Position(4,1))){
            return city2;
        } else if(p.equals(new Position(1,1))) {
            return city1;
        }

        return null;
    }

    public Player getPlayerInTurn() {
        return currentPlayerInTurn;
    }

    public Player getWinner() {
        return Player.RED;
    }

    public int getAge() {
        return worldAge;
    }

    public boolean moveUnit( Position from, Position to ) {
        UnitImpl movingUnit = positionUnitMap.get(from);
        int x = Math.abs(to.getRow() - from.getRow());
        int y = Math.abs(to.getColumn() - from.getColumn());
        Player unitOwner = movingUnit.getOwner();

        boolean playerInTurnMovingUnit = unitOwner.equals(currentPlayerInTurn);
        if(playerInTurnMovingUnit && isValidMove(x, y, to)){
            if(getCityAt(to) != null) {           // there is a city at the to tile
                if (!getCityAt(to).getOwner().equals(currentPlayerInTurn)) {
                    getCityAt(to).changeOwner(currentPlayerInTurn);
                    return true;
                }
            }

            boolean noFriendlyUnitOnDestinationTile = !positionUnitMap.containsKey(to);
            if(noFriendlyUnitOnDestinationTile) {
                positionUnitMap.remove(from);           // update the units position in the positionMap
                positionUnitMap.put(to, movingUnit);
                return true;
            }

            boolean enemyUnitPresentOnDestinationTile = !positionUnitMap.get(to).getOwner().equals(currentPlayerInTurn);
            if (enemyUnitPresentOnDestinationTile){
                positionUnitMap.replace(to, movingUnit); // kill and replace the unit
                positionUnitMap.remove(from);
                return true;
            }
        }
//        System.err.println("Move made by: " + currentPlayerInTurn + " is not valid");
        return false;
    }

    public boolean isValidMove(int x, int y, Position endPostion){
        Tile destinationTile = getTileAt(endPostion);
        if( (x < 2) && (y < 2) ){
            boolean tileIsMountain = destinationTile.getTypeString().equals(GameConstants.MOUNTAINS);
            boolean tileIsOcean = destinationTile.getTypeString().equals(GameConstants.OCEANS);

            return (!tileIsMountain && !tileIsOcean);
        }else return false;
    }

    public void endOfTurn() {
        if(currentPlayerInTurn == Player.BLUE){
            configureNewRound();
            currentPlayerInTurn = Player.RED;
        }else currentPlayerInTurn = Player.BLUE;
    }

    public void changeWorkForceFocusInCityAt( Position p, String balance ) {

    }

    public void changeProductionInCityAt( Position p, String unitType ) {
        getCityAt(p).changeProduction(unitType);
    }

    public void performUnitActionAt( Position p ) {

    }

    public void configureNewRound(){
        city1.incrementProductionPoints();
        if (city1.getProduction() != null && city1.getProductionPoints() >= 12){
            Position firstEmptyTile = checkAdjacentTilesForUnit(new Position(1,1));
            if (firstEmptyTile != null) {
                UnitImpl unitProduced = new UnitImpl(city1.getOwner(), city1.getProduction());
                positionUnitMap.put(firstEmptyTile, unitProduced);
            }
        }

        city2.incrementProductionPoints();
        if (city2.getProduction() != null && city2.getProductionPoints() >= 12){
            Position firstEmptyTile = checkAdjacentTilesForUnit(new Position(4,1));
            if (firstEmptyTile != null) {
                UnitImpl unitProduced = new UnitImpl(city2.getOwner(), city2.getProduction());
                positionUnitMap.put(firstEmptyTile, unitProduced);
            }
        }
        worldAge = worldAgingStrategy.calcWorldAge(worldAge);
    }

    public Position checkAdjacentTilesForUnit(Position p){
        int x = p.getRow();
        int y = p.getColumn();

        List<Position> positions = new ArrayList<>();
        positions.add(new Position(x, y));
        positions.add(new Position(x, y+1));
        positions.add(new Position(x+1, y+1));
        positions.add(new Position(x+1, y));
        positions.add(new Position(x+1, y-1));
        positions.add(new Position(x, y-1));
        positions.add(new Position(x-1, y-1));
        positions.add(new Position(x-1, y));
        positions.add(new Position(x-1, y+1));

        for (Position pos: positions){
            if (getUnitAt(pos) == null) return pos;
        }
        return null;
    }
}
