package detector;

import static org.junit.Assert.*;

import org.junit.Test;

public class DetectorTest {

	@Test
	public void testIsEndOfEstablished() {
		Detector t = new Detector();
		
		assertEquals(false, t.isEndOfEstablished(1000));
		assertEquals(false, t.isEndOfEstablished(1000));
		assertEquals(false, t.isEndOfEstablished(1000));
		assertEquals(false, t.isEndOfEstablished(950));
		assertEquals(true , t.isEndOfEstablished(900));
		assertEquals(false, t.isEndOfEstablished(920));

	}

	@Test
	public void testCalcMovingAverage1() {
		Detector testclass = new Detector();
		
		int input[] = {15, 68, 83};
		int expect[] = {68, 83, 12};
		
		int actuals[] = testclass.calcMovingAverage(input, 12, 3);
		assertArrayEquals(expect, actuals);
	}
	@Test
	public void testCalcMovingAverage2() {
		Detector testclass = new Detector();
		
		int input[]  = {1,2,3,4,5,6,7,8};
		int expect[] = {2,3,4,5,6,7,8,9};
		
		int actuals[] = testclass.calcMovingAverage(input, 9, 8);
		assertArrayEquals(expect, actuals);
	}
	
	@Test
	public void testCalcAverage1() {
		Detector t = new Detector();
		
		int input[]  = {1,2,3,4,5,6,7,8};
		assertEquals(4.5, t.calcAverage(input), 0.0);
	}
	@Test
	public void testCalcAverage2() {
		Detector t = new Detector();
		
		int input[] = new int[5];
		input = t.calcMovingAverage(input, 3, 5);
		assertEquals(0.6, t.calcAverage(input), 0.0);
		input = t.calcMovingAverage(input, 3, 5);
		assertEquals(1.2, t.calcAverage(input), 0.0);
		input = t.calcMovingAverage(input, 4, 5);
		assertEquals(2, t.calcAverage(input), 0.0);
		input = t.calcMovingAverage(input, 8, 5);
		assertEquals(3.6, t.calcAverage(input), 0.0);
		input = t.calcMovingAverage(input, 8, 5);
		assertEquals(5.2, t.calcAverage(input), 0.0);
		input = t.calcMovingAverage(input, 1, 5);
		assertEquals(4.8, t.calcAverage(input), 0.0);
	}
}
