/**
 * Created by Nathan on 02/12/2016.
 */
import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Form form = new Form(primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}