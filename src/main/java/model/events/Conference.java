package model.events;

import map.ParadincRegion;
import model.Game;

import java.util.ArrayList;

/**
 * Created by Alex on 05/12/2016.
 */
public class Conference extends Event {

	public Conference(String nom, int price, int lenghtStars, int lenghtContamination){
		super(nom, price, lenghtStars, lenghtContamination);
	}

	@Override
	public ThrowableEvent getThrowable(Game game) {

		ParadincRegion regions = game.getRegionController().pickRegions();

		return new ThrowableEvent(this, 1, 0, price, regions);


	}

}
