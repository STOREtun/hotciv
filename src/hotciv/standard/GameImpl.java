package hotciv.standard;

import hotciv.framework.*;
import java.util.HashMap;
import java.util.Map;

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
    private Player currentInTurnPlayer = Player.RED;

    private Map<Position, TileImpl> positionTileHashMap = new HashMap<Position, TileImpl>();
    private boolean mapCreated = false;

    private int worldAge = 3000;

    //Create city instance
    public CityImpl city1 = new CityImpl(Player.RED);

    //Create tile instances
    public TileImpl tile1 = new TileImpl(GameConstants.OCEANS);
    public TileImpl tile2 = new TileImpl(GameConstants.HILLS);
    public TileImpl tile3 = new TileImpl(GameConstants.MOUNTAINS);

    public void initTileMap(){
        positionTileHashMap.put(new Position(0,1), tile1);
        positionTileHashMap.put(new Position(1,0), tile2);
        positionTileHashMap.put(new Position(2,2), tile3);
    }

    public Tile getTileAt( Position p ) {
        //If hashmap  hasn't been created a new one is initialized
        if (!mapCreated) {
            initTileMap();
            mapCreated = true;
        }
        //Check if tile is in the hashmap positionTileHashMap
        //If not, we know it will be a plain since every other tile are plains
        if(positionTileHashMap.containsKey(p)) {
            return positionTileHashMap.get(p);
        }else {
            return new TileImpl(GameConstants.PLAINS);
        }
    }

    public Unit getUnitAt( Position p ) {
        return null;
    }

    public City getCityAt( Position p ) {
        return city1;
    }

    public Player getPlayerInTurn() {
        return currentInTurnPlayer;
    }

    public Player getWinner() {
        return Player.RED;
    }

    public int getAge() {
        return worldAge;
    }

    public boolean moveUnit( Position from, Position to ) {
        return false;
    }

    public void endOfTurn() {
        if(currentInTurnPlayer == Player.BLUE){
            configureNewRound();
            currentInTurnPlayer = Player.RED;
        }else currentInTurnPlayer = Player.BLUE;
    }

    public void changeWorkForceFocusInCityAt( Position p, String balance ) {

    }

    public void changeProductionInCityAt( Position p, String unitType ) {

    }

    public void performUnitActionAt( Position p ) {

    }

    public void configureNewRound(){
        city1.incrementProductionPoints();
        worldAge += 100;
    }
}
