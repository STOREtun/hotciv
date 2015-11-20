package hotciv.betaCiv;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.variance.WinnerBetaCivStrategy;
import hotciv.variance.WorldAgingBetaCivStrategy;
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
        game = new GameImpl(new WorldAgingBetaCivStrategy(), new WinnerBetaCivStrategy());
    }

    @Test
    public void shouldletBlueWinWhenTakenOverRedCity() {
        WinnerStrategy betaWinner = new WinnerBetaCivStrategy();
        ArrayList<CityImpl> testCities = new ArrayList<>();
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        assertThat("Should return blue as winner", betaWinner.calcWinner(testCities), is(Player.BLUE));
    }

    @Test
    public void shouldReturnNullOnNoWinnerYet() {
        WinnerStrategy betaWinner = new WinnerBetaCivStrategy();
        ArrayList<CityImpl> testCities = new ArrayList<>();
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.BLUE));
        testCities.add(new CityImpl(Player.RED));
        assertNull(betaWinner.calcWinner(testCities));
    }
}
