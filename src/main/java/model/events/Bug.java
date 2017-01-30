package model.events;

import javafx.util.Pair;
import map.ParadincRegion;
import model.Game;
import model.language.Attribute;
import model.language.Platform;
import model.language.PlatformType;

import java.util.ArrayList;


public class Bug extends Event {

	Pair<Integer, Double> robustness;
	Pair<Integer, Double> facility;
	ArrayList<Attribute> platform;



	public Bug(String nom, int lenghtStars, int lenghtContamination, int price,	ArrayList<Attribute> platform, Pair<Integer, Double> robustness, Pair<Integer, Double> facility){
		super(nom, price, lenghtStars, lenghtContamination);
		this.robustness = robustness;
		this.facility = facility;
		this.platform = platform;
	}

	@Override
	public ThrowableEvent getThrowable(Game game) {
		ArrayList<Integer> proba = new ArrayList<Integer>();
		proba.add(this.processAttribute(this.robustness, game.getLanguage().robustness));
		proba.add(this.processAttribute(this.facility, game.getLanguage().facility));
		proba.add(this.processAttribute(platform, game, PlatformType.class));
		ParadincRegion regions = game.getRegionController().pickRegions();

		return new ThrowableEvent(this, 1, this.getMoyenne(proba), price, regions);
	}


}
