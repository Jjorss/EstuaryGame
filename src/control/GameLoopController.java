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
	Timer timer = new Timer();
	private String time = "" + timer.getTime();

	private ArrayList<Integer> numOfGabionsInRow = new ArrayList<Integer>();

	// list of entities
	private ArrayList<Wave> waves = new ArrayList<Wave>();
	private ArrayList<Gabion> gabions = new ArrayList<Gabion>();
	private ArrayList<ClumpOfOysters> oysters = new ArrayList<ClumpOfOysters>();
	private ArrayList<ConcreteWalls> concreteWalls = new ArrayList<ConcreteWalls>();
	// list of rectangles
	private ArrayList<Rectangle2D> waveRects = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> gabionRects = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> oysterRects = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> concreteRects = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> rows = new ArrayList<Rectangle2D>();

	private Shore shore = new Shore(0, 0);

	private Rectangle2D GAMEBOX = new Rectangle2D.Double(0, 0, 0, 0);
	private Rectangle2D UIBOX = new Rectangle2D.Double(0, 0, 0, 0);
	private Rectangle2D shore1 = new Rectangle2D.Double(0, 0, 0, 0);
	private Rectangle2D gabionBuilder = new Rectangle2D.Double(0, 0, 0, 0);
	private Rectangle2D plantBuilder = new Rectangle2D.Double(0, 0, 0, 0);

	private double gX;
	private double gY;

	public GameLoopController(Game game, Scale scale) {
		this.game = game;
		this.scale = scale;
		System.out.println(game.getBounds().getWidth());

	}

	public void init() {
		int scale = this.game.getScale().getGridSize();
		double width = this.game.getScale().getWidth();
		double height = this.game.getScale().getHeight();
		double uiBoxHeight = this.game.getScale().getHeight() * 0.20;
		double shoreWidth = this.game.getScale().getWidth() * 0.35;
		
		
		spawner = new Spawner(this, this.game);
		UIBOX = new Rectangle2D.Double(0,0, width, uiBoxHeight);
		GAMEBOX = new Rectangle2D.Double(0, this.UIBOX.getHeight(), width, 
				height - this.UIBOX.getHeight());
		
		this.shore = new Shore((int)this.GAMEBOX.getX(), (int)this.GAMEBOX.getY());
		shore1 = new Rectangle2D.Double(shore.getX(), shore.getY(), (int) shoreWidth,
				GAMEBOX.getHeight());

		for (int i = 0; i < 7; i++) {
			rows.add(new Rectangle2D.Double(shore1.getWidth(), (UIBOX.getHeight() + (GAMEBOX.getHeight()/ 7) * i),
					GAMEBOX.getWidth() - shore1.getWidth(), GAMEBOX.getHeight() / 7));
			
			concreteWalls.add(new ConcreteWalls((int)shore1.getWidth(),(int)(GAMEBOX.getHeight()/ 7) * i));
			concreteRects.add(new Rectangle2D.Double(shore1.getWidth(), (UIBOX.getHeight() + (GAMEBOX.getHeight()/ 7) * i),
					20, GAMEBOX.getHeight() / 7));
			
			// in initializing row araylist
			this.numOfGabionsInRow.add(0);
		}

		gX = UIBOX.getWidth() - 27 * scale;
		gY = UIBOX.getY();

		gabionBuilder = new Rectangle2D.Double(gX, gY, 27 * scale, UIBOX.getHeight());
		plantBuilder = new Rectangle2D.Double(UIBOX.getX(), UIBOX.getY(), 100, UIBOX.getHeight());

	}

	/**
	 * The main loop for the game where all the instantiated object's tick
	 * methods get called.
	 */
	public void loop() {
		spawner.spawn();
		timer.countDown();
		for (int i = 0; i < waves.size(); i++) {
			waves.get(i).move();
			waveRects.get(i).setRect(waves.get(i).getX(), waveRects.get(i).getY(), waveRects.get(i).getWidth(),
					waveRects.get(i).getHeight());
		}	
	
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

		// UI
		// ---------------------------
		// Gabion builder/Plant builder

		g2.setColor(Color.gray);
		for (Rectangle2D row : rows) {
			g2.draw(row);
			// g2.fill(row);
		}
		Font f1 = new Font("Arial", 50, 100);
		g2.setFont(f1);
		g2.setColor(Color.BLACK);
		g2.drawString(timer.getTime() + "", (int) UIBOX.getCenterX(), (int) UIBOX.getCenterY());

		g2.setColor(Color.GRAY);
		g2.fill(gabionBuilder);
		g2.draw(gabionBuilder);

		g2.setColor(Color.GREEN);
		g2.fill(plantBuilder);
		g2.draw(plantBuilder);

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
				shore1.setRect(shore1.getX(), shore1.getY(), shore1.getWidth() - (10 * game.getScale().getGridSize()),
						shore1.getHeight());
				
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

	public void handlePlaceGabion(Point p) {
		//System.out.println(p.getX() + ", " + p.getY());
		int padding = 35;
		double gabionWidth = rows.get(0).getHeight() - padding;
		double gabionHeight = rows.get(0).getHeight() - padding;
		// spawn gabion
		if (gb.getGabions() != 0) {
			for (int i = 0; i < rows.size(); i++) {
				Rectangle2D row = rows.get(i);
				if (row.contains(p) && this.numOfGabionsInRow.get(i) < 5) {
					double y = row.getCenterY() - ((gabionHeight) / 2);
					double x = ((gabionWidth + padding) * this.numOfGabionsInRow.get(i)) + row.getX();
					gabions.add(new Gabion((int) x, (int) y, i));
					gabionRects.add(new Rectangle.Double(x, y, gabionWidth, gabionHeight));
					gb.setGabions(gb.getGabions() - 1);
					this.numOfGabionsInRow.set(i, this.numOfGabionsInRow.get(i) + 1);
				}
			}
		}

	}

	public void handleCollectOyster(Point p, int i) {
		int numOfClusters = 0;

		numOfClusters = oysters.get(i).getNumOfOystersInClump();
		oysters.get(i).setVisible(false);
		// adding GB stuff
		gb.build(numOfClusters);
		//System.out.println(p);
	}

	public void handleClick(Point p) {
		for (int i = 0; i < oysterRects.size(); i++) {
			if (oysterRects.get(i).contains(p)) {
				handleCollectOyster(p, i);
				return;
			}
		}
		handlePlaceGabion(p);
		return;
	}

	public ArrayList<Wave> getWaves() {
		return waves;
	}

	public ArrayList<Rectangle2D> getRows() {
		return rows;
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

}
