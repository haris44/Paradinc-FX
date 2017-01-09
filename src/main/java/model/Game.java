package model;

import java.util.ArrayList;
import utils.*;
/**
 * Created by Nathan on 08/12/2016.
 */
public class Game {
    protected Language language;
    protected ArrayList<GameEvent> possibleEvents;
    protected ArrayList<GameEvent> nextEvents;
    protected Integer stars;
    protected String timeline;
    public Game(){}
    public Game(Language language, ArrayList<GameEvent> possibleEvents, ArrayList<GameEvent> nextEvents, Integer stars, String timeline){
        this.language = language;
        this.possibleEvents = possibleEvents;
        this.nextEvents = nextEvents;
        this.stars = stars;
        this.timeline = timeline;
    }
    public Language getLanguage(){return this.language;}
    public void setLanguage(Language language){this.language = language;}
    public ArrayList<GameEvent> getPossibleEvents(){return this.possibleEvents;}
    public void setPossibleEvents(ArrayList<GameEvent> possibleEvents){this.possibleEvents = possibleEvents;}
    public ArrayList<GameEvent> getNextEvents(){return this.nextEvents;}
    public void setNextEvents(ArrayList<GameEvent> nextEvents){this.nextEvents = nextEvents;};
    public Integer getStars(){return this.stars;}
    public void setStars(Integer stars){this.stars = stars;}
    public String getTimeline(){return this.timeline;}
    public void setTimeline(String timeline){this.timeline = timeline;}

    /**
     * Starts the game once the form is filled.
     * we now must have a language created
     */
    public void start(){
        Integer TURN_LENGTH = 10;
        Turn t = new Turn(TURN_LENGTH);
        t.run();
    }
}
