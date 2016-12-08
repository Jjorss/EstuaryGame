package control;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.Wave;

public class WaveController implements Serializable{

	private Wave wave;
	private Rectangle2D rect;
	
	public WaveController(Wave wave, Rectangle2D rect) {
		this.setWave(wave);
		this.setRect(rect);
	}

	public Wave getWave() {
		return wave;
	}

	public void setWave(Wave wave) {
		this.wave = wave;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}
}
