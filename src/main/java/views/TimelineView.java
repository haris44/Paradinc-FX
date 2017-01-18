package views;

import controllers.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

    public TimelineView(Stage stage, GameController controller){
        this.gameCtrl = controller;
        this.stage = stage;
        view = (StackPane) stage.getScene().getRoot().getChildrenUnmodifiable().get(0);
        view.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        view.getChildren().add(grid);


        Text starsCounterLabel = new Text("Stars : ");
        starsCounterLabel.getStyleClass().add("title");
        grid.add(starsCounterLabel, 0, 0);

        starsCounter = new Text(gameCtrl.getGame().getStars().toString());
        grid.add(starsCounter, 0,1);
    }

    /**
     * started by the a Turn, it updates the timeline component with new data
     */
    public void notifyTurn(){
        starsCounter.setText(gameCtrl.getGame().getStars().toString());
    }
    public GridPane createGrid(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(10));
        return grid;
    }
}
