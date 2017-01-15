package factory;

import javafx.util.Pair;
import model.Game;
import model.Timeline;
import model.events.Bug;
import model.events.Event;
import model.language.Language;

import java.util.ArrayList;

public class GameFactory {

    public static Game createGame(Language language, Integer stars){
        ArrayList<Event> event = new ArrayList<Event>();
        event.add(new Bug("Out Of Memory", 0, new Pair<>(20, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("NullPointerException", 0, new Pair<>(10, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Dépendance Deprecated", 0, new Pair<>(48, 2.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Array Out of Bound", 0, new Pair<>(10, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Lenteur de traitement", 0, new Pair<>(70, 1.0), new Pair<>(0, 0.0)));
        event.add(new Bug("Utilisation processeur intensive", 0, new Pair<>(30, 0.4), new Pair<>(0, 0.0)));

        Timeline timeline = new Timeline();

        return new Game(language, stars, timeline, event);
    }
}
