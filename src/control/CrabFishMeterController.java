package control;

import java.awt.geom.Rectangle2D;

import model.CrabFishMeter;

public class CrabFishMeterController {

	private CrabFishMeter cfm;
	private Rectangle2D rect;
	
	public CrabFishMeterController(CrabFishMeter cfm, Rectangle2D rect) {
		this.setCfm(cfm);
		this.setRect(rect);
	}

	public CrabFishMeter getCfm() {
		return cfm;
	}

	public void setCfm(CrabFishMeter cfm) {
		this.cfm = cfm;
	}

	public Rectangle2D getRect() {
		return rect;
	}

	public void setRect(Rectangle2D rect) {
		this.rect = rect;
	}
	
}
