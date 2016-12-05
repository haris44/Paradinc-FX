import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

        Scene scene = createScene(primaryStage, grid);

        Text sceneTitle = new Text("Personalisez votre langage de programmation : ");
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label labelLanguageName = new Label("nom du langage");
        grid.add(labelLanguageName, 0, 1);

        final TextField languageName = new TextField();
        grid.add(languageName, 1, 1);

        HBox hbBtn = createButton(languageName);
        grid.add(hbBtn, 1, 4);

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
        Scene scene = new Scene(grid, 1024, 576);
           scene.getStylesheets().add(
                Form.class.getResource("Form.css").toExternalForm()
        );
        primaryStage.setTitle("Parad'inc");
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }
    public HBox createButton(final TextField field){
        Button btn = new Button("Créez votre incroyable langage !");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println(field.getText());
            }
        });
        return hbBtn;
    }

}
