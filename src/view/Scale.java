package view;

import java.awt.Color;
import java.awt.Graphics;

<<<<<<< HEAD
/**
 * 
 * @author Jackson Jorss
 * @author Jael Flaquer
 * @author Ben Clark
 * @author Robert Lee
 * 
 *
 */

=======
>>>>>>> 3edddabc4187178835a585eb4d0450cc10da5c55
public class Scale {

	private int width;
	private int height;
<<<<<<< HEAD
=======
	
>>>>>>> 3edddabc4187178835a585eb4d0450cc10da5c55
	private int gridSize;
	
	public Scale(int width, int height, int gridSize) {
		this.width = width;
		this.height = height;
		this.gridSize = gridSize;
		
	}
<<<<<<< HEAD
	/**
	 * Temporary method that displays an easily viewable grid
	 * 
	 * @param g Graphics object that is needed to render shapes on screen.
	 */
=======
>>>>>>> 3edddabc4187178835a585eb4d0450cc10da5c55
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		for (int i = 0; i < this.getWidth(); i+=gridSize) {
			g.drawLine(i, 0, i, this.getHeight());
			g.drawLine(0, i, this.getWidth(), i);
		}
	}
<<<<<<< HEAD
	/**
	 * Getter
	 * @return the current instance width
	 */
	public int getWidth() {
		return this.width;
	}
	/**
	 * Getter
	 * @return the current instance height
	 */
	public int getHeight() {
		return this. height;
	}
	/**
	 * Getter
	 * @return the current instance gridsize
	 */
=======
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this. height;
	}
	
>>>>>>> 3edddabc4187178835a585eb4d0450cc10da5c55
	public int getGridSize() {
		return gridSize;
	}

}
