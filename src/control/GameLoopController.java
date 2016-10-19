package control;

import java.awt.Graphics;

public class GameLoopController {

	WaveController wc = new WaveController(1, 70, 0, false);
	
	
	public void loop() {
		wc.tic();
	}
	
	public void render(Graphics g, int scale) {
		wc.render(g, scale);
		
	}
	
}
