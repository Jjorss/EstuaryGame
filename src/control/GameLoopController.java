package control;

import java.awt.Graphics;

/**
 * 
 * @author Jackson Jorss
 * @author Jael Flaquer
 * @author Ben Clark
 * @author Robert Lee
 * 
 *
 */

public class GameLoopController {

	WaveController wc = new WaveController(1, 70, 0, false);
	
	/**
	 * The main loop for the game were all the instantiated object's tic methods get called.
	 */
	public void loop() {
		wc.tic();
	}
	/**
	 * The main loop for the game were all the instantiated object's render methods get called.
	 */
	public void render(Graphics g, int scale) {
		wc.render(g, scale);
		
	}
	
}
