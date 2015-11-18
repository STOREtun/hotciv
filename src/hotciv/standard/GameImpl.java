package hotciv.standard;

import hotciv.framework.*;
import hotciv.variance.WorldAgingLinearStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

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
        } else return city1;
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

    /* Can be rewritten to be more readable */
    public boolean moveUnit( Position from, Position to ) {
        UnitImpl unit = positionUnitMap.get(from);
        int vectorX = Math.abs(to.getRow() - from.getRow());
        int vectorY = Math.abs(to.getColumn() - from.getColumn());
        Player unitOwner = unit.getOwner();

        if (!unitOwner.equals(currentPlayerInTurn)) return false;
        if (!(vectorX < 2) || !(vectorY < 2)) return false;
        if (getTileAt(to).getTypeString().equals(GameConstants.MOUNTAINS)) return false;
        if(getCityAt(to) != null) {
            if (!getCityAt(to).getOwner().equals(currentPlayerInTurn)) {
                getCityAt(to).changeOwner(currentPlayerInTurn);
            }
        }
        if(!positionUnitMap.containsKey(to)){
            positionUnitMap.remove(from);
            positionUnitMap.put(to, unit);
            return true;
        }
        if (!positionUnitMap.get(to).getOwner().equals(currentPlayerInTurn)){
            positionUnitMap.replace(to, unit);
            return true;
        }
        return false;
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

    }

    public void performUnitActionAt( Position p ) {

    }

    public void configureNewRound(){
        city1.incrementProductionPoints();
        worldAge = worldAgingStrategy.calcWorldAge(worldAge);
    }
}
