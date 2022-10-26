package edu.buffalo.cse116.code.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.buffalo.cse116.code.Handler;
/** Multibrot event handler for the Multibrot button
 */
public class MultibrotEventHandler implements ActionListener {
	
	private Handler _model;

	public MultibrotEventHandler(Handler m){
		_model = m;
	}
	/** method that calls the create Multibrot method in handler which changes the fractal to the burning ship 
	 * and then updates the UI
	  */
	@Override
	public void actionPerformed(ActionEvent e) {
		_model.createMultibrot();
		_model.updateImage();
		
	}

}
