package control;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import model.Animation;
import model.ClumpOfOysters;
import model.ConcreteWalls;
import model.CrabFishMeter;
import model.Entity;
import model.Gabion;
import model.GabionBuilder;
import model.GameState;
import model.HorseshoeCrab;
import model.PlantBuilder;
import model.Plants;
import model.RunOff;
import model.Shore;
import model.Timer;
import model.TutorialState;
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
	private BufferedImageController bic;
	private Scale scale;
	private Point click;
	private GabionBuilder gb;
	private Spawner spawner;
	private Timer timer;
	private Timer plantTimer;
	private Timer textTimer;
	private PlantBuilder pb;
	private CrabFishMeter cfMeter;
	private HorseshoeCrab helperHorse;
	private AnimationController ac;

	private ArrayList<Integer> numOfGabionsInRow;
	private ArrayList<Integer> numOfWavesInRow;

	// list of entities
	private ArrayList<Wave> waves;
	private ArrayList<Gabion> gabions;
	private ArrayList<ClumpOfOysters> oysters;
	private ArrayList<ConcreteWalls> concreteWalls;
	private ArrayList<Plants> plants;
	private ArrayList<RunOff> runOff;
	private ArrayList<HorseshoeCrab> hsCrab;
	private ArrayList<AnimationController> animations;
	// list of rectangles
	private ArrayList<Rectangle2D> waveRects;
	private ArrayList<Rectangle2D> gabionRects;
	private ArrayList<Rectangle2D> oysterRects;
	private ArrayList<Rectangle2D> concreteRects;
	private ArrayList<Rectangle2D> waveRows;
	private ArrayList<Rectangle2D> plantRows;
	private ArrayList<Rectangle2D> plantrects;
	private ArrayList<Rectangle2D> runOffRects;
	private ArrayList<Rectangle2D> hsCrabRects;

	private Shore shore;

	private Rectangle2D GAMEBOX;
	private Rectangle2D UIBOX;
	private Rectangle2D MENU;
	private Rectangle2D shore1;
	private Rectangle2D gabionBuilder;
	private Rectangle2D plantBuilder;
	private Rectangle2D uiGabion;
	private Rectangle2D uiPlant;
	private Rectangle2D crabFishMeter;
	private Rectangle2D helperHorseRect;

	private Rectangle2D playButton;
	private Rectangle2D instructionButton;
	private Rectangle2D creditButton;

	private boolean renderDragGabion;
	private boolean renderDragPlant;
	private boolean eroded;
	private boolean isRunOff;
	private boolean placedFirstGabion;
	private boolean placedFirstPlant;
	private boolean init;

	private double gX;
	private double gY;
	private double gbPadding;
	private double gabionWidth;
	private double gabionHeight;
	private double uiPlantWidth;
	private double uiPlantHeight;
	private double concreteWallWidth;

	private int fontSize;
	private int numOfRows = 7;

	private Font f1;

	private String time;
	private String message = "";

	private GameState currentGameState = GameState.LOADING;

	private TutorialState currentTutorialState = TutorialState.OYSTERS;

	private Color ShoreColor = new Color(255, 200, 100, 255);

	public GameLoopController(Game game, Scale scale) {
		this.game = game;
		this.scale = scale;
		System.out.println(game.getBounds().getWidth());

	}

	public GameLoopController(Game game, Scale scale, GameState gs) {
		this.game = game;
		this.scale = scale;
		this.currentGameState = gs;
	}

	public void init() {
		bic = new BufferedImageController();
		gb = new GabionBuilder();
		timer = new Timer();
		spawner = new Spawner(this, game, timer);
		plantTimer = new Timer();
		textTimer = new Timer();
		pb = new PlantBuilder(plantTimer);
		cfMeter = new CrabFishMeter();

		numOfGabionsInRow = new ArrayList<Integer>();
		numOfWavesInRow = new ArrayList<Integer>();

		// list of entities
		waves = new ArrayList<Wave>();
		gabions = new ArrayList<Gabion>();
		oysters = new ArrayList<ClumpOfOysters>();
		concreteWalls = new ArrayList<ConcreteWalls>();
		plants = new ArrayList<Plants>();
		runOff = new ArrayList<RunOff>();
		hsCrab = new ArrayList<HorseshoeCrab>();
		animations = new ArrayList<AnimationController>();
		// list of rectangles
		waveRects = new ArrayList<Rectangle2D>();
		gabionRects = new ArrayList<Rectangle2D>();
		oysterRects = new ArrayList<Rectangle2D>();
		concreteRects = new ArrayList<Rectangle2D>();
		waveRows = new ArrayList<Rectangle2D>();
		plantRows = new ArrayList<Rectangle2D>();
		plantrects = new ArrayList<Rectangle2D>();
		runOffRects = new ArrayList<Rectangle2D>();
		hsCrabRects = new ArrayList<Rectangle2D>();

		shore = new Shore(0, 0);

		GAMEBOX = new Rectangle2D.Double(0, 0, 0, 0);
		UIBOX = new Rectangle2D.Double(0, 0, 0, 0);
		MENU = new Rectangle2D.Double(0, 0, 0, 0);
		shore1 = new Rectangle2D.Double(0, 0, 0, 0);
		gabionBuilder = new Rectangle2D.Double(0, 0, 0, 0);
		plantBuilder = new Rectangle2D.Double(0, 0, 0, 0);
		uiGabion = new Rectangle2D.Double(0, 0, 0, 0);
		uiPlant = new Rectangle2D.Double(0, 0, 0, 0);
		crabFishMeter = new Rectangle2D.Double(0, 0, 0, 0);
		helperHorseRect = new Rectangle2D.Double(0, 0, 0, 0);

		playButton = new Rectangle2D.Double(0, 0, 0, 0);
		instructionButton = new Rectangle2D.Double(0, 0, 0, 0);
		creditButton = new Rectangle2D.Double(0, 0, 0, 0);

		renderDragGabion = false;
		renderDragPlant = false;
		eroded = false;
		isRunOff = false;
		placedFirstGabion = false;
		placedFirstPlant = false;
		init = false;

		time = "" + timer.getTime();
		
		int scale = this.game.getScale().getGridSize();
		double width = this.game.getScale().getWidth();
		double height = this.game.getScale().getHeight();
		double uiBoxHeight = this.game.getScale().getHeight() * 0.165;
		double shoreWidth = this.game.getScale().getWidth() * 0.35;
		double menuWidth = width * 0.4;
		double menuHeight = height * 0.4;

		concreteWallWidth = width * 0.01;
		gbPadding = width * 0.015;

		spawner = new Spawner(this, this.game, this.timer);
		UIBOX = new Rectangle2D.Double(0, 0, width, uiBoxHeight);
		GAMEBOX = new Rectangle2D.Double(0, this.UIBOX.getHeight(), width, height - this.UIBOX.getHeight());
		MENU = new Rectangle2D.Double((width / 2) - (menuWidth / 2), (height / 2) - (menuHeight / 2), menuWidth,
				menuHeight);
		playButton = new Rectangle2D.Double(MENU.getX(), MENU.getY(), menuWidth, menuHeight / 3);
		instructionButton = new Rectangle2D.Double(MENU.getX(), MENU.getY() + menuHeight / 3, menuWidth,
				menuHeight / 3);
		creditButton = new Rectangle2D.Double(MENU.getX(), MENU.getY() + ((menuHeight / 3) * 2), menuWidth,
				menuHeight / 3);

		this.shore = new Shore((int) this.GAMEBOX.getX(), (int) this.GAMEBOX.getY());
		shore1 = new Rectangle2D.Double(shore.getX(), shore.getY(), (int) shoreWidth, GAMEBOX.getHeight());

		fontSize = (int) (UIBOX.getWidth() * 0.03);
		f1 = new Font("Arial", Font.PLAIN, this.fontSize);

		for (int i = 0; i < this.numOfRows; i++) {
			waveRows.add(new Rectangle2D.Double(shore1.getWidth() + concreteWallWidth,
					(UIBOX.getHeight() + (GAMEBOX.getHeight() / this.numOfRows) * i),
					GAMEBOX.getWidth() - shore1.getWidth(), GAMEBOX.getHeight() / this.numOfRows));
			plantRows.add(new Rectangle2D.Double(shore1.getWidth() - (shore1.getWidth() * 0.2),
					(UIBOX.getHeight() + (GAMEBOX.getHeight() / this.numOfRows) * i), shore1.getWidth() * 0.2,
					GAMEBOX.getHeight() / this.numOfRows));

			concreteWalls
					.add(new ConcreteWalls((int) shore1.getWidth(), (int) (GAMEBOX.getHeight() / this.numOfRows) * i));
			concreteRects.add(new Rectangle2D.Double(shore1.getWidth(),
					(UIBOX.getHeight() + (GAMEBOX.getHeight() / this.numOfRows) * i), concreteWallWidth,
					GAMEBOX.getHeight() / this.numOfRows));

			// in initializing row araylist
			this.numOfGabionsInRow.add(0);
			this.numOfWavesInRow.add(0);
			spawner.getPlantsInRow().add(0);
			Random rand = new Random();
			int pattern = rand.nextInt(3) + 1;
			spawner.getPatternInRow().add(pattern);
			spawner.getRunOffInRow().add(false);
			// spawner.spawnPlants(i);
			// spawner.spawnPlants(i);
			
		}

		gabionWidth = waveRows.get(0).getHeight() - gbPadding;
		gabionHeight = waveRows.get(0).getHeight() - gbPadding;
		uiPlantHeight = UIBOX.getHeight() * 0.7;
		uiPlantWidth = uiPlantHeight * (2.0 / 3.0);

		double gWidth = UIBOX.getWidth() * 0.10;
		gX = UIBOX.getWidth() - gWidth;
		gY = UIBOX.getY();

		gabionBuilder = new Rectangle2D.Double(gX, gY, gWidth, UIBOX.getHeight());
		uiGabion = new Rectangle2D.Double(gabionBuilder.getCenterX() - (gabionWidth / 2),
				gabionBuilder.getCenterY() - (gabionHeight / 2), gabionWidth, gabionHeight);
		plantBuilder = new Rectangle2D.Double(UIBOX.getX(), UIBOX.getY(), UIBOX.getHeight() * (2.0 / 3.0),
				UIBOX.getHeight());
		uiPlant = new Rectangle2D.Double(plantBuilder.getCenterX() - (uiPlantWidth / 2),
				plantBuilder.getCenterY() - (uiPlantHeight / 2.0), uiPlantWidth, uiPlantHeight);
		double cfWidth = UIBOX.getWidth() * 0.05;
		double cfX = UIBOX.getX() + plantBuilder.getX() + plantBuilder.getWidth() + (this.fontSize * 2);
		crabFishMeter = new Rectangle2D.Double(cfX, UIBOX.getY(), cfWidth, UIBOX.getHeight());

		double helperHorseWidth = width * 0.1;
		double helperHorseHeight = height * 0.15;
		helperHorse = new HorseshoeCrab((int) (this.crabFishMeter.getX() + this.crabFishMeter.getWidth()),
				(int) (UIBOX.getY()));
		hsCrab.add(helperHorse);
		helperHorseRect = new Rectangle2D.Double(helperHorse.getX(), helperHorse.getY(), helperHorseWidth,
				helperHorseHeight);
		hsCrabRects.add(helperHorseRect);

		bic.loadBufferedImage();
		ac = new AnimationController(this, bic, null, null);
		this.init = true;
	}

	/**
	 * The main loop for the game where all the instantiated object's tick
	 * methods get called.
	 */
	public void loop() {
		// if (timer.getTime() >= 120) {
		// this.currentState = GameState.TUTORIAL;
		// } else {
		// this.currentState = GameState.GAME;
		// }
		//
		switch (this.currentGameState) {
		case TUTORIAL:
			collision();
			switch (this.currentTutorialState) {
			case OYSTERS:
				System.out.println(this.waveRects.size());
				this.message = "Collect Oyster Shells!";
				spawner.spawnOysters(1, 0);
				if (gb.getGabions() >= 2) {
					this.message = "Good Job!";
					// after 3 seconds go to the next state
					textTimer.countUp(3);
					if (textTimer.getTime() >= 3) {
						this.currentTutorialState = TutorialState.WAVES;
						textTimer = new Timer();
					}
				}
				break;
			case WAVES:
				this.message = "Oh no, waves!";
				spawner.spawnWaves(2, 0);
				spawner.spawnOysters(2, 0);
				textTimer.countUp(3);
				System.out.println(textTimer.getTime());
				if (textTimer.getTime() >= 3) {
					this.currentTutorialState = TutorialState.GABIONS;
					textTimer = new Timer();
				}
				break;
			case GABIONS:
				this.message = "Stop the waves by placing gabions!";
				plantTimer.countUp(5);
				// falshing gabion here
				if (this.placedFirstGabion) {
					this.message = "Good Job";
					textTimer.countUp(2);
					if (textTimer.getTime() >= 2) {
						textTimer = new Timer();
						this.currentTutorialState = TutorialState.RUNOFF;

					}

				}

				break;
			case RUNOFF:
				plantTimer.countUp(5);
				textTimer.countUpStop(3);
				if (textTimer.getTime() >= 3) {
					this.message = "Oh no! Runoff!";
					textTimer = new Timer();
					this.currentTutorialState = TutorialState.PLANTS;
				} else {
					this.message = "Look, your Plants have grown!";
					// flash plant number

				}
				spawner.spawnRunOff(1, 0);

				break;
			case PLANTS:
				plantTimer.countUp(5);
				textTimer.countUpStop(2);
				if (textTimer.getTime() >= 2) {
					this.message = "Plant plants to filter the dirty runoff.";
					if (this.placedFirstPlant) {
						textTimer = new Timer();
						this.message = "Good Job! The water is clean! You now know how to defend your estuary!";
						this.currentTutorialState = TutorialState.END;
					}
				}
				spawner.spawnRunOff(1, 0);
				break;
			case END:
				textTimer.countUpStop(5);
				if (textTimer.getTime() >= 5) {
					textTimer = new Timer();
					timer = new Timer();
					spawner.setTimer(timer);
					this.currentGameState = GameState.GAME;

				}
				break;
			default:
				System.out.println("STATES NOT WORKING, CURRENT STATE: " + this.currentTutorialState);
			}
			break;
		case GAME:
			timer.countDown();
			spawner.spawn(this.eroded);
			plantTimer.countUp(5);

			if (timer.getTime() == 0 || shore.getHealth() <= 25) {
				game.setGameOver(true);
			}
			collision();
			break;
		case PAUSED:
			break;
		case MENU:
			for (int i = 0; i < this.waveRects.size(); i++) {
				for (int j = 0; j < this.gabionRects.size(); j++) {
					if (this.waveRects.get(i).intersects(this.gabionRects.get(j))) {
						this.waveRects.remove(i);
						this.waves.remove(i);
						i = i - 1;
						if (i < 0) {
							i = 0;
						}
					}
				}
				if (this.waveRects.get(i).getX() <= this.shore1.getX()) {
					this.waveRects.remove(i);
					this.waves.remove(i);
					i = i - 1;
					if (i < 0) {
						i = 0;
					}
				}
			}

			timer.countDown();
			spawner.spawnWaves(5, 0);
			break;
		case LOADING:
			if (this.init) {
				this.currentGameState = GameState.MENU;
				gb.setGabions(this.numOfRows);
				for (int i = 0; i < this.numOfRows; i++) {
					spawner.spawnWaves(5, 0);
					this.handlePlaceGabion(
							new Point((int) this.waveRows.get(i).getX() + 1, (int) this.waveRows.get(i).getY() - 1));
				}
			}
			break;
		default:
			System.out.println("STATES NOT WORKING, CURRENT STATE: " + this.currentGameState);
		}

		// stuff that always needs to be done

		pb.build();
		if (this.currentTutorialState != TutorialState.GABIONS && this.currentTutorialState != TutorialState.PLANTS) {
			for (int i = 0; i < waves.size(); i++) {
				waves.get(i).move();
				waveRects.get(i).setRect(waves.get(i).getX(), waveRects.get(i).getY(), waveRects.get(i).getWidth(),
						waveRects.get(i).getHeight());
			}
		}

		ArrayList<RunOff> tempRunOff = new ArrayList<RunOff>();
		ArrayList<Rectangle2D> tempBox = new ArrayList<Rectangle2D>();
		for (int i = 0; i < runOff.size(); i++) {
			if (this.currentTutorialState != TutorialState.PLANTS) {
				runOff.get(i).move();
			}
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
		for (int i = 0; i < oysters.size(); i++) {
			if (!oysters.get(i).isVisible()) {
				oysters.remove(i);
				oysterRects.remove(i);
			}
		}

		for (Iterator<AnimationController> it = animations.iterator(); it.hasNext();) {
			AnimationController a = it.next();
			if (a.isPlayed()) {
				it.remove();
				System.out.println("REMOVED");
			}
		}

	}

	/**
	 * The main method for the game were all the instantiated object's render
	 * methods get called.
	 */

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		switch (this.currentGameState) {
		case TUTORIAL:
			// UIBOX Sky
			g2.drawImage(bic.getImages().get(2), (int) UIBOX.getX(), (int) UIBOX.getY(), (int) UIBOX.getWidth(),
					(int) (UIBOX.getHeight()), null);
			// GAMEBOX
			g2.setColor(new Color(163, 232, 255));
			g2.draw(GAMEBOX);
			g2.fill(GAMEBOX);

			this.renderGabions(g2);
			this.renderWaves(g2);
			this.renderConcreteWalls(g2);
			this.renderShore(g2);
			this.renderHorseshoeCrab(g2);
			this.renderPlants(g2);

			// UI
			// ---------------------------
			// Gabion builder/Plant builder
			this.renderGabionBuilder(g2);

			// GabionBuilder Meter
			this.renderGabionMeter(g2);
			this.renderUIGabion(g2);
			this.renderDragGabion(g2);
			this.renderNumberOfGabions(g2);

			this.renderOysters(g2);

			// plant meter
			this.renderPlantMeter(g2);
			this.renderNumberOfPlants(g2);
			this.renderDragPlant(g2);

			this.renderRunoff(g2);

			this.renderCrabFishMeter(g2);

			this.renderAnimation(g2);
			switch (this.currentTutorialState) {
			case OYSTERS:
				break;
			case WAVES:
				break;
			case GABIONS:
				ac.playGabionPlacementAnimation(g2);
				break;
			case RUNOFF:
				break;
			case PLANTS:
				break;
			case END:
				break;
			default:
				break;
			}
			break;
		case GAME:
			// UIBOX Sky
			g2.drawImage(bic.getImages().get(2), (int) UIBOX.getX(), (int) UIBOX.getY(), (int) UIBOX.getWidth(),
					(int) (UIBOX.getHeight()), null);
			// GAMEBOX
			g2.setColor(new Color(163, 232, 255));
			g2.draw(GAMEBOX);
			g2.fill(GAMEBOX);

			this.renderGabions(g2);
			this.renderWaves(g2);
			this.renderConcreteWalls(g2);
			this.renderSun(g2);
			this.renderShore(g2);
			this.renderPlants(g2);

			// UI
			// ---------------------------
			// Gabion builder/Plant builder
			this.renderGameTimer(g2);
			this.renderGabionBuilder(g2);

			// GabionBuilder Meter
			this.renderGabionMeter(g2);
			this.renderUIGabion(g2);
			this.renderDragGabion(g2);
			this.renderNumberOfGabions(g2);

			this.renderOysters(g2);

			// plant meter
			this.renderPlantMeter(g2);
			this.renderNumberOfPlants(g2);
			this.renderDragPlant(g2);

			this.renderRunoff(g2);

			this.renderCrabFishMeter(g2);

			this.renderAnimation(g2);
			break;
		case MENU:
			// UIBOX Sky
			g2.drawImage(bic.getImages().get(2), (int) UIBOX.getX(), (int) UIBOX.getY(), (int) UIBOX.getWidth(),
					(int) (UIBOX.getHeight()), null);
			// GAMEBOX
			g2.setColor(new Color(163, 232, 255));
			g2.draw(GAMEBOX);
			g2.fill(GAMEBOX);

			this.renderGabions(g2);
			this.renderWaves(g2);
			this.renderConcreteWalls(g2);
			this.renderSun(g2);
			this.renderShore(g2);
			this.renderPlants(g2);
			this.renderMenu(g2);
			break;
		case PAUSED:
			break;
		case LOADING:
			break;
		default:
			break;

		}

	}

	public void renderGabions(Graphics2D g2) {
		for (int i = 0; i < gabionRects.size(); i++) {
			if (gabions.get(i).getHealth() == 2) {
				g2.setColor(new Color(0, 0, 0, 175));
			} else if (gabions.get(i).getHealth() == 1) {
				g2.setColor(new Color(0, 0, 0, 100));
			} else {
				// 3
				g2.setColor(Color.BLACK);
			}
			g2.draw(gabionRects.get(i));
			g2.fill(gabionRects.get(i));
		}
	}

	public void renderWaves(Graphics2D g2) {
		g2.setColor(Color.cyan);
		for (Rectangle2D rect : waveRects) {
			// g2.draw(rect);
			// g2.fill(rect);

			g2.drawImage(bic.getImages().get(4), (int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(),
					(int) rect.getHeight(), null);

		}
	}

	public void renderConcreteWalls(Graphics2D g2) {
		for (Rectangle2D wall : concreteRects) {
			g2.setColor(Color.LIGHT_GRAY);
			g2.draw(wall);
			g2.setColor(Color.DARK_GRAY);
			g2.fill(wall);
		}
	}

	public void renderShore(Graphics2D g2) {
		g2.setColor(this.ShoreColor);
		g2.fill(shore1);
		g2.draw(shore1);
	}

	public void renderHorseshoeCrab(Graphics2D g2) {
		// g2.setColor(Color.PINK);

		for (Rectangle2D hsCrab : hsCrabRects) {
			// g2.draw(hsCrab);
			// g2.fill(hsCrab);
			g2.drawImage(bic.getImages().get(5), (int) hsCrab.getX(), (int) hsCrab.getY(), (int) hsCrab.getWidth(),
					(int) hsCrab.getHeight(), null);
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Arial", 1, 36));
			// g2.drawString("" + this.message,
			// (int)(hsCrab.getX()+hsCrab.getWidth()),
			// (int)(hsCrab.getCenterY() - game.getScale().getHeight() *
			// 0.01));
			ac.playTextAnimation(g2, (int) (hsCrab.getX() + hsCrab.getWidth()),
					(int) (hsCrab.getCenterY() - game.getScale().getHeight() * 0.01));
		}

	}

	public void renderGameTimer(Graphics2D g2) {
		g2.setFont(f1);
		g2.setColor(Color.BLACK);
		g2.drawString(timer.getTime() + "", (int) UIBOX.getCenterX(), (int) UIBOX.getCenterY());

	}

	public void renderPlants(Graphics2D g2) {
		// g2.setColor(Color.GREEN);
		for (int i = 0; i < plants.size(); i++) {
			if (plants.get(i).isVisible()) {
				// g2.draw(plantrects.get(i));
				// g2.fill(plantrects.get(i));

				g2.drawImage(bic.getImages().get(0), (int) plantrects.get(i).getX(), (int) plantrects.get(i).getY(),
						(int) (plantrects.get(i).getWidth() * 1.8), (int) (plantrects.get(i).getHeight() * 1.9), null);

			}
		}
	}

	public void renderGabionBuilder(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fill(gabionBuilder);
		g2.draw(gabionBuilder);
	}

	public void renderGabionMeter(Graphics2D g2) {
		g2.setColor(Color.ORANGE);
		double gbPercentage = (double) gb.getNumberOfOysters() / (double) gb.getMaxGabionCapacity();
		double maxHeight = gabionBuilder.getHeight();
		double height = maxHeight * gbPercentage;
		double width = gabionBuilder.getWidth() / 10;
		double x = gabionBuilder.getX(); // - (width/2);
		double y = maxHeight - height;
		Rectangle2D gabionMeter = new Rectangle2D.Double(x, y, width, height);
		g2.draw(gabionMeter);
		g2.fill(gabionMeter);
	}

	public void renderUIGabion(Graphics2D g2) {
		if (gb.getGabions() == 0) {
			g2.setColor(new Color(0, 0, 0, 50));
		} else {
			g2.setColor(new Color(0, 0, 0, 255));
		}
		g2.draw(uiGabion);
		g2.fill(uiGabion);
	}

	public void renderDragGabion(Graphics2D g2) {
		if (this.renderDragGabion) {
			g2.draw(this.createDragGabion(game.getMouseCords()));
			g2.setColor(Color.gray);
			for (Rectangle2D row : waveRows) {
				g2.draw(row);

			}
		}
	}

	public void renderOysters(Graphics2D g2) {
		g2.setColor(Color.GRAY);
		for (int i = 0; i < this.oysters.size(); i++) { // concurrent
														// modification error
			Rectangle2D oyster = this.oysterRects.get(i);
			// g2.draw(oyster);
			// g2.fill(oyster);
			if (this.getOysters().get(i).isCollected()) {

			}
			g2.drawImage(bic.getImages().get(1), (int) oyster.getX(), (int) oyster.getY(), (int) oyster.getWidth(),
					(int) (oyster.getHeight() / 1.5), null);

		}
	}

	public void renderNumberOfGabions(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.drawString("" + gb.getGabions(), (int) uiGabion.getX() + (f1.getSize() / 2),
				(int) uiGabion.getCenterY() + (f1.getSize() / 2));
	}

	public void renderPlantMeter(Graphics2D g2) {
		double maxHeight = plantBuilder.getHeight();
		double height = ((double) plantTimer.getTimeMili() / (pb.getNumOfSecondsPerPlant() * 1000)) * maxHeight;
		double width = plantBuilder.getWidth();
		double x = plantBuilder.getX();
		double y = maxHeight - height;
		Rectangle2D plantMeter = new Rectangle2D.Double(x, y, width, height);

		g2.setColor(new Color(163, 255, 173));
		// g2.fill(plantBuilder);
		// g2.draw(plantBuilder);
		g2.draw(plantMeter);
		g2.fill(plantMeter);

		// g2.setColor(Color.GREEN);
		// g2.draw(uiPlant);
		// g2.fill(uiPlant);

		g2.drawImage(bic.getImages().get(0), (int) uiPlant.getX(), (int) uiPlant.getY(), (int) uiPlant.getWidth(),
				(int) (uiPlant.getHeight()), null);

	}

	public void renderNumberOfPlants(Graphics g2) {
		g2.setColor(Color.BLACK);
		g2.drawString(pb.getNumberOfPlants() + "",
				(int) (plantBuilder.getX() + plantBuilder.getWidth() + (f1.getSize() / 2)),
				(int) plantBuilder.getCenterY());
	}

	public void renderDragPlant(Graphics2D g2) {

		if (this.renderDragPlant) {
			// g2.setColor(Color.GREEN);
			// g2.draw(this.renderDragPlant(game.getMouseCords()));
			Rectangle2D r = this.createDragPlant(game.getMouseCords());
			g2.drawImage(bic.getImages().get(0), (int) r.getX(), (int) r.getY(), (int) r.getWidth(),
					(int) r.getHeight(), null);
			g2.setColor(Color.LIGHT_GRAY);
			for (Rectangle2D row : plantRows) {
				g2.draw(row);
			}
		} else {
			g2.setColor(this.ShoreColor);
			for (Rectangle2D row : plantRows) {
				// g2.fill(row);
			}
		}
	}

	public void renderRunoff(Graphics2D g2) {
		g2.setColor(new Color(184, 138, 0, 127));
		for (Rectangle2D runOff : runOffRects) {
			g2.draw(runOff);
			g2.fill(runOff);
		}
	}

	public void renderCrabFishMeter(Graphics2D g2) {
		g2.setColor(Color.DARK_GRAY);
		g2.draw(crabFishMeter);
		g2.fill(crabFishMeter);

		g2.setColor(Color.WHITE);
		g2.drawString("" + cfMeter.getPhLevels(), (int) crabFishMeter.getCenterX(), (int) crabFishMeter.getCenterY());
		g2.setColor(Color.YELLOW);
	}

	public void renderSun(Graphics2D g2) {

		double sunDim = game.getScale().getWidth() * 0.06;
		double startX = this.crabFishMeter.getX() + this.crabFishMeter.getWidth();
		double maxX = this.gabionBuilder.getX() - (sunDim / 2);
		double sunX = ((((180 * 1000) - timer.getTimeMili()) / (180.0 * 1000)) * (maxX - startX)) + startX;
		double maxY = 0;
		double startY = UIBOX.getCenterY() + (sunDim / 2);
		// double h = maxX - startX;
		// ((-.01)*(UIBOX.getWidth()))*(sunX*sunX)+(UIBOX.getHeight()*100);
		// (-1*(sunHeight/2)) * ((timer.getTimeMili() / (180 * 1000))^2) +
		// UIBOX.getHeight();
		// System.out.println(sunY);
		// Ellipse2D sun = new Ellipse2D.Double(sunX, startY, sunDim,
		// sunDim);
		// g2.draw(sun);
		// g2.fill(sun);
		double x1 = startX;
		double x3 = maxX;
		double x2 = (x3 - x1) / 2;
		double x = sunX;
		double y1 = startY;
		double y3 = startY;
		double y2 = maxY;
		double y = ((x - x2) * (x - x3)) / ((x1 - x2) * (x1 - x3)) * y1
				+ ((x - x1) * (x - x3)) / ((x2 - x1) * (x2 - x3)) * y2
				+ ((x - x1) * (x - x2)) / ((x3 - x1) * (x3 - x2)) * y3;
		g2.drawImage(bic.getImages().get(3), (int) sunX, (int) y, (int) sunDim, (int) sunDim, null);

	}

	public void renderAnimation(Graphics2D g2) {
		for (AnimationController ac : animations) {
			switch (ac.getAnimation()) {
			case OYSTER:
				ac.playCollectOysterAnimation(g2);
				break;
			case PLACEGABION:
				ac.playGabionPlacementAnimation(g2);
				break;
			default:
				break;
			}
		}
	}

	public void renderMenu(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.draw(MENU);
		g2.fill(MENU);
		g2.setColor(Color.ORANGE);
		g2.draw(this.playButton);
		g2.fill(this.playButton);
		g2.setColor(Color.PINK);
		g2.draw(this.instructionButton);
		g2.fill(this.instructionButton);
		g2.setColor(Color.MAGENTA);
		g2.draw(this.creditButton);
		g2.fill(this.creditButton);
	}

	public void collision() {
		class EntityStruct {
			int index = 0;
			String type = "";

			public EntityStruct(int index, String type) {
				this.index = index;
				this.type = type;
			}
		}
		ArrayList<EntityStruct> whatToRemove = new ArrayList<EntityStruct>();

		for (int i = 0; i < waveRects.size(); i++) {
			if (waveRects.get(i).intersects(shore1.getX(), shore1.getY(), shore1.getWidth(), shore1.getHeight())) {
				// wave hit shore
				// erode shore
				if (shore.erode()) {
					this.eroded = true;
					this.ShoreColor = new Color(255, 200, 100);
					shore1.setRect(shore1.getX(), shore1.getY(),
							(shore1.getWidth() - this.gabionWidth - this.gbPadding), shore1.getHeight());
					for (Rectangle2D plantRow : plantRows) {
						plantRow.setRect(shore1.getWidth() - plantRow.getWidth(), plantRow.getY(), plantRow.getWidth(),
								plantRow.getHeight());
					}
					// for implementing moving gabions placement back with he
					// shore
					// for (Rectangle2D gabionRow : waveRows) {
					// gabionRow.setRect(shore1.getWidth(), gabionRow.getY(),
					// gabionRow.getWidth(), gabionRow.getHeight());
					// }
					for (int j = 0; j < plantrects.size(); j++) {
						plantrects.get(j).setRect(plantrects.get(j).getX() - this.gabionWidth - this.gbPadding,
								plantrects.get(j).getY(), plantrects.get(j).getWidth(), plantrects.get(j).getHeight());
					}

				} else {
					this.ShoreColor = new Color(255, 200, 100, 230);

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
					if (gabions.get(j).getHealth() <= 0) {
						whatToRemove.add(new EntityStruct(j, "gabion"));
						// debugging only
						try {
							this.numOfGabionsInRow.set(gabions.get(j).getRowNum(),
									this.numOfGabionsInRow.get(gabions.get(j).getRowNum()) - 1);
						} catch (IndexOutOfBoundsException e) {
							System.out.println(gabions.get(j).getRowNum());
							e.printStackTrace();
						}

					}
				}
			}
			for (int j = 0; j < concreteRects.size(); j++) {
				if (concreteRects.get(j).intersects(waveRects.get(i).getX(), waveRects.get(i).getY(),
						waveRects.get(i).getWidth(), waveRects.get(i).getHeight())) {

					whatToRemove.add(new EntityStruct(i, "wave"));

					whatToRemove.add(new EntityStruct(j, "concrete"));
				}
			}
		}

		for (int i = 0; i < plantrects.size(); i++) {
			for (int j = 0; j < runOffRects.size(); j++) {
				Rectangle2D r = runOffRects.get(j);
				if (plantrects.get(i).intersects(r)) {
					// this.setIsRunOff(true, i);
					plants.get(i).changeHealth(plants.get(i).getHealth() - 1);
					System.out.println(plants.get(i).getHealth());
					if (plants.get(i).getHealth() <= 0) {
						whatToRemove.add(new EntityStruct(i, "plant"));
						// spawner.getPlantsInRow().set(i,
						// spawner.getPlantsInRow().get(i).intValue() - 1);
					}
				} else {
					this.setIsRunOff(false, i);

				}

			}
		}

		for (EntityStruct e : whatToRemove) {
			if (e.type.equals("wave")) {
				this.waves.remove(e.index);
				this.waveRects.remove(e.index);
			} else if (e.type.equals("gabion")) {
				this.gabionRects.remove(e.index);
				this.gabions.remove(e.index);
			} else if (e.type.equals("concrete")) {
				this.concreteRects.remove(e.index);
				this.concreteWalls.remove(e.index);
			} else if (e.type.equals("plant")) {
				this.plantrects.remove(e.index);
				this.plants.remove(e.index);
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
					this.placedFirstPlant = true;
				}
			}
		}
		this.renderDragPlant = false;
	}

	public void handlePlaceGabion(Point p) {
		// System.out.println(p.getX() + ", " + p.getY());

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
					if (this.currentGameState != GameState.MENU) {
						this.placedFirstGabion = true;
					}
				}
			}
		}
		this.renderDragGabion = false;

	}

	public void handleCollectOyster(Point p, int i) {
		int numOfClusters = 0;
		numOfClusters = oysters.get(i).getNumOfOystersInClump();
		Rectangle2D o = oysterRects.get(i);
		gb.build(numOfClusters);
		// this.oysters.get(i).setCollected(true);
		this.animations.add(new AnimationController(this, this.bic, Animation.OYSTER, o));
		oysters.get(i).setVisible(false);

	}

	public Rectangle2D createDragGabion(Point p) {
		Rectangle2D r = new Rectangle2D.Double(p.getX() - (uiGabion.getWidth() / 2),
				p.getY() - (uiGabion.getWidth() / 2), uiGabion.getWidth(), uiGabion.getHeight());
		return r;
	}

	public Rectangle2D createDragPlant(Point p) {
		double width = this.getPlantRows().get(0).getWidth() * 0.2;
		double height = this.getPlantRows().get(0).getWidth() * 0.3;
		Rectangle2D r = new Rectangle2D.Double(p.getX() - (width / 2), p.getY() - (height / 2), width, height);
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
		if (this.currentGameState == GameState.MENU) {
			if (this.playButton.contains(p)) {
				this.init();
				this.currentGameState = GameState.TUTORIAL;
				// this shouldn't be necessary
				this.waveRects.clear();
				this.waves.clear();

			} else if (this.instructionButton.contains(p)) {

			} else if (this.creditButton.contains(p)) {

			}
		}
		for (int i = 0; i < oysterRects.size(); i++) {
			if (oysterRects.get(i).contains(p)) {
				handleCollectOyster(p, i);
				return;
			}
		}
		this.handleDrag(p);

	}

	public void setIsRunOff(boolean newB, int i) {
		if (newB != this.isRunOff) {
			this.isRunOff = newB;
			// System.out.println("RunnOff Hit Plants");
			// bug happens when two runOffs hit two plants at the same time / or
			// there are two runOffs in game at one time
			if (newB) {
				if (spawner.getPlantsInRow().get(i) > 2) {
					cfMeter.setPhLevels(cfMeter.getPhLevels() + 1);
				} else if (spawner.getPlantsInRow().get(i) == 1) {
					cfMeter.setPhLevels(cfMeter.getPhLevels() - 1);
				} else if (spawner.getPlantsInRow().get(i) == 0) {
					cfMeter.setPhLevels(cfMeter.getPhLevels() - 2);
				}
				if (cfMeter.getPhLevels() < 0) {
					cfMeter.setPhLevels(0);
				}
			}
		}
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

	public boolean isEroded() {
		return eroded;
	}

	public void setEroded(boolean eroded) {
		this.eroded = eroded;
	}

	public int getNumOfRows() {
		return numOfRows;
	}

	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}

	public Rectangle2D getUiGabion() {
		return uiGabion;
	}

	public ArrayList<Integer> getNumOfWavesInRow() {
		return numOfWavesInRow;
	}

	public String getMessage() {
		return message;
	}

	public GameState getCurrentGameState() {
		return currentGameState;
	}

}
