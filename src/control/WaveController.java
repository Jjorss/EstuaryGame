package control;

import java.awt.geom.Rectangle2D;

import model.Wave;

public class WaveController {

	private Wave wave;
	private Rectangle2D rect;
	
	public WaveController(Wave wave, Rectangle2D rect) {
		this.wave = wave;
		this.rect = rect;
	}
}
