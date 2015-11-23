package hotciv.variants;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.GameImpl;

/**
 * Created by sditlev on 23/11/15.
 */
public class AlphaWinnerStrategy implements WinnerStrategy{

    @Override
    public Player calcWinner(GameImpl game) {
        if (game.getAge() < -3000){
            return null;
        }else return Player.RED;
    }

}
