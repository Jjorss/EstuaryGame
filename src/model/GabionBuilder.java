package model;

import java.util.ArrayList;
import java.util.Collection;

public class GabionBuilder extends Builder {

	private int numberOfGabions;
	private int gabions;
	private int numOfOysters;
	private int maxGabionCapacity;
	
	@Override
	public void build(){
	}
	
	public void build(int numOfOystersCollected) {
		this.numOfOysters += numOfOystersCollected;
		
		if(this.numOfOysters >= this.maxGabionCapacity){
			this.numOfOysters -= this.maxGabionCapacity;
			this.gabions += 1;
		}else{
			this.numOfOysters += numOfOystersCollected;
		}
		
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

	public int getNumberOfLayers() {
		return numOfOysters;
	}

	public void setNumberOfLayers(int numberOfLayers) {
		this.numOfOysters = numberOfLayers;
	}

	
	

}
