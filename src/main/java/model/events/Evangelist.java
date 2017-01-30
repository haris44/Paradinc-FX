package model.events;

import javafx.util.Pair;
import map.ParadincRegion;
import model.Game;

import java.util.ArrayList;

/**
 * Created by Alex on 30/01/2017.
 */
public class Evangelist extends Event {

	Pair<Integer, Double> contamination;
	Pair<Integer, Double> facilty;

	public Evangelist(String nom, int price, int lenghtStars, int lenghtContamination, Pair<Integer, Double> contamination, Pair<Integer, Double> facility) {
		super(nom, price, lenghtStars, lenghtContamination);
		this.contamination = contamination;
		this.facilty = facility;
	}

	@Override
	public ThrowableEvent getThrowable(Game game) {
		ArrayList<Integer> proba = new ArrayList<>();
		proba.add(processAttribute(contamination, game.getRegionController().getGlobalContamination()));
		proba.add(processAttribute(facilty, game.getLanguage().getFacility()));
		ParadincRegion regions = game.getRegionController().pickRegions();
		return new ThrowableEvent(this, 1, this.getMoyenne(proba), price, regions);
	}
}
