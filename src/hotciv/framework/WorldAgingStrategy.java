package hotciv.framework;

/**
 * Created by asger on 18/11/15.
 *
 * This is a strategy for the world aging.
 *
 */
public interface WorldAgingStrategy {

    /** Calculate the world age.
        @param the current world age
        @return a new age
     */

    public int calcWorldAge(int currentAge);
}
