package edu.buffalo.cse116.code.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.buffalo.cse116.code.Handler;
import edu.buffalo.cse116.code.UI;
import edu.buffalo.cse116.code.fractals.BurningShip;
/**
 * This class contains the code for the ActionListener when the user clicks on the Set Escape Distance button.
 * It creates an association relationship with the Handler class and sets the logic for actionPerformed.
 * 
 */

public class EscapeDistanceEventHandler implements ActionListener{
	private Handler _model;
	private UI _ui;
	private int _escDist;
	private Integer _userInput;
	private String _placeholder;

	/**
	 * This creates the association between the ActionListener andL the model class, Handler, & the UI class UI.
	 */
	public EscapeDistanceEventHandler(Handler m, UI ui){
		_model = m;
		_ui = ui;
		_placeholder = "";
	}
	
	/**
	 * This method stores to user Input from an InputDialog in the String placeholder and then checks that input to ensure that it 
	 * a valid integer > 0. While it is not, the method will continue to prompt the user to input a valid Escape Distance > 0.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		_placeholder = JOptionPane.showInputDialog(_ui.getFrame(), "Enter a number greater than 0.");
		validateInput(_placeholder);
		while (_placeholder instanceof String){
			_placeholder = JOptionPane.showInputDialog(_ui.getFrame(), "Enter a number greater than 0.");
			validateInput(_placeholder);
		}
	}
	/**
	 * This method takes the user input from actionPerformed and attempts to parse it to an int and ensure it is greater than 0.
	 * The method only returns true if both of these actions succeed.
	 */
	public boolean stringIsPositiveNumber(String s){
		boolean retVal = false;
		Integer tempInt = null;
		try{
			tempInt = Integer.parseInt(s);
		}catch (NumberFormatException nfe){
			return retVal;
		}
		if ((tempInt !=null) && (tempInt > 0))
			return true;
		return retVal;
	}
	/**
	 * This method first uses stringIsPositiveNumber to make sure the user input is acceptable. If so, it will then proceed to send
	 * this info through to the Handler and update the image. If the current ImageFractal is a BurningShip, it also checks if the 
	 * escape distance is equal to 1 and calls a method in the model Handler to deal with this (because BurningShip with an Escape
	 * Distance of 1 generates a blank fractal.
	 */
	public void validateInput(String s){
		if (stringIsPositiveNumber(s)){
			_userInput = Integer.parseInt(s);
			_placeholder = null;
			_escDist = (int) _userInput;
			_model.setEscDist(_escDist);
			if (_escDist == 1 && _model.getCurrentFractal() instanceof BurningShip)
				_model.checkBurningShip();
			_model.updateImage();
		}
	}
	/**
	 * This method is a simple getter for the String, not currently used,
	 */
//	public String getPlaceholder(){
//		return _placeholder;
//	}
	
}

