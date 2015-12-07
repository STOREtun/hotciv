package hotciv.SemiCiv;

import hotciv.framework.Factory;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.Utility;
import hotciv.variants.SemiCiv.SemiCivFactory;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by asger on 03/12/15.
 *
 */
public class TestSemiCiv {

    private GameImpl game;

    @Before
    public void setup(){
        game = new GameImpl(new SemiCivFactory());
    }

    @Test
    public void shouldIncreasePopulationLikeEtaCiv() {

    }

    @Test
    public void shouldManipulateWorkFocusLikeEtaCiv() {

    }

    @Test
    public void shouldCollectFood() {
        System.out.println(getSurroundingTiles());

        CityImpl city = game.getCityAt(new Position(4, 1));
        assertThat("Population should be 1", city.getSize(), is(1));
        game.endOfTurn();
        game.endOfTurn();
        assertThat("Population should have incremented", city.getSize(), is(2));
    }

    public List<String> getSurroundingTiles(){
        List<String> tileList = new ArrayList<>();
        Iterator<Position> list = Utility.get8NeighborhoodIterator(new Position(4,1));
        while(list.hasNext()){
            String typeString = game.getTileAt(list.next()).getTypeString();
            tileList.add(typeString);
        }

        return tileList;
    }

}
