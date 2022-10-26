package edu.buffalo.cse116.code.fractals;

import javax.swing.SwingWorker;

import edu.buffalo.cse116.code.Pixel;
import edu.buffalo.cse116.code.SwingWorkerSub;
import edu.buffalo.fractal.ComputePool;
import edu.buffalo.fractal.FractalPanel;
import edu.buffalo.fractal.WorkerResult;

/**
 * This is the Main Parent Image Fractals Class. 
 * All other classes Extend this class.
 * 
 * @author Nick
 * @author Ryan
 * @author Lawzeem
 * @author Philip 
 */

public class ImageFractals {
	/**Current point's x-coordinate*/
	double _xCalc;
	/**Current point's y-coordinate*/
	double _yCalc;
	/**Distance from the point (xCalc, yCalc) to (0,0) using the Pythagorean theorem*/
	double _dist;
	/**The variable which will be set to the Escape Time*/
	int _pass;
	/**This is a Variable that stores the Number of pixels in one axis.*/
	int fractalMult;   
	/**A 2-D array that stores all the escape times for all the pixels */
	int[][] escapeTimes;
	/**int holding value for escape distance */
	int escDist;
	/**int value for maximum passes */
	int escTime;
	/**Range Start and Range End doubles for both x and y*/
	double xRS, xRE, yRS, yRE;	
	double[] xCoordinates;
	double[] yCoordinates;
	//Pixel[][] pixelArray;
	private int numSwingWorkers, rowsPerSW, rowsPerRemainder;
	private ComputePool compPool;

	/**
	 * Method that is used to generate Image Fractals.
	 * @param fractalSize The Size of the Fractal we are calling the method on.
	 * @param xRangeStart The Start of the Range of X values for the given Fractal.
	 * @param xRangeEnd The End of the Range of X values for the given Fractal.
	 * @param yRangeStart The Start of the Range of Y values for the given Fractal.
	 * @param yRangeEnd The End of the Range of X values for the given Fractal.
	 */
	public ImageFractals(int fractalSize, int escape, int passes, double xRangeStart, double xRangeEnd, double yRangeStart, double yRangeEnd){  
		fractalMult = fractalSize;
		escapeTimes = new int[fractalMult][fractalMult];
		//pixelArray = new Pixel[fractalMult][fractalMult];
		xRS = xRangeStart;
		xRE = xRangeEnd;
		yRS = yRangeStart;
		yRE = yRangeEnd;
		escDist = escape;
		escTime = passes;
		compPool = new ComputePool();
		findCoordinates();   
	}

	/**
	 * Method used to find the Coordinates of each pixel in a fractal using their ranges .
	 */
	public void findCoordinates(){
		/**Array used to store the x and y values of a pixel*/
		xCoordinates = new double[fractalMult]; 
		yCoordinates = new double[fractalMult]; 
		/**Variables used to store the range of x and y values found by subtracting the start and the end*/
		double xRange = xRE - xRS;
		double yRange = yRE - yRS;
		/**Variables used to store the the value of the range divided by the number of pixels in each axis*/
		double deltaX = (xRange/fractalMult);
		double deltaY = (yRange/fractalMult);
		/**Variable used to store the last x and y values*/
		double x = xRS;
		double y = yRS;
		/**Setting the first value in the arrays equal to the start of the range*/
		xCoordinates[0] =  x;
		yCoordinates[0] =  y;
		/**A loop used to find all the x and y values for each pixel*/
		for (int i=1; i <fractalMult; i++){
			x = x + deltaX;
			y = y + deltaY;
			xCoordinates[i] =  x;
			yCoordinates[i] =  y;
		}
		//		/**A loop used to take both the x and y values from the array and add it to one single array as coordinates*/
		//		for (int i= 0; i<fractalMult; i++){
		//			for (int j=0; j<fractalMult; j++){
		//				pixelArray[i][j] = new Pixel();
		//				pixelArray[i][j].setXCoord(xCoordinates[i]);
		//				pixelArray[i][j].setYCoord(yCoordinates[j]);
		//			}
		//		}	
	}

	/**Sets the number of swing workers 
	 * 
	 * @param number of swingWorkers
	 * */
	public void setNumSwingWorkers(int num){
		numSwingWorkers = num;
		rowsPerSW = fractalMult / numSwingWorkers;
		rowsPerRemainder = fractalMult % numSwingWorkers;
	}


