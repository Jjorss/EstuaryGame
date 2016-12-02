package control;

import java.awt.geom.Rectangle2D;

import model.Gabion;

public class GabionController {

	private Gabion gabion;
	private Rectangle2D rect;
	
	public GabionController(Gabion gabion, Rectangle2D rect) {
		this.setGabion(gabion);
		this.setRect(rect);
	}

	public Gabion getGabion() {
		return gabion;
	}

	public void setGabion(Gabion gabion) {
		this.gabion = gabion;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}
}
