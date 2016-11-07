package control;

import java.awt.geom.Rectangle2D;
import java.util.Random;

import model.CrabFishMeter;
import model.Wave;
import view.Game;

public class Spawner {

	private GameLoopController glc;
	private Game game;
	public  Spawner(GameLoopController glc, Game game) {
		this.glc = glc;
		this.game = game;
	}
	
//	public int determineSkill(int mood, int shoreHealth) {
//		if () {
//			
//		}
//		
//		//0 good
//		//1 doing badly
//		//2 failing
//	}
	
	public int determineWaveQuantity(int intensity, int skill) {
		return intensity;// *skill
	}
	
	public void spawnWaves(int intensity, int time) {
		Random rand = new Random();
		int padding = 35;
		double waveHeight = (glc.getRows().get(0).getHeight() - padding);
		double waveWidth = glc.getRows().get(0).getHeight() - padding;
		int  numRow = rand.nextInt(7);
		int y = (int) ((glc.getRows().get(numRow).getCenterY()) - (waveHeight/2));
		int x = 2000;
		if (glc.getWaves().size() < 5) {
			glc.getWaves().add(new Wave(8,x,y));
			glc.getWaveRects().add(new Rectangle2D.Double(x,y,waveWidth, waveHeight ));
			
		}
	}
	
	public void spawn() {
		this.spawnWaves(0, 0);
	}
	
}
