package utils;

import model.Action;

import java.util.Date;
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

    public void run() {
        Integer delay = length == null || length < 1 ? 10 : length;
        service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        }, 0, delay, TimeUnit.SECONDS);
    }

    // do we need to specify if the method throws Exception ?
    public void pause() throws InterruptedException {
        service.shutdown();
    }

    public void resume() {
        run();
    }

    public void kill() {
        service.shutdown();
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
