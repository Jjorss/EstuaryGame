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

	/**
	 * Object Wave wave getter
	 * @return
	 */
	public Wave getWave() {
		return wave;
	}
	
	/**
	 * Object Wave wave setter
	 * @param Wave wave
	 */
	public void setWave(Wave wave) {
		this.wave = wave;
	}
	/**
	 * Rectangle2D rect getter.
	 * @return Rectangle2D rect
	 */
	public Rectangle2D getRect() {
		return rect;
	}
	/**
	 * Rectangle2D rect setter.
	 * @param Rectangle2D rect
	 */
	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}
}
