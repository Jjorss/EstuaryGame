package control;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import model.ClumpOfOysters;
import model.CrabFishMeter;
import model.Plants;
import model.RunOff;
import model.Timer;
import model.Wave;
import view.Game;

public class Spawner {

	private GameLoopController glc;
	private Game game;
	private Timer timer;
	
	private ArrayList<Integer>plantsInRow = new ArrayList<Integer>();
	private ArrayList<Integer>patternInRow = new ArrayList<Integer>();
	private ArrayList<Boolean>runOffInRow = new ArrayList<Boolean>();
	
	private boolean increasedIntensity = false;
	private int intensity = 1;
	
	public  Spawner(GameLoopController glc, Game game, Timer timer) {
		this.glc = glc;
		this.game = game;
		this.timer = timer;
	}
	

	public void determineIntensity(int currentIntensity, boolean eroded) {
		
		if (this.intensity < 10) {
			if (timer.getTime() % 20 == 0 && !this.increasedIntensity && !eroded) {
				this.increasedIntensity = true;
				this.intensity++;
				System.out.println("Intensity: " + this.intensity);
			} else if (timer.getTime() % 10 != 0){
				this.increasedIntensity = false;
			} 
			if (eroded) {
				if (this.intensity <= 1) {
					this.intensity = 1;
				} else {
					this.intensity -=1;
					System.out.println("Intensity: " + this.intensity);
				}
				glc.setEroded(false);
			}
		}
		
	}
	
	public void spawnOysters(int intensity, int time) {
		Random rand = new Random();
		int width = 50;
		int height = 50;
		int padding = 10;
		int max = 0;
		
		for (Integer i : glc.getNumOfGabionsInRow()) {
			if (max < i.intValue()) {
				max = i.intValue();
			}
		}
		
		double xLeftBound = glc.getWaveRows().get(0).getX() + width + padding + (max * (glc.getGabionWidth()+glc.getGbPadding()));
		double xRightBound = glc.getGAMEBOX().getWidth() - width - padding;
		int x = (int) (rand.nextInt((int) ((xRightBound - xLeftBound) + 1)) + xLeftBound);
		double yTopBound = glc.getGAMEBOX().getY() + height + padding;
		double yBottomBound = glc.getGAMEBOX().getHeight() - height - padding;
		int y = (int) (rand.nextInt((int) ((yBottomBound - yTopBound) + 1)) + yTopBound);
		
		if (glc.getOysters().size() < intensity*1.5) {
			glc.getOysters().add(new ClumpOfOysters(x, y));
			glc.getOysterRects().add(new Rectangle2D.Double(x, y, width, height));
			System.out.println("spawned");
		}
		
	}
	
	public void spawnWaves(int intensity, int time) {
		Random rand = new Random();
		int padding = 35;
		double waveHeight = (glc.getWaveRows().get(0).getHeight() - padding);
		double waveWidth = glc.getWaveRows().get(0).getHeight() - padding;
		int  numRow = rand.nextInt(7);
		int y = (int) ((glc.getWaveRows().get(numRow).getCenterY()) - (waveHeight/2));
		int x = game.getWidth();
		if (glc.getWaves().size() < intensity) {
			glc.getWaves().add(new Wave(rand.nextInt(intensity - (intensity-3)) + 3,x,y));
			glc.getWaveRects().add(new Rectangle2D.Double(x,y,waveWidth, waveHeight ));
			glc.getNumOfWavesInRow().set(numRow, glc.getNumOfWavesInRow().get(numRow)+1);
		}
	}
	
