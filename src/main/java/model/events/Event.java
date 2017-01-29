package model.events;

import javafx.util.Pair;
import model.Game;
import model.language.Attribute;

import java.util.ArrayList;

/**
 * Created by Alex on 05/12/2016.
 */
public abstract class Event {

	int price;
	String nom;
	int lenghtStars;
	int lenghtContamination;


	public Event(String nom, int price, int lenghtStars, int lenghtContamination){
		this.nom = nom;
		this.lenghtContamination = lenghtContamination;
		this.lenghtStars = lenghtStars;
		this.price = price;

	}

	public int getMoyenne(ArrayList<Integer> list){
		int somme = 0;
		for (int i: list) {
			somme += i;
		}
		return somme / list.size();
	}
	public int processAttribute(Pair<Integer, Double> EventProps, int GameProps){
			if((int) (100 - Math.round(Math.abs(GameProps - EventProps.getKey())) * EventProps.getValue()) > 0) {
				return (int) (100 - Math.round(Math.abs(GameProps - EventProps.getKey())) * EventProps.getValue());
			}
			else{
				return 0;
			}
	}

	public int processAttribute(ArrayList<Attribute> props, Game game, Class type){
		boolean isOkay = false;
		ArrayList<Attribute> langProps = game.getLanguage().getAttributes();
		for(Attribute prop : props) {
			if (game.getLanguage().getAttributes().contains(prop) && type.isInstance(langProps.get(langProps.lastIndexOf(props)))){
				isOkay = true;
			}
		}
		return isOkay ? 100 : 0;
	}


	public String getName(){
	    return nom;
    }

	public boolean isBuyable(){return price > 0; }
	public abstract ThrowableEvent getThrowable(Game game);

	public int getPrice() {
		return price;
	}
}
