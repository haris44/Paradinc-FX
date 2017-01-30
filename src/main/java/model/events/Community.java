package model.events;

import javafx.util.Pair;
import map.ParadincRegion;
import model.Game;
import model.language.Attribute;
import model.language.PlatformType;

import java.util.ArrayList;

/**
 * Created by Alex on 30/01/2017.
 */
public class Community extends Event {

	Pair<Integer, Double> facility;
	Pair<Integer, Double> robustness;
	ArrayList<Attribute> platform;

	public Community(String nom, int price, int lenghtStars, int lenghtContamination, Pair<Integer, Double> facility, Pair<Integer, Double> robustness, ArrayList<Attribute> platform) {
		super(nom, price, lenghtStars, lenghtContamination);
		this.facility = facility;
		this.robustness = robustness;
		this.platform = platform;
	}

	@Override
	public ThrowableEvent getThrowable(Game game) {
		ArrayList<Integer> proba = new ArrayList<>();
		proba.add(this.processAttribute(this.robustness, game.getLanguage().robustness));
		proba.add(this.processAttribute(this.facility, game.getLanguage().facility));
		proba.add(this.processAttribute(platform, game, PlatformType.class));
		ParadincRegion regions = game.getRegionController().pickRegions();
		return new ThrowableEvent(this, 1, this.getMoyenne(proba), price, regions);

	}
}
