package factory;

import controllers.ParadincRegionController;
import javafx.util.Pair;
import map.ParadincRegion;
import model.Game;
import model.Timeline;
import model.events.Bug;
import model.events.Conference;
import model.events.Event;
import model.language.Attribute;
import model.language.Language;
import model.language.Platform;
import model.language.PlatformType;

import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;

public class GameFactory {

    public static Game createGame(Language language, Integer stars){
        ArrayList<Event> event = new ArrayList<Event>();

        ArrayList<Attribute> allPlatform = new ArrayList<Attribute>(3){{
            add(Platform.fromPlatformType(PlatformType.Windows));
            add(Platform.fromPlatformType(PlatformType.Unix));
            add(Platform.fromPlatformType(PlatformType.Linux));
        }};

        event.add(new Bug("Out Of Memory", -10, -2, 0, allPlatform , new Pair<>(20, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("NullPointerException", -10, -2, 0, allPlatform, new Pair<>(10, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Dépendance Deprecated", -10, -2, 0, allPlatform, new Pair<>(48, 2.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Array Out of Bound", -10, -2, 0, allPlatform, new Pair<>(10, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Lenteur de traitement", -10, -2, 0, allPlatform, new Pair<>(70, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Utilisation processeur intensive", -10, -2, 0, allPlatform, new Pair<>(30, 0.4), new Pair<>(0, 0.0)));

        // Please, refactor with the xslx. Do not use Random
        Integer presLang = ThreadLocalRandom.current().nextInt(5,15);
        Integer presUpdate = ThreadLocalRandom.current().nextInt(5,10);

        event.add(new Conference("Conférence de présentation du langage",10,15,5 ));
        event.add(new Conference("Présentation de mise à jour",5,15,5 ));

        ParadincRegionController regionController = ParadincRegionFactory.createRegion();

        // SET DEFAULT CONTAMINATION OF FIRST REGIONS HERE
        ParadincRegion europe = regionController.getRegions("Europe");
        europe.setContamination(30);

        return new Game(regionController, language, stars, event);
    }
}
