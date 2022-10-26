package tests;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import edu.buffalo.cse116.code.*;
import edu.buffalo.cse116.code.fractals.BurningShip;
import edu.buffalo.cse116.code.fractals.Julia;
import edu.buffalo.cse116.code.fractals.Mandelbrot;
import edu.buffalo.cse116.code.fractals.Multibrot;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * This is the JUnit Test Cases Class for the Image Fractals.
 * @author Nick
 * @author Ryan
 * @author Lawzeem
 * @author Philip  
 * */

public class ImageFractalsTest {
	int fractalMult = 2048;
	/**Instances of each Set we are testing.*/
	BurningShip bsTest = new BurningShip(fractalMult, 2, 255,-1.8, -1.7, -.08, .025);
	Julia jTest = new Julia(fractalMult, 2, 255, -1.7, 1.7, -1, 1);
	Mandelbrot mTest = new Mandelbrot(fractalMult, 2, 255,-2.15, .6, -1.3, 1.3);
	Multibrot m2Test = new Multibrot(fractalMult, 2, 255, -1, 1, -1.3, 1.3);



	@Before
	public void doFirst(){
		mTest.generateFractal(); 
		jTest.generateFractal();
		bsTest.generateFractal();
		m2Test.generateFractal();


		int[][] mEscapes = mTest.getEscapeTimes();
		int[][] jEscapes = jTest.getEscapeTimes();
		int[][] bsEscapes = bsTest.getEscapeTimes();
		int[][] m2Escapes = m2Test.getEscapeTimes();

				SwingWorkerSub sw = new SwingWorkerSub(bsTest, 0, fractalMult);
				bsTest.setEscapeArray(sw.generatePartial(0, fractalMult));
	}

	/**This Test checks if a pixel's row and column is translated to it's X and Y Coordinate.*/
	@Test
	public void rowsAndCols() {
		//Mandelbrot -2.15, .6, -1.3, 1.3
		double xCoord = mTest.getXCoordinateOfRow(0);
		double xCoord2 = mTest.getXCoordinateOfRow(fractalMult-1);
		double yCoord = mTest.getYCoordinateOfCol(0);
		double yCoord2 = mTest.getYCoordinateOfCol(fractalMult-1);
		assertEquals(xCoord, -2.15, 0.01);
		assertEquals(xCoord2, 0.6, 0.01);
		assertEquals(yCoord, -1.3, 0.01);
		assertEquals(yCoord2, 1.3, 0.01);

		//Julia -1.7, 1.7, -1, 1
		xCoord = jTest.getXCoordinateOfRow(0);
		xCoord2 = jTest.getXCoordinateOfRow(fractalMult-1);
		yCoord = jTest.getYCoordinateOfCol(0);
		yCoord2 = jTest.getYCoordinateOfCol(fractalMult-1);
		assertEquals(xCoord, -1.7, 0.01);
		assertEquals(xCoord2, 1.7, 0.01);
		assertEquals(yCoord, -1.0, 0.01);
		assertEquals(yCoord2, 1.0, 0.01);

		//BurningShip -1.8, -1.7, -.08, .025
		xCoord = bsTest.getXCoordinateOfRow(0);
		xCoord2 = bsTest.getXCoordinateOfRow(fractalMult-1);
		yCoord = bsTest.getYCoordinateOfCol(0);
		yCoord2 = bsTest.getYCoordinateOfCol(fractalMult-1);
		assertEquals(xCoord, -1.8, 0.01);
		assertEquals(xCoord2, -1.7, 0.01);
		assertEquals(yCoord, -.08, 0.01);
		assertEquals(yCoord2, .025, 0.01);

		//Multibrot -1, 1, -1.3, 1.3
		xCoord = m2Test.getXCoordinateOfRow(0);
		xCoord2 = m2Test.getXCoordinateOfRow(fractalMult-1);
		yCoord = m2Test.getYCoordinateOfCol(0);
		yCoord2 = m2Test.getYCoordinateOfCol(fractalMult-1);
		assertEquals(xCoord, -1, 0.01);
		assertEquals(xCoord2, 1, 0.01);
		assertEquals(yCoord, -1.3, 0.01);
		assertEquals(yCoord2, 1.3, 0.01);
	}


	/**This test checks all four fractals with "coordinate(s) whose distance from the origin never exceeds the escape 
	 * distance". The quoted portion means that all four of these coordinates will run through the entire while loop,
	 * until they hit the 255 barrier (because none of them will hit the 2 dist barrier). Thus, we expect the escape 
	 * time to be 255 passes*/
	@Test
	public void calcEscapeTimesPartA(){

		//Mandelbrot
		int testVal = mTest.calcEscapeTime(0.3207031250000001, -0.07109374999999386);
		assertEquals(255,testVal,0.01);
		//Julia
		testVal = jTest.calcEscapeTime(1.0492187499999897, -0.234375);
		assertEquals(255,testVal,0.01);
		//BurningShip
		testVal = bsTest.calcEscapeTime(-1.7443359374999874, -0.017451171875000338);
		assertEquals(255,testVal,0.01);
		//Multibrot
		testVal = m2Test.calcEscapeTime(0.5859375, 0.24375000000000108);
		assertEquals(255,testVal,0.01);
	}

