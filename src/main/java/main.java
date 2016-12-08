/**
 * Created by Nathan on 02/12/2016.
 */
import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        game.setStars(100);
        Form form = new Form(primaryStage,game);
    }
    public static void main(String[] args) {
        launch(args);
    }
}