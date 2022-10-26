package edu.buffalo.cse116.code.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.buffalo.cse116.code.Handler;
/** Julia event handler for the Julia button
 */
//push test
public class JuliaEventHandler implements ActionListener {
	private Handler _model;

	public JuliaEventHandler(Handler m){
		_model = m;

	}
	/** method that calls the create Julia method in handler which changes the fractal to the burning ship 
	 * and then updates the UI
	  */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		_model.createJulia();
		_model.updateImage();
	}

}
