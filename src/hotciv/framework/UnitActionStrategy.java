package hotciv.framework;

import hotciv.standard.GameImpl;

/**
 * Created by sditlev on 23/11/15.
 */
public interface UnitActionStrategy {
    public void doUnitAction(GameImpl game, Position p);
}
