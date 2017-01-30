package factory;

import controllers.ParadincRegionController;
import map.ParadincMap;
import map.ParadincRegion;

/**
 * Created by Alex on 22/01/2017.
 */
public class ParadincRegionFactory {

	public static ParadincRegionController createRegion(){
		ParadincRegionController controller = new ParadincRegionController();

		ParadincMap map = new ParadincMap();

		ParadincRegion europe = new ParadincRegion("Europe", map.getEurope());
		ParadincRegion amerique = new ParadincRegion("Amerique", map.getAmerica());
		controller.addRegions(europe);
		controller.addRegions(amerique);

		controller.setMap(map);
		return controller;

	}

}
