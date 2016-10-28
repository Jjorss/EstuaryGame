package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.PlantBuilder;

import org.junit.Assert;
import org.junit.Test;


public class PlantBuilderTest {

	@Test
	public void test() {
		PlantBuilder plants = new PlantBuilder();
		
		int prevNumPlants = plants.getNumberOfPlants();
		
		plants.build();
		
		Assert.assertTrue(prevNumPlants<plants.getNumberOfPlants());
		
	}

}
