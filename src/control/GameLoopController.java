package control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import model.ClumpOfOysters;
import model.ConcreteWalls;
import model.Entity;
import model.Gabion;
import model.GabionBuilder;
import model.Oysters;
import model.PlantBuilder;
import model.Plants;
import model.RunOff;
import model.Shore;
import model.Timer;
import model.Wave;
import view.Game;
import view.Scale;

/**
 * 
 * @author Jackson Jorss
 * @author Jael Flaquer
 * @author Ben Clark
 * @author Robert Ley
 * 
 *
 */

public class GameLoopController {
	private Game game;
	private Scale scale;
	private Point click;
	private GabionBuilder gb = new GabionBuilder();
	private Spawner spawner;
	private Timer timer = new Timer();
	private Timer plantTimer = new Timer();
	private PlantBuilder pb = new PlantBuilder(plantTimer);
	private String time = "" + timer.getTime();

	private ArrayList<Integer> numOfGabionsInRow = new ArrayList<Integer>();

	// list of entities
	private ArrayList<Wave> waves = new ArrayList<Wave>();
	private ArrayList<Gabion> gabions = new ArrayList<Gabion>();
	private ArrayList<ClumpOfOysters> oysters = new ArrayList<ClumpOfOysters>();
	private ArrayList<ConcreteWalls> concreteWalls = new ArrayList<ConcreteWalls>();
	private ArrayList<Plants> plants = new ArrayList<Plants>();
	private ArrayList<RunOff> runOff = new ArrayList<RunOff>();
	// list of rectangles
	private ArrayList<Rectangle2D> waveRects = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> gabionRects = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> oysterRects = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> concreteRects = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> waveRows = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> plantRows = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> plantrects = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> runOffRects = new ArrayList<Rectangle2D>();

	private Shore shore = new Shore(0, 0);

	private Rectangle2D GAMEBOX = new Rectangle2D.Double(0, 0, 0, 0);
	private Rectangle2D UIBOX = new Rectangle2D.Double(0, 0, 0, 0);
	private Rectangle2D shore1 = new Rectangle2D.Double(0, 0, 0, 0);
	private Rectangle2D gabionBuilder = new Rectangle2D.Double(0, 0, 0, 0);
	private Rectangle2D plantBuilder = new Rectangle2D.Double(0, 0, 0, 0);
	private Rectangle2D uiGabion = new Rectangle2D.Double(0,0,0,0);
	private Rectangle2D uiPlant = new Rectangle2D.Double(0,0,0,0);

	private boolean renderDragGabion = false;
	private boolean renderDragPlant = false;
	
	private double gX;
	private double gY;
	
	private double gbPadding;
	private double gabionWidth;
	private double gabionHeight;
	private double uiPlantWidth;
	private double uiPlantHeight;
	private double concreteWallWidth;

	public GameLoopController(Game game, Scale scale) {
		this.game = game;
		this.scale = scale;
		System.out.println(game.getBounds().getWidth());

	}

