package model;

import java.util.ArrayList;
import java.util.Collection;

public class GabionBuilder extends Builder {

	private int numberOfGabions;
	private int gabions = 3;
	private int numOfOysters;
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
		System.out.println("Num of Gabions: " + this.gabions);
		System.out.println("Num of Oysters Collected: " + numOfOystersCollected);
		System.out.println("Num of Oysters TOTAL: " + this.numOfOysters);
		
	}

	public int getNumberOfGabions() {
		return numberOfGabions;
	}

	public void setNumberOfGabions(int numberOfGabions) {
		this.numberOfGabions = numberOfGabions;
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

	
	

}
