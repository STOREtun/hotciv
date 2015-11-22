package hotciv.betaCiv;

import hotciv.framework.CivType;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.variance.BetaCiv;
import hotciv.variance.SimpleWorldWorldStrategy;
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
        game = new GameImpl(new BetaCiv(), new SimpleWorldWorldStrategy());
    }

    @Test
    public void shouldletBlueWinWhenTakenOverRedCity() {
        CivType beta = new BetaCiv();
        ArrayList<CityImpl> testCities = new ArrayList<>();
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        assertThat("Should return blue as winner", beta.calcWinner(testCities), is(Player.BLUE));
    }

    @Test
    public void shouldReturnNullOnNoWinnerYet() {
        CivType beta = new BetaCiv();
        ArrayList<CityImpl> testCities = new ArrayList<>();
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.RED));
        assertNull(beta.calcWinner(testCities));
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
