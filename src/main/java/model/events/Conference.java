package model.events;

import model.Game;

/**
 * Created by Alex on 05/12/2016.
 */
public class Conference extends Event {

	public Conference(String nom, int price){
		super(nom, price);

	}

	@Override
	public ThrowableEvent getThrowable(Game game) {
		return null;
	}

}
