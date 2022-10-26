package edu.buffalo.cse116.code.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.buffalo.cse116.code.Handler;
/**
 * This class contains the code for the ActionListener when the user clicks on the Grays Color Scheme button.
 * It creates an association relationship with the Handler class and sets the logic for actionPerformed.
 * @author Nick
 * @author Ryan
 * @author Lawzeem
 * @author Philip
 */
public class ZoomResetEventHandler implements ActionListener {
	private Handler _model;
	/**
	 * This method creates the association between the ActionListener and the model class, Handler.
	 */
	public ZoomResetEventHandler(Handler m){
		_model = m;
	}
	/**
	 * This method resets the Zoom in the fractal. 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		_model.resetZoom();
	}
}