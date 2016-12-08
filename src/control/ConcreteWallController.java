package control;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.ConcreteWalls;

public class ConcreteWallController implements Serializable {

	private ConcreteWalls concreteWall;
	private Rectangle2D rect;
	
	public ConcreteWallController(ConcreteWalls c, Rectangle2D rect) {
		this.setConcreteWall(c);
		this.setRect(rect);
	}

	public ConcreteWalls getConcreteWall() {
		return concreteWall;
	}

	public void setConcreteWall(ConcreteWalls concreteWall) {
		this.concreteWall = concreteWall;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}
	
}
