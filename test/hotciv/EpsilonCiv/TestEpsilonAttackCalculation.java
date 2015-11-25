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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;


/**
 * Created by asger on 25/11/15.
 */
public class TestEpsilonAttackCalculation {

    UnitImpl redArcher;
    UnitImpl blueSettler_01;
    UnitImpl blueSettler_02;

    GameImpl game;

    @Before
    public void setup() {
        game = new GameImpl(new EpsilonFactory(new FakeDie()));

        redArcher = new UnitImpl(Player.RED, GameConstants.ARCHER);
        blueSettler_01 = new UnitImpl(Player.BLUE, GameConstants.SETTLER);
        blueSettler_02 = new UnitImpl(Player.BLUE, GameConstants.SETTLER);

        game.positionUnitMap.put(new Position(10,10), redArcher);
        game.positionUnitMap.put(new Position(11,10), blueSettler_01);
        game.positionUnitMap.put(new Position(10,11), blueSettler_02);
    }

    /* We need test stubs for this method */

    @Test
    public void shouldLetArcherWinOverSettlerWithEpsilonAttackAlgorithm() {
        boolean didMove = game.moveUnit(new Position(10,10), new Position(11,10));
        assertNull(game.getUnitAt(new Position(10, 10)));
        assertThat("This is now an archer", game.getUnitAt(new Position(11,10)).getTypeString(), is(GameConstants.ARCHER));
    }
}
