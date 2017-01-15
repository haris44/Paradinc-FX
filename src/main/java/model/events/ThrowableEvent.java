package model.events;

import model.events.Event;

/**
 * Created by Alex on 05/12/2016.
 */
public class ThrowableEvent {
	Event event;
	int durability;
	int probability;

	public ThrowableEvent(Event event, int durability, int probability){
		this.durability = durability;
		this.event = event;
		this.probability = probability;
	}
}
