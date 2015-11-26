package hotciv.EpsilonCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.EpsilonCiv.EpsilonFactory;
import hotciv.variants.FakeDie;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by asger on 25/11/15.
 */
public class TestEpsilonCivWinnerCalculation {

    UnitImpl redArcher;
    UnitImpl blueSettler_01;
    UnitImpl blueSettler_02;
    UnitImpl blueSettler_03;

    GameImpl game;

    @Before
    public void setup(){
        game = new GameImpl(new EpsilonFactory(new FakeDie()));

        redArcher = new UnitImpl(Player.RED, GameConstants.ARCHER);
        blueSettler_01 = new UnitImpl(Player.BLUE, GameConstants.SETTLER);
        blueSettler_02 = new UnitImpl(Player.BLUE, GameConstants.SETTLER);
        blueSettler_03 = new UnitImpl(Player.BLUE, GameConstants.SETTLER);

        game.positionUnitMap.put(new Position(10,10), redArcher);
        game.positionUnitMap.put(new Position(11,10), blueSettler_01);
        game.positionUnitMap.put(new Position(10,11), blueSettler_02);
        game.positionUnitMap.put(new Position(10,12), blueSettler_03);
    }

    @Test
    public void shouldCalculateWinnerAccordingToWonAttacks() {
        // first attack results in redArcher taking over settler_01's tile
        assertTrue(game.moveUnit(new Position(10,10), new Position(11, 10)));
        assertThat("This unit is a red archer", game.getUnitAt(new Position(11, 10)).getOwner(), is(Player.RED));

        // second attack removes another settler
        assertTrue(game.moveUnit(new Position(11,10), new Position(10, 11)));
        assertThat("This unit is a red archer", game.getUnitAt(new Position(10, 11)).getOwner(), is(Player.RED));

        // third, and final attack removes the last settler and let red win the game
        assertTrue(game.moveUnit(new Position(10,11), new Position(10, 12)));
        assertThat("This unit is a red archer", game.getUnitAt(new Position(10, 12)).getOwner(), is(Player.RED));

        assertThat("Red has won the game after three successful attacks", game.getWinner(), is(Player.RED));
    }
}
