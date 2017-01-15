package views;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import map.ParadincMap;
import map.World;

import java.awt.*;

/**
 * Created by Alexandre on 15/01/2017.
 */
public class Map {

    public Map(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root);

        World map = new ParadincMap().getWorld();
        StackPane mapPan = new StackPane(map);
        mapPan.setBackground(new Background(new BackgroundFill(map.getBackgroundColor(), CornerRadii.EMPTY, Insets.EMPTY)));



//        StackPane topBarPan = new StackPane();
//        topBarPan.setBackground(new Background(new BackgroundFill(Color.RED)));
//        root.getChildren().add(topBarPan);

        root.getChildren().add(mapPan);

        stage.setTitle("Parad'Inc");
        stage.setScene(scene);
        stage.show();
    }

}
