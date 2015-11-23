package hotciv.variants;

import hotciv.framework.WorldAgingStrategy;

/**
 * Created by sditlev on 23/11/15.
 */
public class AlphaAgingStrategy implements WorldAgingStrategy{
    @Override
    public int calcWorldAge(int currentWorldAge) {
        return currentWorldAge+100;
    }
}
