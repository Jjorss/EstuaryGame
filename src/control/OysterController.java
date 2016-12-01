package control;

import java.awt.geom.Rectangle2D;

import model.ClumpOfOysters;

public class OysterController {

	private ClumpOfOysters oyster;
	private Rectangle2D rect;
	
	public OysterController(ClumpOfOysters o, Rectangle2D rect) {
		this.oyster = o;
		this.rect = rect;
	}
	
	
}
