package hotciv.betaCiv;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.variants.BetaManager;
import hotciv.variants.SimpleWorldWorldStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import java.util.ArrayList;

/**
 * Created by sditlev on 20/11/15.
 */
public class TestBetaCivWinnerCalculation {
    Game game;

    @Before
    public void setupGame() {
        game = new GameImpl(new BetaManager());
    }

    @Test
    public void shouldLetRedWinAfterBlueCityTakeOver() {
        assertThat("Owner is initial blue", game.getCityAt(new Position(4,1)).getOwner(), is(Player.BLUE));
        assertTrue(game.moveUnit(new Position(2, 0), new Position(3, 1)));
        game.endOfTurn();
        game.endOfTurn();
        assertTrue(game.moveUnit(new Position(3, 1), new Position(4, 1)));
        assertThat("Owner should be Red", game.getCityAt(new Position(4,1)).getOwner(), is(Player.RED));
        assertThat("Winner is Red", game.getWinner(), is(Player.RED));
    }
}
