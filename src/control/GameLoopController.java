package control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;

import model.Gabion;
import model.Shore;
import model.Wave;
import view.Game;

/**
 * 
 * @author Jackson Jorss
 * @author Jael Flaquer
 * @author Ben Clark
 * @author Robert Lee
 * 
 *
 */

public class GameLoopController {
	// list of entities
	private ArrayList<Wave> waves = new ArrayList<Wave>();
	private ArrayList<Gabion> gabions = new ArrayList<Gabion>();
	//list of rectangles
	private ArrayList<Rectangle2D> waveRects = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> gabionRects = new ArrayList<Rectangle2D>();

	private Shore shore = new Shore(0,0);
	// must be initialized so collision does throw a null pointer exception
	private Rectangle2D shore1 = new Rectangle2D.Double(0,0,0,0);
	
	private Game game;
	
	public GameLoopController(Game game) {
		this.game = game;
		
		waves.add(new Wave(1, 75, 10));
		waves.add(new Wave(1, 75, 20));
		waveRects.add(new Rectangle2D.Double(0,0,0,0));
		waveRects.add(new Rectangle2D.Double(0,0,0,0));
	}
	
	
	/**
	 * The main loop for the game were all the instantiated object's tic methods get called.
	 */
	public void loop() {
		for (Wave wave : waves) {
			wave.move();
		}
		collision();
		handleControls();
		System.out.println("I'm looping");
	}
	/**
	 * The main loop for the game were all the instantiated object's render methods get called.
	 */

	public void render(Graphics g, int scale) {
		Graphics2D g2 = (Graphics2D) g;
		//wave1 = new Rectangle2D.Double(wave.getX()* scale,wave.getY()* scale, 50, 50);
		// creating new rectangles and tieing them to waves
		for (int i = 0; i < waveRects.size(); i++) {
			waveRects.set(i, new Rectangle2D.Double(waves.get(i).getX()*scale,waves.get(i).getY()*scale,50,50));
		}
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
		
		// single way
		shore1 = new Rectangle2D.Double(shore.getX()* scale,shore.getY()* scale, 300, 800);		
		g2.setColor(Color.YELLOW);
		g2.fill(shore1);
		g2.draw(shore1);
	}
	
	public void collision() {
		for (int i = 0 ; i < waveRects.size(); i++) {
			if (waveRects.get(i).intersects(shore1.getX(), shore1.getY(), shore1.getWidth(), shore1.getHeight())) {
				// wave hit shore
				// erode shore
				shore.erode();
				// remove waves hitBox
				waveRects.remove(i);
				// remove wave object
				waves.remove(i);
			}
		}
	}
	
	public void handleControls(){
		System.out.println(game.getClick().getX() + ", " + game.getClick().getY());
		// spawn gabion
		gabions.add(new Gabion((int)game.getClick().getX(), (int)game.getClick().getY()));
		gabionRects.add(new Rectangle.Double(game.getClick().getX(), game.getClick().getY(),
				50, 50));
		
	}
	
}
