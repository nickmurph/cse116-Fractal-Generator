package edu.buffalo.cse116.code.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.buffalo.cse116.code.Handler;
/** Burning ship event handler for the burning ship button
 */
public class BurningShipEventHandler implements ActionListener {
	private Handler _model;

	public BurningShipEventHandler(Handler m){
		_model = m;
	}
	/** method that calls the create burning ship method in handler which changes the fractal to the burning ship 
	 * and then updates the UI
	  */
	@Override
	public void actionPerformed(ActionEvent e) {
		_model.createBurningShip();
		_model.updateImage();
	}


}
