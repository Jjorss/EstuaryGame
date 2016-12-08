package control;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.Shore;

public class ShoreController implements Serializable{

	private Shore shore;
	private Rectangle2D rect;
	
	public ShoreController(Shore shore, Rectangle2D rect) {
		
	}

	public Shore getShore() {
		return shore;
	}

	public void setShore(Shore shore) {
		this.shore = shore;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}
}
