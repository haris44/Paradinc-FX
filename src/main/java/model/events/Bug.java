package model.events;

import javafx.util.Pair;
import model.Game;

import java.util.ArrayList;


public class Bug extends Event {

	Pair<Integer, Double> robustness;
	Pair<Integer, Double> facility;

	public Bug(String nom, int price, Pair<Integer, Double> robustness, Pair<Integer, Double> facility){
		super(nom, price);
		this.robustness = robustness;
		this.facility = facility;
	}

	@Override
	public ThrowableEvent getThrowable(Game game) {
		ArrayList<Integer> proba = new ArrayList<Integer>();
		proba.add(this.processAttribute(this.robustness, game.getLanguage().robustness));
		proba.add(this.processAttribute(this.facility, game.getLanguage().facility));

		return new ThrowableEvent(this, 1, this.getMoyenne(proba));
	}


}