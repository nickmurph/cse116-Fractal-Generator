package edu.buffalo.cse116.code;

import java.awt.geom.Point2D;

/**
 * This is a simple class that is used to create a Pixel Object 
 * which stores The Escape Time and a Point Object that containing Coordinates.
 * 
 * @author Nick
 * @author Ryan
 * @author Lawzeem
 * @author Philip 
 * */

//this a simple class to create a Pixel object which stores an int escape and a Point object containing coordinates
public class Pixel {
	/**The Variable used to Store the Escape Time of the Object*/
	int escapeTime;
	/**The Variable used to Store the Coordinate of the Object*/
	Point2D.Double coordinate;
	/**This is the Method Called to Create a New Pixel Object.*/
	public Pixel(){
		escapeTime = 0;
		coordinate = new Point2D.Double(0,0);
	}

	//setters
	/**Setter Method That Sets The Escape Time to an Instance Variable.
	 * @param int esc is the Escape Time
	 * */
	public void setEscape(int esc){
		escapeTime = esc;
	}
	/**Setter Method That Sets 2 inputs as a Coordinate.
	 * @param x and y values for the Coordinate.
	 * */
	public void setCoordinates(double x, double y){
		coordinate = new Point2D.Double(x,y);
	}
	/**Setter Method That Sets the X Coordinate of an Object.
	 * @param X value of the Coordinate
	 * */
	public void setXCoord(double x){
		coordinate.x = x;
	}
	/**Setter Method That Sets the Y Coordinate of an Object.
	 * @param Y value of the Coordinate
	 * */
	public void setYCoord(double y){
		coordinate.y = y;
	}

	//getters
	/**Getter Method that returns Coordinates.
	 * @return The Points Coordinate.
	 * */
	public Point2D.Double getCoordinate(){
		return coordinate;
	}
	/**Getter Method that returns The X Coordinate
	 * @return The Points X Coordinate*/
	public double getXCoord(){
		return coordinate.x;
	}
	/**Getter Method that returns The Y Coordinate
	 * @return The Points Y Coordinate*/
	public double getYCoord(){
		return coordinate.y;
	}

	//toString
	/**This is a simple Method that returns a String.
	 * @return A String Statement with the Coordinates and Escape Time.
	 * */
	public String toString(){
		return ("Pixel Coordinates: " + "(" + coordinate.x + "," + coordinate.y + ") " + "and Escape Time: " + escapeTime);
	}
	
	public int getEscapeTime(){
		return escapeTime;
	}

}
