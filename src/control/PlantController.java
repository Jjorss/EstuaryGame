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

	public Plants getPlant() {
		return plant;
	}

	public void setPlant(Plants plant) {
		this.plant = plant;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}
}
