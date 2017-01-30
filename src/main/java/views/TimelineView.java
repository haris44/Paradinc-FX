package views;

import controllers.GameController;
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
import map.ParadincRegion;
import model.actions.Tweet;
import model.events.*;
import utils.Triple;

import java.util.*;

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
    ChoiceBox<ParadincRegion> cbRegions ;

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
        Integer infection = gameCtrl.getGame().getRegionController().getGlobalContamination();
        globalInfection = new Text(infection + "%");

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
                            text.setWrappingWidth(200);
                            setGraphic(text);
                        }
                    }
                };

                return cell;
            }
        });
        tweetsView.setMaxWidth(240);

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
        Integer infection = gameCtrl.getGame().getRegionController().getGlobalContamination();
        globalInfection.setText(infection + "%");
    }


    public void openModal(Stage parent){

        gameCtrl.pauseTimer();

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
        grid.add(modalStarsLabel,2,currentRow);
        currentRow+=1;
        grid.add(modalStarsCounter,2,currentRow);
        currentRow+=1;

        Integer minRob = gameCtrl.getLanguage().robustness;
        Triple<Slider,Label,Label> robustness = createSlider("Robustesse du langage",minRob,100,gameCtrl.getLanguage().getRobustness());

        grid.add(robustness.y,2,currentRow);
        grid.add(robustness.x,3,currentRow);
        grid.add(robustness.z,4,currentRow);

        this.robustness = robustness.x;
        Integer minFac = gameCtrl.getLanguage().facility;
        Triple<Slider,Label,Label> facility = createSlider("Simplicité du langage",minFac,100,gameCtrl.getLanguage().getFacility());
        currentRow+=1;

        grid.add(facility.y,2,currentRow);
        grid.add(facility.x,3,currentRow);
        grid.add(facility.z,4,currentRow);
        this.facility = facility.x;

        currentRow+=1;


        Button validate = createButton("Valider les modifications du langage");
        validate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Integer newRobustness = ((int) robustness.x.getValue());
                Integer newFacility = ((int) facility.x.getValue());
                gameCtrl.getGame().getLanguage().setFacility(newFacility);
                gameCtrl.getGame().getLanguage().setRobustness(newRobustness);
                closeModal(modal);
            }
        });
        grid.add(validate, 2,currentRow);

        currentRow +=1;
        Separator separator = new Separator();

        Separator separator2 = new Separator();

        grid.add(separator, 1,currentRow);
        grid.add(separator2, 2,currentRow);

        currentRow +=1;


        // first we need to select a region, where our action will be executed
        Label regionsLabel = new Label("Region :");
        grid.add(regionsLabel,1,currentRow);

        ArrayList<ParadincRegion> regions = gameCtrl.getGame().getRegionController().getListRegions();
        cbRegions = new ChoiceBox(FXCollections.observableArrayList(regions));
        cbRegions.getSelectionModel().selectFirst();
        grid.add(cbRegions,2,currentRow);

        currentRow+=1;
        currentRow+=1;

        Integer col = 0;
        currentRow = getBuyableEventsButtons(modal, grid, currentRow);


        Button closeModalBtn = new Button("Retour au jeu ! ");
        closeModalBtn.setMaxWidth(300);
        closeModalBtn.setWrapText(true);
        closeModalBtn.getStyleClass().add("button");

        closeModalBtn.setOnAction(e -> closeModal(modal));

        grid.add(closeModalBtn, 2, currentRow + 1 );

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
                    Integer gap = newValue.intValue() - oldValue.intValue();
                    Integer currentStars = selectedStars;
                    Integer nextStars = currentStars - gap;


                    if (nextStars >= 10 && nextStars <= 100) {
                        labelValue.setText(value.toString());
                        selectedStars = nextStars;

                    } else {
                        slider.setValue(oldValue.intValue());
                        selectedStars = currentStars;
                    }
                    gameCtrl.setStars(selectedStars);
                    modalStarsCounter.setText("Stars restantes : " + selectedStars.toString());


            }
        });
        return new Triple<Slider,Label,Label>(slider,label,labelValue);
    }

    public Integer getBuyableEventsButtons(Stage stage, GridPane grid, Integer currentRow) {


        ArrayList<Event> events = gameCtrl.getGame().getBuyableEvents();


        grid.add(new Text("Conferences"),0,currentRow);
        grid.add(new Text("Tweets"),1,currentRow);
        grid.add(new Text("Comunautées"),2,currentRow);
        grid.add(new Text("Journaux"),3,currentRow);
        grid.add(new Text("Evangelist"),4,currentRow);
        currentRow ++;
        Integer row1 = currentRow;
        Integer row2 = currentRow;
        Integer row3 = currentRow;
        Integer row4 = currentRow;
        Integer row5 = currentRow;
        for (Iterator<Event> I = events.iterator(); I.hasNext(); ) {
           Event event = I.next();
           Integer col;
           String label = new String("(-" + event.getPrice()  +  " stars)  " + event.getName() );
           Button btn = createButton(label);
           TimelineView self = this;
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    ThrowableEvent throwableEvent = event.getThrowable(gameCtrl.getGame());
                    throwableEvent.setRegion(cbRegions.getValue());
                    gameCtrl.turnEvent.add(throwableEvent);
                    closeModal(stage);
                }
            });
            System.out.println(event.getClass());
            if(Conference.class.isInstance(event)){
                btn.getStyleClass().add("conference");
                grid.add(btn,0,row1);
                row1 ++;
            }
            else if (model.events.Tweet.class.isInstance(event) ) {
                btn.getStyleClass().add("tweet");

                grid.add(btn,1,row2);
                row2++;
            }
            else if (Community.class.isInstance(event) ) {
                btn.getStyleClass().add("comunity");

                grid.add(btn,2,row3);
                row3++;
            }
            else if (NewsPaper.class.isInstance(event) ) {
                btn.getStyleClass().add("newspaper");

                grid.add(btn,3,row4);
                row4++;
            }
            else if (Evangelist.class.isInstance(event) ){
                btn.getStyleClass().add("evangelist");
                grid.add(btn,4,row5);
                row5++;
            }
            else {
                grid.add(btn,5,8);
                row5++;
            }


        }

        return Collections.max(Arrays.asList(row1,row2,row3,row4,row5));
    }

    private void closeModal(Stage stage){
        gameCtrl.resumeTimer();
        stage.close();
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

        return btn;
    }

    public void tweet(String nom) {
        tweets.add(0,new Tweet(new Date(),nom));
    }
}
