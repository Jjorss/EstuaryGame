package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.RunOff;

public class RunoffTest {

	RunOff run;
	@Before
	public void setUp() throws Exception {
		run = new RunOff(8, 0, 0, 0);
	}

	@Test
	public void testMove() {
		int prevX = run.getX();
		int prevY = run.getY();
		run.move();
		assertTrue(run.getX()!=prevX);
		assertTrue(run.getY() == prevY);
	}
	@Test
	public void testSpeed() {
		
		run.setSpeed(20);
		assertTrue(run.getSpeed() == 20);
	}
	@Test
	public void testX() {
		run.setX(50);
		assertTrue(run.getX() == 50);
	}
	@Test
	public void testY() {
		run.setY(2);
		assertTrue(run.getY() == 2);
	}
	@Test
	public void testRowNum() {
		run.setRowNum(4);
		assertTrue(run.getRowNum() == 4);
	}

}
