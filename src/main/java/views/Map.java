package views;

import controllers.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import map.ParadincMap;
import map.World;
import utils.WindowSize;

import javafx.scene.image.Image;


/**
 * Created by Alexandre on 15/01/2017.
 */
public class Map {

    public int compteur = 0;
    private final GridPane topBarPan;
    private final Text tour;
    private ImageView image;

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

        topBarPan = new GridPane();
        topBarPan.setAlignment(Pos.CENTER);
        topBarPan.setHgap(10);
        topBarPan.setVgap(10);
        topBarPan.setBackground(new Background(new BackgroundFill(Color.web("#212121"), CornerRadii.EMPTY, Insets.EMPTY)));
        topBarPan.getStyleClass().add("title");
        topBarPan.setMinHeight(WindowSize.TOP_BAR_Y);
        topBarPan.setMaxHeight(WindowSize.TOP_BAR_Y);
        topBarPan.setMinWidth(WindowSize.TOP_BAR_X);
        topBarPan.setMaxWidth(WindowSize.TOP_BAR_X);


        image = new ImageView(new Image(getClass().getResourceAsStream("../paradinc.png")));
        image.setFitHeight(50);
        image.setFitWidth(100);
        topBarPan.add(image,4,0);

        tour = new Text();
        tour.getStyleClass().add("logo");
        tour.setFill(Color.web("#F5F5F5"));
        tour.setText(" tour n°" + compteur);
        topBarPan.add(tour,5,0);


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
        tour.setText(" tour n°" + compteur);
        topBarPan.getChildren().add(tour);
    }

}
