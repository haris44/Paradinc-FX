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
    Timer timer;
    private GameController gamectrl;


    public Turn(Integer length) {
        this.length = length;
    }

    public void run(GameController gamectrl) {
		this.timer = new java.util.Timer();
        this.gamectrl = gamectrl;
        this.timer.schedule(getTimerTask(), 1000, length * 1000);
    }

    private TimerTask getTimerTask(){
        return new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        System.out.println(new Date());
                        gamectrl.turn();
                        gamectrl.mapViews.notifyTour();
                        gamectrl.timelineView.notifyTurn();
                    }
                });
            }
        };
    }
    public void stop(){
        timer.cancel();
    }

}
