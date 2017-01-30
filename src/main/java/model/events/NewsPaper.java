package model.events;

import javafx.util.Pair;
import map.ParadincRegion;
import model.Game;

import java.util.ArrayList;

/**
 * Created by Alex on 30/01/2017.
 */
public class NewsPaper extends Event {

	Pair<Integer, Double> robustness;
	Pair<Integer, Double> facility;

	public NewsPaper(String nom, int price, int lenghtStars, int lenghtContamination, Pair<Integer, Double> robustness, Pair<Integer, Double> facility) {
		super(nom, price, lenghtStars, lenghtContamination);
		this.robustness = robustness;
		this.facility = facility;
	}

	@Override
	public ThrowableEvent getThrowable(Game game) {
		ArrayList<Integer> proba = new ArrayList<>();
		proba.add(processAttribute(robustness, game.getLanguage().getRobustness());
		proba.add(processAttribute(facility, game.getLanguage().getFacility()));
		ParadincRegion regions = game.getRegionController().pickRegions();
		return new ThrowableEvent(this, 1, this.getMoyenne(proba), price, regions);
	}
}
