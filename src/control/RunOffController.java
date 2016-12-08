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

	public RunOff getRunOff() {
		return runOff;
	}

	public void setRunOff(RunOff runOff) {
		this.runOff = runOff;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}
}
