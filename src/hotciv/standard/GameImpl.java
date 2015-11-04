package hotciv.standard;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.List;

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
    Player currentInTurnPlayer;

    //Create city instance
    public CityImpl city1 = new CityImpl(Player.RED);

    //Create tile instance
    public TileImpl tile1 = new TileImpl("ocean");

    public Tile getTileAt( Position p ) {
        return tile1;
    }

    public Unit getUnitAt( Position p ) {
        return null;
    }

    public City getCityAt( Position p ) {
        return city1;
    }

    public Player getPlayerInTurn() {
        if(currentInTurnPlayer == Player.BLUE || currentInTurnPlayer == null){
            currentInTurnPlayer = Player.RED;
        }else currentInTurnPlayer = Player.BLUE;

        return currentInTurnPlayer;
    }

    public Player getWinner() {
        return Player.RED;
    }

    public int getAge() {
        return 3000;
    }

    public boolean moveUnit( Position from, Position to ) {
        return false;
    }
    public void endOfTurn() {

    }

    public void changeWorkForceFocusInCityAt( Position p, String balance ) {

    }

    public void changeProductionInCityAt( Position p, String unitType ) {

    }

    public void performUnitActionAt( Position p ) {

    }
}
