package hotciv.framework;

import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by asger on 25/11/15.
 */
public interface AttackStrategy {
    boolean attackSuccessful(Position from, Position to, GameImpl game);
}
