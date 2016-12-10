package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class GabionBuilder extends Builder implements Serializable{

	private int gabions = 0;
	private int numOfOysters = 0;
	private final int maxGabionCapacity = 5;

	/**
	 * Overrides abstract class Builder's build() function 
	 */
	@Override
	public void build() {
		this.numOfOysters ++;
		if (this.numOfOysters >= this.maxGabionCapacity) {
			this.numOfOysters -= this.maxGabionCapacity;
			this.gabions += 1;
		}
	}

	/**
	 * Get number of gabions.
	 * @return int number of gabions
	 */

	public int getGabions() {
		return gabions;
	}
	/**
	 * Set number of gabions.
	 * @param gabions
	 */
	public void setGabions(int gabions) {
		this.gabions = gabions;
	}
	/**
	 * Get number of oysters
	 * @return number of oysters
	 */
	public int getNumberOfOysters() {
		return numOfOysters;
	}
	/**
	 * Set number of oysters equal to the number of layers
	 * @param desired numberOfLayers (int)
	 */
	public void setNumberOfLayers(int numberOfLayers) {
		this.numOfOysters = numberOfLayers;
	}
	/**
	 * Getter for maxGabionCapacity.
	 * @return int maxGabionCapacity
	 */
	public int getMaxGabionCapacity() {
		return maxGabionCapacity;
	}

}
