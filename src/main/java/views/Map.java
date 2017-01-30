package views;

import controllers.GameController;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import map.ParadincMap;
import map.World;
import utils.WindowSize;


/**
 * Created by Alexandre on 15/01/2017.
 */
public class Map {

    public int compteur = 0;
    private final StackPane topBarPan;
    private final Text tour;

    public Map(Stage stage, GameController controller) {
        Group root = new Group();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
            Map.class.getResource("../Map.css").toExternalForm()
        );
        scene.getStylesheets().add(
                Map.class.getResource("../Timeline.css").toExternalForm()
        );

        World map = controller.getGame().getRegionController().getMap().getWorld();

        StackPane mapPan = new StackPane(map);
        mapPan.setBackground(new Background(new BackgroundFill(map.getBackgroundColor(), CornerRadii.EMPTY, Insets.EMPTY)));
        map.setTranslateY(WindowSize.TOP_BAR_Y);

        topBarPan = new StackPane();

        topBarPan.setBackground(new Background(new BackgroundFill(Color.web("#3f3f4f"), CornerRadii.EMPTY, Insets.EMPTY)));
        topBarPan.getStyleClass().add("title");
        topBarPan.setMinHeight(WindowSize.TOP_BAR_Y);
        topBarPan.setMaxHeight(WindowSize.TOP_BAR_Y);
        topBarPan.setMinWidth(WindowSize.TOP_BAR_X);


        tour = new Text();
        tour.setFill(Color.web("#F5F5F5"));
        tour.setText("PARAD'INC ! tour n°" + compteur);
        topBarPan.getChildren().add(tour);


        StackPane rightBarPan = new StackPane();
        rightBarPan.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        rightBarPan.setTranslateX(WindowSize.MAP_X);
        rightBarPan.setTranslateY(WindowSize.TOP_BAR_Y);
        rightBarPan.setMinHeight(WindowSize.RIGHT_BAR_Y);
        rightBarPan.setMinWidth(WindowSize.RIGHT_BAR_X);

        root.getChildren().add(rightBarPan);
        root.getChildren().add(mapPan);
        root.getChildren().add(topBarPan);

        stage.setTitle("Parad'Inc");
        stage.setScene(scene);
        stage.show();
    }

    public synchronized void notifyTour() {
        compteur = compteur + 1;
        topBarPan.getChildren().remove(tour);
        tour.setText("PARAD'INC ! tour n°" + compteur);
        topBarPan.getChildren().add(tour);
    }

}
