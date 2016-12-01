package control;

import java.awt.geom.Rectangle2D;

import model.HorseshoeCrab;

public class HorseshoeCrabController {

	private HorseshoeCrab hsc;
	private Rectangle2D rect;
	
	public HorseshoeCrabController(HorseshoeCrab hsc, Rectangle2D rect) {
		this.hsc = hsc;
		this.rect = rect;
	}
}
