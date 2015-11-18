package hotciv.betaCiv;

import hotciv.framework.Game;
import hotciv.standard.GameImpl;
import hotciv.variance.WorldAgingBetaCivStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by asger on 18/11/15.
 */
public class TestBetaWorldAging {

    private Game game;
    private WorldAgingBetaCivStrategy agingBetaCivStrategy;

    @Before
    public void setupGame(){
        game = new GameImpl(new WorldAgingBetaCivStrategy());
        agingBetaCivStrategy = new WorldAgingBetaCivStrategy();
    }

    @Test
    public void shouldAdd100YearsToWorldAgeBetween4000BCAnd100BC() {
        int worldAge = game.getAge();
        game.endOfTurn();
        game.endOfTurn(); // end turn twice to start a new round (both players have had their turn)
        assertThat("World age is incremented with a 100 years", game.getAge(), is(worldAge + 100));
    }

    @Test
    public void shouldIncrementWithinSequenceInBirthOfChrist() {
        assertThat("Sequence is -100 to -1", agingBetaCivStrategy.calcWorldAge(-100), is(-1));
        assertThat("Sequence is -100 to -1", agingBetaCivStrategy.calcWorldAge(-1), is(1));
        assertThat("Sequence is -100 to -1", agingBetaCivStrategy.calcWorldAge(1), is(50));
    }

    @Test
    public void shouldIncrement50YearsBetween50ADAnd1750AD() {
        assertThat("World age should increment with 50 years", agingBetaCivStrategy.calcWorldAge(50), is(100));
    }

    @Test
    public void shouldIncrementWith25Between1750ADAnd1900AD() {
        assertThat("Should increment with 25 years", agingBetaCivStrategy.calcWorldAge(1750), is(1775));
    }

    @Test
    public void shouldIncrement5YearsBetween1900ADAnd1970AD() {
        assertThat("Should increment with 5 years", agingBetaCivStrategy.calcWorldAge(1900), is(1905));
    }

    @Test
    public void shouldInrementWith1YearAfter1970AD() {
        assertThat("Should increment with 1 year", agingBetaCivStrategy.calcWorldAge(1970), is(1971));
    }
}
