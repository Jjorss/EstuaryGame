package view;

import java.awt.Color;
import java.awt.Graphics;

public class Scale {

	private int width;
	private int height;
	
	private int gridSize;
	
	public Scale(int width, int height, int gridSize) {
		this.width = width;
		this.height = height;
		this.gridSize = gridSize;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		for (int i = 0; i < this.getWidth(); i+=gridSize) {
			g.drawLine(i, 0, i, this.getHeight());
			g.drawLine(0, i, this.getWidth(), i);
		}
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this. height;
	}
	
	public int getGridSize() {
		return gridSize;
	}

}
