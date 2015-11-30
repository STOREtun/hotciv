package hotciv.variants.ZetaCiv;

import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.GameImpl;

import java.util.HashMap;

/**
 * Created by sditlev on 30/11/15.
 */
public class ZetaWinnerStrategy implements WinnerStrategy {
    private int roundCount = 0;
    static private HashMap<Player, Integer> playerWinCount = new HashMap<>();

    @Override
    public Player calcWinner(GameImpl game) {
        return null;
    }

    @Override
    public void upDateWinCount(Player player) {
        if (playerWinCount.containsKey(player)){
            int currentWins = playerWinCount.get(player);
            playerWinCount.replace(player, currentWins+1);
        } else playerWinCount.put(player, 1);
    }
}
