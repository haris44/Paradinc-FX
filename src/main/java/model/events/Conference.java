package model.events;

import javafx.util.Pair;
import map.ParadincRegion;
import model.Game;

import java.util.ArrayList;


public class Conference extends Event {

	Pair<Integer, Double> contamination;
	Pair<Integer, Double> facility;
	Pair<Integer, Double> robustness;

	public Conference(String nom, int price, int lenghtStars, int lenghtContamination, Pair<Integer, Double> contamination,  Pair<Integer, Double> facility,  Pair<Integer, Double> robustness){
		super(nom, price, lenghtStars, lenghtContamination);
		this.contamination = contamination;
		this.facility = facility;
		this.robustness = robustness;
	}

	@Override
	public ThrowableEvent getThrowable(Game game) {
		ArrayList<Integer> proba = new ArrayList<>();
		proba.add(this.processAttribute(this.robustness, game.getLanguage().robustness));
		proba.add(this.processAttribute(this.facility, game.getLanguage().facility));
		proba.add(processAttribute(contamination, game.getRegionController().getGlobalContamination()));
		ParadincRegion regions = game.getRegionController().pickRegions();
		return new ThrowableEvent(this, 1, this.getMoyenne(proba), price, regions);
	}

}