	/**This test checks the three required fractals with "coordinate(s) whose distance from the origin exceeds 
	 * the escape distance after a single loop pass". The quoted part means that each of these test coordinates will 
	 * only complete one loop, so we expect each to have an escape time of 1.*/
	@Test
	public void calcEscapeTimesPartB(){
		//Mandelbrot
		int testVal = mTest.calcEscapeTime(0.5946289062500001, 1.2949218750000122);
		assertEquals(1,testVal,0.01);
		//Julia
		testVal = jTest.calcEscapeTime(1.6933593749999853, 0.9765625);
		assertEquals(1,testVal,0.01);
		//Multibrot
		testVal = m2Test.calcEscapeTime(0.9921875, 1.05625);
		assertEquals(1,testVal,0.01);
	}

	/** This Test calls findOneOrZeroEscVal, which returns a boolean (true if an escape value of 1 or 0 is found, false otherwise
	 * A return of false is desired, as a 1 or 0 in the BurningShip escapeTime 2D array would indicate a bug
	 * */
	@Test
	public void NoOneOrZeroEscVal(){
		boolean testVal = bsTest.findOneOrZeroEscVal();
		assertFalse(testVal);

		//set the escape time at location 0,0 to 0, run the test again
		//expect True this time, because we WILL find a 0 in the 2D array
		bsTest.setSingleEscapeTime(0, 0, 0);
		assertTrue(bsTest.getEscapeTimes()[0][0] == 0);
		testVal = bsTest.findOneOrZeroEscVal();
		assertTrue(testVal);	
	}

	/**This Test simply checks that the 2D int array returned by the updatePos/generateFractal method is a
	 * double array with size 512 x 512*/
	@Test
	public void checkEscapeArraySize(){
		//Mandelbrot
		int[][] mEscapes = mTest.getEscapeTimes();
		assertEquals(mEscapes.length, fractalMult);
		assertEquals(mEscapes[0].length, fractalMult);	

		//Julia
		int[][] jEscapes = jTest.getEscapeTimes();
		assertEquals(jEscapes.length, fractalMult);
		assertEquals(jEscapes[0].length, fractalMult);	

		//BurningShip
		int[][] bsEscapes = bsTest.getEscapeTimes();
		assertEquals(bsEscapes.length, fractalMult);
		assertEquals(bsEscapes[0].length, fractalMult);	

		//Multibrot
		int[][] m2Escapes = m2Test.getEscapeTimes();
		assertEquals(m2Escapes.length, fractalMult);
		assertEquals(m2Escapes[0].length, fractalMult);	
	}

	//	@Test 
	//	/**This Test runs a variety of checks on the Pixel class, ensuring that the various getters, setters and toString work properly*/
	//	public void PixelTest(){
	//		Pixel[][] pixelArray = mTest.getPixelArray();
	//		Point2D.Double testPoint = new Point2D.Double(10, 10);
	//		assertFalse(pixelArray[0][0].getCoordinate().equals(testPoint));
	//		pixelArray[0][0].setCoordinates(10, 10);
	//		assertTrue(pixelArray[0][0].getCoordinate().equals(testPoint));
	//		assertTrue(pixelArray[0][0].toString().equals("Pixel Coordinates: " + "(" + testPoint.getX() + "," + testPoint.getY() + ") " + "and Escape Time: " + pixelArray[0][0].getEscapeTime()));
	//		assertTrue(pixelArray[0][0].getEscapeTime() == 0);
	//		
	//	}	

	/**	This test checks all four fractals by setting the escDist to 3,  then finding the escape time 
	 * for coordinates "whose distance from the origin exceeds the escape distance after a 10 passes
	 *  of the loop", meaning we would expect them to have an escape time of 10.
	 *  */
	@Test 
	public void Phase2CalcTest(){
		//set escDist for all fractals to 3
		bsTest.setEscDist(3);
		mTest.setEscDist(3);
		jTest.setEscDist(3);
		m2Test.setEscDist(3);

		//test each fractal class
		//Mandelbrot
		int testVal = mTest.calcEscapeTime(0.46007827788650374, -0.3383561643835661);
		assertEquals(10,testVal,0.01);
		//Julia
		testVal = jTest.calcEscapeTime(1.4538160469667272, -0.13502935420743645);
		assertEquals(10,testVal,0.01);
		//BurningShip
		testVal = bsTest.calcEscapeTime(-1.6999999999999802, 0.0030136986301371603);
		assertEquals(10,testVal,0.01);
		//Multibrot
		testVal = m2Test.calcEscapeTime(0.7025440313111545, -0.5520547945205528);
		assertEquals(10,testVal,0.01);

		//reset all escDist to 2 so that if this test is called before another there is no interference
		bsTest.setEscDist(2);
		mTest.setEscDist(2);
		jTest.setEscDist(2);
		m2Test.setEscDist(2);
	}

	/**	This test checks all four fractals by setting the maximum escTime to 135.
	 *  */
	@Test
	public void Phase3Calctest(){
		//set escTimes for fractals to 135
		bsTest.setEscTime(135);
		jTest.setEscTime(135);
		mTest.setEscTime(135);
		m2Test.setEscTime(135);

		//test each coordinate
		int testVal = mTest.calcEscapeTime(0.3207031250000001, -0.07109374999999386);
		assertEquals(135,testVal,0.01);
		//Julia
		testVal = jTest.calcEscapeTime(1.0492187499999897, -0.234375);
		assertEquals(135,testVal,0.01);
		//BurningShip
		testVal = bsTest.calcEscapeTime(-1.7443359374999874, -0.017451171875000338);
		assertEquals(135,testVal,0.01);
		//Multibrot
		testVal = m2Test.calcEscapeTime(0.5859375, 0.24375000000000108);
		assertEquals(135,testVal,0.01);
		//reset escTimes to avoid interfering with other Tests
		bsTest.setEscTime(255);
		jTest.setEscTime(255);
		mTest.setEscTime(255);
		m2Test.setEscTime(255);

	}

}
