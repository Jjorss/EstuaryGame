package control;


import java.awt.geom.Rectangle2D;

import model.PlantBuilder;

public class PlantBuilderController {

	private PlantBuilder pb;
	private Rectangle2D rect;
	
	public PlantBuilderController(PlantBuilder pb, Rectangle2D rect) {
		this.pb = pb;
		this.rect = rect;
	}
}
