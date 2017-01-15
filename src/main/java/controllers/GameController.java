package controllers;

import javafx.stage.Stage;
import model.Game;
import model.events.Event;
import model.events.ThrowableEvent;
import model.language.Language;
import utils.Turn;
import views.Map;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Alexandre on 15/01/2017.
 */
public class GameController {

    Game game;
    Stage stage;
    public Map mapViews;

    public GameController(Stage stage, Game game){
        this.stage = stage;
        this.game = game;

    }

    public void startGame(){

        mapViews = new Map(stage, this);
        this.start();
    }

    public Language getLanguage(){
        return game.getLanguage();
    }

    private void start() {
        Integer TURN_LENGTH = 10;
        Turn t = new Turn(TURN_LENGTH);
        t.run(this);
    }

    public ArrayList<ThrowableEvent> turn(ArrayList<ThrowableEvent> choosenEvent){

        Random rand = new Random();
        ThrowableEvent currEvent = null;

        int nombrePickEvent = rand.nextInt(3); //Entre 1 et la sommes des ponderation

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

        choosenEvent.addAll(pickedEvents);

        return choosenEvent;

    }

    public ThrowableEvent pickEvent(){
        int somme = 0;
        for (ThrowableEvent ev : game.getPossibleEvent()) {
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
