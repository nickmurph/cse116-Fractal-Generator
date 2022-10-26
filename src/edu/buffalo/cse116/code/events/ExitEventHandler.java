package edu.buffalo.cse116.code.events;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.buffalo.cse116.code.Handler;
import edu.buffalo.cse116.code.UI;
/**
 * This class contains the code for the ActionListener when the user clicks on the Exit button.
 * It creates an association relationship with the Handler class and sets the logic for actionPerformed.
 * 
 */


public class ExitEventHandler implements ActionListener {
	private Handler _model;
	private UI _ui;
	/**
	 * This creates the association between the ActionListener andL the model class, Handler, & the UI class UI.
	 */
	public ExitEventHandler(Handler m, UI ui){
		_model = m;
		_ui = ui;
	}
	/**
	 * This method retrieves the Jframe via the UI class and then calls dispose on it, closing the window if the user
	 * hits the Exit button.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		_ui.getFrame().dispose();
	}
}
