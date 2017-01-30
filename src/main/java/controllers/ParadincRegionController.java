package controllers;

import map.ParadincMap;
import map.ParadincRegion;
import model.Game;
import model.events.ThrowableEvent;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Alex on 22/01/2017.
 */
public class ParadincRegionController {

	ArrayList<ParadincRegion> listRegions;
	ParadincMap map;

	public ParadincRegionController(){
		listRegions = new ArrayList<>();
	}

	public int getGlobalContamination(){
		int conta = 0;
		for (ParadincRegion region : listRegions) {
			conta += region.getContamination();
		}
		return conta;
	}
	public ParadincRegion pickRegions(){
		int somme = 0;
		for (ParadincRegion ev : listRegions) {
			somme += ev.getContamination();
		}

		Random rand = new Random();
		ParadincRegion currEvent = null;

		int nombre = rand.nextInt(somme - 1) + 1; //Entre 1 et la sommes des ponderation
		int i = 0;

		while (nombre >= 0){
			currEvent = listRegions.get(i);
			nombre = nombre - currEvent.getContamination();
			i += 1;
		}

		return currEvent;
	}
	// MUST REFACTO / PLEASE, DO NOT USE STRING NAME TO PICK REGION
	public ParadincRegion getRegions(String name){
		for (ParadincRegion regions: listRegions) {
			if(regions.getName() == name){
				return regions;
			}
		}
		return null;
	}
	public void addRegions(ParadincRegion region){
		listRegions.add(region);
	}
	public ArrayList<ParadincRegion> getListRegions() {
		return listRegions;
	}

	public void setMap(ParadincMap map) {
		this.map = map;
	}

	public ParadincMap getMap() {
		return map;
	}
}
