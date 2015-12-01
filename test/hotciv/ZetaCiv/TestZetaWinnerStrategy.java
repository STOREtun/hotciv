package hotciv.ZetaCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.ZetaCiv.ZetaFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by asger on 30/11/15.
 */
public class TestZetaWinnerStrategy {

    private GameImpl game;

    @Before
    public void setup(){
        game = new GameImpl(new ZetaFactory());
    }

    @Test
    public void shouldUseEpsilonStrategyIfMoreThan20RoundsHasPassed() {
        UnitImpl redArcher_00 = new UnitImpl(Player.RED, GameConstants.ARCHER);
        UnitImpl blueLegion_00 = new UnitImpl(Player.BLUE, GameConstants.LEGION);
        game.positionUnitMap.put(new Position(16, 15), redArcher_00);
        game.positionUnitMap.put(new Position(16, 16), blueLegion_00);
        game.moveUnit(new Position(16,15), new Position(16, 16));

        for(int x = 0; x <= 19; x++) game.configureNewRound(); // passing 20 rounds

        assertThat("World age is now -2000. Meaning 20 rounds passed in alphaAgingStrategy terms",
                game.getAge(), is(-2000));

        UnitImpl redArcher = new UnitImpl(Player.RED, GameConstants.ARCHER);
        UnitImpl redArcher_02 = new UnitImpl(Player.RED, GameConstants.ARCHER);
        UnitImpl redArcher_03 = new UnitImpl(Player.RED, GameConstants.ARCHER);
        game.positionUnitMap.put(new Position(10, 10), redArcher);
        game.positionUnitMap.put(new Position(10, 11), redArcher_02);
        game.positionUnitMap.put(new Position(10, 12), redArcher_03);

        UnitImpl blueLegion = new UnitImpl(Player.BLUE, GameConstants.LEGION);
        game.positionUnitMap.put(new Position(10, 9), blueLegion);

        game.endOfTurn(); // pass turn so blue can attack

        assertTrue(game.moveUnit(new Position(10, 9), new Position(10, 10)));
        game.endOfTurn();
        game.endOfTurn();
        assertTrue(game.moveUnit(new Position(10, 10), new Position(10, 11)));
        game.endOfTurn();
        game.endOfTurn();
        assertTrue(game.moveUnit(new Position(10, 11), new Position(10, 12)));

        assertThat("Winner is blue", game.getWinner(), is(Player.BLUE));
    }

    @Test
    public void shouldUseBetaStrategyIfLessThan20RoundsHasPassed() {
        assertNull(game.getWinner()); // no winner yet

        assertThat("Owner is initial blue", game.getCityAt(new Position(4,1)).getOwner(), is(Player.BLUE));
        assertTrue(game.moveUnit(new Position(2, 0), new Position(3, 1)));

        game.endOfTurn();
        game.endOfTurn();

        assertTrue(game.moveUnit(new Position(3, 1), new Position(4, 1)));
        assertThat("Owner should be Red", game.getCityAt(new Position(4,1)).getOwner(), is(Player.RED));
        assertThat("Winner is Red", game.getWinner(), is(Player.RED));

    }
}
