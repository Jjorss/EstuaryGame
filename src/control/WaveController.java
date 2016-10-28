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
	
	public void render(Graphics g, int scale) {
		g.setColor(Color.BLUE);
		g.fillRect(this.getX()*scale, this.getY()*scale, 50, 50);
		System.out.println(this.getX()*scale);
		
	}

}