	public void init() {
		int scale = this.game.getScale().getGridSize();
		double width = this.game.getScale().getWidth();
		double height = this.game.getScale().getHeight();
		double uiBoxHeight = this.game.getScale().getHeight() * 0.165;
		double shoreWidth = this.game.getScale().getWidth() * 0.35;
		
		concreteWallWidth = width * 0.01;
		gbPadding = width * 0.015;
		
		spawner = new Spawner(this, this.game);
		UIBOX = new Rectangle2D.Double(0,0, width, uiBoxHeight);
		GAMEBOX = new Rectangle2D.Double(0, this.UIBOX.getHeight(), width, 
				height - this.UIBOX.getHeight());
		
		this.shore = new Shore((int)this.GAMEBOX.getX(), (int)this.GAMEBOX.getY());
		shore1 = new Rectangle2D.Double(shore.getX(), shore.getY(), (int) shoreWidth,
				GAMEBOX.getHeight());
		
		
		for (int i = 0; i < 7; i++) {
			waveRows.add(new Rectangle2D.Double(shore1.getWidth() + concreteWallWidth, (UIBOX.getHeight() + (GAMEBOX.getHeight()/ 7) * i),
					GAMEBOX.getWidth() - shore1.getWidth(), GAMEBOX.getHeight() / 7));
			plantRows.add(new Rectangle2D.Double(shore1.getWidth()-(shore1.getWidth()*0.2),(UIBOX.getHeight() + (GAMEBOX.getHeight()/ 7) * i),
					shore1.getWidth()*0.2,GAMEBOX.getHeight() / 7));
			
			concreteWalls.add(new ConcreteWalls((int)shore1.getWidth(),(int)(GAMEBOX.getHeight()/ 7) * i));
			concreteRects.add(new Rectangle2D.Double(shore1.getWidth(), (UIBOX.getHeight() + (GAMEBOX.getHeight()/ 7) * i),
					concreteWallWidth, GAMEBOX.getHeight() / 7));			
			
			// in initializing row araylist
			this.numOfGabionsInRow.add(0);
			spawner.getPlantsInRow().add(0);
			Random rand = new Random();
			int pattern = rand.nextInt(3) + 1;
			spawner.getPatternInRow().add(pattern);
			spawner.getRunOffInRow().add(false);
			//spawner.spawnPlants(i);
		}

		gabionWidth = waveRows.get(0).getHeight() - gbPadding;
		gabionHeight = waveRows.get(0).getHeight() - gbPadding;
		uiPlantHeight = UIBOX.getHeight()  * 0.7;
		uiPlantWidth = uiPlantHeight * (2.0/3.0);
		
		
		double gWidth = 60 * scale;
		gX = UIBOX.getWidth() - gWidth;
		gY = UIBOX.getY();

		gabionBuilder = new Rectangle2D.Double(gX, gY, gWidth, UIBOX.getHeight());
		uiGabion = new Rectangle2D.Double(gabionBuilder.getCenterX() - (gabionWidth),gabionBuilder.getCenterY() - (gabionHeight/2),
				gabionWidth, gabionHeight);
		plantBuilder = new Rectangle2D.Double(UIBOX.getX(), UIBOX.getY(), UIBOX.getHeight() * (2.0/3.0), UIBOX.getHeight());
		uiPlant = new Rectangle2D.Double(plantBuilder.getCenterX() - (uiPlantWidth/2),plantBuilder.getCenterY() - (uiPlantHeight/2.0),
				uiPlantWidth, uiPlantHeight);
		
	}

	/**
	 * The main loop for the game where all the instantiated object's tick
	 * methods get called.
	 */
	public void loop() {
		spawner.spawn();
		timer.countDown();
		plantTimer.countUp();
		pb.build();
		for (int i = 0; i < waves.size(); i++) {
			waves.get(i).move();
			waveRects.get(i).setRect(waves.get(i).getX(), waveRects.get(i).getY(), waveRects.get(i).getWidth(),
					waveRects.get(i).getHeight());
		}	
	
		ArrayList<RunOff> tempRunOff = new ArrayList<RunOff>();
		ArrayList<Rectangle2D> tempBox = new ArrayList<Rectangle2D>();
		for (int i = 0; i < runOff.size(); i++) {
			runOff.get(i).move();
			if (shore1.getWidth() + concreteWallWidth + 8 < runOffRects.get(i).getX() + runOffRects.get(i).getWidth()) {
				runOffRects.get(i).setRect(runOff.get(i).getX(), runOff.get(i).getY(), 
						runOffRects.get(i).getWidth() - runOff.get(i).getSpeed(), runOffRects.get(i).getHeight());
				
				if (runOffRects.get(i).getWidth() > 0) {
					tempBox.add(runOffRects.get(i));
					tempRunOff.add(runOff.get(i));
					
				} else {
					spawner.getRunOffInRow().set(runOff.get(i).getRowNum(), false);
				}
			} else {
				runOffRects.get(i).setRect(runOff.get(i).getX(), runOff.get(i).getY(), runOffRects.get(i).getWidth(),
						runOffRects.get(i).getHeight());
				tempBox.add(runOffRects.get(i));
				tempRunOff.add(runOff.get(i));
			}
			
		}
		runOff = tempRunOff;
		runOffRects = tempBox;
		// System.out.println(waves.size());
		for (int i = 0; i < oysters.size(); i++) {
			if (!oysters.get(i).isVisible()) {
				oysters.remove(i);
				oysterRects.remove(i);
			}
		}

		// collision detections
		collision();

		// System.out.println("I'm looping");
	}

