package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

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
public class TestAlphaCiv {
    private Game game;
    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl();
    }

    @Test
    public void shouldHaveRedCityAt1_1() {
        City c = game.getCityAt(new Position(1,1));
        assertThat("There should be a city at (1,1)",
                   c, is(notNullValue()));

        Player p = c.getOwner();
        assertThat("The city should be owned by RED player",
                   p, is(Player.RED));
    }

    @Test
    public void shouldHaveOceanAt0_1() {
        Tile tile = game.getTileAt(new Position(0,1));
        assertThat("There should be ocean at (0,1)", tile.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldHaveHillsAt1_0() {
        Tile tile = game.getTileAt(new Position(1,0));
        assertThat("There should be hills at (1,0)", tile.getTypeString(), is(GameConstants.HILLS));
    }

    @Test
    public void shouldHaveMountainAt2_2() {
        Tile tile = game.getTileAt(new Position(2,2));
        assertThat("There should be mountain at (2,2)", tile.getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void shouldLetRedWinIn300BC() {
        //Emulate 70 rounds
        for (int i = 0; i < 10*7; i++) {
            game.endOfTurn();
            game.endOfTurn();
        }
        assertThat("Age should be 3000", game.getAge(), is(3000));
        assertThat("red wins", game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldAlwaysHaveCityPopulationOf1() {
        assertThat("City population should always be one", game.getCityAt(new Position(1,1)).getSize(), is(1));
    }

    @Test
    public void shouldSwitchTurnFromRedToBlue() {
        assertThat("It is red's turn first", game.getPlayerInTurn(), is(Player.RED));
        game.endOfTurn();
        assertThat("It is blue's turn after reds end of turn", game.getPlayerInTurn(), is(Player.BLUE));
    }

    @Test
    public void shouldProduceSixProductionAfterIncrement() {
        CityImpl city1 = new CityImpl(Player.RED);
        assertThat("City production should be 0 at game start", city1.getProductionPoints(), is(0));
        city1.incrementProductionPoints();
        assertThat("City should produce six production after round end",city1.getProductionPoints(), is(6));
    }

    @Test
    public void shouldIncrementWorldAgeAfterEachRound() {
        int age = game.getAge();
        game.endOfTurn();
        game.endOfTurn();
        assertThat("World age is 3100", game.getAge(), is(age+100));
    }

    @Test
    public void shouldHavePlainsEverywhereBut2_2And1_0And0_1() {
        for (int row = GameConstants.WORLDSIZE-1; row >= 0; row--){
            for (int column = GameConstants.WORLDSIZE-1; column >= 0; column--) {
                if (!(row == 1 && column == 0 || row == 2 && column == 2 || row == 0 && column == 1)){
                    assertThat(" There should be plains at (" + row + "," + column + ")", game.getTileAt(new Position(row, column)).getTypeString(), is(GameConstants.PLAINS));
                }
            }
        }

    }
}
