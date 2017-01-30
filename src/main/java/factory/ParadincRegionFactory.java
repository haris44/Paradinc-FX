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

		ParadincRegion europe = new ParadincRegion("Europe", map.getEurope(), map.getWorld());
		ParadincRegion amerique = new ParadincRegion("Amerique", map.getAmerica(), map.getWorld());
		ParadincRegion afrique = new ParadincRegion("Afrique", map.getAfrica(), map.getWorld());
		ParadincRegion oceanie = new ParadincRegion("Océanie", map.getOceanie(), map.getWorld());
		controller.addRegions(europe);
		controller.addRegions(amerique);
		controller.addRegions(afrique);
        controller.addRegions(oceanie);

		controller.setMap(map);
		return controller;

	}

}
