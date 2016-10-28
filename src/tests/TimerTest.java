package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Timer;


public class TimerTest {
	
	@Test
	public void test(){
		Timer tester1 = new Timer();
		Timer tester2 = new Timer();
		
		tester1.setTime(10);
		tester1.countDown();
		
		tester2.setTime(0);
		tester2.countDown();
		
		assertTrue(tester1.getTime() < 10);
		assertTrue(tester2.getTime() == 0);
	}
}
