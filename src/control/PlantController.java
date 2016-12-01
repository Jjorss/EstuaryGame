package control;

import java.awt.geom.Rectangle2D;

import model.Plants;

public class PlantController {

	private Plants plant;
	private Rectangle2D rect;
	
	public PlantController(Plants plant, Rectangle2D rect) {
		this.plant = plant;
		this.rect = rect;
	}
}
