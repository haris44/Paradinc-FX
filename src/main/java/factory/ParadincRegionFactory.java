package factory;

import controllers.ParadincRegionController;
import map.ParadincRegion;

/**
 * Created by Alex on 22/01/2017.
 */
public class ParadincRegionFactory {

	public static ParadincRegionController createRegion(){
		ParadincRegionController controller = new ParadincRegionController();

		ParadincRegion europe = new ParadincRegion("Europe");
		ParadincRegion amerique = new ParadincRegion("Amerique");
		controller.addRegions(europe);
		controller.addRegions(amerique);
		return controller;

	}

}
