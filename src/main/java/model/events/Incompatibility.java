package model.events;

import javafx.util.Pair;
import map.ParadincRegion;
import model.Game;
import model.language.Attribute;
import model.language.Platform;
import model.language.PlatformType;

import java.util.ArrayList;

/**
 * Created by Alex on 29/01/2017.
 */
public class Incompatibility extends Event {

	private ArrayList<Attribute> platform;
	Pair<Integer, Double> robustness;

	public Incompatibility(String nom, int price, int lenghtStars, int lenghtContamination,	ArrayList<Attribute> platform,  Pair<Integer, Double> robustness) {
		super(nom, price, lenghtStars, lenghtContamination);
		this.platform = platform;
		this.robustness = robustness;

	}

	@Override
	public ThrowableEvent getThrowable(Game game) {
		ArrayList<Integer> proba = new ArrayList<>();
		proba.add(this.processAttribute(this.robustness, game.getLanguage().robustness));
		proba.add(this.processAttribute(platform, game, PlatformType.class));

		ParadincRegion regions = game.getRegionController().pickRegions();

		return new ThrowableEvent(this, 1, this.getMoyenne(proba), price, regions);
	}
}
