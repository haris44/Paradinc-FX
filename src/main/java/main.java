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
        Game game = new Game();
        game.setStars(100);
        Form form = new Form(primaryStage,game);
    }

//    @Override
//    public void start(Stage stage) {
//        World map = new ParadincMap().getWorld();
//        StackPane pane = new StackPane(map);
//        pane.setBackground(new Background(new BackgroundFill(map.getBackgroundColor(), CornerRadii.EMPTY, Insets.EMPTY)));
//
//        Scene scene = new Scene(pane);
//
//        stage.setTitle("Parad'Inc");
//        stage.setScene(scene);
//        stage.show();
//    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}