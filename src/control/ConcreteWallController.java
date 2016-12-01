package control;

import java.awt.geom.Rectangle2D;

import model.ConcreteWalls;

public class ConcreteWallController {

	private ConcreteWalls concreteWall;
	private Rectangle2D rect;
	
	public ConcreteWallController(ConcreteWalls c, Rectangle2D rect) {
		this.concreteWall = c;
		this.rect = rect;
	}
	
}
