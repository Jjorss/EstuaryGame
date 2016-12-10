package control;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.GabionBuilder;

public class GabionBuilderController implements Serializable{

	private GabionBuilder gb;
	private Rectangle2D rect;

	public GabionBuilderController(GabionBuilder gb, Rectangle2D rect) {
		this.setGb(gb);
		this.setRect(rect);
	}
	/**
	 * GabionBuilder gb getter.
	 * @return GabionBuilder gb
	 */
	public GabionBuilder getGb() {
		return gb;
	}
	/**
	 * GabionBuilder gb setter.
	 * @param GabionBuilder gb
	 */
	public void setGb(GabionBuilder gb) {
		this.gb = gb;
	}
	/**
	 * Rectangle2D rect getter.
	 * @return Rectangle2D rect
	 */
	public Rectangle2D getRect() {
		return rect;
	}
	/**
	 * Rectangle2D rect setter.
	 * @param Rectangle2D rect
	 */
	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}

}
