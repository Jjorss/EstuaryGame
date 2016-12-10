package control;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.Plants;

public class PlantController implements Serializable{

	private Plants plant;
	private Rectangle2D rect;
	
	public PlantController(Plants plant, Rectangle2D rect) {
		this.setPlant(plant);
		this.setRect(rect);
	}
	/**
	 * Plants plant getter.
	 * @return Plants plant
	 */
	public Plants getPlant() {
		return plant;
	}
	/**
	 * Plants plant setter.
	 * @param Plants plant
	 */
	public void setPlant(Plants plant) {
		this.plant = plant;
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
