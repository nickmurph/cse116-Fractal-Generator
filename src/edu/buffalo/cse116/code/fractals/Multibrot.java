package edu.buffalo.cse116.code.fractals;
/**
 * This is the Multibrot Fractal Class.
 * Class which extends the Main Image Fractal Class.
 * @author Nick
 * @author Ryan
 * @author Lawzeem
 * @author Philip  
 * */
public class Multibrot extends ImageFractals {
	/**The method that generates Multibrot Fractals.*/
	public Multibrot(int fractalSize, int escDist, int passes, double xRangeStart, double xRangeEnd, double yRangeStart, double yRangeEnd){
		/**Giving the Fractal Size and x and y ranges to the method.*/
		super(fractalSize, escDist, passes, xRangeStart, xRangeEnd, yRangeStart, yRangeEnd);	
	}
	/**
	 * Methods used adjust the X and Y points
	 * @return adjX and adjY used in the checkDis method.
	 * */
	@Override
	public double adjX(double xCalc, double yCalc, double xCoord, double yCoord){
		return  (Math.pow(xCalc, 3)- ( 3 * xCalc * Math.pow(yCalc, 2)) + xCoord);
	}
	@Override
	public double adjY(double xCalc, double yCalc, double xCoord, double yCoord){
		return ( (3 * Math.pow(xCalc, 2) * yCalc) - Math.pow(yCalc, 3) + yCoord);
	}
}
