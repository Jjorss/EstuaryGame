package model;

import java.util.ArrayList;
import java.util.Collection;

public class GabionBuilder extends Builder {

	
	private int gabions = 0;
	private int numOfOysters = 0;
	private int maxGabionCapacity = 20;
	
	@Override
	public void build(){
	}
	
	public void build(int numOfOystersCollected) {
		this.numOfOysters += numOfOystersCollected;
		
		if(this.numOfOysters >= this.maxGabionCapacity){
			this.numOfOysters -= this.maxGabionCapacity;
			this.gabions += 1;
		}
		//System.out.println("Num of Gabions: " + this.gabions);
		//System.out.println("Num of Oysters Collected: " + numOfOystersCollected);
		//System.out.println("Num of Oysters TOTAL: " + this.numOfOysters);
		
	}

	
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
