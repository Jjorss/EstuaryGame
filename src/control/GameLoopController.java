package control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	// list of rectangles
	private ArrayList<Rectangle2D> waveRects = new ArrayList<Rectangle2D>();
	private ArrayList<Rectangle2D> gabionRects = new ArrayList<Rectangle2D>();

	private Shore shore = new Shore(0, 0);
	// must be initialized so collision does throw a null pointer exception
	private Rectangle2D shore1 = new Rectangle2D.Double(0, 0, 0, 0);

	private Game game;
	private Point click;

	public GameLoopController(Game game) {
		this.game = game;

		waves.add(new Wave(1, 75, 10));
		waves.add(new Wave(1, 75, 20));
		waveRects.add(new Rectangle2D.Double(0, 0, 0, 0));
		waveRects.add(new Rectangle2D.Double(0, 0, 0, 0));
	}

	/**
	 * The main loop for the game were all the instantiated object's tic methods
	 * get called.
	 */
	public void loop() {
		for (int i = 0; i < waves.size(); i++) {
			if (waves.get(i).isVisable()) {
				waves.get(i).move();
			} else {
				waves.remove(i);
				waveRects.remove(i);
			}
		}
		collision();
		
		System.out.println("I'm looping");
	}

	/**
	 * The main loop for the game were all the instantiated object's render
	 * methods get called.
	 */

	public void render(Graphics g, int scale) {
		Graphics2D g2 = (Graphics2D) g;
		// wave1 = new Rectangle2D.Double(wave.getX()* scale,wave.getY()* scale,
		// 50, 50);
		// creating new rectangles and tieing them to waves
		for (int i = 0; i < waveRects.size(); i++) {
			waveRects.set(i, new Rectangle2D.Double(waves.get(i).getX() * scale, waves.get(i).getY() * scale, 50, 50));
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
		shore1 = new Rectangle2D.Double(shore.getX() * scale, shore.getY() * scale, 300, 800);
		g2.setColor(Color.YELLOW);
		g2.fill(shore1);
		g2.draw(shore1);
	}

	public void collision() {
		for (int i = 0; i < waveRects.size(); i++) {
			if (waveRects.get(i).intersects(shore1.getX(), shore1.getY(), shore1.getWidth(), shore1.getHeight())) {
				// wave hit shore
				// erode shore
				shore.erode();
				// set wave to not visible to get deleted in logic
				waves.get(i).setVisable(false);
			}

		}

		for (int i = 0; i < waveRects.size(); i++) {
			// if a wave hits a gabion, remove wave
			// PUT CHANGE OF HEALTH HERE IF WE DECIDE TO GO WITH HEALTH FOR
			// GABIONS
			for (int j = 0; j < gabionRects.size(); j++) {
				System.out.println("index: " + j + "\t" + "Size: " + gabionRects.size());
				if (gabionRects.get(j).intersects(waveRects.get(i).getX(), waveRects.get(i).getY(),
						waveRects.get(i).getWidth(), waveRects.get(i).getHeight())) {
					// set wave to not visible to get deleted in logic
					waves.get(i).setVisable(false);
				}
			}
		}

	}

	public void handlePlaceGabion(Point p) {
		System.out.println(p.getX() + ", " + p.getY());
		// spawn gabion
		gabions.add(new Gabion((int) p.getX(), (int) p.getY()));
		gabionRects.add(new Rectangle.Double(p.getX(), p.getY(), 50, 50));
	}

}
