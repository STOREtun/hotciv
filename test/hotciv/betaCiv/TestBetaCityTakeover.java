package hotciv.betaCiv;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.variance.WorldAgingBetaCivStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by sditlev on 18/11/15.
 */
public class TestBetaCityTakeover {
    Game game;

    @Before
    public void setupGame() {
        game = new GameImpl(new WorldAgingBetaCivStrategy());
    }

    @Test
    public void shouldLetCitiesChangeOwnerOnAttack() {
        assertThat("Owner is initial blue", game.getCityAt(new Position(4,1)).getOwner(), is(Player.BLUE));
        assertTrue(game.moveUnit(new Position(2, 0), new Position(3, 1)));
        endGameRound();
        assertTrue(game.moveUnit(new Position(3, 1), new Position(4, 1)));
        assertThat("Owner should be Red", game.getCityAt(new Position(4,1)).getOwner(), is(Player.RED));
    }

    private void endGameRound(){
        game.endOfTurn();
        game.endOfTurn();
    }
}


