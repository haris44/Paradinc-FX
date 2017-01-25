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

/**
 * Created by Alexandre on 15/01/2017.
 */
public class GameController {

    Game game;
    Stage stage;
    public Map mapViews;
    public TimelineView timelineView;
    public ArrayList<ThrowableEvent> turnEvent;
    private Turn t;

    public GameController(Stage stage, Game game){
        this.stage = stage;
        this.game = game;
        turnEvent = new ArrayList<>();

    }
    public Game getGame(){
        return this.game;
    }
    public void startGame(){

        mapViews = new Map(stage, this);
        timelineView = new TimelineView(stage, this);
        this.start();
    }

    public Language getLanguage(){
        return game.getLanguage();
    }

    private void start() {
        t = new Turn(5);
        t.run(this);
    }


    public void turn(){
        ArrayList<ThrowableEvent> randomEvent = PickedRandomEvent();
        turnEvent.addAll(randomEvent);
        for (ThrowableEvent ev : turnEvent) {
            ev.throwEvent(this, timelineView);
        }
        turnEvent = new ArrayList<>();
    }

    public void setStars(int stars){
        if(stars > 0){
            game.setStars(stars);
        } else {
            game.setStars(0);
          this.stopGame(false);
        }
    }

    private void stopGame(boolean win){
        t.stop();
        if(win){
            System.out.println("Vous avez gagner");
        }
        else{
            System.out.println("Vous avez perdu");
        }
    }
    public int getStars(){
        return game.getStars();
    }
    private ArrayList<ThrowableEvent> PickedRandomEvent(){

        Random rand = new Random();
        ThrowableEvent currEvent = null;

        int nombrePickEvent = rand.nextInt(3); // nombre d'event qui vont être picked

        game.setPossibleEvent(new ArrayList<ThrowableEvent>());
        for (Event ev :game.getEvent()) {
           game.getPossibleEvent().add(ev.getThrowable(game));
        }

        ArrayList<ThrowableEvent> pickedEvents = new ArrayList<ThrowableEvent>();

        for(int i = 0; i < nombrePickEvent; i++)
        {
            ThrowableEvent picked = pickEvent();
            System.out.println(picked);
            pickedEvents.add(picked);

        }
        return pickedEvents;

    }

    public ThrowableEvent pickEvent(){
        int somme = 0;
        for (ThrowableEvent ev : game.getPossibleEvent()) {
            // buyable evants shouldn't be thrown randomly
            if(ev.event.isBuyable() == false)
                somme += ev.probability;
        }

        Random rand = new Random();
        ThrowableEvent currEvent = null;

        int nombre = rand.nextInt(somme - 1) + 1; //Entre 1 et la sommes des ponderation
        int i = 0;

        while (nombre >= 0){
            currEvent = game.getPossibleEvent().get(i);
            nombre = nombre - currEvent.probability;
            i += 1;
        }

        return currEvent;
    }



}
