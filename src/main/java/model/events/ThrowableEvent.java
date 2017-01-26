package model.events;

import controllers.GameController;
import map.ParadincRegion;
import model.Game;
import model.events.Event;
import views.TimelineView;

/**
 * Created by Alex on 05/12/2016.
 */
public class ThrowableEvent {
	public Event event;
	ParadincRegion region;
	public int durability;
	public int probability;
	public int price;


	// MUST REFACTO WITH GETTER AND SETTER
	public void throwEvent(GameController game, TimelineView timelineView ){
		game.setStars(game.getStars() - price);
		timelineView.tweet(this.event.nom);
		double probaLenght = ((float) probability) / 100.0;
		int stars = (int) Math.round(event.lenghtStars * probaLenght);
		int contamination = (int) Math.round(event.lenghtContamination * probaLenght);
		region.setContamination(region.getContamination() + contamination);
		game.setStars(game.getStars() + stars);


		// REMOVE BEFORE FLIGHT
		System.out.println("====== THROW EVENT ======");
		System.out.println("Name : "  + this.event.nom);
		System.out.println("Regions : "  + region.getName());
		System.out.println("Probability : "  + probability);
		System.out.println("Probability lenght: "  + probaLenght);
		System.out.println("Stars impact : " + event.lenghtStars * probaLenght);
		System.out.println("contamination impact : " + event.lenghtContamination * probaLenght);
		System.out.println("=========================");

	}

	public ThrowableEvent(Event event, int durability, int probability, int price, ParadincRegion region){
		this.durability = durability;
		this.event = event;
		this.probability = probability;
		this.region = region;
		this.price = price;
	}
}