	/**
	 * The main loop for the game were all the instantiated object's render
	 * methods get called.
	 */

	public void render(Graphics g, int scale) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		g2.draw(UIBOX);
		g2.fill(UIBOX);
		
		g2.setColor(Color.cyan);
		g2.draw(GAMEBOX);
		g2.fill(GAMEBOX);

		// abstract way
		g2.setColor(Color.BLUE);
		for (Rectangle2D rect : waveRects) {
			g2.draw(rect);
			g2.fill(rect);
		}

		g2.setColor(Color.BLACK);
		for (Rectangle2D gabions : gabionRects) {
			g2.draw(gabions);
			g2.fill(gabions);
		}
		g2.setColor(Color.GRAY);
		for (Rectangle2D oyster : oysterRects) {
			g2.draw(oyster);
			g2.fill(oyster);
		}
		for (Rectangle2D wall : concreteRects) {
			g2.draw(wall);
			g2.fill(wall);
		}
		
		// single way
		g2.setColor(Color.YELLOW);
		g2.fill(shore1);
		g2.draw(shore1);

		g2.setColor(new Color(184, 138, 0, 127));
		for (Rectangle2D runOff : runOffRects) {
			g2.draw(runOff);
			g2.fill(runOff);
		}
		
		g2.setColor(Color.GREEN);
		for (int i = 0; i < plants.size(); i++) {
			if (plants.get(i).isVisible()) {
				g2.draw(plantrects.get(i));
				g2.fill(plantrects.get(i));
			}
		}
		
		
		// UI
		// ---------------------------
		// Gabion builder/Plant builder

		
		Font f1 = new Font("Arial", 50, 100);
		g2.setFont(f1);
		g2.setColor(Color.BLACK);
		g2.drawString(timer.getTime() + "", (int) UIBOX.getCenterX(), (int) UIBOX.getCenterY());
		
		g2.setColor(Color.GRAY);
		g2.fill(gabionBuilder);
		g2.draw(gabionBuilder);

		// GabionBuilder Meter
		g2.setColor(Color.ORANGE);
		double gbPercentage = (double)gb.getNumberOfOysters() / (double)gb.getMaxGabionCapacity();
		double maxHeight = gabionBuilder.getHeight();
		double height = maxHeight * gbPercentage;
		double width = gabionBuilder.getWidth()/10;
		double x = gabionBuilder.getX(); //- (width/2);
		double y = maxHeight - height;
		Rectangle2D gabionMeter = new Rectangle2D.Double(x, y, width, height);
		g2.draw(gabionMeter);
		g2.fill(gabionMeter);
		// Fake gabion in UI meter
		if (gb.getGabions() == 0) {
			g2.setColor(new Color(0, 0, 0, 50));
		} else {
			g2.setColor(new Color(0, 0, 0, 255));
		}
		g2.draw(uiGabion);
		g2.fill(uiGabion);
		
		if (this.renderDragGabion) {
			g2.draw(this.renderDragGabion(game.getMouseCords()));
			g2.setColor(Color.gray);
			for (Rectangle2D row : waveRows) {
				g2.draw(row);
				
			}
		}
		
		// number of gabions
		g2.setColor(Color.WHITE);
		g2.drawString("" + gb.getGabions(), (int)gabionBuilder.getCenterX() + (f1.getSize()/2), (int)gabionBuilder.getCenterY());
		// plant meter
		maxHeight = plantBuilder.getHeight();
		height = ((double)plantTimer.getTimeMili()/(pb.getNumOfSecondsPerPlant()*1000)) * maxHeight;
		width = plantBuilder.getWidth();
		x = plantBuilder.getX();
		y = maxHeight - height;
		Rectangle2D plantMeter = new Rectangle2D.Double(x, y, width, height);
		
		g2.setColor(Color.ORANGE);
		//g2.fill(plantBuilder);
		g2.draw(plantBuilder);
		g2.draw(plantMeter);
		g2.fill(plantMeter);
		
		g2.setColor(Color.GREEN);
		g2.draw(uiPlant);
		g2.fill(uiPlant);
		
		g2.setColor(Color.BLACK);
		g2.drawString(pb.getNumberOfPlants()+"", (int)(plantBuilder.getX() + plantBuilder.getWidth() + (f1.getSize()/2)),
				(int)plantBuilder.getCenterY());
		
