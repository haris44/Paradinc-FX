package model.events;

import javafx.util.Pair;
import model.Game;

import java.util.ArrayList;

/**
 * Created by Alex on 05/12/2016.
 */
public abstract class Event {

	int price;
	String nom;
	boolean buyable = false;
	int lenghtStars;
	int lenghtContamination;


	public Event(String nom, int price, int lenghtStars, int lenghtContamination){
		this.nom = nom;
		this.lenghtContamination = lenghtContamination;
		this.lenghtStars = lenghtStars;
		this.price = price;
		if(price > 0 ){
			this.buyable = true;
		}

	}

	public int getMoyenne(ArrayList<Integer> list){
		int somme = 0;
		for (int i: list) {
			somme += i;
		}
		return somme / list.size();
	}
	public int processAttribute(Pair<Integer, Double> EventProps, int GameProps){
		return (int) (100 - Math.round(Math.abs(GameProps - EventProps.getKey()))* EventProps.getValue());
	}
	public String getName(){
	    return nom;
    }

	public boolean isBuyable(){return  this.buyable;}
	public abstract ThrowableEvent getThrowable(Game game);
}
