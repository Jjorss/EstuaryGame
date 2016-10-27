package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Wave;

public class WaveTest {

	Wave w;

	@Before
	public void setUp() throws Exception {
		w = new Wave(1, 5, 5);
	}


	@Test
	public void MoveTest() {
		int previousX = w.getX();
		int previousY = w.getY();
		w.move();
		// wave moved in a direction
		assertTrue(w.getX()*w.getSpeed() != previousX);
		//wave moved in the correct direction
		assertTrue(w.getX()*w.getSpeed() < previousX);
		//wave isn't moving vertically
		assertTrue(w.getY() == previousY);
		
	}

}
