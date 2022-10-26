package edu.buffalo.cse116.code.events;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;
import edu.buffalo.cse116.code.Handler;
import edu.buffalo.fractal.FractalPanel;
/**
 * This class contains the code for the EventHandlers for mouse actions.
 * @author Nick
 * @author Ryan
 * @author Lawzeem
 * @author Philip
 */
public class mouseDragEventHandler extends MouseInputAdapter{
	/**Instance of the FractalPanel.*/
	private FractalPanel _f;
	/**Instance of the Handler.*/
	private Handler _h;
	/**Instance variables of the starting and ending X and Y values.*/
	private int _xPressed,_yPressed, _xReleased, _yReleased;
	/**A method that assigns values to the Instances of the FractalPanel and Handler.*/
	public mouseDragEventHandler(FractalPanel f, Handler h){
		_f = f;
		_h = h; 
	}
	/**Method that gets the Starting X and Y values.*/
	public void mousePressed(MouseEvent e){
		_xPressed = e.getX();
		_yPressed = e.getY();
	}	
	/**Method that draws the rectangle around the selected region.*/
	public void mouseDragged(MouseEvent e){
		Graphics g = _f.getGraphics();
		_f.repaint();
		int x = Math.min(_xPressed, e.getX());
		int y = Math.min(_yPressed, e.getY());
		g.drawRect(x,y,Math.abs(e.getX()-_xPressed), Math.abs(e.getY()-_yPressed));
		//g.drawRect(x,y,Math.abs(_xPressed - e.getX()), Math.abs(_yPressed - e.getY()));
	}	
	/**Method that calculates which region to zoom.*/
	public void mouseReleased(MouseEvent e){
		_xReleased = e.getX();
		_yReleased = e.getY();
		int xDiff = _xReleased - _xPressed;
		int yDiff = _yReleased - _yPressed;
		if (Math.abs(xDiff) > 5 && Math.abs(yDiff) > 5){ 
			_h.setDimension(_f.getSize());
			contextSensitiveChangeCoordinates(xDiff, yDiff);
		}
		else
			JOptionPane.showMessageDialog(_f, "You must click then drag the mouse to select a rectangular area for zooming!", "Warning!", 2);
	}
	/**Method that zooms the region calculated by the mouseReleased method.*/
	public void contextSensitiveChangeCoordinates (int xD, int yD){
		if (xD > 0 && yD > 0)
			_h.changeCoordinates(_xPressed, _xReleased, _yPressed, _yReleased);
		if (xD > 0 && yD < 0)
			_h.changeCoordinates(_xPressed, _xReleased, _yReleased, _yPressed);
		if (xD < 0 && yD > 0)
			_h.changeCoordinates(_xReleased, _xPressed, _yPressed, _yReleased);
		if (xD < 0 && yD < 0) 
			_h.changeCoordinates(_xReleased, _xPressed,_yReleased, _yPressed);
	}
}
