package hotciv.variants.AlphaCiv;

import hotciv.framework.AttackInterface;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

/**
 * Created by asger on 25/11/15.
 */
public class AlphaAttackStrategy implements AttackInterface {

    @Override
    public boolean attackSuccessful(Position from, Position to, GameImpl game) {
        return true;
    }
}