	public void spawnPlants(int indexOfRow) {
//		Random rand = new Random();
		int pattern = this.getPatternInRow().get(indexOfRow);
		double plantWidth = glc.getPlantRows().get(0).getWidth()  * 0.2;
		double plantHeight = glc.getPlantRows().get(0).getHeight() * 0.3;
		
		double rowX = glc.getPlantRows().get(indexOfRow).getX();
		double rowY =glc.getPlantRows().get(indexOfRow).getY();
		double rowWidth = glc.getPlantRows().get(indexOfRow).getWidth();
		double rowHeight = glc.getPlantRows().get(indexOfRow).getHeight();
		
		int x1= 0;
		int y1 = 0;
		int x2= 0;
		int y2 = 0;
		int x3= 0;
		int y3 = 0;
		System.out.println("Pattern: " + "\t" + pattern);
		//System.out.println("Plants in row: " + "\t" + this.getPlantsInRow().get(indexOfRow));
		
		if (pattern == 1) {
			x1 = (int)(rowX + (rowWidth * 0.1));
			y1 = (int)(rowY + (rowHeight * 0.1));
			
			x2 = (int)(rowX + (rowWidth * 0.4));
			y2 = (int)(rowY + (rowHeight * 0.35));
			
			x3 = (int)(rowX + (rowWidth * 0.7));
			y3 = (int)(rowY + (rowHeight * 0.6));
			
		} else if (pattern == 2) {
			x1 = (int)(rowX + (rowWidth * 0.1));
			y1 = (int)(rowY + (rowHeight * 0.2));
			
			x2 = (int)(rowX + (rowWidth * 0.4));
			y2 = (int)(rowY + (rowHeight * 0.6));
			
			x3 = (int)(rowX + (rowWidth * 0.7));
			y3 = (int)(rowY + (rowHeight * 0.1));
		} else {
			x1 = (int)(rowX + (rowWidth * 0.1));
			y1 = (int)(rowY + (rowHeight * 0.5));
			
			x2 = (int)(rowX + (rowWidth * 0.4));
			y2 = (int)(rowY + (rowHeight * 0.1));
			
			x3 = (int)(rowX + (rowWidth * 0.7));
			y3 = (int)(rowY + (rowHeight * 0.6));
		}
		if (this.getPlantsInRow().get(indexOfRow).intValue() == 0) {
			glc.getPlantrects().add(new Rectangle2D.Double(x1,y1, plantWidth, plantHeight));
			glc.getPlants().add(new Plants(x1,y1, true));
			this.getPlantsInRow().set(indexOfRow, this.getPlantsInRow().get(indexOfRow) + 1);
			glc.getPb().setNumberOfPlants(glc.getPb().getNumberOfPlants() - 1);
		} else if (this.getPlantsInRow().get(indexOfRow).intValue() == 1) {
			glc.getPlantrects().add(new Rectangle2D.Double(x2,y2, plantWidth, plantHeight));
			glc.getPlants().add(new Plants(x2,y2, true));
			this.getPlantsInRow().set(indexOfRow, this.getPlantsInRow().get(indexOfRow) + 1);
			glc.getPb().setNumberOfPlants(glc.getPb().getNumberOfPlants() - 1);
		} else if (this.getPlantsInRow().get(indexOfRow).intValue() == 2) {
			glc.getPlantrects().add(new Rectangle2D.Double(x3,y3, plantWidth, plantHeight));
			glc.getPlants().add(new Plants(x3,y3, true));
			this.getPlantsInRow().set(indexOfRow, this.getPlantsInRow().get(indexOfRow) + 1);
			glc.getPb().setNumberOfPlants(glc.getPb().getNumberOfPlants() - 1);
		}
		
		
		
	}
	
	public void spawnRunOff(int intensity, int time) {
		Random rand = new Random();
		double max = game.getScale().getWidth() * 0.75;
		double min = game.getScale().getWidth() * 0.10;
		double rfHeight = glc.getPlantRows().get(0).getHeight() *0.2 ;
		int rfWidth = rand.nextInt((int)max - (int)min + 1) + (int)min;
		int numRow = rand.nextInt(7);
		int y = (int) ((glc.getPlantRows().get(numRow).getCenterY()) - (rfHeight/2));
		int x = 0 - rfWidth;
		if (glc.getRunOff().size() < 1/*this.intensity/3*/ && !this.runOffInRow.get(numRow) && time < 120) {
			glc.getRunOff().add(new RunOff(8,x,y, numRow));
			glc.getRunOffRects().add(new Rectangle2D.Double(x,y,rfWidth, rfHeight ));
			this.runOffInRow.set(numRow, true);
//			System.out.println("spawning runOff");
		}
	}
	
	public void spawn(boolean eroded) {
		this.determineIntensity(this.intensity, eroded);
		this.spawnWaves(this.intensity, 0);
		this.spawnOysters(this.intensity, 0);
		this.spawnRunOff(this.intensity, timer.getTime());
	}

	public ArrayList<Integer> getPlantsInRow() {
		return plantsInRow;
	}

	public void setPlantsInRow(ArrayList<Integer> plantsInRow) {
		this.plantsInRow = plantsInRow;
	}

	public ArrayList<Integer> getPatternInRow() {
		return patternInRow;
	}

	public void setPatternInRow(ArrayList<Integer> patternInRow) {
		this.patternInRow = patternInRow;
	}

	public ArrayList<Boolean> getRunOffInRow() {
		return runOffInRow;
	}

	public void setRunOffInRow(ArrayList<Boolean> runOffInRow) {
		this.runOffInRow = runOffInRow;
	}
	
}
