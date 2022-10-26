package edu.buffalo.cse116.code.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.buffalo.cse116.code.Handler;
import edu.buffalo.cse116.code.UI;
/**
 * This class contains the code for the ActionListener when the user clicks on the Set Swing Workers button.
 * It creates an association relationship with the Handler class and sets the logic for actionPerformed.
 * @author Nick
 * @author Ryan
 * @author Lawzeem
 * @author Philip 
 */
public class SwingWorkersEventHandler implements ActionListener{
	private Handler _model;
	private UI _ui;
	private int _numSwingWorkers;
	private Integer _userInput;
	private String _placeholder;

	/**
	 * This creates the association between the ActionListener andL the model class, Handler, & the UI class UI.
	 * @param Handler m is an instance of the Handler class.
	 * @param UI ui is an instance of the UI class.
	 */
	public SwingWorkersEventHandler(Handler m, UI ui){
		_model = m;
		_ui = ui;
		_placeholder = "";
	}
	
	/**
	 * This method stores to user Input from an InputDialog in the String placeholder and then checks that input to ensure that it 
	 * a valid integer > 0 and < 128. While it is not, the method will continue to prompt the user to input a valid Escape Distance.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		_placeholder = JOptionPane.showInputDialog(_ui.getFrame(), "Enter a number from 1-128.");
		passThroughInput(_placeholder);
		while (_placeholder instanceof String){
			_placeholder = JOptionPane.showInputDialog(_ui.getFrame(), "Enter a number from 1-128.");
			passThroughInput(_placeholder);
		}
	}
	/**
	 * This method takes the user input from actionPerformed and attempts to parse it to an int and ensure it is greater than 0 and
	 * less than 129
	 * The method only returns true if all of these actions succeed.
	 * @param String s the user input.
	 */
	public boolean stringIsValidNumber(String s){
		boolean retVal = false;
		Integer tempInt = null;
		try{
			tempInt = Integer.parseInt(s);
		}catch (NumberFormatException nfe){
			return retVal;
		}
		if ((tempInt !=null) && (tempInt > 0) && (tempInt <= 128))
			return true;
		return retVal;
	}
	/**
	 * This method first uses stringIsPositiveNumber to make sure the user input is acceptable. If so, it will then proceed to send
	 * this info through to the Handler and update the image.
	 * @param String s the user input.
	 */
	public void passThroughInput(String s){
		if (stringIsValidNumber(s)){
			_userInput = Integer.parseInt(s);
			_placeholder = null;
			_numSwingWorkers = (int) _userInput;
			_model.setSwingWorkers(_numSwingWorkers);
			_model.updateImage();
		}
	}
}