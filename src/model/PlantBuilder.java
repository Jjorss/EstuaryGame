package model;

import java.io.Serializable;

public class PlantBuilder extends Builder implements Serializable{

	private int numberOfPlants = 0;
	private Timer timer;
	private int numOfSecondsPerPlant = 6;
	private boolean addPlant = false;

	public PlantBuilder(Timer timer) {
		this.timer = timer;
	}
	
	@Override
	public void build() {
		if (timer.getTime() >= numOfSecondsPerPlant) {
			this.setAddPlant(true);
		} else {
			this.addPlant = false;
		}
		//System.out.println(this.numberOfPlants + "\t" + timer.getTime() % this.numOfSecondsPerPlant);
	}

	public int getNumberOfPlants() {
		return numberOfPlants;
	}

	public void setNumberOfPlants(int numberOfPlants) {
		this.numberOfPlants = numberOfPlants;
	}
	
	public void setAddPlant(boolean add) {
		if (add != this.addPlant) {
			this.numberOfPlants++;
			this.addPlant = add;
		}
	}

	public int getNumOfSecondsPerPlant() {
		return numOfSecondsPerPlant;
	}
	
}
