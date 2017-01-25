package factory;

import controllers.ParadincRegionController;
import javafx.util.Pair;
import map.ParadincRegion;
import model.Game;
import model.Timeline;
import model.events.Bug;
import model.events.Conference;
import model.events.Event;
import model.language.Language;
import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;

public class GameFactory {

    public static Game createGame(Language language, Integer stars){
        ArrayList<Event> event = new ArrayList<Event>();
        event.add(new Bug("Out Of Memory", -10, -2, 0, new Pair<>(20, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("NullPointerException", -10, -2, 0, new Pair<>(10, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Dépendance Deprecated", -10, -2, 0, new Pair<>(48, 2.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Array Out of Bound", -10, -2, 0, new Pair<>(10, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Lenteur de traitement", -10, -2, 0, new Pair<>(70, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Utilisation processeur intensive", -10, -2, 0, new Pair<>(30, 0.4), new Pair<>(0, 0.0)));

        // we generate a random int for the stars you will win are less predictable
        Integer presLang = ThreadLocalRandom.current().nextInt(5,15);
        Integer presUpdate = ThreadLocalRandom.current().nextInt(5,10);

        event.add(new Conference("Conférence de présentation du langage",10,presLang,5 ));
        event.add(new Conference("Présentation de mise à jour",5,presUpdate,5 ));

        Timeline timeline = new Timeline();
        ParadincRegionController regionController = ParadincRegionFactory.createRegion();

        // SET DEFAULT CONTAMINATION OF FIRST REGIONS HERE
        ParadincRegion europe = regionController.getRegions("Europe");
        europe.setContamination(30);

        return new Game(regionController, language, stars, timeline, event);
    }
}
