package hotciv.variants.AlphaCiv;

import hotciv.framework.AttackStrategy;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

/**
 * Created by asger on 25/11/15.
 */
public class AlphaAttackStrategy implements AttackStrategy {

    @Override
    public boolean attackSuccessful(Position from, Position to, GameImpl game) {
        return true;
    }
}
