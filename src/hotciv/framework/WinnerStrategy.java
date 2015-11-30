package hotciv.framework;

import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;

import java.util.ArrayList;

/**
 * Created by sditlev on 23/11/15.
 */
public interface WinnerStrategy {

    /** Calculate the world winner.
     @param all cities in list
     @return winner or null if no one have won the game yet
     */
    Player calcWinner(GameImpl game);

    /** Update the winner count
     * @param the winning attacking player
     * @param current worldAge
     */
    void updateWinCount(Player player, int roundCount);
}
