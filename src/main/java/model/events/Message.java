package model.events;

import model.Game;

/**
 * Created by Alex on 05/12/2016.
 */
public class Message extends Event {

	public Message(String nom, int lenghtStars, int lenghtContamination, int price){
		super(nom, price, lenghtStars, lenghtContamination);
	}

	@Override
	public ThrowableEvent getThrowable(Game game) {
		return null;
	}
}
