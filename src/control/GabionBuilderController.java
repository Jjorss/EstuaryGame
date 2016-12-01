package control;

import java.awt.geom.Rectangle2D;

import model.GabionBuilder;

public class GabionBuilderController {

	private GabionBuilder gb;
	private Rectangle2D rect;
	
	public GabionBuilderController(GabionBuilder gb, Rectangle2D rect) {
		this.setGb(gb);
		this.setRect(rect);
	}

	public GabionBuilder getGb() {
		return gb;
	}

	public void setGb(GabionBuilder gb) {
		this.gb = gb;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}
	
}
