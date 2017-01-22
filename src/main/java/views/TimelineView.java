package views;

import controllers.GameController;
import factory.GameFactory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Game;
import model.Timeline;
import model.actions.Tweet;
import model.language.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

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
    private ListView<Tweet> tweetsView;
    final private ObservableList<Tweet> tweets;


    public TimelineView(Stage stage, GameController controller){

        this.gameCtrl = controller;
        this.stage = stage;


        view = (StackPane) stage.getScene().getRoot().getChildrenUnmodifiable().get(0);
        view.getStylesheets().add("Timeline.css");
        view.setBackground(new Background(new BackgroundFill(Color.web("#E0E0E0"), CornerRadii.EMPTY, Insets.EMPTY)));
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

        String langName = this.gameCtrl.getGame().getLanguage().getName();
        Tweet welcome = new Tweet(new Date(), "Un nouveau langage vient d'apparaitre : " + langName);
        Text tweetsLabel = new Text("Tweets sur votre langage :");
        tweetsLabel.getStyleClass().add("title");
        grid.add(tweetsLabel,0,3);
        tweets = FXCollections.observableArrayList(welcome);
        tweetsView = new ListView<Tweet>(tweets);
        tweets.addListener((ListChangeListener<Tweet>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    System.out.println("++Ajout");
                    change.getAddedSubList().forEach(value -> System.out.printf("\"%s\" a été ajouté.", value).println());
                }
            }
        });
        grid.add(tweetsView,0,4);
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
        if(turnNumber%10 == 0){
            Tweet tenTurns = new Tweet(new Date(),"Le langage a passé 10 tours de plus !");
            this.tweets.add(tenTurns);
        }
    }

    public GridPane createGrid(){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(10));
        return grid;
    }

    public HBox createButton(String label)    {
        Button btn = new Button(label);
        btn.getStyleClass().add("button");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


            }
        });
        return hbBtn;
    }

    public void tweet(String nom) {
        // NOT IMPLEMENTED
    }
}
