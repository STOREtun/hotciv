package hotciv.alphaCiv;
import hotciv.framework.*;

import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.variants.AlphaCiv.AlphaFactory;
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
    private GameImpl game;
    /** Fixture for alphaCiv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new AlphaFactory());
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
    public void shouldHaveOceanAt1_0() {
        Tile tile = game.getTileAt(new Position(1,0));
        assertThat("There should be ocean at (0,1)", tile.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldHaveHillsAt0_1() {
        Tile tile = game.getTileAt(new Position(0,1));
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
        assertThat("World age is +100 years", game.getAge(), is(age+100));
    }

    @Test
    public void shouldStartGameAtAge4000BC() {
        assertThat("age should be -4000 in start of game", game.getAge(), is(-4000));
    }

    @Test
    public void shouldHavePlainsEverywhereBut2_2And1_0And0_1() {
        assertThat("Should have plains at (5,5)", game.getTileAt(new Position(5, 5)).getTypeString(), is(GameConstants.PLAINS));
        assertThat("Should have plains at (10,3)", game.getTileAt(new Position(10,3)).getTypeString(), is(GameConstants.PLAINS));
    }

    @Test
    public void shouldHaveBlueCityAt4_1() {
        City city = game.getCityAt(new Position(4,1));
        assertThat("Should be a city", city, is(notNullValue()));
        Player owner = city.getOwner();
        assertThat("owner should be blue", owner, is(Player.BLUE));
    }

    @Test
    public void shouldHaveRedArcherAT2_0ATStart() {
        Unit unit = game.getUnitAt(new Position(2, 0));
        assertThat("Should have unit at (2,0)", unit, is(notNullValue()));
        Player owner = unit.getOwner();
        assertThat("Owner of unit at (2,0) is red", owner, is(Player.RED));
        assertThat("Type of unit should be archer", unit.getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldHaveBlueLegionAt3_2AtStart() {
        Unit unit = game.getUnitAt(new Position(3, 2));
        assertThat("Should have unit at (3,2)", unit, is(notNullValue()));
        Player owner = unit.getOwner();
        assertThat("Owner of unit at (3,2) is blue", owner, is(Player.BLUE));
        assertThat("Type of unit should be legion", unit.getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void shouldHaveRedSettlerAt4_3AtStart() {
        Unit unit = game.getUnitAt(new Position(4, 3));
        Player owner = unit.getOwner();
        assertThat("Owner of unit at (4,3) is blue", owner, is(Player.RED));
        assertThat("Type of unit should be legion", unit.getTypeString(), is(GameConstants.SETTLER));
    }

    @Test
    public void shouldMoveUnit() {
        assertTrue(game.moveUnit(new Position(2, 0), new Position(2, 1)));
    }

    @Test
    public void shouldNotBeAbleToMoveMoreThan1Tile() {
        assertFalse(game.moveUnit(new Position(2, 0), new Position(2, 3)));
        assertFalse(game.moveUnit(new Position(2,0), new Position(0,0)));
    }

    @Test
    public void shouldNotLetRedMoveBlueUnit() {
        assertFalse(game.moveUnit(new Position(3,2), new Position(3,3)));
    }

    @Test
    public void shouldNotLetBlueMoveRedUnit() {
        game.endOfTurn();
        assertFalse(game.moveUnit(new Position(2,0), new Position(2,1)));
    }

    @Test
    public void shouldNotBeAbleToMoveUnitOverMountains() {
        game.moveUnit(new Position(2,0), new Position(2,1));
        game.endOfTurn();
        game.endOfTurn();
        assertFalse(game.moveUnit(new Position(2,1), new Position(2,2)));
    }

    @Test
    public void shouldNotMoveRedUnitsOnTopOfEachOther() {
        game.moveUnit(new Position(2,0), new Position(2,1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(2,1), new Position(3,2));
        game.endOfTurn();
        game.endOfTurn();
        assertFalse(game.moveUnit(new Position(3, 2), new Position(4, 3)));
    }

    @Test
    public void shouldLetRedDestroyBlueUnit() {
        assertTrue(game.moveUnit(new Position(2,0), new Position(2,1)));
        game.endOfTurn();
        game.endOfTurn();
        assertTrue(game.moveUnit(new Position(2,1), new Position(3,2)));
        assertThat("Red should own tile", game.getUnitAt(new Position(3,2)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldLetBlueDestroyRedUnit() {
        game.endOfTurn(); // blue turn
        assertTrue(game.moveUnit(new Position(3, 2), new Position(2, 1)));
        game.endOfTurn();
        game.endOfTurn(); // blue turn again
        assertTrue(game.moveUnit(new Position(2, 1), new Position(2, 0)));
        assertThat("Blue should own the tile", game.getUnitAt(new Position(2,0)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldSetAndGetProductionFromCity() {
        CityImpl city = game.getCityAt(new Position(4,1));
        game.changeProductionInCityAt(new Position(4, 1), GameConstants.ARCHER);
        assertThat("Should be producing archer", city.getProduction(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldSpawnUnitsWhenAccumulatedEnoughProductionPoints() {
        assertNull(game.getUnitAt(new Position(4, 1)));
        assertNull(game.getUnitAt(new Position(1, 1)));

        game.changeProductionInCityAt(new Position(4, 1), GameConstants.ARCHER);
        game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);

        game.endOfTurn();
        game.endOfTurn(); // ending round

        // the unit is not created yet due to low production points
        assertNull(game.getUnitAt(new Position(4, 1)));

        game.endOfTurn();
        game.endOfTurn(); // ending round

        assertNotNull(game.getUnitAt(new Position(4, 1)));
        assertNotNull(game.getUnitAt(new Position(1, 1)));

        // both cities have spent all their production points
        assertThat("production points is 0 after new unit is created",
                game.getCityAt(new Position(4,1)).getProductionPoints(), is(0));
        assertThat("production points is 0 after new unit is created",
                game.getCityAt(new Position(1,1)).getProductionPoints(), is(0));
    }

    @Test
    public void shouldNotProduceUnitsWhenNoTilesAreFree() {
        produce9Units();
        assertNotNull(game.getUnitAt(new Position(0,2))); // the last empty tile

        game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn(); // accumulating 12 production points by ending round twice

        assertThat("The production points are >= 12",
                game.getCityAt(new Position(1, 1)).getProductionPoints(),
                is(12));
    }

    public void produce9Units(){
        for (int x = 0; x <= 10; x++){
            game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
            for (int i = 0; i <= 2; i++)
                game.endOfTurn();
        }
    }
}
