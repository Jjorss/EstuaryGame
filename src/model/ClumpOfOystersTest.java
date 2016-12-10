package model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.ClumpOfOysters;

public class ClumpOfOystersTest {
		
	@Test(expected=UnsupportedOperationException.class)
	public void exceptionTest() {
		ClumpOfOysters tester = new ClumpOfOysters(0, 0);
	    tester.move();
	}
	
	@Test
	public void visibilityTest() {
		ClumpOfOysters tester = new ClumpOfOysters(0, 0);
		assertTrue(tester.isVisible());
		tester.setVisible(false);
		assertFalse(tester.isVisible());
	}
	
	@Test
	public void testGetX(){
		ClumpOfOysters tester = new ClumpOfOysters(0, 0);
		assertTrue(tester.getX() == 0);
	}
	
	@Test
	public void testGetY() {
		ClumpOfOysters tester = new ClumpOfOysters(0,0);
		assertTrue(tester.getX() == 0);
	}
	
	@Test
	public void testSetX() {
		ClumpOfOysters tester = new ClumpOfOysters(0,0);
		tester.setX(5);
		assertTrue(tester.getX() == 5);
	}
	
	@Test
	public void testSetY() {
		ClumpOfOysters tester = new ClumpOfOysters(0,0);
		tester.setY(8);
		assertTrue(tester.getY() == 8);
		
	}
	
	public void testSetAndGetNumOfOystersInClump() {
		ClumpOfOysters tester = new ClumpOfOysters(0,0);
		assertTrue(tester.getNumOfOystersInClump() == 1);
		tester.setNumOfOystersInClump(3);
		assertTrue(tester.getNumOfOystersInClump() == 3);
	}
	@Test
	public void testVisible() {
		ClumpOfOysters tester = new ClumpOfOysters(0,0);
		assertTrue(tester.isVisible() == true);
		tester.setVisible(false);
		assertTrue(tester.isVisible() == false);
	}
	
}
