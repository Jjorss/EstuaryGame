package view;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;


/**
 * 
 * @author Jackson Jorss
 * @author Jael Flaquer
 * @author Ben Clark
 * @author Robert Lee
 * 
 *
 */


public class Scale implements Serializable {

	private int width;
	private int height;
	private int gridSize;
	
	public Scale(int width, int height, int gridSize) {
		this.width = width;
		this.height = height;
		this.gridSize = gridSize;
		
	}

	/**
	 *  Method for testing purposes only, helps us visualize grid
	 * @param g Graphics object that is needed to render shapes on screen.
	 */
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		for (int i = 0; i < this.getWidth(); i+=gridSize) {
			g.drawLine(i, 0, i, this.getHeight());
			g.drawLine(0, i, this.getWidth(), i);
		}
	}

	/**
	 * Getter for the parameter width.
	 * @return The current instance width.
	 */
	public int getWidth() {
		return this.width;
	}
	/**
	 * Getter for the parameter height.
	 * @return The current instance height
	 */
	public int getHeight() {
		return this. height;
	}
	/**
	 * Getter for the parameter GridSize.
	 * @return The current instance gridSize.
	 */

	public int getGridSize() {
		return gridSize;
	}

}
