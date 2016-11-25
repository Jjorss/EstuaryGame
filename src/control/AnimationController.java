package control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class AnimationController {

	private GameLoopController glc;
	private BufferedImageController bic;
	
	private int gabionAnimationstate = 0;
	private int oysterAnimationState = 0;
	private int picNum = 0;
	
	private double currentX = 0;
	private double currentY = 0;
	private double progress = 0;
	private double currentXOyster = 0;
	private double currentYOyster = 0;
	private double progressOyster = 0;
	
	private Point start;
	private Point end;
	private Point startOyster;
	private Point endOyster;
	
	private long startTime = 0;
	private long duration = 0;
	private long startTimeOyster = 0;
	private long durationOyster = 0;
	
	
	
	public AnimationController(GameLoopController glc, BufferedImageController bic) {
		this.glc = glc;
		this.bic = bic;
	}
	
	public void playGabionPlacementAnimation(Graphics2D g2) {
		
		switch(gabionAnimationstate) {
		case 0 :
			start = new Point((int)glc.getUiGabion().getCenterX(), (int)glc.getUiGabion().getCenterY());
			end = new Point((int) ((int) glc.getWaveRows().get(0).getX()),
					(int)glc.getWaveRects().get(0).getCenterY());
			startTime = System.currentTimeMillis();
			currentX = (int)start.getX();
			currentY = (int)start.getY();
			this.gabionAnimationstate = 1;
			break;
		case 1:
			picNum = (picNum + 1) % 2;
			picNum += 6;
			g2.drawImage(bic.getImages().get(picNum), (int)currentX, (int)currentY, 100, 150, null);
			//System.out.println("drew image: " + currentX + "\t" + currentY + "\t" + progress);
			duration = System.currentTimeMillis() - startTime;
			progress = duration / 2000.0;
			currentX = (currentX + ((end.getX() - currentX) * progress));
			currentY = (currentY + ((end.getY() - currentY) * progress));
			//g2.setColor(Color.ORANGE);
			//g2.drawRect(end.x, end.y, 50, 50);
			//g2.drawRect((int)glc.getWaveRects().get(0).getCenterX(), (int)glc.getWaveRects().get(0).getCenterY(), 50, 50);
			if (currentY >= end.getY() && currentX <= end.getX()) {
				this.gabionAnimationstate = 0;
			}			
			break;
		}
	}
	
	public void playCollectOysterAnimation(int index, Graphics2D g2) {
		switch(this.oysterAnimationState) {
		case 0:
			this.startOyster = new Point((int)glc.getOysterRects().get(index).getX(), (int)glc.getOysterRects().get(index).getY());
			this.endOyster = new Point((int)glc.getUiGabion().getCenterX(), (int)glc.getUiGabion().getCenterY());
			this.startTimeOyster = System.currentTimeMillis();
			this.currentXOyster = (int) startOyster.getX();
			this.currentYOyster = (int) startOyster.getY();
			this.oysterAnimationState = 1;
			break;
		case 1: 
			glc.getOysterRects().get(index).setRect(currentXOyster, currentYOyster, glc.getOysterRects().get(index).getWidth(),
					glc.getOysterRects().get(index).getHeight());
			this.durationOyster = System.currentTimeMillis() - this.startTimeOyster;
			this.progressOyster = this.durationOyster / 1000.0;
			this.currentXOyster = (this.currentXOyster + ((this.endOyster.getX() - this.currentXOyster) * this.progressOyster));
			this.currentYOyster = (this.currentYOyster + ((this.endOyster.getY() - this.currentYOyster) * this.progressOyster));
			g2.setColor(Color.orange);
			g2.drawLine((int)this.currentXOyster, (int)this.currentYOyster, this.endOyster.x, this.endOyster.y);
			if (glc.getUiGabion().contains(glc.getOysterRects().get(index))) {
				this.oysterAnimationState = 0;
				glc.getOysters().get(index).setVisible(false);
				System.out.println("OVER");
			}
			break;
		default:
		}
	}
	
}
