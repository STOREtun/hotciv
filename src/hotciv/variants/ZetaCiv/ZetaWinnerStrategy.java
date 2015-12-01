package hotciv.variants.ZetaCiv;

import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.GameImpl;
import hotciv.variants.BetaCiv.BetaWinnerStrategy;
import hotciv.variants.EpsilonCiv.EpsilonWinnerStrategy;

/**
 * Created by sditlev on 30/11/15.
 */
public class ZetaWinnerStrategy implements WinnerStrategy {
    EpsilonWinnerStrategy epsilonWinnerStrategy;
    BetaWinnerStrategy betaWinnerStrategy;

    public ZetaWinnerStrategy() {
        epsilonWinnerStrategy = new EpsilonWinnerStrategy();
        betaWinnerStrategy = new BetaWinnerStrategy();
    }

    @Override
    public Player calcWinner(GameImpl game) {
        if(game.getRoundCount() >= 20){
            return epsilonWinnerStrategy.calcWinner(game);
        }else{
            return betaWinnerStrategy.calcWinner(game);
        }
    }

    @Override
    public void updateWinCount(Player player, int roundCounter) {
        if(roundCounter >= 20) epsilonWinnerStrategy.updateWinCount(player, roundCounter);
    }
}
