package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import model.GabionBuilder;

public class GabionBuilderTest {

	@Test
	public void test() {
		GabionBuilder gabion = new GabionBuilder();
		
		int prevNumOyster = gabion.getNumberOfOysters();
		int prevNumGabions = gabion.getGabions();
		
		gabion.build();
		// if number of oyster increase
		Assert.assertTrue(prevNumOyster < gabion.getNumberOfOysters());
		// if number of gabions increase
		gabion.setNumberOfLayers(4);
		gabion.build();
		assertTrue(gabion.getGabions() > prevNumGabions);
		assertTrue(gabion.getNumberOfOysters() == 0);
	}

}
