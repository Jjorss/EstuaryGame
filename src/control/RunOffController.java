package control;

import java.awt.geom.Rectangle2D;

import model.RunOff;

public class RunOffController {

	private RunOff runOff;
	private Rectangle2D rect;
	
	public RunOffController(RunOff runOff, Rectangle2D rect) {
		this.runOff = runOff;
		this.rect = rect;
	}
}
