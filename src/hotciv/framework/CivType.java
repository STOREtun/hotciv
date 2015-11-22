package hotciv.framework;

import hotciv.standard.CityImpl;

import java.util.ArrayList;

/**
 * Created by asger on 22/11/15.
 *
 * This interface is responsible for assigning a type to the civilization.
 * E.g the game can be implemented as either Alpha or Beta.
 *
 * The variability points in the game are winner and world aging
 *
 */
public interface CivType {
    /** Calculate the world age.
     @param the current world age
     @return a new age
     */
    public int calcWorldAge(int currentWorldAge);

    /** Calculate the world winner.
     @param all cities in list
     @return winner or null if no one have won the game yet
     */
    public Player calcWinner(ArrayList<CityImpl> cities);
}
