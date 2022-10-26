package edu.buffalo.cse116.code;

import javax.swing.SwingWorker;

import edu.buffalo.cse116.code.fractals.ImageFractals;
import edu.buffalo.fractal.WorkerResult;

public class SwingWorkerSub extends SwingWorker<WorkerResult, Void>{
	private int _start, _rows, _size;
	private ImageFractals _iF;
	private int[][] resultsTemp;

	/**
	 *This is the swingWorker instance used to calculate each segment of the array
	 *   
	 * @author Nick
	 * @author Ryan
	 * @author Lawzeem
	 * @author Philip 
	 * @param frac The instance of the image fractal being used
	 * @param start The row to start off in when calculating the array of escape times
	 * @param rows how many rows to be calculate
	 */
	public SwingWorkerSub(ImageFractals frac, int start, int rows){
		_start = start;
		_rows = rows;
		_iF = frac;
		_size = _iF.getFractalSize();
	}
	/**This method returns a new instance of calculating WorkerResult with partial arrays and the start index.*/
	@Override
	public WorkerResult doInBackground(){
		int[][] results = generatePartial(_start, _rows);
		return new WorkerResult(_start, results);
	}

	/**This method calculates the partial array of escape times
	 * 
	 * @param startRow the row to start on
	 * @param totalRows the number of rows to iterate over
	 * */
	public int[][] generatePartial(int startRow, int totalRows){
		int[][] resultsTemp = new int[totalRows][_size];
		for (int i = 0; i < totalRows; i++){
			for (int j= 0; j < _size; j++){
				resultsTemp[i][j] = _iF.calcEscapeTime(_iF.getXCoordinateOfRow(i+startRow), _iF.getYCoordinateOfCol(j));
				_iF.setSingleEscapeTime((i+startRow), j, resultsTemp[i][j]);
			}
		}
		return resultsTemp;	
	}



}
