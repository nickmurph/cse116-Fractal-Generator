package edu.buffalo.cse116.code;

import java.awt.Dimension;
import java.awt.image.IndexColorModel;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import edu.buffalo.cse116.code.events.BluesColorEventHandler;
import edu.buffalo.cse116.code.events.BurningShipEventHandler;
import edu.buffalo.cse116.code.events.EscapeDistanceEventHandler;
import edu.buffalo.cse116.code.events.ExitEventHandler;
import edu.buffalo.cse116.code.events.GraysColorEventHandler;
import edu.buffalo.cse116.code.events.GreensColorEventHandler;
import edu.buffalo.cse116.code.events.JuliaEventHandler;
import edu.buffalo.cse116.code.events.MandelbrotEventHandler;
import edu.buffalo.cse116.code.events.MultibrotEventHandler;
import edu.buffalo.cse116.code.events.RainbowColorEventHandler;
import edu.buffalo.cse116.code.events.ZoomResetEventHandler;
import edu.buffalo.cse116.code.events.mouseDragEventHandler;
import edu.buffalo.cse116.code.events.SwingWorkersEventHandler;
import edu.buffalo.fractal.FractalPanel;
import edu.buffalo.cse116.code.events.EscapeTimeEventHandler;

/**
 * This is the UI Class. 
 * It takes in the FractalPanel class and displays the fractals to the user.
 * It calls the EventHandlers to change settings on the UI.
 * 
 * @author Nick
 * @author Ryan
 * @author Lawzeem
 * @author Philip 
 */

public class UI implements Runnable{
	/**Main JFrame, the UI window.*/
	private JFrame _frame;
	/**The MenuBar that holds all the Menu Items.*/
	private JMenuBar _menu;
	/**The Options that the user can select.*/
	private JMenu _fileMenu, _fractMenu, _colMenu, _optionsMenu;
	/**Menu Items under the File Menu.*/
    private JMenuItem _exit;
    /**Menu Items under the Options Menu.*/
    private JMenuItem _escDist, _escTime, _zoomReset, _swingWorkers;
    /**Menu Items under the Fractal Menu.*/
    private JMenuItem _julia, _mand, _burn, _multi;
    /**Menu Items under the Color Menu.*/
    private JMenuItem _col1, _col2, _col3, _col4;
    /**Instance of the FractalPanel class.*/
    private FractalPanel _fractalP;
    /**Instance of the Handler class.*/
    private Handler _handler;
    private mouseDragEventHandler _m;
    /**
	 * Method that is used to generate the UI and display the Fractals.
	 */
	@Override public void run(){
		/**Instantiating the JFrame and setting Preferred Dimensions.*/
		_frame = new JFrame("Fractals");
	//	_frame.setPreferredSize(new Dimension(800,800));
		/**Instantiating the MenuBar.*/
		_menu = new JMenuBar();
		/**Instantiating the Instance of the Handler Variable and Adding the Observer.*/
		_handler = new Handler();
		_handler.addObserver(this);
		/**Instantiating the Menu Items and Adding their EventHandlers.*/
		_exit = new JMenuItem("Exit");
		_exit.addActionListener(new ExitEventHandler(_handler, this));
		
		_escDist = new JMenuItem("Set Escape Distance");
		_escDist.addActionListener(new EscapeDistanceEventHandler(_handler, this));
		_escTime = new JMenuItem("Set Escape Time");
		_escTime.addActionListener(new EscapeTimeEventHandler(_handler, this));
		_zoomReset = new JMenuItem("Reset Zoom");
		_zoomReset.addActionListener(new ZoomResetEventHandler(_handler)); 
		_swingWorkers = new JMenuItem("Set Number of SwingWorkers");
		_swingWorkers.addActionListener(new SwingWorkersEventHandler(_handler, this));
	
		
		_julia = new JMenuItem("Julia");
		_julia.addActionListener(new JuliaEventHandler(_handler));
		_mand = new JMenuItem("Mandlebrot");
		_mand.addActionListener(new MandelbrotEventHandler(_handler));
		_burn = new JMenuItem("BurningShip");
		_burn.addActionListener(new BurningShipEventHandler(_handler));
		_multi = new JMenuItem("Multibrot");
		_multi.addActionListener(new MultibrotEventHandler(_handler));
		
		_col1 = new JMenuItem("Rainbow Color Scheme");
		_col1.addActionListener(new RainbowColorEventHandler(_handler));
		_col2 = new JMenuItem("Blues Color Scheme");
		_col2.addActionListener(new BluesColorEventHandler(_handler));
		_col3 = new JMenuItem("Grays Color Scheme");
		_col3.addActionListener(new GraysColorEventHandler(_handler));
		_col4 = new JMenuItem("Greens Color Scheme");
		_col4.addActionListener(new GreensColorEventHandler(_handler));
		
		/**Instantiating the Menu and adding the Menu Items.*/
		
		_fileMenu = new JMenu("File");
		_fractMenu = new JMenu("Fractal");
		_colMenu = new JMenu("Color");
		_optionsMenu = new JMenu("Options");
		_fileMenu.add(_exit);
		_fractMenu.add(_julia);
		_fractMenu.add(_mand);
		_fractMenu.add(_burn);
		_fractMenu.add(_multi);
		_colMenu.add(_col1);
		_colMenu.add(_col2);
		_colMenu.add(_col3);
		_colMenu.add(_col4);
		_optionsMenu.add(_escDist);
		_optionsMenu.add(_escTime);
		_optionsMenu.add(_zoomReset);
		_optionsMenu.add(_swingWorkers);
		_menu.add(_fileMenu);
		_menu.add(_fractMenu);
		_menu.add(_colMenu);
		_menu.add(_optionsMenu);
		/**Instantiating the FractalPanel.*/
		_fractalP  = new FractalPanel();
		//_fractalP.setMaximumSize(new Dimension(2048,2048));
		_fractalP.setSize(new Dimension(2048,2048));
		_m = new mouseDragEventHandler(_fractalP,_handler);
		_fractalP.addMouseMotionListener(_m);
		_fractalP.addMouseListener(_m);
		
		/**Updating the Image and Colors in the Handler.*/
		_handler.updateColorIndexModel();
		/**Adding the FractalPanel to the JFrame.*/
		_frame.add(_fractalP);
		/**Adding the MenuBar to the JFrame.*/
		_frame.setJMenuBar(_menu);
		/**Setting the Default Operations and other Miscellaneous options.*/
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.pack();
		_frame.setVisible(true);
		_frame.setAlwaysOnTop(true);		
		/**Calling the Update Method.*/
		_handler.getPanelSize();
		update();
		/**Calling the getSize method.*/
		getSize();
		
	}
	/**This method Packs everything in the UI together.*/
	public void update(){
		_frame.pack();
	}
	/**This method Updates the Image Fractal displayed.*/
	public void updateImage(int[][] arr){
		_fractalP.updateImage(arr);
	}
	/**This method Updates the Colors.*/
	public void updateColorIndexModel(IndexColorModel m){
		_fractalP.setIndexColorModel(m);
	}
	/**This method gets the JFrame.*/
	public JFrame getFrame(){
		return _frame;
	}
	/**This method gets the Dimensions of the JFrame.*/
	public Dimension getSize(){
		return _fractalP.getSize();	
	}
	
	public FractalPanel getPanel(){
		return _fractalP;
	}
}
