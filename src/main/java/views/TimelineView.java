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
import model.events.Conference;
import model.events.Event;
import model.events.ThrowableEvent;
import model.language.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
        Integer currentRow = 0;


        view = (StackPane) stage.getScene().getRoot().getChildrenUnmodifiable().get(0);
        view.getStylesheets().add("Timeline.css");
        view.setBackground(new Background(new BackgroundFill(Color.web("#E0E0E0"), CornerRadii.EMPTY, Insets.EMPTY)));
        view.getChildren().add(grid);
        view.getStyleClass().add("timeline");

        Text turnCounterLabel = new Text("Tour : ");
        turnCounterLabel.getStyleClass().add("title");
        grid.add(turnCounterLabel, 0, currentRow);
        turnCounter = new Text(turnNumber.toString());
        grid.add(turnCounter, 1,currentRow);

        currentRow +=1;

        Text starsCounterLabel = new Text("Stars : ");
        starsCounterLabel.getStyleClass().add("title");
        grid.add(starsCounterLabel, 0, currentRow);
        starsCounter = new Text(gameCtrl.getGame().getStars().toString());
        grid.add(starsCounter, 1,currentRow);

        currentRow +=1;

        Text globalInfectionLabel = new Text("Population infectée :");
        globalInfectionLabel.getStyleClass().add("title");
        grid.add(globalInfectionLabel,0,currentRow);
        globalInfection = new Text("10 %");
        grid.add(globalInfection,1,currentRow);

        currentRow+=1;
                /* we add to the grid all events that we can throw */
        Text throwableEventsLabel = new Text("Actions possibles");
        throwableEventsLabel.getStyleClass().add("title");
        grid.add(throwableEventsLabel,0,currentRow);
        for (Iterator<Button> I = getBuyableEventsButtons().iterator(); I.hasNext(); ) {
            Button btn = I.next();
            grid.add(btn,0,currentRow);
            currentRow +=1;
        }


        currentRow +=1;

        String langName = this.gameCtrl.getGame().getLanguage().getName();
        Tweet welcome = new Tweet(new Date(), "Un nouveau langage vient d'apparaitre : " + langName);
        Text tweetsLabel = new Text("Tweets sur votre langage :");
        tweetsLabel.getStyleClass().add("title");
        grid.add(tweetsLabel,0,currentRow);
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
        grid.add(tweetsView,0,currentRow++);
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

    public ArrayList<Button> getBuyableEventsButtons() {
        ArrayList buttons = new ArrayList<HBox>();
        ArrayList<Event> events = gameCtrl.getGame().getBuyableEvents();
        for (Iterator<Event> I = events.iterator(); I.hasNext(); ) {
            Event event = I.next();
           Button btn = createButton(event.getName());
           TimelineView self = this;
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    ThrowableEvent throwableEvent = event.getThrowable(gameCtrl.getGame());
                    throwableEvent.throwEvent(gameCtrl,self);
                }
            });
            buttons.add(btn);
        }
        return buttons;
    }


    public GridPane createGrid(){
        GridPane grid = new GridPane();
        // space between elements
        grid.setVgap(10);
        grid.setPadding(new Insets(5));
        return grid;
    }

    public Button createButton(String label)    {
        Button btn = new Button(label);
        btn.getStyleClass().add("button");

       /* btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


            }
        });*/
        return btn;
    }

    public void tweet(String nom) {
        // NOT IMPLEMENTED
    }
}
