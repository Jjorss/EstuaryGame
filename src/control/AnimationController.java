package control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import model.Animation;

public class AnimationController implements Serializable{

	private GameLoopController glc;
	private BufferedImageController bic;
	//
	private int gabionAnimationstate = 0;
	private int oysterAnimationState = 0;
	private int plantAnimationState = 0;
	private int textAnimationState = 0;
	private int picNum = 0;
	private int textCounter = 0;
	private int index = 0;

	private double currentX = 0;
	private double currentY = 0;
	private double progress = 0;
	private double currentXOyster = 0;
	private double currentYOyster = 0;
	private double progressOyster = 0;
	private double currentXPlant = 0;
	private double currentYPlant = 0;
	private double progressPlant = 0;

	private Point start;
	private Point end;
	private Point startOyster;
	private Point endOyster;
	private Point startPlant;
	private Point endPlant;

	private long startTime = 0;
	private long duration = 0;
	private long startTimeOyster = 0;
	private long durationOyster = 0;
	private long startTimePlant = 0;
	private long durationPlant = 0;

	private Rectangle2D oyster;

	private Animation animation;
	private boolean played = false;
	private Rectangle2D rect;

	private String message;


	public AnimationController(GameLoopController glc, BufferedImageController bic, Animation animation, Rectangle2D rect, int index) {
		this.glc = glc;
		this.bic = bic;
		this.setAnimation(animation);
		this.rect = rect;
		this.index = index;
	}

	/**
	 * Draws hand that to move from the gabionUI to the correct play to drop the gabion.
	 * This is used in the tutorial.
	 * 
	 * @param g2 The games  {@link Graphics2D} 
	 */
	public void playGabionPlacementAnimation(Graphics2D g2) {

		switch(gabionAnimationstate) {
		case 0 :
			start = new Point((int)glc.getUiGabion().getCenterX(), (int)glc.getUiGabion().getCenterY());
			end = new Point((int) ((int) glc.getWaveRows().get(index).getX()),
					(int) glc.getWaveRows().get(index).getCenterY());
			startTime = System.currentTimeMillis();
			currentX = (int)start.getX();
			currentY = (int)start.getY();
			this.gabionAnimationstate = 1;
			break;
		case 1:
			g2.drawImage(bic.getImageAtIndex(Image.HAND.getIndex()), (int)currentX, (int)currentY,
					(int)(glc.getGAMEBOX().getWidth() * 0.05), (int)(glc.getGAMEBOX().getWidth() * 0.075), null);
			duration = System.currentTimeMillis() - startTime;
			progress = duration / 2000.0;
			currentX = (currentX + ((end.getX() - currentX) * progress));
			currentY = (currentY + ((end.getY() - currentY) * progress));
			if (currentY >= end.getY() && currentX <= end.getX()) {
				this.gabionAnimationstate = 0;
			}			
			break;
		}
	}

	/**
	 * Draws hand that to move from the plantUI to the correct play to drop the plant.
	 * This is used in the tutorial.
	 * 
	 * @param g2 the game's {@link Graphics2D}
	 */
	public void playPlantPlacementAnimation(Graphics2D g2) {
		switch(this.plantAnimationState){
		case 0:
			startPlant = new Point((int)glc.getUiPlant().getCenterX(), (int)glc.getUiPlant().getCenterY());
			endPlant = new Point((int)glc.getPlantRows().get(index).getX(), (int)glc.getPlantRows().get(index).getCenterY());
			startTimePlant = System.currentTimeMillis();
			currentXPlant = (int)startPlant.getX();
			currentYPlant = (int)startPlant.getY();
			this.plantAnimationState = 1;
			break;
		case 1:
			g2.drawImage(bic.getImageAtIndex(Image.HAND.getIndex()), (int)currentXPlant, (int)currentYPlant,
					(int)(glc.getGAMEBOX().getWidth() * 0.05), (int)(glc.getGAMEBOX().getWidth() * 0.075), null);
			durationPlant = System.currentTimeMillis() - startTimePlant;
			progressPlant = durationPlant / 2000.0;
			currentXPlant = (currentXPlant + ((endPlant.getX() - currentXPlant) * progressPlant));
			currentYPlant = (currentYPlant + ((endPlant.getY() - currentYPlant) * progressPlant));
			if(currentYPlant >= endPlant.getY() && currentXPlant <= endPlant.getX()) {
				this.plantAnimationState = 0;
			}
			break;
		default:

		}
	}

	/**
	 * This function plays the animation of a clump of oysters moving to gabionUI and
	 * then deletes the clump.
	 * @param g2 the game's {@link Graphics2D}
	 */
	public void playCollectOysterAnimation(Graphics2D g2) {
		switch(this.oysterAnimationState) {
		case 0:
			this.oyster = this.rect;
			this.startOyster = new Point((int)oyster.getX(), (int)oyster.getY());
			this.endOyster = new Point((int)glc.getUiGabion().getCenterX(), (int)glc.getUiGabion().getCenterY());
			this.startTimeOyster = System.currentTimeMillis();
			this.currentXOyster = (int) startOyster.getX();
			this.currentYOyster = (int) startOyster.getY();
			this.oysterAnimationState = 1;

			break;
		case 1: 

			oyster.setRect(currentXOyster, currentYOyster, oyster.getWidth(),
					oyster.getHeight());
			this.durationOyster = System.currentTimeMillis() - this.startTimeOyster;
			this.progressOyster = this.durationOyster / 4000.0;
			this.currentXOyster = (this.currentXOyster + ((this.endOyster.getX() - this.currentXOyster) * this.progressOyster));
			this.currentYOyster = (this.currentYOyster + ((this.endOyster.getY() - this.currentYOyster) * this.progressOyster));
			//g2.setColor(Color.red);
			//g2.drawLine((int)this.currentXOyster, (int)this.currentYOyster, this.endOyster.x, this.endOyster.y);
			g2.drawImage(bic.getImageAtIndex(Image.OYSTER.getIndex()), (int)oyster.getX(), (int)oyster.getY(), 
					(int)oyster.getWidth(), (int)(oyster.getHeight()), null);

			if (glc.getUiGabion().intersects(oyster)) {
				this.played = true;
				//System.out.println("OVER");
			}
			break;
		default:
			this.played = true;
			System.out.println("Oyster animation failed");
		}
	}

	/**
	 * Draws text, one letter at a time
	 * 
	 * @param g2 the game's {@link Graphics2D}
	 * @param x position
	 * @param y position
	 */
	public void playTextAnimation(Graphics2D g2, int x, int y) {
		String m = message;
		switch(this.textAnimationState) {
		case 0:
			if (this.message != glc.getMessage()) {
				this.textAnimationState = 0;
				this.textCounter = 0;
				this.message = glc.getMessage();
			}
			m = message.substring(0, this.textCounter);
			this.textCounter++;
			if (this.textCounter == message.length()) {
				this.textAnimationState = 1;
			}
			glc.setSpeaking(true);
			g2.drawString(m, x, y);
			break;
		case 1:
			g2.drawString(this.message, x, y);
			glc.setSpeaking(false);
			if (this.message != glc.getMessage()) {
				this.textAnimationState = 0;
				this.textCounter = 0;
				this.message = glc.getMessage();
			}
			break;
		default:
		}
	}

	/**
	 * played getter
	 * @return boolean played
	 */
	public boolean isPlayed() {
		return played;
	}

	/**
	 * animation getter
	 * @return Animation animation
	 */
	public Animation getAnimation() {
		return animation;
	}

	/**
	 * animation setter
	 * @param Animation animation
	 */
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}


}
