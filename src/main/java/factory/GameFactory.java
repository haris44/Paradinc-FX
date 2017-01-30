package factory;

import controllers.ParadincRegionController;
import javafx.util.Pair;
import map.ParadincRegion;
import model.Game;
import model.Timeline;
import model.events.*;
import model.language.Attribute;
import model.language.Language;
import model.language.Platform;
import model.language.PlatformType;
import utils.PlatformArray;

import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;

public class GameFactory {


    public static Game createGame(Language language, Integer stars, String region){
        ArrayList<Event> event = new ArrayList<>();

        event.add(new Bug("Out Of Memory", -10, -2, 0, PlatformArray.getAllPlatform(), new Pair<>(20, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("NullPointerException", -10, -2, 0, PlatformArray.getAllPlatform(), new Pair<>(10, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Dépendance Deprecated", -10, -2, 0, PlatformArray.getAllPlatform(), new Pair<>(48, 2.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Array Out of Bound", -10, -2, 0, PlatformArray.getAllPlatform(), new Pair<>(10, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Lenteur de traitement", -10, -2, 0, PlatformArray.getAllPlatform(), new Pair<>(70, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Utilisation processeur intensive", -10, -2, 0, PlatformArray.getAllPlatform(), new Pair<>(30, 0.4), new Pair<>(0, 0.0)));

        event.add(new NewsPaper("Utilisation processeur intensive", -10, -2, 0, PlatformArray.getAllPlatform(), new Pair<>(30, 0.4), new Pair<>(0, 0.0)));



        ParadincRegionController regionController = ParadincRegionFactory.createRegion();

        // SET DEFAULT CONTAMINATION OF FIRST REGIONS HERE
        ParadincRegion europe = regionController.getRegions(region);
        europe.setContamination(30);

        return new Game(regionController, language, stars, event);
    }
}
