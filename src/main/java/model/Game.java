package model;

import java.util.ArrayList;

import controllers.ParadincRegionController;
import map.ParadincRegion;
import model.events.Event;
import model.events.ThrowableEvent;
import model.language.Language;


public class Game {
    protected Language language;
    protected Integer stars;
    protected Timeline timeline;
    protected ArrayList<Event> event;
    protected ArrayList<ThrowableEvent> possibleEvent;
    protected ParadincRegionController regionController;

    public Game(ParadincRegionController regionController, Language language, Integer stars, Timeline timeline, ArrayList<Event> event) {
        this.regionController = regionController;
        this.language = language;
        this.stars = stars;
        this.timeline = timeline;
        this.event = event;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Integer getStars() {
        return this.stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }


    public ArrayList<Event> getEvent() {
        return event;
    }

    public void setEvent(ArrayList<Event> event) {
        this.event = event;
    }

    public ArrayList<ThrowableEvent> getPossibleEvent() {
        return possibleEvent;
    }

    public void setPossibleEvent(ArrayList<ThrowableEvent> possibleEvent) {
        this.possibleEvent = possibleEvent;
    }

    public ParadincRegionController getRegionController() {
        return regionController;
    }

    public void setRegionController(ParadincRegionController regionController) {
        this.regionController = regionController;
    }
}
