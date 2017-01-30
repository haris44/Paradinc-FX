package controllers;

import javafx.stage.Stage;
import model.Game;
import model.Timeline;
import model.events.Event;
import model.events.ThrowableEvent;
import model.language.Language;
import utils.Turn;
import views.Map;
import views.TimelineView;

import java.util.ArrayList;
import java.util.Random;

public class GameController {

    public final int TIME_TURN = 5;
    public final int STARS_TURN = 10;
    public final int MAX_RANDOM_EVENT = 2;
    public final int MAX_PRICE_RANDOM_EVENT = 20;

    Game game;
    Stage stage;
    public Map mapViews;
    public TimelineView timelineView;
    public ArrayList<ThrowableEvent> turnEvent;
    private Turn t;

    public GameController(Stage stage, Game game) {
        this.stage = stage;
        this.game = game;
        turnEvent = new ArrayList<>();

    }

    public Game getGame() {
        return this.game;
    }

    public void startGame() {

        mapViews = new Map(stage, this);
        timelineView = new TimelineView(stage, this);
        this.start();
    }

    public Language getLanguage() {
        return game.getLanguage();
    }

    private void start() {
        t = new Turn(TIME_TURN);
        t.run(this);
    }

    public void turn() {
        if (game.getStars() <= 0 || game.getRegionController().getGlobalContamination() <= 0) {
            stopGame(false);
        } else if (game.getRegionController().getGlobalContamination() >= 99) {
            stopGame(true);
        } else {
            ArrayList<ThrowableEvent> randomEvent = PickedRandomEvent();
            turnEvent.addAll(randomEvent);
            for (ThrowableEvent ev : turnEvent) {
                ev.throwEvent(this, timelineView);
            }
            turnEvent = new ArrayList<>();
            game.setStars(game.getStars() + STARS_TURN);
        }
    }

    public void setStars(int stars) {
        if (stars > 0) {
            game.setStars(stars);
        } else {
            game.setStars(0);
            this.stopGame(false);
        }
    }

    private void stopGame(boolean win) {
        t.stop();
        if (win) {
            System.out.println("Vous avez gagner");
            timelineView.win();
        } else {
            System.out.println("Vous avez perdu");
            timelineView.loose();
        }
    }

    public Integer getStars() {
        return game.getStars();
    }

    private ArrayList<ThrowableEvent> PickedRandomEvent() {

        Random rand = new Random();

        int nombrePickEvent = rand.nextInt(MAX_RANDOM_EVENT);

        game.setPossibleEvent(new ArrayList<>());
        for (Event ev : game.getEvent()) {
            if (ev.getPrice() < MAX_PRICE_RANDOM_EVENT)
                game.getPossibleEvent().add(ev.getThrowable(game));
        }

        ArrayList<ThrowableEvent> pickedEvents = new ArrayList<>();

        for (int i = 0; i < nombrePickEvent; i++) {
            ThrowableEvent picked = pickEvent();
            // picked event is free ! :p
            picked.price = 0;
            System.out.println(picked);
            pickedEvents.add(picked);

        }
        return pickedEvents;

    }

    public ThrowableEvent pickEvent() {
        int somme = 0;
        for (ThrowableEvent ev : game.getPossibleEvent()) {
            somme += ev.probability;

        }

        Random rand = new Random();
        ThrowableEvent currEvent = null;

        int nombre = rand.nextInt(somme - 1) + 1; //Entre 1 et la sommes des ponderation
        int i = 0;

        while (nombre >= 0) {
            currEvent = game.getPossibleEvent().get(i);
            nombre = nombre - currEvent.probability;
            i += 1;
        }

        game.getPossibleEvent().remove(currEvent);
        return currEvent;
    }

    public void pauseTimer() {
        t.stop();
    }

    public void resumeTimer() {
        t.run(this);
    }
}
