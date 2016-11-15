package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import model.GabionBuilder;

public class GabionBuilderTest {

	@Test
	public void test() {
		GabionBuilder gabion = new GabionBuilder();
		
		int prevNumLayers = gabion.getNumberOfOysters();
		int prevNumGabions = gabion.getGabions();
		int prevCollectionSize = gabion.getGabions();
		
		gabion.build();
		
		Assert.assertTrue(gabion.getGabions() > prevNumGabions);
		Assert.assertTrue(prevNumLayers > gabion.getNumberOfOysters());
		Assert.assertTrue(gabion.getGabions() > prevCollectionSize);
	}

}
