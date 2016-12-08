import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Nathan on 02/12/2016.
 */
public class Form {
    public Form(Stage primaryStage){

        GridPane grid = createGrid();
        grid.getStyleClass().add("grid");
        Scene scene = createScene(primaryStage, grid);

        Text sceneTitle = new Text("Personalisez votre langage de programmation : ");
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label labelLanguageName = new Label("nom du langage");
        grid.add(labelLanguageName, 0, 1);

        final TextField languageName = new TextField();
        grid.add(languageName, 1, 1);

        Label labelParadygme = new Label("Paradygme du langage");
        grid.add(labelParadygme,0,2);

        final ChoiceBox paradygmChoice = createChoiceBox();
        grid.add(paradygmChoice,1,2);

        Triple<Slider,Label,Label> robustness = createSlider("Robustesse du langage",0,100,1);
        grid.add(robustness.y,0,3);
        grid.add(robustness.x,1,3);
        grid.add(robustness.z,2,3);

        Triple<Slider,Label,Label> facility = createSlider("Simplicité du langage",0,100,1);
        grid.add(facility.y,0,4);
        grid.add(facility.x,1,4);
        grid.add(facility.z,2,4);

        HBox hbBtn = createButton(languageName,paradygmChoice,robustness.x,facility.x);
        grid.add(hbBtn, 0, 6);

    }

    public GridPane createGrid(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        return grid;
    }
    public ChoiceBox createChoiceBox() {
        return new ChoiceBox(FXCollections.observableArrayList(
            ParadygmType.Fonctionel,
            ParadygmType.Imperatif,
            ParadygmType.Object
        ));
    }

    public Scene createScene(Stage primaryStage, GridPane grid){
        Scene scene = new Scene(grid, 1024, 576);
           scene.getStylesheets().add(
                Form.class.getResource("Form.css").toExternalForm()
        );
        primaryStage.setTitle("Parad'inc");
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }

    public HBox createButton(final TextField languageName, final ChoiceBox paradygm,
                             final Slider robustness, final Slider facility){
        Button btn = new Button("Créez votre incroyable langage !");
        btn.getStyleClass().add("button");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println(languageName.getText());
                System.out.println(paradygm.getValue());
                System.out.println((int)robustness.getValue());
                System.out.println((int)facility.getValue());
            }
        });
        return hbBtn;
    }
    public Triple<Slider,Label,Label> createSlider(String labelText, int min, int max, int defaultValue){
        Label label = new Label(labelText);
        final Label labelValue = new Label();
        Slider slider = new Slider(min,max,defaultValue);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Integer value = newValue.intValue();
                labelValue.setText(value.toString());
            }
        });
        return new Triple<Slider,Label,Label>(slider,label,labelValue);
    }
}

