package views;

import controllers.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Game;
import model.Timeline;

/**
 * Created by nathan on 18/01/17.
 */
public class TimelineView {
    Stage stage;
    GameController gameCtrl;
    StackPane view;
    GridPane grid = createGrid();
    private Text starsCounter;
    private Text globalInfection;
    private Text turnCounter;
    private Integer turnNumber = 0;
    public TimelineView(Stage stage, GameController controller){

        this.gameCtrl = controller;
        this.stage = stage;


        view = (StackPane) stage.getScene().getRoot().getChildrenUnmodifiable().get(0);
        view.getStylesheets().add("Timeline.css");
        view.setBackground(new Background(new BackgroundFill(Color.web("#3f3f4f"), CornerRadii.EMPTY, Insets.EMPTY)));
        view.getChildren().add(grid);
        view.getStyleClass().add("timeline");

        Text turnCounterLabel = new Text("Tour : ");
        turnCounterLabel.getStyleClass().add("title");
        grid.add(turnCounterLabel, 0, 0);
        turnCounter = new Text(turnNumber.toString());
        grid.add(turnCounter, 1,0);

        Text starsCounterLabel = new Text("Stars : ");
        starsCounterLabel.getStyleClass().add("title");
        grid.add(starsCounterLabel, 0, 2);
        starsCounter = new Text(gameCtrl.getGame().getStars().toString());
        grid.add(starsCounter, 1,2);

        Text globalInfectionLabel = new Text("Population infectée :");
        globalInfectionLabel.getStyleClass().add("title");
        grid.add(globalInfectionLabel,3,2);
        globalInfection = new Text("10 %");
        grid.add(globalInfection,4,2);

        stage.getScene().getStylesheets().add(
                getClass().getResource("../Timeline.css").toExternalForm()
        );
    }

    /**
     * started by the a Turn, it updates the timeline component with new data
     */
    public synchronized void notifyTurn(){
        turnNumber += 1;
        turnCounter.setText(turnNumber.toString());
        starsCounter.setText(gameCtrl.getGame().getStars().toString());
    }
    public GridPane createGrid(){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(10));
        return grid;
    }
}
