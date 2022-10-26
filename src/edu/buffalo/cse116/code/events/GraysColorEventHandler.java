package edu.buffalo.cse116.code.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.buffalo.cse116.code.Handler;
/**
 * This class contains the code for the ActionListener when the user clicks on the Grays Color Scheme button.
 * It creates an association relationship with the Handler class and sets the logic for actionPerformed.
 * 
 */

public class GraysColorEventHandler implements ActionListener {
	private Handler _model;



	/**
	 * This method creates the association between the ActionListener and the model class, Handler.
	 */
	public GraysColorEventHandler(Handler m){
		_model = m;

	}
	/**
	 * This method calls the corresponding method in the Handler class to create the color and then the 
	 * method in the Handler class to update the ColorIndexModel, refreshing the image.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		_model.createGrays();
	}

}
