package control;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.RunOff;

public class RunOffController implements Serializable{

	private RunOff runOff;
	private Rectangle2D rect;
	
	public RunOffController(RunOff runOff, Rectangle2D rect) {
		this.setRunOff(runOff);
		this.setRect(rect);
	}
	/**
	 * RunOff runOff getter.
	 * @return RunOff runOff
	 */
	public RunOff getRunOff() {
		return runOff;
	}
	/**
	 * RunOff runOff setter.
	 * @param RunOff runOff
	 */
	public void setRunOff(RunOff runOff) {
		this.runOff = runOff;
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
