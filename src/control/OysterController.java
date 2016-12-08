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

	public ClumpOfOysters getOyster() {
		return oyster;
	}

	public void setOyster(ClumpOfOysters oyster) {
		this.oyster = oyster;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}
	
	
}
