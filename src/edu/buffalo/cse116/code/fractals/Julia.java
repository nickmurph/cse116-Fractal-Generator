package edu.buffalo.cse116.code.fractals;
/**
 * This is the Julia Fractal Class.
 * Class which extends the Main Image Fractal Class.
 * @author Nick
 * @author Ryan
 * @author Lawzeem
 * @author Philip  
 * */
public class Julia extends ImageFractals {
	/**The method that generates Julia Fractals.*/
	public Julia(int fractalSize, int escDist, int passes, double xRangeStart, double xRangeEnd, double yRangeStart, double yRangeEnd){
		/**Giving the Fractal Size and x and y ranges to the method.*/
		super(fractalSize, escDist, passes, xRangeStart, xRangeEnd, yRangeStart, yRangeEnd);	
	}
	/**
	 * Methods used adjust the X and Y points
	 * @return adjX and adjY used in the checkDis method.
	 * */
	@Override
	public double adjX(double xCalc, double yCalc, double xCoord, double yCoord){
		return (super.adjX(xCalc,yCalc,xCoord,yCoord)  + -0.72689);
	}
	@Override
	public double adjY(double xCalc, double yCalc, double xCoord, double yCoord){
		return super.adjY(xCalc,yCalc, xCoord,yCoord) + 0.188887;
	}
}
