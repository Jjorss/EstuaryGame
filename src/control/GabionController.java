package control;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.Gabion;

public class GabionController implements Serializable{

	private Gabion gabion;
	private Rectangle2D rect;
	
	public GabionController(Gabion gabion, Rectangle2D rect) {
		this.setGabion(gabion);
		this.setRect(rect);
	}
	/**
	 * Gabion gabion getter.
	 * @return Gabion gabion
	 */
	public Gabion getGabion() {
		return gabion;
	}
	/**
	 * Gabion gabion setter.
	 * @param Gabion gabion
	 */
	public void setGabion(Gabion gabion) {
		this.gabion = gabion;
	}
	/**
	 * Rectangle2D Rect getter.
	 * @return Rectangle2D Rect
	 */
	public Rectangle2D getRect() {
		return rect;
	}
	/**
	 * Rectangle2D Rect setter.
	 * @param Rectangle2D Rect
	 */
	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}
}
