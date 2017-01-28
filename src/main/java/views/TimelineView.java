package views;

import controllers.GameController;
import factory.GameFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Game;
import model.Timeline;
import model.actions.Tweet;
import model.events.Conference;
import model.events.Event;
import model.events.ThrowableEvent;
import model.language.*;
import utils.Triple;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Observable;

import static com.sun.org.apache.xml.internal.serializer.utils.Utils.messages;

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
    private Slider robustness;
    private Slider facility;
    private ListView<Tweet> tweetsView;
    final private ObservableList<Tweet> tweets;
    Text modalStarsCounter = new Text();
    Label modalStarsLabel = new Label("Stars disponibles");

    public TimelineView(Stage stage, GameController controller){

        this.gameCtrl = controller;
        this.stage = stage;
        Integer currentRow = 0;
        grid.getStylesheets().add(
                getClass().getResource("../Form.css").toExternalForm()
        );

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

        currentRow +=1;

        grid.add(globalInfection,0,currentRow);

        currentRow+=1;
        /* we add to the grid all events that we can throw */
        Text throwableEventsLabel = new Text("Actions possibles");
        Button eventsButton = createButton("Actions");
        eventsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                openModal(stage);
            }
        });
        eventsButton.getStyleClass().add("title");
        grid.add(throwableEventsLabel,0,currentRow);

        currentRow +=1;

        grid.add(eventsButton,0,currentRow);



        currentRow +=1;

        String langName = this.gameCtrl.getGame().getLanguage().getName();
        Tweet welcome = new Tweet(new Date(), "Un nouveau langage vient d'apparaitre : " + langName);
        Text tweetsLabel = new Text("Tweets sur votre langage :");
        tweetsLabel.getStyleClass().add("title");
        grid.add(tweetsLabel,0,currentRow);
        tweets = FXCollections.observableArrayList(welcome);
        tweetsView = new ListView<Tweet>(tweets);
        /*
        we need to set the text wrap of list  view cells
         */
        tweetsView.setCellFactory(new Callback<ListView<Tweet>, ListCell<Tweet>>() {
            @Override
            public ListCell<Tweet> call(ListView<Tweet> list) {
                final ListCell cell = new ListCell() {
                    private Text text;

                    @Override
                    public void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            text = new Text(item.toString());
                            text.setWrappingWidth(300);
                            setGraphic(text);
                        }
                    }
                };

                return cell;
            }
        });
        tweetsView.setMaxWidth(350);

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


    public void openModal(Stage parent){
        Stage modal = new Stage();

        modalStarsCounter.getStyleClass().add("title");

        GridPane grid = new GridPane();
        grid.getStylesheets().add(
                getClass().getResource("../Form.css").toExternalForm()
        );
        grid.getStyleClass().add("grid");
        grid.getStyleClass().add("padding");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        Integer currentRow = 0;
        Scene modalScene = new Scene(grid,1366, 768);
        modal.initOwner(parent);
        modalStarsCounter.setText(gameCtrl.getStars().toString());
        GridPane modalGrid = createGrid();
        modal.setScene(modalScene);
        grid.add(modalStarsLabel,0,currentRow);
        currentRow+=1;
        grid.add(modalStarsCounter,0,currentRow);
        currentRow+=1;

        Triple<Slider,Label,Label> robustness = createSlider("Robustesse du langage",0,100,gameCtrl.getLanguage().getRobustness());

        grid.add(robustness.y,0,currentRow);
        grid.add(robustness.x,1,currentRow);
        grid.add(robustness.z,2,currentRow);

        this.robustness = robustness.x;

        Triple<Slider,Label,Label> facility = createSlider("Simplicité du langage",0,100,gameCtrl.getLanguage().getFacility());
        currentRow+=1;

        grid.add(facility.y,0,currentRow);
        grid.add(facility.x,1,currentRow);
        grid.add(facility.z,2,currentRow);
        this.facility = facility.x;

        currentRow+=1;
        for (Iterator<Button> I = getBuyableEventsButtons().iterator(); I.hasNext(); ) {
            Button btn = I.next();
            grid.add(btn,0,currentRow);
            currentRow +=1;
        }

        modal.initModality(Modality.APPLICATION_MODAL);
        modal.showAndWait();
    }


    public Triple<Slider,Label,Label> createSlider(String labelText, int min, int max, Integer value){
        Label label = new Label(labelText);
        final Label labelValue = new Label(value.toString());
        final Slider slider = new Slider(min,max,value);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Integer selectedStars = gameCtrl.getStars();
                Integer value = newValue.intValue();
                Integer gap = newValue.intValue()-oldValue.intValue();
                Integer currentStars = selectedStars;
                Integer nextStars = currentStars - gap;

                if (nextStars>=10 && nextStars<=100) {
                    labelValue.setText(value.toString());
                    selectedStars = nextStars;

                }
                else{
                    slider.setValue(oldValue.intValue());
                    selectedStars = currentStars;
                }
                gameCtrl.setStars(selectedStars);
                modalStarsCounter.setText(selectedStars.toString());
            }
        });
        return new Triple<Slider,Label,Label>(slider,label,labelValue);
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
                    gameCtrl.turnEvent.add(throwableEvent);
                }
            });
            buttons.add(btn);
        }
        return  buttons;
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
        btn.setMaxWidth(300);
        btn.setWrapText(true);
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
