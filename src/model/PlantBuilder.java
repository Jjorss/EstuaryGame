package model;

import java.io.Serializable;

public class PlantBuilder extends Builder implements Serializable{

	private int numberOfPlants = 0;
	private Timer timer;
	private final int numOfSecondsPerPlant = 6;
	private boolean addPlant = false;

	public PlantBuilder(Timer timer) {
		this.timer = timer;
	}
	/**
	 * Sets the attribute addPlant to true if timer is larger or equal to numOfSecondsPerPlant.
	 * Otherwise sets the attribute addPlant to false.
	 */
	@Override
	public void build() {
		if (timer.getTime() >= numOfSecondsPerPlant) {
			this.setAddPlant(true);
		} else {
			this.addPlant = false;
		}
		//System.out.println(this.numberOfPlants + "\t" + timer.getTime());
	}
	/**
	 *Getter for the attribute numberOfPlants.
	 * @return The current instance of numberOfPlants.
	 */
	public int getNumberOfPlants() {
		return numberOfPlants;
	}
	/**
	 * Setter of the attribute numberOfPlants.
	 * @param numberOfPlants int The number of plants the player has to plant.
	 */
	public void setNumberOfPlants(int numberOfPlants) {
		this.numberOfPlants = numberOfPlants;
	}

	private void setAddPlant(boolean add) {
		if (add != this.addPlant) {
			this.numberOfPlants++;
			this.addPlant = add;
		}
	}
	/**
	 * Getter for the attribute numOfSecondsPerPlant.
	 * @return The current instance of numOfSecondsPerPlant.
	 */
	public int getNumOfSecondsPerPlant() {
		return numOfSecondsPerPlant;
	}
	
}
