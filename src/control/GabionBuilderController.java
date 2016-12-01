package control;

import java.awt.geom.Rectangle2D;

import model.GabionBuilder;

public class GabionBuilderController {

	private GabionBuilder gb;
	private Rectangle2D rect;
	
	public GabionBuilderController(GabionBuilder gb, Rectangle2D rect) {
		this.gb = gb;
		this.rect = rect;
	}
	
}
