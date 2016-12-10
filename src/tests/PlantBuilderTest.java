package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.PlantBuilder;
import model.Timer;

import org.junit.Assert;
import org.junit.Test;


public class PlantBuilderTest {

	@Test
	public void testBuild() {
		Timer timer = new Timer();
		PlantBuilder pb = new PlantBuilder(timer);
		int prevNumPlants = pb.getNumberOfPlants();
		
		timer.setTime(pb.getNumOfSecondsPerPlant());	
		//timer has now reached correct time to add a plant
		pb.build();
		Assert.assertTrue(prevNumPlants<pb.getNumberOfPlants());
		
		// reseting timer, plants should not build
		timer = new Timer();
		prevNumPlants = pb.getNumberOfPlants();
		pb.build();
		assertTrue(prevNumPlants==pb.getNumberOfPlants());
		
	}
	@Test
	public void testNumberOfPlants() {
		PlantBuilder pb = new PlantBuilder(null);
		pb.setNumberOfPlants(50);
		assertTrue(pb.getNumberOfPlants() == 50);
	}
	@Test
	public void testAddPlant() {
		PlantBuilder pb = new PlantBuilder(null);
		int prevNum = pb.getNumberOfPlants();
		pb.setAddPlant(true);
		assertTrue(pb.getNumberOfPlants() > prevNum);
		prevNum = pb.getNumberOfPlants();
		pb.setAddPlant(true);
		assertTrue(pb.getNumberOfPlants() == prevNum);
		pb.setAddPlant(false);
		pb.setAddPlant(true);
		assertTrue(pb.getNumberOfPlants() > prevNum);
	}
	@Test
	public void testPlantsPerSecond() {
		PlantBuilder pb = new PlantBuilder(null);
		// final;
		assertTrue(pb.getNumOfSecondsPerPlant() == 6);
	}
	

}
