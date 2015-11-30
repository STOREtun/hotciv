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
    static private boolean twentyRoundsHasPassed;
    static private int roundCounter;

    EpsilonWinnerStrategy epsilonWinnerStrategy;
    BetaWinnerStrategy betaWinnerStrategy;

    public ZetaWinnerStrategy() {
        this.twentyRoundsHasPassed = false;
        roundCounter = 0;

        epsilonWinnerStrategy = new EpsilonWinnerStrategy();
        betaWinnerStrategy = new BetaWinnerStrategy();
    }

    @Override
    public Player calcWinner(GameImpl game) {
        System.out.println("ZetaWinnerStrategy, twentyRoundsHasPassed: " + twentyRoundsHasPassed);
        checkIfTwentyRoundsHavePassed();

        // if more than 20 rounds have passed.
        // If we wish to have a variable starting world age this should be set dynamically
        if(twentyRoundsHasPassed){
            System.out.println("ZetaWinnerStrategy, returning epsilon strategy");
            return epsilonWinnerStrategy.calcWinner(game);
        }else{
            System.out.println("ZetaWinnerStrategy, returning beta strategy");
            return betaWinnerStrategy.calcWinner(game);
        }
    }

    @Override
    public void updateWinCount(Player player) {
        checkIfTwentyRoundsHavePassed();

        if(twentyRoundsHasPassed) epsilonWinnerStrategy.updateWinCount(player);
    }

    @Override
    public void incrementRoundCounter() {
        roundCounter = roundCounter + 1;
    }

    public void checkIfTwentyRoundsHavePassed(){
        if(roundCounter >= 20) twentyRoundsHasPassed = true;
    }
}
