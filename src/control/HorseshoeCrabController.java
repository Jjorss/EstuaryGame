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

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}

	public HorseshoeCrab getHsc() {
		return hsc;
	}

	public void setHsc(HorseshoeCrab hsc) {
		this.hsc = hsc;
	}
}
