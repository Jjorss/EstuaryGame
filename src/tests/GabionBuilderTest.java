package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import model.GabionBuilder;

public class GabionBuilderTest {

	@Test
	public void test() {
		GabionBuilder gabion = new GabionBuilder();
		
		int prevNumLayers = gabion.getNumberOfLayers();
		int prevNumGabions = gabion.getNumberOfGabions();
		int prevCollectionSize = gabion.getGabions();
		
		gabion.build();
		
		Assert.assertTrue(gabion.getNumberOfGabions() > prevNumGabions);
		Assert.assertTrue(prevNumLayers > gabion.getNumberOfLayers());
		Assert.assertTrue(gabion.getGabions() > prevCollectionSize);
	}

}
