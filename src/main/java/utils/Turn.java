package utils;

import controllers.GameController;
import javafx.application.Platform;
import model.Action;

import java.time.Period;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;


/**
 * Created by Nathan on 09/01/2017.
 */

public class Turn {
    // specifies the length (in seconds) of a turn
    private Integer length;
    ScheduledExecutorService service;
    private ArrayList<Action> nextActions = new ArrayList<Action>();
    private ArrayList<Action> pastActions = new ArrayList<Action>();

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
                        gamectrl.turn();
                        gamectrl.mapViews.notifyTour();
                    }
                });
            }
        }, delay, 1000);


    }

    public ArrayList<Action> addAction(Action action) {
        nextActions.add(action);
        return nextActions;
    }

    public ArrayList<Action> getNextActions() {
        return nextActions;
    }

    public ArrayList<Action> getPastActions() {
        return pastActions;
    }
}
