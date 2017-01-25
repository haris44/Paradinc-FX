package views;

import javafx.beans.property.ReadOnlyIntegerProperty;
import controllers.GameController;
import factory.GameFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import model.*;
import model.language.*;
import utils.*;

/**
 * Created by Nathan on 02/12/2016.
 */
public class Form {
    private Game game;
    private Text starsCounter;

    /**
    inputs : stored to get their values :
     */

    private TextField languageName;
    private ChoiceBox paradygmChoice;
    private Slider robustness;
    private Slider facility;
    private ListView platforms;
    private Integer latestAdded;
    private Stage primaryStage;
    private Integer selectedStars = 100;

    private Text languageNameError;
    private Text paraDygmeChoiceError;
    private Text platformsError;
    private Text facilityError;
    private Text robustnessError;


    public Form(Stage primaryStage){

        this.primaryStage = primaryStage;
        latestAdded = 0;
        GridPane grid = createGrid();
        grid.getStyleClass().add("grid");
        Scene scene = createScene(primaryStage, grid);

        Text sceneTitle = new Text("Personalisez votre langage de programmation : ");
        sceneTitle.getStyleClass().add("title");
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label starsCounterLabel = new Label("Stars disponibles : ");
        starsCounter = new Text();
        grid.add(starsCounterLabel,3,0);
        grid.add(starsCounter,4,0);

        Label labelLanguageName = new Label("Nom du langage");
        grid.add(labelLanguageName, 0, 1);
        languageName = new TextField();
        grid.add(languageName, 1, 1);
        languageNameError = new Text();
        languageNameError.getStyleClass().add("error");
        grid.add(languageNameError,2,1);

        Label labelParadygm = new Label("Paradygme du langage");
        grid.add(labelParadygm,0,2);
        paradygmChoice = new ChoiceBox(ParadygmType.toObservable());
        grid.add(paradygmChoice,1,2);
        paraDygmeChoiceError = new Text();
        paraDygmeChoiceError.getStyleClass().add("error");
        grid.add(paraDygmeChoiceError,2,2);

        Label labelPlatform = new Label("Plateformes supportées : ");
        grid.add(labelPlatform,0,3);
        this.platforms = createPlatformChoice();
        grid.add(platforms,1,3);
        platformsError = new Text();
        platformsError.getStyleClass().add("error");
        grid.add(platformsError,2,3);

        Triple<Slider,Label,Label> robustness = createSlider("Robustesse du langage",0,100);
        robustnessError = new Text();
        robustnessError.getStyleClass().add("error");
        grid.add(robustness.y,0,4);
        grid.add(robustness.x,1,4);
        grid.add(robustness.z,2,4);
        grid.add(robustnessError,3,4);
        this.robustness = robustness.x;

        Triple<Slider,Label,Label> facility = createSlider("Simplicité du langage",0,100);
        facilityError = new Text();
        facilityError.getStyleClass().add("error");
        grid.add(facility.y,0,5);
        grid.add(facility.x,1,5);
        grid.add(facility.z,2,5);
        grid.add(facilityError,3,5);
        this.facility = facility.x;

        HBox hbBtn = createButton();
        grid.add(hbBtn, 0, 7);
    }

    public GridPane createGrid(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        return grid;
    }

    public Scene createScene(Stage primaryStage, GridPane grid){
        Scene scene = new Scene(grid, 1366, 768);
           scene.getStylesheets().add(
                Form.class.getResource("../Form.css").toExternalForm()
        );
        primaryStage.setTitle("Parad'inc");
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }
    public void clearErrors(){
        languageNameError.setText("");
        robustnessError.setText("");
        facilityError.setText("");
        platformsError.setText("");
        paraDygmeChoiceError.setText("");

    }

    public boolean validate() {
        boolean isValid = true;
        String emptyError = "Ce champ ne peut pas être vide";
        clearErrors();
        if(languageName.getText().isEmpty()){
            isValid = false;
            languageNameError.setText(emptyError);
        }
        if(Double.isNaN(robustness.getValue())) {
            isValid = false;
            robustnessError.setText(emptyError);
        }

        if(Double.isNaN(facility.getValue())) {
            isValid = false;
            facilityError.setText(emptyError);
        }

        ReadOnlyIntegerProperty platformIndex = platforms.getSelectionModel().selectedIndexProperty();
        if(platformIndex.get() == -1) {
            isValid = false;
            platformsError.setText(emptyError);
        }

        ReadOnlyIntegerProperty paradygmIndex = paradygmChoice.getSelectionModel().selectedIndexProperty();
        if(paradygmIndex.get() == -1){
            isValid = false;
            paraDygmeChoiceError.setText(emptyError);
        }

        return isValid;
    }

    public HBox createButton()    {
        Button btn = new Button("Créez votre incroyable langage !");
        btn.getStyleClass().add("button");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(validate()){

                    String name = languageName.getText();
                    int robs = (int)robustness.getValue();
                    int faci = (int)facility.getValue();
                    ArrayList<Attribute> attr = new ArrayList<Attribute>();
                    for (Object a: platforms.getSelectionModel().getSelectedItems()){
                        Platform p = Platform.fromPlatformType(PlatformType.valueOf(a.toString()));
                        attr.add(p);
                    }
                    ParadygmType para = ParadygmType.valueOf(paradygmChoice.getValue().toString());
                    attr.add(Paradygm.fromParadygmeType(para));
                    Language lang = new Language(name,robs,faci,0,0);
                    lang.setAttributes(attr);
                    System.out.println(lang.toString());

                    Game game = GameFactory.createGame(lang, selectedStars);
                    // now that or game have a language, we can start tu turn loop !
                    new GameController(primaryStage, game).startGame();
                }

            }
        });
        return hbBtn;
    }
    public Triple<Slider,Label,Label> createSlider(String labelText, int min, int max){
        Label label = new Label(labelText);
        final Label labelValue = new Label();
        final Slider slider = new Slider(min,max,0);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Integer value = newValue.intValue();
                Integer gap = newValue.intValue()-oldValue.intValue();
                Integer currentStars = selectedStars;
                Integer nextStars = currentStars - gap;

                if (nextStars>=0 && nextStars<=100) {
                    labelValue.setText(value.toString());
                    selectedStars = nextStars;
                }
                else{
                    slider.setValue(oldValue.intValue());
                    selectedStars = currentStars;
                }
                starsCounter.setText(selectedStars.toString());
            }
        });
        return new Triple<Slider,Label,Label>(slider,label,labelValue);
    }

    public ListView createPlatformChoice(){
        final ListView<PlatformType> platformList = new ListView(PlatformType.toObservable());
        platformList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        platformList.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                /**
                 * need to check if inputs are already selected, to avoid removing stars when select/unselect input
                 */
                Integer stars = platformList.getSelectionModel().getSelectedItems().size();
                //Integer unselected = platformList.getItems().size();
                Integer nextStars =  selectedStars + (latestAdded*10) - (stars*10);
                if (nextStars >= 0 && nextStars <= 100){
                    selectedStars = selectedStars + (latestAdded*10)  - (stars*10);
                    latestAdded = stars;
                } else {
                    platformList.getSelectionModel().clearSelection();
                    selectedStars = selectedStars + (latestAdded*10);
                    latestAdded = 0;
                }
                starsCounter.setText(selectedStars.toString());
            }
        });
        return platformList;
    }
}

