package hotciv.variants.ZetaCiv;

import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.GameImpl;
import hotciv.variants.BetaCiv.BetaWinnerStrategy;
import hotciv.variants.EpsilonCiv.EpsilonWinnerStrategy;

import java.util.HashMap;

/**
 * Created by sditlev on 30/11/15.
 */
public class ZetaWinnerStrategy implements WinnerStrategy {
    private boolean twentyRoundsHavePassed = false;

    static private HashMap<Player, Integer> playerWinCount = new HashMap<>();

    EpsilonWinnerStrategy epsilonWinnerStrategy = new EpsilonWinnerStrategy();
    BetaWinnerStrategy betaWinnerStrategy = new BetaWinnerStrategy();

    @Override
    public Player calcWinner(GameImpl game) {
        if(game.getAge() <= -2000) twentyRoundsHavePassed = true;

        // if more than 20 rounds have passed.
        // If we wish to have a variable starting world age this should be set dynamically
        if(twentyRoundsHavePassed){
            return betaWinnerStrategy.calcWinner(game);
        }else{
            return epsilonWinnerStrategy.calcWinner(game);
        }
    }

    @Override
    public void updateWinCount(Player player) {
        if(twentyRoundsHavePassed) epsilonWinnerStrategy.updateWinCount(player);
    }
}
