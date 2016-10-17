package control;

import java.awt.Color;
import java.awt.Graphics;

import model.Wave;

public class WaveController extends Wave {
	
	public WaveController(int speed, int x, int y, boolean collided) {
		super(speed, x, y, collided);
	}
	
	public void tic() {
		super.tic();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(this.getX(), this.getY(), 50, 50);
	}

}
