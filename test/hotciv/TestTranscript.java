package hotciv;

import hotciv.framework.Factory;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.TranscriptDecorator;
import hotciv.standard.GameImpl;
import hotciv.variants.AlphaCiv.AlphaFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by asger on 15/12/15.
 */
public class TestTranscript {

    private TranscriptDecorator transcriber;

    @Before
    public void setup(){
        Factory factory = new AlphaFactory();
        GameImpl game = new GameImpl(factory);
        transcriber = new TranscriptDecorator(game);
    }

    @Test
    public void shouldTranscribe() {
        assertTrue(transcriber.moveUnit(new Position(2,0), new Position(2,1)));
        transcriber.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
        transcriber.endTurn();
        transcriber.endTurn();
        transcriber.toggleTranscript();
        assertTrue(transcriber.moveUnit(new Position(2, 1), new Position(2,0)));
        transcriber.printTranscription();
    }
}
