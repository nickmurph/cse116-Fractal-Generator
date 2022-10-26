package edu.buffalo.cse116.code;

import java.awt.Dimension;
import java.awt.image.IndexColorModel;

import javax.swing.JOptionPane;

import edu.buffalo.cse116.code.events.EscapeDistanceEventHandler;
import edu.buffalo.cse116.code.fractals.BurningShip;
import edu.buffalo.cse116.code.fractals.ImageFractals;
import edu.buffalo.cse116.code.fractals.Julia;
import edu.buffalo.cse116.code.fractals.Mandelbrot;
import edu.buffalo.cse116.code.fractals.Multibrot;

/**
 * This is the Model in the model view separation
 * This class provides access to all of the different ImageFractal subclasses from one class 
 * This class also stores the current escape distances and color models so users don't have to update it every time they
 * change fractals
 *   
 * @author Nick
 * @author Ryan
 * @author Lawzeem
 * @author Philip  
 */
public class Handler {
	/**Current escape distance and max passes*/
	private int _escDist;
	private int _escTime;
	/**The size of the fractal */
	private final int _size;
	/**Current fractal type */
	private ImageFractals _iF;
	/**UI reference*/
	private UI _ui;
	/**Current color model*/
	private ColorModelFactory _factory;
	/**The color model factory */
	private IndexColorModel _colorModel;
	private double _xS,_xF,_yS,_yF;
	private Dimension _d;
	/** starting and ending values*/
	private double[][] preZoomValues;
	/** When changing fractals, stores an int that corresponds to the fractal;
	 * Julia - 0
	 * BurningShip - 1
	 * Mandelbrot - 2
	 * Multibrot - 3
	 * These values correspond to the x column location for the preZoomValues 2D array*/
	private int currentFractalNum;
	private int numSwingWorkers;
	
	
	/** Sets up the initial fractal that is to be displayed using default values and color models */
	public Handler(){
		_size = 2048;
		_escDist = 2;
		_escTime = 255;
		_factory = new ColorModelFactory();
		_colorModel = _factory.createRainbowColorModel(256);
		_xS = -1.7;
		_xF = 1.7;
		_yS = -1;
		_yF = 1;
		/**Stores original coordinates, pre-Zoom. Order detailed above, same order as declared in methods below. */
		preZoomValues = new double[][]{ {-1.7, 1.7, -1, 1}, {-1.8,-1.7,-.08,.025}, {-2.15,.6,-1.3,1.3}, {-1,1,-1.3,1.3} };
		currentFractalNum = 0;	
		_iF = new Julia(_size, _escDist, _escTime,-1.7,1.7,-1,1);
		numSwingWorkers = 4;
		_iF.setNumSwingWorkers(numSwingWorkers);
		
	}
	/** 
	 * Methods called by event handlers to change type of image fractal
	 */
	
	public void createJulia(){
		currentFractalNum = 0;	
		createJulia(-1.7,1.7,-1,1);
	}
	public void createJulia(double xS,double xF,double yS,double yF){
		_xS = xS;
		_xF = xF;
		_yS = yS;
		_yF = yF;
		currentFractalNum = 0;	
		_iF = new Julia(_size,_escDist, _escTime,xS,xF,yS,yF);
	}
	public void createBurningShip(){
		currentFractalNum = 1;
		createBurningShip(-1.8,-1.7,-.08,.025);
	}
	public void createBurningShip(double xS,double xF,double yS,double yF){
		_xS = xS;
		_xF = xF;
		_yS = yS;
		_yF = yF;
		checkBurningShip();
		currentFractalNum = 1;
		_iF = new BurningShip(_size,_escDist, _escTime,xS,xF,yS,yF);
		checkBurningShip();
	}
	public void createMandelbrot(){
		currentFractalNum = 2;	
		createMandelbrot(-2.15,.6,-1.3,1.3);
	}
	public void createMandelbrot(double xS,double xF,double yS,double yF){
		_xS = xS;
		_xF = xF;
		_yS = yS;
		_yF = yF;
		currentFractalNum = 2;	
		_iF = new Mandelbrot(_size,_escDist, _escTime,xS,xF,yS,yF);
	}
	public void createMultibrot(){
		currentFractalNum = 3;
		createMultibrot(-1,1,-1.3,1.3);
	}
	public void createMultibrot(double xS,double xF,double yS,double yF){
		_xS = xS;
		_xF = xF;
		_yS = yS;
		_yF = yF;
		currentFractalNum = 3;
		_iF = new Multibrot(_size,_escDist, _escTime,xS,xF,yS,yF);
	}
	/** Gives the model a reference to the UI */
	public void addObserver(UI ui){
		_ui = ui;
		
	}
	public void getPanelSize(){
		_d = _ui.getSize();
	}
	/** Updates the image fractal */
	public void updateImage(){
		_iF.findCoordinates();
		_iF.setNumSwingWorkers(numSwingWorkers);
		_iF.setCompPoolFractal(_ui.getPanel());
		_iF.generateFractal();
		//_iF.setCompPoolFractal(_ui.getPanel());
		
	}
	/** Changes the escape distance */
	public void setEscDist(int escDist){
		_escDist = escDist;
		_iF.setEscDist(_escDist);
	}
	
