import java.util.ArrayList;

/**
 * Created by Nathan on 08/12/2016.
 */
public class Game {
    protected Language language;
    protected ArrayList<Event> possibleEvents;
    protected ArrayList<Event> nextEvents;
    protected Integer stars;
    protected String timeline;
    public Game(){}
    public Game(Language language,ArrayList<Event> possibleEvents,ArrayList<Event> nextEvents,Integer stars,String timeline){
        this.language = language;
        this.possibleEvents = possibleEvents;
        this.nextEvents = nextEvents;
        this.stars = stars;
        this.timeline = timeline;
    }
    public Language getLanguage(){return this.language;}
    public void setLanguage(Language language){this.language = language;}
    public ArrayList<Event> getPossibleEvents(){return this.possibleEvents;}
    public void setPossibleEvents(ArrayList<Event> possibleEvents){this.possibleEvents = possibleEvents;}
    public ArrayList<Event> getNextEvents(){return this.nextEvents;}
    public void setNextEvents(ArrayList<Event> nextEvents){this.nextEvents = nextEvents;};
    public Integer getStars(){return this.stars;}
    public void setStars(Integer stars){this.stars = stars;}
    public String getTimeline(){return this.timeline;}
    public void setTimeline(String timeline){this.timeline = timeline;}
}
