package controllers;

import javafx.stage.Stage;
import model.Game;
import utils.Turn;
import views.Map;

/**
 * Created by Alexandre on 15/01/2017.
 */
public class GameController {

    Game game;
    Stage stage;

    public GameController(Stage stage, Game game){
        this.stage = stage;
        this.game = game;
    }

    public void startGame(){
        this.start();
        new Map(stage);
    }

    private void start() {
        Integer TURN_LENGTH = 10;
        Turn t = new Turn(TURN_LENGTH);
        t.run();
    }

}
