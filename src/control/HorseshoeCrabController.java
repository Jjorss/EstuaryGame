package control;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.HorseshoeCrab;

public class HorseshoeCrabController implements Serializable{

	private HorseshoeCrab hsc;
	private Rectangle2D rect;
	
	public HorseshoeCrabController(HorseshoeCrab hsc, Rectangle2D rect) {
		this.setHsc(hsc);
		this.setRect(rect);
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
	/**
	 * HorseshoeCrab hsc getter.
	 * @return HorseshoeCrab hsc
	 */
	public HorseshoeCrab getHsc() {
		return hsc;
	}
	/**
	 * HorseshoeCrab hsc setter.
	 * @param HorseshoeCrab hsc
	 */
	public void setHsc(HorseshoeCrab hsc) {
		this.hsc = hsc;
	}
}
