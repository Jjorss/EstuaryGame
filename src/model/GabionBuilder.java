package model;

import java.util.ArrayList;
import java.util.Collection;

public class GabionBuilder extends Builder {

	private int numberOfGabions;
	private Collection<Gabion> gabions = new ArrayList<Gabion>();
	private int numberOfLayers;
	
	@Override
	public void build() {
		
		
	}

	public int getNumberOfGabions() {
		return numberOfGabions;
	}

	public void setNumberOfGabions(int numberOfGabions) {
		this.numberOfGabions = numberOfGabions;
	}

	public Collection<Gabion> getGabions() {
		return gabions;
	}

	public void setGabions(Collection<Gabion> gabions) {
		this.gabions = gabions;
	}

	public int getNumberOfLayers() {
		return numberOfLayers;
	}

	public void setNumberOfLayers(int numberOfLayers) {
		this.numberOfLayers = numberOfLayers;
	}
	
	

}