		if (this.renderDragPlant) {
			g2.setColor(Color.GREEN);
			g2.draw(this.renderDragPlant(game.getMouseCords()));
			g2.setColor(Color.gray);
			for (Rectangle2D row : plantRows) {
				g2.draw(row);
				
			}
		}
		
	}

	public void collision() {
		class EntityStruct {
			int index = 0;
			String type = "";
			public EntityStruct(int index, String type){
				this.index = index;
				this.type = type;
			}
		}
		ArrayList<EntityStruct>whatToRemove = new ArrayList<EntityStruct>();
		
		
		
		for (int i = 0; i < waveRects.size(); i++) {
			if (waveRects.get(i).intersects(shore1.getX(), shore1.getY(), shore1.getWidth(), shore1.getHeight())) {
				// wave hit shore
				// erode shore
				shore.erode();
				shore1.setRect(shore1.getX(), shore1.getY(), shore1.getWidth() - (game.getScale().getWidth()*0.03),
						shore1.getHeight());
				for (Rectangle2D plantRow: plantRows) {
					plantRow.setRect(shore1.getWidth() - plantRow.getWidth(), plantRow.getY(), plantRow.getWidth(), plantRow.getHeight());
				}
				for (int j = 0; j < plantrects.size(); j++ ) {
					plantrects.get(j).setRect(plantrects.get(j).getX() - (game.getScale().getWidth()*0.03), plantrects.get(j).getY(),
							plantrects.get(j).getWidth(), plantrects.get(j).getHeight());
				}
				System.out.println("Wave Hit Shore");
				whatToRemove.add(new EntityStruct(i, "wave"));

			}
			// if a wave hits a gabion, remove wave
			// PUT CHANGE OF HEALTH HERE IF WE DECIDE TO GO WITH HEALTH FOR
			// GABIONS
			for (int j = 0; j < gabionRects.size(); j++) {

				if (gabionRects.get(j).intersects(waveRects.get(i).getX(), waveRects.get(i).getY(),
						waveRects.get(i).getWidth(), waveRects.get(i).getHeight())) {
					
					whatToRemove.add(new EntityStruct(i, "wave"));
					gabions.get(j).changeHealth(gabions.get(j).getHealth() - 1);
					System.out.println("wave hit gabion");
					if (gabions.get(j).getHealth() <=0) {
						whatToRemove.add(new EntityStruct(j, "gabion"));
						// debugging only
						try {
							this.numOfGabionsInRow.set(gabions.get(j).getRowNum(), this.numOfGabionsInRow.get(gabions.get(j).getRowNum())-1);
						} catch(IndexOutOfBoundsException e) {
							System.out.println(gabions.get(j).getRowNum());
							e.printStackTrace();
						}
						System.out.println("row: " + j + " " + this.numOfGabionsInRow.get(i) );
					}
				}
			}
			for (int j = 0; j < concreteRects.size(); j++) {
				if (concreteRects.get(j).intersects(waveRects.get(i).getX(), waveRects.get(i).getY(),
						waveRects.get(i).getWidth(), waveRects.get(i).getHeight())) {
					
					whatToRemove.add(new EntityStruct(i,"wave"));
					
					whatToRemove.add(new EntityStruct(j, "concrete"));
				}
			}
		}
		// this is throwing an array out of bounds exception from time to time
		for (EntityStruct e: whatToRemove) {
			if (e.type.equals("wave")) {
				this.waves.remove(e.index);
				this.waveRects.remove(e.index);
			} else if (e.type.equals("gabion")) {
				this.gabionRects.remove(e.index);
				this.gabions.remove(e.index);
			} else if (e.type.equals("concrete")) {
				this.concreteRects.remove(e.index);
				this.concreteWalls.remove(e.index);
			} else {
				System.out.println("really not suppose to happen!!!");
			}
		}
		

	}
	
	public void handlePlacePlant(Point p) {
		if (pb.getNumberOfPlants() != 0) {
			for (int i = 0; i < plantRows.size(); i++) {
				Rectangle2D row = plantRows.get(i);
				if (row.contains(p)) {
					spawner.spawnPlants(i);
					
				}
			}
		}
		this.renderDragPlant = false;
	}

	public void handlePlaceGabion(Point p) {
		//System.out.println(p.getX() + ", " + p.getY());
		
		// spawn gabion
		if (gb.getGabions() != 0) {
			for (int i = 0; i < waveRows.size(); i++) {
				Rectangle2D row = waveRows.get(i);
				if (row.contains(p) && this.numOfGabionsInRow.get(i) < 5) {
					double y = row.getCenterY() - ((gabionHeight) / 2);
					double x = ((gabionWidth + gbPadding) * this.numOfGabionsInRow.get(i)) + row.getX();
					gabions.add(new Gabion((int) x, (int) y, i));
					gabionRects.add(new Rectangle.Double(x + gbPadding, y, gabionWidth, gabionHeight));
					gb.setGabions(gb.getGabions() - 1);
					this.numOfGabionsInRow.set(i, this.numOfGabionsInRow.get(i) + 1);
				}
			}
		}
		this.renderDragGabion = false;

	}

	public void handleCollectOyster(Point p, int i) {
		int numOfClusters = 0;

		numOfClusters = oysters.get(i).getNumOfOystersInClump();
		oysters.get(i).setVisible(false);
		// adding GB stuff
		gb.build(numOfClusters);
		//System.out.println(p);
	}
	
	public Rectangle2D renderDragGabion(Point p) {
		Rectangle2D r = new Rectangle2D.Double(p.getX()-(uiGabion.getWidth()/2),p.getY()-(uiGabion.getWidth()/2),uiGabion.getWidth(),uiGabion.getHeight());
		return r;
	}
	
	public Rectangle2D renderDragPlant(Point p) {
		double width = this.getPlantRows().get(0).getWidth()  * 0.2;
		double height = this.getPlantRows().get(0).getWidth()  * 0.3;
		Rectangle2D r = new Rectangle2D.Double(p.getX() - (width/2), p.getY() - (height/2),
				width, height);
		return r;
	}
	
	public void handleDrag(Point p) {
		if (uiGabion.contains(p)) {
			System.out.println("uiGabion clicked");
			this.renderDragGabion = true;
			game.setDragging(true);
		}
		if (uiPlant.contains(p)) {
			System.out.println("uiPlant Clicked");
			this.renderDragPlant = true;
			game.setDragging(true);
		}
	}

	public Point getMousePressed(Point p) {
		return p;
	}
	
	public void handlePressed(Point p) {
		for (int i = 0; i < oysterRects.size(); i++) {
			if (oysterRects.get(i).contains(p)) {
				handleCollectOyster(p, i);
				return;
			}
		}
		this.handleDrag(p);
		
	}

	public ArrayList<Wave> getWaves() {
		return waves;
	}

	public ArrayList<Rectangle2D> getWaveRows() {
		return waveRows;
	}

	public ArrayList<Rectangle2D> getWaveRects() {
		return waveRects;
	}

	public ArrayList<ClumpOfOysters> getOysters() {
		return oysters;
	}

	public ArrayList<Rectangle2D> getOysterRects() {
		return oysterRects;
	}

	public Rectangle2D getShore1() {
		return shore1;
	}

	public Rectangle2D getGAMEBOX() {
		return GAMEBOX;
	}

	public ArrayList<Integer> getNumOfGabionsInRow() {
		return numOfGabionsInRow;
	}

	public double getGbPadding() {
		return gbPadding;
	}

	public double getGabionWidth() {
		return gabionWidth;
	}

	public ArrayList<Plants> getPlants() {
		return plants;
	}

	public void setPlants(ArrayList<Plants> plants) {
		this.plants = plants;
	}

	public ArrayList<Rectangle2D> getPlantrects() {
		return plantrects;
	}

	public void setPlantrects(ArrayList<Rectangle2D> plantrects) {
		this.plantrects = plantrects;
	}

	public ArrayList<Rectangle2D> getPlantRows() {
		return plantRows;
	}

	public PlantBuilder getPb() {
		return pb;
	}

	public void setPb(PlantBuilder pb) {
		this.pb = pb;
	}
	
	public boolean isRenderDragGabion() {
		return renderDragGabion;
	}

	public boolean isRenderDragPlant() {
		return renderDragPlant;
	}

	public ArrayList<Rectangle2D> getRunOffRects() {
		return runOffRects;
	}

	public ArrayList<RunOff> getRunOff() {
		return runOff;
	}

}
