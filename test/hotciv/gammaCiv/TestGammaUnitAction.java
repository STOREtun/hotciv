package hotciv.gammaCiv;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;
import hotciv.variants.GammaCiv.GammaFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by sditlev on 21/11/15.
 */
public class TestGammaUnitAction {

    private Game game;

    @Before
    public void setupGame(){
        game = new GameImpl(new GammaFactory());
    }

    @Test
    public void shouldLetArcherPerformFortifyAction() {
        UnitImpl archer = (UnitImpl) game.getUnitAt(new Position(2, 0));
        int defensiveStrength = archer.getDefensiveStrength();
        assertThat("No defensive strength to start with", defensiveStrength, is(3));
        archer.fortifyAction();
        assertThat("DefensiveStrength is double", archer.getDefensiveStrength(), is(6));
    }

    @Test
    public void shouldLetSettlerCreateCityOnAction() {
        Position settlerPos = new Position(4,3);
        UnitImpl settler = (UnitImpl) game.getUnitAt(settlerPos);
        assertThat("No city at settler pos", game.getCityAt(settlerPos), is(nullValue()));
        game.performUnitActionAt(settlerPos);
        assertThat("City at location", game.getCityAt(settlerPos), is(notNullValue()));
    }
}
