package edu.buffalo.cse116.code.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.buffalo.cse116.code.Handler;
/** Mandelbrot event handler for the Mandelbrot button
 */
public class MandelbrotEventHandler implements ActionListener {
	private Handler _model;

	public MandelbrotEventHandler(Handler m){
		_model = m;
	}

	/** method that calls the create Mandelbrot method in handler which changes the fractal to the burning ship 
	 * and then updates the UI
	  */
	@Override
	public void actionPerformed(ActionEvent e) {
		_model.createMandelbrot();
		_model.updateImage();
		
	}

}
