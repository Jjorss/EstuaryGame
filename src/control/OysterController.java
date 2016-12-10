package control;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.ClumpOfOysters;

public class OysterController implements Serializable{

	private ClumpOfOysters oyster;
	private Rectangle2D rect;
	
	public OysterController(ClumpOfOysters o, Rectangle2D rect) {
		this.setOyster(o);
		this.setRect(rect);
	}
	/**
	 * ClumpOfOysters oyster getter.
	 * @return ClumpOfOysters oyster
	 */
	public ClumpOfOysters getOyster() {
		return oyster;
	}
	/**
	 * ClumpOfOysters oyster setter.
	 * @param ClumpOfOysters oyster
	 */
	public void setOyster(ClumpOfOysters oyster) {
		this.oyster = oyster;
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
