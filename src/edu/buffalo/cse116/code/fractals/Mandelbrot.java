package edu.buffalo.cse116.code.fractals;
/**
 * This is the Mandelbrot Fractal Class.
 * Class which extends the Main Image Fractal Class.
 * @author Nick
 * @author Ryan
 * @author Lawzeem
 * @author Philip  
 * */
public class Mandelbrot extends ImageFractals {
	/**The method that generates Mandelbrot Fractals.*/
	public Mandelbrot(int fractalSize, int escDist, int passes, double xRangeStart, double xRangeEnd, double yRangeStart, double yRangeEnd){
		/**Giving the Fractal Size and x and y ranges to the method.*/
		super(fractalSize, escDist, passes, xRangeStart, xRangeEnd, yRangeStart, yRangeEnd);	
	}
	/**
	 * Methods used adjust the X and Y points
	 * @return adjX and adjY used in the checkDis method.
	 * */
	@Override
	public double adjX(double xCalc, double yCalc, double xCoord, double yCoord){
		return super.adjX(xCalc, yCalc,xCoord, yCoord) + xCoord;
	}
	@Override
	public double adjY(double xCalc, double yCalc, double xCoord, double yCoord){
		return super.adjY(xCalc, yCalc, xCoord, yCoord) + yCoord;
	}
}
