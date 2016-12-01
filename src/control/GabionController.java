package control;

import java.awt.geom.Rectangle2D;

import model.Gabion;

public class GabionController {

	private Gabion gabion;
	private Rectangle2D rect;
	
	public GabionController(Gabion gabion, Rectangle2D rect) {
		this.gabion = gabion;
		this.rect = rect;
	}
}
