package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class GabionBuilder extends Builder implements Serializable{

	private int gabions = 0;
	private int numOfOysters = 0;
	private int maxGabionCapacity = 5;

	@Override
	public void build() {
		this.numOfOysters ++;
		if (this.numOfOysters >= this.maxGabionCapacity) {
			this.numOfOysters -= this.maxGabionCapacity;
			this.gabions += 1;
		}
	}

//	public void build(int numOfOystersCollected) {
//		this.numOfOysters += numOfOystersCollected;
//
//		if (this.numOfOysters >= this.maxGabionCapacity) {
//			this.numOfOysters -= this.maxGabionCapacity;
//			this.gabions += 1;
//		}
//	}

	public int getGabions() {
		return gabions;
	}

	public void setGabions(int gabions) {
		this.gabions = gabions;
	}

	public int getNumberOfOysters() {
		return numOfOysters;
	}

	public void setNumberOfLayers(int numberOfLayers) {
		this.numOfOysters = numberOfLayers;
	}

	public int getMaxGabionCapacity() {
		return maxGabionCapacity;
	}

}
