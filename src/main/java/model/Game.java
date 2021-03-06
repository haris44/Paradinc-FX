package model;

import java.util.ArrayList;
import java.util.Iterator;

import controllers.ParadincRegionController;
import map.ParadincRegion;
import model.events.Event;
import model.events.ThrowableEvent;
import model.language.Language;


public class Game {
    protected Language language;
    protected Integer stars;
    protected ArrayList<Event> event;
    protected ArrayList<ThrowableEvent> possibleEvent;
    protected ParadincRegionController regionController;

    public Game(ParadincRegionController regionController, Language language, Integer stars,  ArrayList<Event> event) {
        this.regionController = regionController;
        this.language = language;
        this.stars = stars;
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
    public ArrayList<Event> getBuyableEvents(){
        ArrayList<Event> buyableEvents = new ArrayList<Event>();
        for (Iterator<Event> I = event.iterator(); I.hasNext(); ) {
            Event event = I.next();
            if(event.isBuyable())
                buyableEvents.add(event);
        }
        return buyableEvents;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
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
