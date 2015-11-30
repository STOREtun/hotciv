package hotciv.variants.EpsilonCiv;

import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.GameImpl;

import java.util.HashMap;

/**
 * Created by asger on 25/11/15.
 */
public class EpsilonWinnerStrategy implements WinnerStrategy {

    static private HashMap<Player, Integer> playerWinCount = new HashMap<>();

    @Override
    public Player calcWinner(GameImpl game) {
        if (playerWinCount.containsKey(Player.BLUE) && playerWinCount.get(Player.BLUE) >= 3){
          return Player.BLUE;
        }else if (playerWinCount.containsKey(Player.RED) && playerWinCount.get(Player.RED) >= 3){
            return Player.RED;
        }

        return null;
    }

    @Override
    public void updateWinCount(Player player) {
        if (playerWinCount.containsKey(player)){
            int currentWins = playerWinCount.get(player);
            playerWinCount.replace(player, currentWins + 1);
        } else playerWinCount.put(player, 1);
    }

    @Override
    public void incrementRoundCounter() {
        // not used
    }
}
