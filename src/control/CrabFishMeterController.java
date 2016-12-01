package control;

import java.awt.geom.Rectangle2D;

import model.CrabFishMeter;

public class CrabFishMeterController {

	private CrabFishMeter cfm;
	private Rectangle2D rect;
	
	public CrabFishMeterController(CrabFishMeter cfm, Rectangle2D rect) {
		this.cfm = cfm;
		this.rect = rect;
	}
	
}
