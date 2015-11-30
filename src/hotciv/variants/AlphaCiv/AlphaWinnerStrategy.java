package hotciv.variants.AlphaCiv;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.GameImpl;

import java.util.HashMap;

/**
 * Created by sditlev on 23/11/15.
 */
public class AlphaWinnerStrategy implements WinnerStrategy{

    static private HashMap<Player, Integer> playerWinCount = new HashMap<>();

    @Override
    public Player calcWinner(GameImpl game) {
        if (game.getAge() < -3000){
            return null;
        }else return Player.RED;
    }

    @Override
    public void upDateWinCount(Player player) {
        if (playerWinCount.containsKey(player)){
            int currentWins = playerWinCount.get(player);
            playerWinCount.replace(player, currentWins+1);
        } else playerWinCount.put(player, 1);
    }

}
