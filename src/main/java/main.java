/**
 * Created by Nathan on 02/12/2016.
 */
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import map.ParadincMap;
import map.World;
import model.Game;
import views.Form;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Form form = new Form(primaryStage);
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}