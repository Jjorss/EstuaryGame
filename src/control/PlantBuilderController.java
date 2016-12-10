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
	/**
	 * PlantBuilder pb getter.
	 * @return PlantBuilder pb
	 */
	public PlantBuilder getPb() {
		return pb;
	}
	/**
	 * PlantBuilder pb setter.
	 * @param PlantBuilder pb
	 */
	public void setPb(PlantBuilder pb) {
		this.pb = pb;
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
