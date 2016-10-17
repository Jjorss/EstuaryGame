package control;

import java.awt.Graphics;

public class GameLoopController {

	WaveController wc = new WaveController(1, 50, 50, false);
	
	public void loop() {
		wc.tic();
	}
	
	public void render(Graphics g) {
		wc.render(g);
	}
	
}
