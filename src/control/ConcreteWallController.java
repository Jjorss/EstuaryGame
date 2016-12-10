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

	/**
	 * ConcreteWall object's getter.
	 * @return ConcreteWalls
	 */
	public ConcreteWalls getConcreteWall() {
		return concreteWall;
	}
	/**
	 * ConcreteWall object's setter.
	 * @param concreteWall
	 */
	public void setConcreteWall(ConcreteWalls concreteWall) {
		this.concreteWall = concreteWall;
	}
	/**
	 * Rectangle2D rect getter.
	 * @return Rectangle2D rect
	 */
	public Rectangle2D getRect() {
		return rect;
	}
	/**
	 * Rectangle2D rect setter,
	 * @param rRectangle2D rect
	 */
	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}

}
