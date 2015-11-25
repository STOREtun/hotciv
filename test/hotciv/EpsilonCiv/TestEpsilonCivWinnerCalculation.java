package hotciv.EpsilonCiv;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.UnitImpl;
import hotciv.variants.EpsilonCiv.EpsilonFactory;
import hotciv.variants.FakeDie;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by asger on 25/11/15.
 */
public class TestEpsilonCivWinnerCalculation {

    UnitImpl blueArcher;
    UnitImpl redSettler_01;
    UnitImpl redSettler_02;
    UnitImpl redSettler_03;

    EpsilonFactory factory;

    HashMap positionUnitMap;

    @Before
    public void setup(){
        factory = new EpsilonFactory(new FakeDie());

        positionUnitMap = new HashMap<Position, UnitImpl>();

        blueArcher = new UnitImpl(Player.BLUE, GameConstants.ARCHER);
        redSettler_01 = new UnitImpl(Player.RED, GameConstants.SETTLER);
        redSettler_02 = new UnitImpl(Player.RED, GameConstants.SETTLER);
        redSettler_03 = new UnitImpl(Player.RED, GameConstants.SETTLER);

        positionUnitMap.put(new Position(10,10), blueArcher);
        positionUnitMap.put(new Position(11,10), redSettler_01);
        positionUnitMap.put(new Position(10,11), redSettler_02);
        positionUnitMap.put(new Position(11,11), redSettler_03);
    }

    @Test
    public void shouldCalculateWinnerAccordingToWonAttacks() {

    }
}
