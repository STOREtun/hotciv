package hotciv.DeltaCiv;

import hotciv.framework.Game;
import hotciv.framework.Manager;
import hotciv.standard.GameImpl;
import hotciv.variants.BetaManager;
import hotciv.variants.DeltaManager;
import org.junit.Before;

/**
 * Created by sditlev on 23/11/15.
 */
public class TestDeltaCivWorldMap {

    private Game game;
    private Manager manager;

    @Before
    public void setupGame(){
        manager = new DeltaManager();
        game = new GameImpl(manager);
    }

    
}
