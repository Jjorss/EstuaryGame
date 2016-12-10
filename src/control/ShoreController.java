package control;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.Shore;

public class ShoreController implements Serializable{

	private Shore shore;
	private Rectangle2D rect;
	
	public ShoreController(Shore shore, Rectangle2D rect) {
		
	}
	/**
	 * Shore shore getter.
	 * @return Shore shore
	 */
	public Shore getShore() {
		return shore;
	}
	/**
	 * Shore shore setter.
	 * @param Shore shore
	 */
	public void setShore(Shore shore) {
		this.shore = shore;
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
