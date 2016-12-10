package control;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.BlueCrab;

public class BlueCrabController implements Serializable{

	private BlueCrab hsc;
	private Rectangle2D rect;
	
	public BlueCrabController(BlueCrab hsc, Rectangle2D rect) {
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
	public BlueCrab getHsc() {
		return hsc;
	}
	/**
	 * HorseshoeCrab hsc setter.
	 * @param BlueCrab hsc
	 */
	public void setHsc(BlueCrab hsc) {
		this.hsc = hsc;
	}
}
