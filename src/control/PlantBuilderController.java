package control;


import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.PlantBuilder;

public class PlantBuilderController implements Serializable{

	private PlantBuilder pb;
	private Rectangle2D rect;
	
	public PlantBuilderController(PlantBuilder pb, Rectangle2D rect) {
		this.setPb(pb);
		this.setRect(rect);
	}

	public PlantBuilder getPb() {
		return pb;
	}

	public void setPb(PlantBuilder pb) {
		this.pb = pb;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}
}
