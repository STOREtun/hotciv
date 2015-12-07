package hotciv.ThetaCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.variants.AlphaCiv.AlphaFactory;
import hotciv.variants.ThetaCiv.ThetaCivGameConstant;
import hotciv.variants.ThetaCiv.ThetaFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by sditlev on 02/12/15.
 */
public class TestChariot {
    GameImpl game;

    @Before
    public void setup(){
        game = new GameImpl(new ThetaFactory());
        CityImpl city = game.getCityAt(new Position(4, 1));
        city.changeProduction(ThetaCivGameConstant.CHARIOT);
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
    }

    @Test
    public void shouldProduceChariot() {
        assertThat("Should have produced new unit", game.getUnitAt(new Position(4,1)).getTypeString(), is(ThetaCivGameConstant.CHARIOT));
    }

    @Test
    public void shouldLetChariotDoFortifyAction() {
        int denfense = game.getUnitAt(new Position(4,1)).getDefensiveStrength();
        assertThat("defense is 1 ", denfense, is(1));
        game.getUnitAt(new Position(4,1)).fortifyAction();
        denfense = game.getUnitAt(new Position(4,1)).getDefensiveStrength();
        assertThat("defense is 2 ", denfense, is(2));
    }
}
