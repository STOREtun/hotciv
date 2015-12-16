package hotciv.framework;

import hotciv.standard.GameImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asger on 15/12/15.
 */
public class TranscriptDecorator {

    private GameImpl game;
    private boolean shouldTranscribe;
    private List<String> transcription;

    public TranscriptDecorator(GameImpl game) {
        this.game = game;
        shouldTranscribe = true;
        transcription = new ArrayList<String>();
    }

    // ===== GameImpl Methods ===== //

    public boolean moveUnit(Position from, Position to){
        if(shouldTranscribe){
            Player playerInTurn = game.getPlayerInTurn();
            Unit movedUnit = game.getUnitAt(from);
            String transcription = "Player " + playerInTurn + " moved " + movedUnit.getTypeString() + " from " + from + " to " + to;
            transcribe(transcription);
        }
        return game.moveUnit(from, to);
    }

    public void changeProductionInCityAt(Position p, String unitType){
        if(shouldTranscribe){
            Player playerInTurn = game.getPlayerInTurn();
            String transcription = "Player " + playerInTurn + " changed prodcuction in city at " + p + " to produce " + unitType;
            transcribe(transcription);
        }
        game.changeProductionInCityAt(p, unitType);
    }

    public void endTurn(){
        if(shouldTranscribe){
            Player playerInTurn = game.getPlayerInTurn();
            String transcription = "Player " + playerInTurn + " ends turn";
            transcribe(transcription);
        }
        game.endOfTurn();
    }

    public void toggleTranscript(){
        shouldTranscribe = !shouldTranscribe;
    }

    public void printTranscription(){
        for(int x = 0; x < transcription.size(); x++){
            System.out.println(transcription.get(x));
        }
    }

    private void transcribe(String event){
        transcription.add(event);
    }
}
