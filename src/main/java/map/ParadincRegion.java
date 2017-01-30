package map;

import javafx.scene.paint.Color;

/**
 * Created by Alex on 22/01/2017.
 */
public class ParadincRegion {

	// CRegion region ???
	private int contamination;
	private String name;
	CountryRegion region;
	World world;


	public ParadincRegion(String name, CountryRegion region, World world){
		this.name = name;
		this.world = world;
		this.region = region;
		setContamination(0);
	}

	public int getContamination() {
		return contamination;
	}

	public void setContamination(int contamination) {
		this.contamination = contamination;
		Double otherColor = Math.abs((contamination / 100.0) - 1 );
		Color newcolor =  new Color(1.0, otherColor, otherColor, 1.0);
		world.setFillColorOfRegion(region, newcolor);
		region.setColor(newcolor);
	}

	public String getName() {
		return name;
	}
}
