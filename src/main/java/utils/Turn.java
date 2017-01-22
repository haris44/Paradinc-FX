package utils;

import controllers.GameController;
import javafx.application.Platform;
import model.events.Event;
import model.events.ThrowableEvent;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.ArrayList;


/**
 * Created by Nathan on 09/01/2017.
 */

public class Turn {
    // specifies the length (in seconds) of a turn
    private Integer length;
    ScheduledExecutorService service;
    private ArrayList<Event> nextActions = new ArrayList<Event>();
    private ArrayList<Event> pastActions = new ArrayList<Event>();

    public Turn(Integer length) {
        this.length = length;
    }

    public void run(GameController gamectrl) {
        Integer delay = length == null || length < 1 ? 10 : length;
        Timer timer = new java.util.Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        System.out.println(new Date());
                        gamectrl.turn(new ArrayList<ThrowableEvent>());
                        gamectrl.mapViews.notifyTour();
                        gamectrl.timelineView.notifyTurn();
                    }
                });
            }
        }, 1000, length * 1000);


    }

    public ArrayList<Event> addAction(Event action) {
        nextActions.add(action);
        return nextActions;
    }

    public ArrayList<Event> getNextActions() {
        return nextActions;
    }

    public ArrayList<Event> getPastActions() {
        return pastActions;
    }
}
