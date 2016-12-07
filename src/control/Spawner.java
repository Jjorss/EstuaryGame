package control;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import model.ClumpOfOysters;
import model.Plants;
import model.RunOff;
import model.Timer;
import model.Wave;
import view.Game;

public class Spawner {

	private GameLoopController glc;
	private Game game;
	private Timer timer;
	private Timer spawnTimer = new Timer();
	
	private ArrayList<Integer>plantsInRow = new ArrayList<Integer>();
	private ArrayList<Integer>patternInRow = new ArrayList<Integer>();
	private ArrayList<Boolean>runOffInRow = new ArrayList<Boolean>();
	
	private boolean increasedIntensity = false;
	private int intensity = 1;
	private int rowForRunOff = 2;
	
	public  Spawner(GameLoopController glc, Game game, Timer timer) {
		this.glc = glc;
		this.game = game;
		this.timer = timer;
	}
	

	public void determineIntensity(int currentIntensity, boolean eroded) {
		
		if (this.intensity < 10) {
			if (timer.getTime() % 10 == 0 && !this.increasedIntensity && !eroded) {
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
		int width = (int) (game.getWidth() * 0.03);
		int height = (int) (game.getWidth() * 0.03);
		int padding = 10;
		int max = 0;
		double spawnTime = ((11 - this.intensity)/10)*2000 + 1000;
		spawnTimer.countUp(spawnTime);
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
		
		if (spawnTimer.getTimeMili() >= spawnTime) {
			ClumpOfOysters clump = new ClumpOfOysters(x, y);
			Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
			glc.getOysters().add(new OysterController(clump, rect));
			System.out.println("Spawned oyster");
		}
		
	}
	public void tutorialSpawnOysters() {
		Random rand = new Random();
		int width = (int) (game.getWidth() * 0.03);
		int height = (int) (game.getWidth() * 0.03);
		int padding = 10;
		int max = 0;
		
		double xLeftBound = glc.getWaveRows().get(0).getX() + width + padding + (max * (glc.getGabionWidth()+glc.getGbPadding()));
		double xRightBound = glc.getGAMEBOX().getWidth() - width - padding;
		int x = (int) (rand.nextInt((int) ((xRightBound - xLeftBound) + 1)) + xLeftBound);
		double yTopBound = glc.getGAMEBOX().getY() + height + padding;
		double yBottomBound = glc.getGAMEBOX().getHeight() - height - padding;
		int y = (int) (rand.nextInt((int) ((yBottomBound - yTopBound) + 1)) + yTopBound);
		
		if (glc.getOysters().size() < 1) {
			ClumpOfOysters clump = new ClumpOfOysters(x, y);
			Rectangle2D rect = new Rectangle2D.Double(x, y, width, height);
			glc.getOysters().add(new OysterController(clump, rect));
			System.out.println("Spawned oyster");
		}
		
	}
	
	public void spawnWaves(int intensity, int time) {
		Random rand = new Random();
		int padding = 35;
		double waveHeight = (glc.getWaveRows().get(0).getHeight() - padding);
		double waveWidth = (glc.getWaveRows().get(0).getHeight()*1.4) - padding;
		int min = (int)(game.getScale().getWidth() * 0.0014);
		double interval = this.intensity * 0.00032;
		int max = (int)((min+interval)*game.getScale().getWidth());
		
		int waveSpeed = rand.nextInt(max - (max-min)) + min; 
		int  numRow = rand.nextInt(7);
		int y = (int) ((glc.getWaveRows().get(numRow).getCenterY()) - (waveHeight/2));
		int x = game.getWidth();
		if (glc.getWaves().size() < intensity) {
			Wave w = new Wave(waveSpeed,x,y);
			Rectangle2D rect = new Rectangle2D.Double(x,y,waveWidth, waveHeight );
			glc.getWaves().add(new WaveController(w, rect));
			glc.getNumOfWavesInRow().set(numRow, glc.getNumOfWavesInRow().get(numRow)+1);
		}
	}
	
	public void TutorialSpawnWaves() {
		Random rand = new Random();
		int padding = 35;
		double waveHeight = (glc.getWaveRows().get(0).getHeight() - padding);
		double waveWidth = (glc.getWaveRows().get(0).getHeight())*1.4 - padding;
		int min = (int)(game.getScale().getWidth() * 0.0014);
		double interval = this.intensity * 0.00032;
		int max = (int)((min+interval)*game.getScale().getWidth());
		int waveSpeed = rand.nextInt(max - (max-min)) + min; 
		int row1 = 1;
		int row2 = 4;
		int y1 = (int) ((glc.getWaveRows().get(row1).getCenterY()) - (waveHeight/2));
		int y2 = (int) ((glc.getWaveRows().get(row2).getCenterY()) - (waveHeight/2));
		int x = game.getWidth();
		if (glc.getWaves().size() < 2) {
			Wave w = new Wave(waveSpeed,x,y1);
			Rectangle2D rect = new Rectangle2D.Double(x,y1,waveWidth, waveHeight );
			glc.getWaves().add(new WaveController(w, rect));
			glc.getNumOfWavesInRow().set(row1, glc.getNumOfWavesInRow().get(row1)+1);
			
			Wave w2 = new Wave(waveSpeed,x,y2);
			Rectangle2D rect2 = new Rectangle2D.Double(x,y2,waveWidth, waveHeight );
			glc.getWaves().add(new WaveController(w2, rect2));
			glc.getNumOfWavesInRow().set(row2, glc.getNumOfWavesInRow().get(row2)+1);
		}
	}
	
	public void spawnPlants(int indexOfRow) {
//		Random rand = new Random();
		int pattern = this.getPatternInRow().get(indexOfRow);
		double plantWidth = glc.getPlantRows().get(0).getWidth()  * 0.2;
		double plantHeight = glc.getPlantRows().get(0).getHeight() * 0.45;
		
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
			y1 = (int)(rowY + (rowHeight * 0.0));
			
			x2 = (int)(rowX + (rowWidth * 0.4));
			y2 = (int)(rowY + (rowHeight * 0.15));
			
			x3 = (int)(rowX + (rowWidth * 0.6));
			y3 = (int)(rowY + (rowHeight * 0.4));
			
		} else if (pattern == 2) {
			x1 = (int)(rowX + (rowWidth * 0.1));
			y1 = (int)(rowY + (rowHeight * 0.1));
			
			x2 = (int)(rowX + (rowWidth * 0.4));
			y2 = (int)(rowY + (rowHeight * 0.4));
			
			x3 = (int)(rowX + (rowWidth * 0.6));
			y3 = (int)(rowY + (rowHeight * 0.1));
		} else {
			x1 = (int)(rowX + (rowWidth * 0.1));
			y1 = (int)(rowY + (rowHeight * 0.3));
			
			x2 = (int)(rowX + (rowWidth * 0.4));
			y2 = (int)(rowY + (rowHeight * 0.0));
			
			x3 = (int)(rowX + (rowWidth * 0.6));
			y3 = (int)(rowY + (rowHeight * 0.4));
		}
		
		boolean first = false;
		boolean second = false;
		boolean third = false;
		for (PlantController plant: glc.getPlants()) {
			if ((int)plant.getRect().getX() == x1 && (int)plant.getRect().getY() == y1) {
				first = true;
			}
			if ((int)plant.getRect().getX() == x2 && (int)plant.getRect().getY() == y2) {
				second = true;
			}
			if ((int)plant.getRect().getX() == x3 && (int)plant.getRect().getY() == y3) {
				third = true;
			}
		}
		if (!first) {
			Rectangle2D rect = new Rectangle2D.Double(x1,y1, plantWidth, plantHeight);
			Plants p = new Plants(x1,y1, true);
			glc.getPlants().add(new PlantController(p, rect));
			
			this.getPlantsInRow().set(indexOfRow, this.getPlantsInRow().get(indexOfRow) + 1);
			glc.getPb().setNumberOfPlants(glc.getPb().getNumberOfPlants() - 1);
		} else if (!second) {
			
			Rectangle2D rect = new Rectangle2D.Double(x2,y2, plantWidth, plantHeight);
			Plants p = new Plants(x2,y2, true);
			glc.getPlants().add(new PlantController(p, rect));
			
			this.getPlantsInRow().set(indexOfRow, this.getPlantsInRow().get(indexOfRow) + 1);
			glc.getPb().setNumberOfPlants(glc.getPb().getNumberOfPlants() - 1);
		} else if (!third) {
			Rectangle2D rect = new Rectangle2D.Double(x3,y3, plantWidth, plantHeight);
			Plants p = new Plants(x3,y3, true);
			glc.getPlants().add(new PlantController(p, rect));

			this.getPlantsInRow().set(indexOfRow, this.getPlantsInRow().get(indexOfRow) + 1);
			glc.getPb().setNumberOfPlants(glc.getPb().getNumberOfPlants() - 1);
		}
//		
//		double upperBound = glc.getPlantRows().get(indexOfRow).getY();
//		double lowerBound = upperBound + glc.getPlantRows().get(indexOfRow).getHeight();
//		for (int i  = 0; i < glc.getPlantrects().size(); i++) {
//			Rectangle2D plant = glc.getPlantrects().get(i);
//			if (plant.getY() >= upperBound && plant.getY() <= lowerBound) {
//				
//			}
//		}
		
	}
	
	public void spawnRunOff(int intensity, int time) {
		Random rand = new Random();
		double max = game.getScale().getWidth() * 0.50;
		double min = game.getScale().getWidth() * 0.10;
		double rfHeight = glc.getPlantRows().get(0).getHeight() *0.2 ;
		int rfWidth = rand.nextInt((int)max - (int)min + 1) + (int)min;
		int numRow = rand.nextInt(7);
		int y = (int) ((glc.getPlantRows().get(numRow).getCenterY()) - (rfHeight/2));
		int x = 0 - rfWidth;
		int speed = (int) (game.getScale().getWidth()*0.003);
		if (glc.getRunOff().size() < this.intensity/3 && /*!this.runOffInRow.get(numRow) &&*/ time < 170) {
			RunOff r = new RunOff(speed,x,y, numRow);
			Rectangle2D rect = new Rectangle2D.Double(x,y,rfWidth, rfHeight );
			glc.getRunOff().add(new RunOffController(r, rect));
			this.runOffInRow.set(numRow, true);
//			System.out.println("spawning runOff");
		}
	}
	
	public void spawnTutorialRunOff() {
		Random rand = new Random();
		double max = game.getScale().getWidth() * 0.75;
		double min = game.getScale().getWidth() * 0.10;
		double rfHeight = glc.getPlantRows().get(0).getHeight() *0.2 ;
		int rfWidth = rand.nextInt((int)max - (int)min + 1) + (int)min;
		int numRow = this.rowForRunOff;
		int y = (int) ((glc.getPlantRows().get(numRow).getCenterY()) - (rfHeight/2));
		int x = 0 - rfWidth;
		int speed = (int) (game.getScale().getWidth()*0.003);
		if (glc.getRunOff().size() < 1) {
			RunOff r = new RunOff(speed,x,y, numRow);
			Rectangle2D rect = new Rectangle2D.Double(x,y,rfWidth, rfHeight );
			glc.getRunOff().add(new RunOffController(r, rect));
			this.runOffInRow.set(numRow, true);

		}
	}
	
	public void spawn(boolean eroded) {
		this.determineIntensity(this.intensity, eroded);
		this.spawnWaves(this.intensity, 0);
		this.spawnOysters(this.intensity, 0);
		this.spawnRunOff(this.intensity, timer.getTime());
		//System.out.println(this.intensity);
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


	public void setTimer(Timer timer) {
		this.timer = timer;
	}


	public int getRowForRunOff() {
		return rowForRunOff;
	}
	
}
