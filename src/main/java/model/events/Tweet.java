package model.events;

import javafx.util.Pair;
import map.ParadincRegion;
import model.Game;
import model.language.Attribute;
import model.language.PlatformType;

import java.util.ArrayList;


public class Tweet extends Event {

	Pair<Integer, Double> facility;
	Pair<Integer, Double> robustness;
	ArrayList<Attribute> platform;

	public Tweet(String nom, int price, int lenghtStars, int lenghtContamination, Pair<Integer, Double> robustness, Pair<Integer, Double> facility, ArrayList<Attribute> platform){
		super(nom, price, lenghtStars, lenghtContamination);
		this.facility = facility;
		this.robustness = robustness;
		this.platform = platform;
	}

	@Override
	public ThrowableEvent getThrowable(Game game) {
		ArrayList<Integer> proba = new ArrayList<>();
		proba.add(processAttribute(facility, game.getLanguage().getFacility()));
		proba.add(processAttribute(robustness, game.getLanguage().getRobustness()));
		proba.add(this.processAttribute(platform, game, PlatformType.class));
		ParadincRegion regions = game.getRegionController().pickRegions();
		return new ThrowableEvent(this, 1, this.getMoyenne(proba), price, regions);
	}
}
