package controllers;

import javafx.stage.Stage;
import model.Game;
import model.language.Language;
import utils.Turn;
import views.Map;

/**
 * Created by Alexandre on 15/01/2017.
 */
public class GameController {

    Game game;
    Stage stage;
    public Map mapViews;

    public GameController(Stage stage, Game game){
        this.stage = stage;
        this.game = game;

    }

    public void startGame(){

        mapViews = new Map(stage, this);
        this.start();
    }

    public Language getLanguage(){
        return game.getLanguage();
    }

    private void start() {
        Integer TURN_LENGTH = 10;
        Turn t = new Turn(TURN_LENGTH);
        t.run(this);
    }

    public void turn(){

        System.out.print("tour here");
//        if(mapViews != null){
//            mapViews.notifyTour();
//        }
    }

}