	public void setEscTime(int passes){
		_escTime = passes;
		_iF.setEscTime(_escTime);
	}
	
	/**
	 * These methods change the color model being used and are called by an event handler  
	 */
	public void createRainbow(){
		_colorModel = _factory.createRainbowColorModel(256);
		updateColorIndexModel();
	}
	public void createBlues(){
		_colorModel= _factory.createBluesColorModel(256);
		updateColorIndexModel();
	}
	public void createGrays(){
		_colorModel = _factory.createGrayColorModel(256);
		updateColorIndexModel();
	}
	public void createGreens(){
		_colorModel = _factory.createGreensColorModel(256);
		updateColorIndexModel();
	}
	/**Updates the color index model */
	public void updateColorIndexModel(){
		_ui.updateColorIndexModel(_colorModel);
		updateImage();
	}
	/** 
	 * Method checks to see if the escape distance is
	 * not equal to one because all of the escape times are the same so a blank screen will happen  
	 */
	public void checkBurningShip(){
		if (_escDist == 1){
			JOptionPane.showMessageDialog(_ui.getFrame(), "Burning Ship generates no fractal with an Escape Distance of 1. Try another!");
			EscapeDistanceEventHandler eD = new EscapeDistanceEventHandler(this, _ui);
			eD.actionPerformed(null);
			//commenting out the line below will allow users to cancel out of the looping prompt to enter an escape distance greater than 1
			//createBurningShip();
		}
	}
	/**A method that returns the Current ImageFractal*/
	public ImageFractals getCurrentFractal(){
		return _iF;
	}
	/**This method will change the coordinate ranges by adjusting
	 * the x and y proportionately based on the size of the fractal panel
	 * 
	 * The instance variable are the coordinates of the fractal itself not the panel
	 * 
	 * @param xS start of the range from the fractal panel
	 * @param xF end of the range from the fractal panel
	 * @param yS start of y range from the fractal panel
	 * @param yF end of y range from the fractal panel
	 * */
	public void changeCoordinates(int xS, int xF,int yS, int yF){
		double deltaX = _xF-_xS;
		double deltaY = _yF-_yS;

		double newXStart = _xS + (xS/_d.getWidth())*deltaX;
		double newXEnd = _xS+ (xF/_d.getWidth())*deltaX;
		double newYStart = _yS+(yS/_d.getHeight())*deltaY;
		double newYEnd = _yS+(yF/_d.getHeight())*deltaY;
		_xS = newXStart; 
		_xF = newXEnd;
		_yS = newYStart;
		_yF = newYEnd;
		_iF.setCoordinates(newXStart, newXEnd, newYStart, newYEnd); 
		updateImage();	
	}
	/**resets the fractal to its original zoom*/
	public void resetZoom(){
		_xS = preZoomValues[currentFractalNum][0];
		_xF = preZoomValues[currentFractalNum][1];
		_yS = preZoomValues[currentFractalNum][2];
		_yF = preZoomValues[currentFractalNum][3];
		_iF.setCoordinates(_xS, _xF, _yS, _yF); 
		updateImage();	
	}
	
	public void setDimension(Dimension d){
		_d = d;
	}
	/**sets the number of swing workers
	 * 
	 * 
	 * @param numeber of swingWorkers to be used
	 * */
	public void setSwingWorkers(int num){
		numSwingWorkers = num;
		_iF.setNumSwingWorkers(numSwingWorkers);
	}
}
