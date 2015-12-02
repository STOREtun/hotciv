package hotciv.standard;

import hotciv.framework.*;

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
    private int roundCount;

    //Maps
    public Map<Position, UnitImpl> positionUnitMap;
    public Map<Position, CityImpl> positionCityMap;
    private Map<Position, TileImpl> positionSpecialTileMap;

    //Create city instance
    private CityImpl city1;
    private CityImpl city2;

    //Factory
    public Factory factory;
    public WinnerStrategy winnerStrategy;
    public UnitActionStrategy unitActionStrategy;
    public WorldAgingStrategy worldAgingStrategy;
    public WorldMapStrategy worldMapStrategy;
    public AttackStrategy attackStrategy;

    public GameImpl(Factory factory){
        //Setup factory imle
        this.factory = factory;
        winnerStrategy = factory.getWinnerStrategy();
        unitActionStrategy = factory.getUnitActionStrategy();
        worldAgingStrategy = factory.getWorldAgingStrategy();
        worldMapStrategy = factory.getWorldMapStrategy();
        attackStrategy = factory.getAttackStrategy();

        currentPlayerInTurn = Player.RED;
        worldAge = -4000;
        roundCount = 0;

        //Create cities
        city1 = new CityImpl(Player.RED);
        city2 = new CityImpl(Player.BLUE);

        //Create hash maps of units
        positionUnitMap = new HashMap<Position, UnitImpl>();
        positionCityMap = new HashMap<Position, CityImpl>();
        positionSpecialTileMap = worldMapStrategy.getWorldLayout();

        //Create units
        UnitImpl blueLegion = new UnitImpl(Player.BLUE, GameConstants.LEGION);
        UnitImpl redArcher = new UnitImpl(Player.RED, GameConstants.ARCHER);
        UnitImpl redSettler = new UnitImpl(Player.RED, GameConstants.SETTLER);

        //add units to unit map
        positionUnitMap.put(new Position(3,2), blueLegion);
        positionUnitMap.put(new Position(2,0), redArcher);
        positionUnitMap.put(new Position(4,3), redSettler);

        //Add cities to city map
        positionCityMap.put(new Position(1,1), city1);
        positionCityMap.put(new Position(4,1), city2);
    }

    public TileImpl getTileAt( Position p ) {
        if (positionSpecialTileMap.containsKey(p)){
            return positionSpecialTileMap.get(p);
        } else return new TileImpl(GameConstants.PLAINS);
    }

    public UnitImpl getUnitAt( Position p ) {
        return positionUnitMap.get(p);
    }

    public CityImpl getCityAt( Position p ) {
        return positionCityMap.get(p);
    }

    public Player getPlayerInTurn() {
        return currentPlayerInTurn;
    }

    /* Could be called after city takeover */
    public Player getWinner() {
        return winnerStrategy.calcWinner(this);
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
        if(playerInTurnMovingUnit && isValidMove(x, y, to) && movingUnit.isMovable()){
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

            /*
             * Instead of calling .updateWinCount(winner) we could call calcWinner directly.
             * That would also open a possibility to stop the game instantly when a player
             * wins.
            */
            boolean enemyUnitPresentOnDestinationTile = !positionUnitMap.get(to).getOwner().equals(currentPlayerInTurn);
            if (enemyUnitPresentOnDestinationTile){
               if(attackStrategy.attackSuccessful(from, to, this)){
                   Player winner = getUnitAt(from).getOwner();
                   winnerStrategy.updateWinCount(winner, roundCount);
                   positionUnitMap.replace(to, movingUnit); // kill and replace the unit
                   positionUnitMap.remove(from);
                   return true;
               }else positionUnitMap.remove(from);
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
        if (currentPlayerInTurn == Player.BLUE) {
            configureNewRound();
            currentPlayerInTurn = Player.RED;
        } else currentPlayerInTurn = Player.BLUE;
    }

    public void changeWorkForceFocusInCityAt( Position p, String balance ) {

    }

    public void changeProductionInCityAt( Position p, String unitType ) {
        getCityAt(p).changeProduction(unitType);
    }

    public void performUnitActionAt( Position p ) {
        unitActionStrategy.doUnitAction(this, p);
    }

    public void configureNewRound(){
        for (HashMap.Entry<Position, CityImpl> entry : positionCityMap.entrySet()){
            CityImpl city = entry.getValue();
            city.incrementProductionPoints();
            if (city.canProduceUnit()){
                Position firstEmptyTile = checkAdjacentTilesForUnit(entry.getKey());
                if (firstEmptyTile != null) {
                    UnitImpl unitProduced = new UnitImpl(city.getOwner(), city.getUnitInProduction());
                    positionUnitMap.put(firstEmptyTile, unitProduced);
                    city.endProduction();
                } /* else alert player that there is no room for new units */
            }
        }

        worldAge = worldAgingStrategy.calcWorldAge(worldAge);
        roundCount = roundCount + 1;
    }

    public int getRoundCount(){
        return roundCount;
    }

    public Map<Position, CityImpl> getCityMap(){
        return positionCityMap;
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
            if (!positionUnitMap.containsKey(pos)) return pos;
        }
        return null;
    }
}