	/**A method used to Generate Fractals.
	 * creates the swingWorker instances and passes in the correct starting and ending values for each swing worker.
	 * passes the array of swing workers into the computePool
	 */
	public void generateFractal (){
		SwingWorker<WorkerResult, Void>[] swingArray = new SwingWorkerSub[numSwingWorkers];

		int startSpot = 0;

		for (int i=0;i<numSwingWorkers;i++){
			int tempRows = rowsPerSW;
			if (i < rowsPerRemainder)
				tempRows = 1 + rowsPerSW;
			swingArray[i] = new SwingWorkerSub(this, startSpot, tempRows);
			//swingArray[i].execute();
			//System.out.println(startSpot + "    " + i + "     " + tempRows);
			startSpot = startSpot + tempRows;		
		}

		compPool.clearPool();
		compPool.generateFractal(fractalMult, swingArray);

	}
	/**A method that calculates Pythagorean theorem of two values.
	 * @param xCalc The X value for calculation the Pythagorean theorem.
	 * @param yCalc The Y value for calculation the Pythagorean theorem.
	 * @return The Results of Pythagorean Theorem of the 2 inputs.
	 * */
	public double checkDis(double xCalc, double yCalc){
		return Math.sqrt(Math.pow(xCalc, 2)+ Math.pow(yCalc, 2));
	}
	/**Methods used to adjust X and Y. These values are used to in Calculating Escape Times.
	 * @param xCalc The X value for adjusting x.
	 * @param yCalc The Y value for adjusting y.
	 * @param xCoord The X Coordinate for adjusting x.
	 * @param yCoord The Y Coordinate for adjusting y.
	 * @return The adjX and adjY values used in the checkDis method.
	 * */
	public double adjX(double xCalc, double yCalc, double xCoord, double yCoord){
		return (Math.pow(xCalc, 2) - Math.pow(yCalc, 2) );
	}
	public double adjY(double xCalc, double yCalc, double xCoord, double yCoord){
		return (2 * xCalc * yCalc);
	}
	/**
	 * A method used to check if the given fractal has any escape values of 0 or 1.
	 * @return true if it finds a 1 or 0, false otherwise
	 * */
	public boolean findOneOrZeroEscVal(){
		boolean retVal = false;
		for (int i=0; i < escapeTimes.length; i++){
			for (int j=0; j < escapeTimes[0].length; j++){
				//System.out.println(i + "    " + j);
				if (escapeTimes[i][j] <= 1)
					return true;
			}
		}
		return retVal; 
	}
	/**
	 * A method used to calculate the escape time for two given doubles.
	 * @param givenX The X coordinate of the pixel whose Escape Time we are trying to Calculate.
	 * @param givenY The Y coordinate of the pixel whose Escape Time we are trying to Calculate.
	 * @return Escape Time for the given X and Y Values 
	 */
	//eventually fold into the generateFractal
	public int calcEscapeTime(double givenX, double givenY){
		double xCalc;
		double yCalc;
		double tempDist = 0;
		int tempPass = 0;
		double tempX = givenX;
		double tempY = givenY;
		tempDist = checkDis(tempX, tempY);
		while (tempDist <= escDist && tempPass < escTime){ 
			xCalc = adjX(tempX, tempY, givenX, givenY);
			yCalc = adjY(tempX, tempY, givenX, givenY);
			tempX = xCalc;
			tempY = yCalc;
			tempPass +=1;
			tempDist = checkDis(tempX, tempY);
		}
		return tempPass;
	}
	/**A Method used to Get the X Coordinate of the First Item in a Given Row.
	 * @param Index of the Row in the Array
	 * @return The X Coordinate of the Item in the First Index 
	 * */
	public double getXCoordinateOfRow(int row){
		//double retVal = pixelArray[row][0].getXCoord();
		double retVal = xCoordinates[row];
		return retVal;
	}
	/**A Method used to Get the Y Coordinate of the First Item in a Given Column.
	 * @param Index of the Column in the Array
	 * @return The Y Coordinate of the Item in the First Index 
	 * */
	public double getYCoordinateOfCol(int col){
		//double retVal = pixelArray[0][col].getYCoord();
		double retVal = yCoordinates[col];
		return retVal;
	}
	/** method used to return the escape times 2D array, necessary for testing. **/
	public int[][] getEscapeTimes(){
		return escapeTimes;
	}	
	//	/** method used to return the Pixel Array, necessary for testing. **/
	//	public Pixel[][] getPixelArray(){
	//		return pixelArray;
	//	}
	/** method used to change a single escape time in the 2D escape times array, necessary for testing. **/
	public void setSingleEscapeTime(int x, int y, int esc){
		escapeTimes[x][y] = esc;
	}
	/** method used to change the escDist variable used in generateFractal and calcEscTime **/
	public void setEscDist(int newEsc){
		escDist = newEsc;
	}
	/** method used to change the escDist variable used in generateFractal and calcEscTime **/
	public void setEscTime(int maxpass){
		escTime = maxpass;
	}
	public void setCoordinates(double xS,double xF,double yS,double yF){
		xRS = xS;
		xRE = xF;
		yRS = yS;
		yRE = yF;
	}

	public void setCompPoolFractal(FractalPanel fp){
		compPool.changePanel(fp);
	}
	
	public int getFractalSize(){
		return fractalMult;
	}
	
	public void setEscapeArray(int[][] array){
		escapeTimes = array;
	}
}