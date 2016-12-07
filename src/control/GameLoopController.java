package control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import model.Animation;
import model.ConcreteWalls;
import model.Gabion;
import model.GabionBuilder;
import model.GameState;
import model.HorseshoeCrab;
import model.PlantBuilder;
import model.Shore;
import model.Timer;
import model.TutorialState;
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
	private GabionBuilderController gb;
	private Spawner spawner;
	private Timer timer;
	private Timer plantTimer;
	private Timer textTimer;
	private Timer runOffTimer;
	private Timer cleanWaterTimer;
	private PlantBuilderController pb;
	private HorseshoeCrabController helperHorse;
	private AnimationController ac;

	private ArrayList<Integer> numOfGabionsInRow;
	private ArrayList<Integer> numOfWavesInRow;

	// list of entities
	private ArrayList<WaveController> waves;
	private ArrayList<GabionController> gabions;
	private ArrayList<OysterController> oysters;
	private ArrayList<ConcreteWallController> concreteWalls;
	private ArrayList<PlantController> plants;
	private ArrayList<RunOffController> runOff;
	private ArrayList<HorseshoeCrabController> hsCrab;
	private ArrayList<AnimationController> animations;
	// list of rectangles
	private ArrayList<Rectangle2D> waveRows;
	private ArrayList<Rectangle2D> plantRows;

	private ShoreController shore;

	private Rectangle2D GAMEBOX;
	private Rectangle2D UIBOX;
	private Rectangle2D MENU;
	// private Rectangle2D gabionBuilder;
	// private Rectangle2D plantBuilder;
	private Rectangle2D uiGabion;
	private Rectangle2D uiPlant;
	private Rectangle2D helperHorseRect;

	private Rectangle2D playButton;
	private Rectangle2D instructionButton;
	private Rectangle2D creditButton;

	private boolean renderDragGabion;
	private boolean renderDragPlant;
	private boolean eroded;
	private boolean isRunOff;
	private boolean placedFirstGabion;
	private boolean placedWrongGabion;
	private boolean placedWrongPlant;
	private boolean placedFirstPlant;
	private boolean ableToPlaceGabion;
	private boolean init;
	private boolean hittingPlant;
	private boolean hittingWater;

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
	private Color runOffColor = new Color(184, 138, 0, 127);
	private Color dirtyWater = new Color(184, 138, 0, 0);

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
		gb = new GabionBuilderController(new GabionBuilder(), new Rectangle2D.Double(0, 0, 0, 0));
		timer = new Timer();
		spawner = new Spawner(this, game, timer);
		plantTimer = new Timer();
		textTimer = new Timer();
		runOffTimer = new Timer();
		cleanWaterTimer = new Timer();
		pb = new PlantBuilderController(new PlantBuilder(plantTimer), new Rectangle2D.Double(0, 0, 0, 0));

		numOfGabionsInRow = new ArrayList<Integer>();
		numOfWavesInRow = new ArrayList<Integer>();

		// list of entities
		waves = new ArrayList<WaveController>();
		gabions = new ArrayList<GabionController>();
		oysters = new ArrayList<OysterController>();
		concreteWalls = new ArrayList<ConcreteWallController>();
		plants = new ArrayList<PlantController>();
		runOff = new ArrayList<RunOffController>();
		hsCrab = new ArrayList<HorseshoeCrabController>();
		animations = new ArrayList<AnimationController>();

		waveRows = new ArrayList<Rectangle2D>();
		plantRows = new ArrayList<Rectangle2D>();

		shore = new ShoreController(new Shore(0, 0), new Rectangle2D.Double(0, 0, 0, 0));

		GAMEBOX = new Rectangle2D.Double(0, 0, 0, 0);
		UIBOX = new Rectangle2D.Double(0, 0, 0, 0);
		MENU = new Rectangle2D.Double(0, 0, 0, 0);
		uiGabion = new Rectangle2D.Double(0, 0, 0, 0);
		uiPlant = new Rectangle2D.Double(0, 0, 0, 0);
		helperHorseRect = new Rectangle2D.Double(0, 0, 0, 0);

		playButton = new Rectangle2D.Double(0, 0, 0, 0);
		instructionButton = new Rectangle2D.Double(0, 0, 0, 0);
		creditButton = new Rectangle2D.Double(0, 0, 0, 0);

		renderDragGabion = false;
		renderDragPlant = false;
		eroded = false;
		isRunOff = false;
		placedFirstGabion = false;
		placedWrongGabion = false;
		placedWrongPlant = false;
		placedFirstPlant = false;
		ableToPlaceGabion = false;
		init = false;
		hittingPlant = false;
		hittingWater = false;

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

		this.shore.setShore(new Shore((int) this.GAMEBOX.getX(), (int) this.GAMEBOX.getY()));
		this.shore.setRect(new Rectangle2D.Double(shore.getShore().getX(), shore.getShore().getY(), (int) shoreWidth,
				GAMEBOX.getHeight()));

		fontSize = (int) (width * 0.03);
		f1 = new Font("Arial", Font.PLAIN, this.fontSize);

		for (int i = 0; i < this.numOfRows; i++) {
			waveRows.add(new Rectangle2D.Double(shore.getRect().getWidth() + concreteWallWidth,
					(UIBOX.getHeight() + (GAMEBOX.getHeight() / this.numOfRows) * i),
					GAMEBOX.getWidth() - shore.getRect().getWidth(), GAMEBOX.getHeight() / this.numOfRows));
			plantRows.add(new Rectangle2D.Double(shore.getRect().getWidth() - (shore.getRect().getWidth() * 0.2),
					(UIBOX.getHeight() + (GAMEBOX.getHeight() / this.numOfRows) * i), shore.getRect().getWidth() * 0.2,
					GAMEBOX.getHeight() / this.numOfRows));

			concreteWalls.add(new ConcreteWallController(
					new ConcreteWalls((int) shore.getRect().getWidth(),
							(int) (GAMEBOX.getHeight() / this.numOfRows) * i),
					new Rectangle2D.Double(shore.getRect().getWidth(),
							(UIBOX.getHeight() + (GAMEBOX.getHeight() / this.numOfRows) * i), concreteWallWidth,
							GAMEBOX.getHeight() / this.numOfRows)));

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

		double gWidth = UIBOX.getWidth() * 0.125;
		gX = UIBOX.getWidth() - gWidth;
		gY = UIBOX.getY();
		gb.setRect(new Rectangle2D.Double(gX, gY, gWidth, UIBOX.getHeight()));

		uiGabion = new Rectangle2D.Double(gb.getRect().getX(), gb.getRect().getCenterY() - (gabionHeight / 2),
				gabionWidth, gabionHeight);

		pb.setRect(
				new Rectangle2D.Double(UIBOX.getX(), UIBOX.getY(), UIBOX.getHeight() * (2.0 / 3.0), UIBOX.getHeight()));

		uiPlant = new Rectangle2D.Double(pb.getRect().getCenterX() - (uiPlantWidth / 2),
				pb.getRect().getCenterY() - (uiPlantHeight / 2.0), uiPlantWidth, uiPlantHeight);

		double cWidth = UIBOX.getWidth() * 0.05;
		double cX = UIBOX.getWidth()*0.2;
		double helperHorseWidth = width * 0.1;
		double helperHorseHeight = height * 0.15;
		HorseshoeCrab helperHorseCrab = new HorseshoeCrab((int) (cX + cWidth), (int) (UIBOX.getY()));

		Rectangle2D helperHorseRect = new Rectangle2D.Double(helperHorseCrab.getX(), helperHorseCrab.getY(),
				helperHorseWidth, helperHorseHeight);
		helperHorse = new HorseshoeCrabController(helperHorseCrab, helperHorseRect);
		hsCrab.add(helperHorse);

		bic.loadBufferedImage();
		ac = new AnimationController(this, bic, null, null, 0);
		this.init = true;
		System.out.println("game ready");
	}

	/**
	 * The main loop for the game where all the instantiated object's tick
	 * methods get called.
	 */
	public void loop() {
		switch (this.currentGameState) {
		case TUTORIAL:
			collision();
			switch (this.currentTutorialState) {
			case OYSTERS:
				// System.out.println(this.waves.size());
				this.message = "Collect Oyster Shells!";
				spawner.spawnOysters(1, 0);
				if (gb.getGb().getGabions() >= 2) {
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
				spawner.TutorialSpawnWaves();
				spawner.spawnOysters(2, 0);
				textTimer.countUp(3);
				// System.out.println(textTimer.getTime());
				if (textTimer.getTime() >= 3) {
					this.animations.add(new AnimationController(this, bic, Animation.PLACEGABION, null, 1));
					this.animations.add(new AnimationController(this, bic, Animation.PLACEGABION, null, 4));
					this.currentTutorialState = TutorialState.GABIONS;
					textTimer = new Timer();
					this.ableToPlaceGabion = true;
				}
				break;
			case GABIONS:
				if (this.placedWrongGabion) {
					this.message = "Make sure to place your gabions in front of waves.";
				} else {
					this.message = "Stop the waves by placing gabions!";
				}

				// plantTimer.countUp(5);
				pb.getPb().setNumberOfPlants(1);
				// flashing gabion here
				if (this.placedFirstGabion) {
					this.message = "Good Job";
					textTimer.countUp(2);
					if (textTimer.getTime() >= 2) {
						textTimer = new Timer();
						animations.clear();
						this.currentTutorialState = TutorialState.RUNOFF;

					}

				}

				break;
			case RUNOFF:
				plantTimer.countUp(5);
				// textTimer.countUpStop(3);
				spawner.spawnTutorialRunOff();
				if (this.runOff.get(0).getRect().getX() + this.runOff.get(0).getRect()
						.getWidth() >= (shore.getRect().getX() + shore.getRect().getWidth()) / 3) {
					this.message = "Oh no! Runoff!";
					textTimer = new Timer();
					this.animations.add(new AnimationController(this, bic, Animation.PLACEPLANT, null, 2));
					this.currentTutorialState = TutorialState.PLANTS;
				} else {
					this.message = "Look, your Plants have grown!";
					// flash plant number

				}

				break;
			case PLANTS:
				plantTimer.countUp(5);
				textTimer.countUpStop(2);
				if (textTimer.getTime() >= 2) {
					if (!this.placedWrongPlant) {
						this.message = "Plant plants to filter the dirty runoff.";
					} else {
						this.message = "Make sure to place your plants in front of runoff.";
					}
					if (this.placedFirstPlant) {
						textTimer = new Timer();
						this.message = "Good Job! The water is clean! You now know how to defend your estuary!";
						this.animations.clear();
						this.currentTutorialState = TutorialState.END;
					}
				}
				// spawner.spawnRunOff(1, 0);
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

			if (timer.getTime() == 0 || shore.getShore().getHealth() <= 25 || this.dirtyWater.getAlpha() >= 200) {
				game.setGameOver(true);
				this.currentGameState = GameState.OVER;
			}
			collision();
			break;
		case PAUSED:
			break;
		case MENU:
			for (Iterator<WaveController> itw = waves.iterator(); itw.hasNext();) {
				WaveController wave = itw.next();
				for (Iterator<GabionController> itg = gabions.iterator(); itg.hasNext();) {
					GabionController gabion = itg.next();
					if (wave.getRect().intersects(gabion.getRect())) {
						itw.remove();
					}
				}
				if (wave.getRect().intersects(shore.getRect())) {
					itw.remove();
				}
			}
			timer.countDown();
			spawner.spawnWaves(5, 0);
			break;
		case LOADING:
			System.out.println(this.init);
			if (this.init) {
				// System.out.println("Entered");
				this.currentGameState = GameState.MENU;
				gb.getGb().setGabions(this.numOfRows);
				for (int i = 0; i < this.numOfRows; i++) {
					spawner.spawnWaves(5, 0);
					this.handlePlaceGabion(
							new Point((int) this.waveRows.get(i).getX() + 1, (int) this.waveRows.get(i).getY() - 1));
				}
			}
			break;
		case OVER:
			break;
		default:
			System.out.println("STATES NOT WORKING, CURRENT STATE: " + this.currentGameState);
		}

		// stuff that always needs to be done

		pb.getPb().build();

		if (this.currentTutorialState != TutorialState.GABIONS && this.currentTutorialState != TutorialState.PLANTS) {
			for (WaveController wave : waves) {
				wave.getWave().move();
				Rectangle2D newWave = new Rectangle2D.Double(wave.getWave().getX(), wave.getWave().getY(),
						wave.getRect().getWidth(), wave.getRect().getHeight());
				wave.setRect(newWave);
			}
		}

		for (Iterator<RunOffController> it = runOff.iterator(); it.hasNext();) {
			RunOffController runOff = it.next();
			if (this.currentTutorialState != TutorialState.PLANTS) {
				runOff.getRunOff().move();
			}
			if (shore.getRect().getWidth() + concreteWallWidth + 8 < runOff.getRect().getX()
					+ runOff.getRect().getWidth()) {
				runOff.getRect().setRect(runOff.getRunOff().getX(), runOff.getRunOff().getY(),
						runOff.getRect().getWidth() - runOff.getRunOff().getSpeed(), runOff.getRect().getHeight());
				this.hittingWater = true;
				this.runOffTimer.countUp(1);
				// System.out.println("Plant: " + this.hittingPlant);
				// System.out.println("Timer: " + runOffTimer.getTime());
				// System.out.println("Water: " + this.hittingWater );
				if (!this.hittingPlant && runOffTimer.getTime() >= 1 && this.hittingWater) {
					int newAlpha = this.dirtyWater.getAlpha() + 10;
					if (newAlpha >= 250) {
						newAlpha = 250;
					}
					this.dirtyWater = new Color(this.dirtyWater.getRed(), this.dirtyWater.getGreen(),
							this.dirtyWater.getBlue(), newAlpha);
					// System.out.println("changing color!!!");
				}

				if (runOff.getRect().getWidth() > 0) {
					spawner.getRunOffInRow().set(runOff.getRunOff().getRowNum(), false);

				}
			} else {
				runOff.setRect(new Rectangle2D.Double(runOff.getRunOff().getX(), runOff.getRunOff().getY(),
						runOff.getRect().getWidth(), runOff.getRect().getHeight()));
				this.hittingWater = false;

			}
			if (runOff.getRect().getWidth() <= 0) {
				it.remove();
				// System.out.println("runOff size: " +
				// this.getRunOff().size());
			}

		}

		if (!this.hittingWater) {
			cleanWaterTimer.countUp(5);
			int newAlpha = this.dirtyWater.getAlpha();
			if (cleanWaterTimer.getTime() >= 5) {
				newAlpha = this.dirtyWater.getAlpha() - 10;
			}
			if (newAlpha <= 0) {
				newAlpha = 0;
			}
			this.dirtyWater = new Color(this.dirtyWater.getRed(), this.dirtyWater.getGreen(), this.dirtyWater.getBlue(),
					newAlpha);
		}

		for (Iterator<OysterController> it = oysters.iterator(); it.hasNext();) {
			OysterController oyster = it.next();
			if (!oyster.getOyster().isVisible()) {
				it.remove();
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

			g2.drawImage(bic.getImageAtIndex(Image.SKY.getIndex()), (int) UIBOX.getX(), (int) UIBOX.getY(),
					(int) UIBOX.getWidth(), (int) (UIBOX.getHeight()), null);
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
			// this.renderGabionBuilder(g2);

			// GabionBuilder Meter
			// this.renderGabionMeter(g2);
			this.renderUIGabion(g2);
			this.renderDragGabion(g2);
			this.renderNumberOfGabions(g2);

			this.renderOysters(g2);

			// plant meter
			this.renderPlantMeter(g2);
			this.renderNumberOfPlants(g2);
			this.renderDragPlant(g2);

			this.renderRunoff(g2);

			this.renderAnimation(g2);
			switch (this.currentTutorialState) {
			case OYSTERS:
				break;
			case WAVES:
				break;
			case GABIONS:
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
			g2.drawImage(bic.getImageAtIndex(Image.SKY.getIndex()), (int) UIBOX.getX(), (int) UIBOX.getY(),
					(int) UIBOX.getWidth(), (int) (UIBOX.getHeight()), null);
			// GAMEBOX
			this.renderSun(g2);
			g2.setColor(new Color(163, 232, 255));
			g2.draw(GAMEBOX);
			g2.fill(GAMEBOX);
			this.renderDirtyWater(g2);
			this.renderGabions(g2);
			this.renderWaves(g2);
			this.renderConcreteWalls(g2);
			this.renderShore(g2);
			this.renderPlants(g2);

			// UI
			// ---------------------------
			// Gabion builder/Plant builder
			this.renderGameTimer(g2);
			// this.renderGabionBuilder(g2);

			// GabionBuilder Meter
			// this.renderGabionMeter(g2);
			this.renderUIGabion(g2);
			this.renderDragGabion(g2);
			this.renderNumberOfGabions(g2);

			this.renderOysters(g2);

			// plant meter
			this.renderPlantMeter(g2);
			this.renderNumberOfPlants(g2);
			this.renderDragPlant(g2);

			this.renderRunoff(g2);

			this.renderAnimation(g2);
			break;
		case MENU:
			// UIBOX Sky
			g2.drawImage(bic.getImageAtIndex(Image.SKY.getIndex()), (int) UIBOX.getX(), (int) UIBOX.getY(),
					(int) UIBOX.getWidth(), (int) (UIBOX.getHeight()), null);
			// System.out.println(Image.HAND.getIndex() + " " +
			// Image.HAND.getPath() + " " + bic.getImages().get(2));
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
			// UIBOX Sky
			g2.drawImage(bic.getImageAtIndex(Image.SKY.getIndex()), (int) UIBOX.getX(), (int) UIBOX.getY(),
					(int) UIBOX.getWidth(), (int) (UIBOX.getHeight()), null);
			// GAMEBOX
			g2.setColor(new Color(163, 232, 255));
			g2.draw(GAMEBOX);
			g2.fill(GAMEBOX);
			this.renderDirtyWater(g2);
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
			// this.renderGabionBuilder(g2);

			// GabionBuilder Meter
			// this.renderGabionMeter(g2);
			this.renderUIGabion(g2);
			this.renderDragGabion(g2);
			this.renderNumberOfGabions(g2);

			this.renderOysters(g2);

			// plant meter
			this.renderPlantMeter(g2);
			this.renderNumberOfPlants(g2);
			this.renderDragPlant(g2);

			this.renderRunoff(g2);

			this.renderAnimation(g2);
			break;
		case LOADING:
			break;
		case OVER:
			// UIBOX Sky
			g2.drawImage(bic.getImageAtIndex(Image.SKY.getIndex()), (int) UIBOX.getX(), (int) UIBOX.getY(),
					(int) UIBOX.getWidth(), (int) (UIBOX.getHeight()), null);
			// GAMEBOX
			this.renderSun(g2);
			g2.setColor(new Color(163, 232, 255));
			g2.draw(GAMEBOX);
			g2.fill(GAMEBOX);
			this.renderDirtyWater(g2);
			this.renderGabions(g2);
			this.renderWaves(g2);
			this.renderConcreteWalls(g2);
			this.renderShore(g2);
			this.renderPlants(g2);

			// UI
			// ---------------------------
			// Gabion builder/Plant builder
			this.renderGameTimer(g2);
			// this.renderGabionBuilder(g2);

			// GabionBuilder Meter
			// this.renderGabionMeter(g2);
			this.renderUIGabion(g2);
			this.renderDragGabion(g2);
			this.renderNumberOfGabions(g2);

			this.renderOysters(g2);

			// plant meter
			this.renderPlantMeter(g2);
			this.renderNumberOfPlants(g2);
			this.renderDragPlant(g2);

			this.renderRunoff(g2);

			this.renderAnimation(g2);
		default:
			break;

		}

	}

	public void renderDirtyWater(Graphics2D g2) {
		g2.setColor(this.dirtyWater);
		double width = this.GAMEBOX.getWidth() - this.shore.getRect().getWidth();
		double height = this.GAMEBOX.getHeight();
		Rectangle2D dirtywaterRect = new Rectangle2D.Double(
				this.shore.getRect().getX() + this.shore.getRect().getWidth(),
				this.UIBOX.getY() + this.UIBOX.getHeight(), width, height);
		g2.draw(dirtywaterRect);
		g2.fill(dirtywaterRect);
	}

	public void renderGabions(Graphics2D g2) {
		for (int i = 0; i < gabions.size(); i++) {
			Rectangle2D gabion = gabions.get(i).getRect();
			if (gabions.get(i).getGabion().getHealth() == 2) {
				g2.setColor(new Color(0, 0, 0, 175));
			} else if (gabions.get(i).getGabion().getHealth() == 1) {
				g2.setColor(new Color(0, 0, 0, 100));
			} else {
				// 3
				g2.setColor(Color.BLACK);
			}
			// g2.draw(gabions.get(i).getRect());
			// g2.fill(gabions.get(i).getRect());
			g2.drawImage(bic.getImageAtIndex(Image.GABION.getIndex()), (int) gabion.getX(), (int) gabion.getY(),
					(int) gabion.getWidth(), (int) gabion.getHeight(), null);
		}
	}

	public void renderWaves(Graphics2D g2) {
		g2.setColor(Color.cyan);
		for (WaveController wc : waves.toArray(new WaveController[0])) {
			// g2.draw(wc.getRect());
			// g2.fill(wc.getRect());
			if (game.getFramePerSecond() % 3 == 0) {
				g2.drawImage(bic.getImageAtIndex(Image.WAVE1.getIndex()), (int) wc.getRect().getX(),
						(int) wc.getRect().getY(), (int) wc.getRect().getWidth(), (int) wc.getRect().getHeight(), null);
			} else if (game.getFramePerSecond() % 3 == 1) {
				g2.drawImage(bic.getImageAtIndex(Image.WAVE2.getIndex()), (int) wc.getRect().getX(),
						(int) wc.getRect().getY(), (int) wc.getRect().getWidth(), (int) wc.getRect().getHeight(), null);
			} else {
				g2.drawImage(bic.getImageAtIndex(Image.WAVE3.getIndex()), (int) wc.getRect().getX(),
						(int) wc.getRect().getY(), (int) wc.getRect().getWidth(), (int) wc.getRect().getHeight(), null);
			}
			// g2.drawImage(bic.getImageAtIndex(Image.WAVE1.getIndex()), (int)
			// wc.getRect().getX(),
			// (int) wc.getRect().getY(), (int) wc.getRect().getWidth(), (int)
			// wc.getRect().getHeight(), null);
		}

	}

	public void renderConcreteWalls(Graphics2D g2) {
		for (ConcreteWallController wall : concreteWalls) {
			g2.setColor(Color.LIGHT_GRAY);
			g2.draw(wall.getRect());
			g2.setColor(Color.DARK_GRAY);
			g2.fill(wall.getRect());
		}
	}

	public void renderShore(Graphics2D g2) {
		g2.setColor(this.ShoreColor);
		g2.fill(shore.getRect());
		g2.draw(shore.getRect());
	}

	public void renderHorseshoeCrab(Graphics2D g2) {
		// g2.setColor(Color.PINK);

		for (HorseshoeCrabController hsCrab : hsCrab) {
			// g2.draw(hsCrab);
			// g2.fill(hsCrab);
			g2.drawImage(bic.getImageAtIndex(Image.BLUECRAB.getIndex()), (int) hsCrab.getRect().getX(),
					(int) hsCrab.getRect().getY(), (int) hsCrab.getRect().getWidth(),
					(int) hsCrab.getRect().getHeight(), null);
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Arial", Font.ITALIC, (int) (game.getScale().getWidth() * 0.015)));
			// g2.drawString("" + this.message,
			// (int)(hsCrab.getX()+hsCrab.getWidth()),
			// (int)(hsCrab.getCenterY() - game.getScale().getHeight() *
			// 0.01));
			ac.playTextAnimation(g2, (int) (hsCrab.getRect().getX() + hsCrab.getRect().getWidth()),
					(int) (hsCrab.getRect().getCenterY() - game.getScale().getHeight() * 0.01));
		}

	}

	public void renderGameTimer(Graphics2D g2) {
		g2.setFont(f1);
		g2.setColor(Color.BLACK);
		g2.drawString(timer.getTime() + "", (int) UIBOX.getCenterX(), (int) UIBOX.getCenterY());

	}

	public void renderPlants(Graphics2D g2) {
		g2.setColor(Color.red);
		for (PlantController plant : plants) {
			if (plant.getPlant().isVisible()) {
				g2.draw(plant.getRect());
				g2.fill(plant.getRect());
				if (plant.getPlant().getHealth() > (int) (plant.getPlant().getMaxHealth() * .66)) {
					g2.drawImage(bic.getImageAtIndex(Image.GRASS1.getIndex()), (int) plant.getRect().getX(),
							(int) plant.getRect().getY(), (int) (plant.getRect().getWidth() * 1.8),
							(int) (plant.getRect().getHeight() * 1.5), null);
				} else if (plant.getPlant().getHealth() > plant.getPlant().getMaxHealth() * 0.33) {
					g2.drawImage(bic.getImageAtIndex(Image.GRASS2.getIndex()), (int) plant.getRect().getX(),
							(int) plant.getRect().getY(), (int) (plant.getRect().getWidth() * 1.8),
							(int) (plant.getRect().getHeight() * 1.5), null);
				} else {
					g2.drawImage(bic.getImageAtIndex(Image.GRASS3.getIndex()), (int) plant.getRect().getX(),
							(int) plant.getRect().getY(), (int) (plant.getRect().getWidth() * 1.8),
							(int) (plant.getRect().getHeight() * 1.5), null);
				}
			}
		}
	}

	public void renderGabionBuilder(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fill(gb.getRect());
		g2.draw(gb.getRect());
	}

	public void renderGabionMeter(Graphics2D g2) {
		g2.setColor(Color.ORANGE);
		double gbPercentage = (double) gb.getGb().getNumberOfOysters() / (double) gb.getGb().getMaxGabionCapacity();
		double maxHeight = gb.getRect().getHeight();
		double height = maxHeight * gbPercentage;
		double width = gb.getRect().getWidth() / 10;
		double x = gb.getRect().getX(); // - (width/2);
		double y = maxHeight - height;
		Rectangle2D gabionMeter = new Rectangle2D.Double(x, y, width, height);
		g2.draw(gabionMeter);
		g2.fill(gabionMeter);
	}

	public void renderUIGabion(Graphics2D g2) {
		if (gb.getGb().getGabions() == 0) {
			g2.setColor(new Color(0, 0, 0, 50));
		} else {
			g2.setColor(new Color(0, 0, 0, 255));
		}
		// g2.draw(uiGabion);
		// g2.fill(uiGabion);

		if (this.gb.getGb().getNumberOfOysters() == 0 && this.gb.getGb().getGabions() == 0) {
			g2.drawImage(bic.getImageAtIndex(Image.GABION1.getIndex()), (int) uiGabion.getX(), (int) uiGabion.getY(),
					(int) uiGabion.getWidth(), (int) uiGabion.getHeight(), null);
		} else if (this.gb.getGb().getNumberOfOysters() == 1) {
			g2.drawImage(bic.getImageAtIndex(Image.GABION2.getIndex()), (int) uiGabion.getX(), (int) uiGabion.getY(),
					(int) uiGabion.getWidth(), (int) uiGabion.getHeight(), null);
		} else if (this.gb.getGb().getNumberOfOysters() == 2) {
			g2.drawImage(bic.getImageAtIndex(Image.GABION3.getIndex()), (int) uiGabion.getX(), (int) uiGabion.getY(),
					(int) uiGabion.getWidth(), (int) uiGabion.getHeight(), null);
		} else if (this.gb.getGb().getNumberOfOysters() == 3) {
			g2.drawImage(bic.getImageAtIndex(Image.GABION4.getIndex()), (int) uiGabion.getX(), (int) uiGabion.getY(),
					(int) uiGabion.getWidth(), (int) uiGabion.getHeight(), null);
		} else if (this.gb.getGb().getNumberOfOysters() == 4 || this.gb.getGb().getNumberOfOysters() == 5) {
			g2.drawImage(bic.getImageAtIndex(Image.GABION5.getIndex()), (int) uiGabion.getX(), (int) uiGabion.getY(),
					(int) uiGabion.getWidth(), (int) uiGabion.getHeight(), null);
		} else {
			g2.drawImage(bic.getImageAtIndex(Image.GABIONFULL.getIndex()), (int) uiGabion.getX(), (int) uiGabion.getY(),
					(int) uiGabion.getWidth(), (int) uiGabion.getHeight(), null);
		}
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
		for (OysterController oyster : oysters.toArray(new OysterController[0])) {
			// OysterController oyster = oysters.get(i);
			// g2.draw(oyster.getRect());
			// g2.fill(oyster.getRect());
			g2.drawImage(bic.getImageAtIndex(Image.OYSTER.getIndex()), (int) oyster.getRect().getX(),
					(int) oyster.getRect().getY(), (int) oyster.getRect().getWidth(),
					(int) (oyster.getRect().getHeight()), null);
		}
	}

	public void renderNumberOfGabions(Graphics2D g2) {
		g2.setFont(f1);
		g2.setColor(Color.WHITE);
		if (gb.getGb().getGabions() >= 10) {
			g2.drawString("x" + gb.getGb().getGabions(),
					(int) ((int) (gb.getRect().getCenterX() + (this.uiGabion.getWidth() / 2)) - (f1.getSize() * .75)),
					(int) uiGabion.getCenterY() + (f1.getSize() / 2));
		} else {
			g2.drawString("x" + gb.getGb().getGabions(),
					(int) (gb.getRect().getCenterX() + (this.uiGabion.getWidth() / 2)) - (f1.getSize() / 2),
					(int) uiGabion.getCenterY() + (f1.getSize() / 2));
		}
	}

	public void renderPlantMeter(Graphics2D g2) {
		double maxHeight = pb.getRect().getHeight();
		double height = ((double) plantTimer.getTimeMili() / (pb.getPb().getNumOfSecondsPerPlant() * 1000)) * maxHeight;
		double width = pb.getRect().getWidth();
		double x = pb.getRect().getX();
		double y = maxHeight - height;
		Rectangle2D plantMeter = new Rectangle2D.Double(x, y, width, height);

		g2.setColor(new Color(163, 255, 173));
		// g2.fill(plantBuilder.getRect());
		// g2.draw(plantBuilder.getRect());
		g2.draw(plantMeter);
		g2.fill(plantMeter);

		// g2.setColor(Color.GREEN);
		// g2.draw(uiPlant);
		// g2.fill(uiPlant);

		g2.drawImage(bic.getImageAtIndex(Image.GRASS1.getIndex()), (int) uiPlant.getX(), (int) uiPlant.getY(),
				(int) uiPlant.getWidth(), (int) (uiPlant.getHeight()), null);

	}

	public void renderNumberOfPlants(Graphics g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(f1);
		g2.drawString("x" + pb.getPb().getNumberOfPlants(),
				(int) (pb.getRect().getX() + pb.getRect().getWidth() + (f1.getSize() / 2)),
				(int) pb.getRect().getCenterY());
	}

	public void renderDragPlant(Graphics2D g2) {

		if (this.renderDragPlant) {
			// g2.setColor(Color.GREEN);
			// g2.draw(this.renderDragPlant(game.getMouseCords()));
			Rectangle2D r = this.createDragPlant(game.getMouseCords());
			g2.drawImage(bic.getImageAtIndex(Image.GRASS1.getIndex()), (int) r.getX(), (int) r.getY(),
					(int) r.getWidth(), (int) r.getHeight(), null);
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
		g2.setColor(runOffColor);
		for (RunOffController runOff : runOff) {
			g2.draw(runOff.getRect());
			g2.fill(runOff.getRect());
		}
	}

//	public void renderCrabFishMeter(Graphics2D g2) {
//		g2.setColor(Color.DARK_GRAY);
//		g2.draw(cfMeter.getRect());
//		g2.fill(cfMeter.getRect());
//
//		g2.setColor(Color.WHITE);
//		g2.drawString("" + cfMeter.getCfm().getPhLevels(), (int) cfMeter.getRect().getCenterX(),
//				(int) cfMeter.getRect().getCenterY());
//		g2.setColor(Color.YELLOW);
//	}

	public void renderSun(Graphics2D g2) {

		double sunDim = game.getScale().getWidth() * 0.06;
		double startX = UIBOX.getX() + pb.getRect().getX() + pb.getRect().getWidth() + UIBOX.getWidth() * 0.05;
		double maxX = this.gb.getRect().getX() - (sunDim / 2);
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
		g2.drawImage(bic.getImageAtIndex(Image.SUN.getIndex()), (int) sunX, (int) y, (int) sunDim, (int) sunDim, null);

	}

	public void renderAnimation(Graphics2D g2) {
		for (AnimationController ac : animations) {
			switch (ac.getAnimation()) {
			case OYSTER:
				ac.playCollectOysterAnimation(g2);
				break;
			case PLACEGABION:
				ac.playGabionPlacementAnimation(g2);
				// ac.playGabionPlacementAnimation(g2);
				break;
			case PLACEPLANT:
				ac.playPlantPlacementAnimation(g2);
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
		for (Iterator<WaveController> itw = waves.iterator(); itw.hasNext();) {
			WaveController wave = itw.next();
			if (wave.getRect().intersects(shore.getRect())) {
				// wave hit shore
				// erode shore
				if (shore.getShore().erode()) {
					this.eroded = true;
					this.ShoreColor = new Color(255, 200, 100);
					shore.setRect(new Rectangle2D.Double(shore.getRect().getX(), shore.getRect().getY(),
							(shore.getRect().getWidth() - this.gabionWidth - this.gbPadding),
							shore.getRect().getHeight()));
					for (Rectangle2D plantRow : plantRows) {
						plantRow.setRect(shore.getRect().getWidth() - plantRow.getWidth(), plantRow.getY(),
								plantRow.getWidth(), plantRow.getHeight());
					}

					for (PlantController plant : plants) {
						plant.setRect(new Rectangle2D.Double(plant.getRect().getX() - this.gabionWidth - this.gbPadding,
								plant.getRect().getY(), plant.getRect().getWidth(), plant.getRect().getHeight()));
					}
				} else {
					this.ShoreColor = new Color(255, 200, 100, 230);

				}
				System.out.println("Wave Hit Shore");
				itw.remove();
			}
			for (Iterator<GabionController> itg = gabions.iterator(); itg.hasNext();) {
				GabionController gabion = itg.next();

				if (gabion.getRect().intersects(wave.getRect())) {
					itw.remove();
					gabion.getGabion().changeHealth(gabion.getGabion().getHealth() - 1);
					System.out.println("wave hit gabion");

					if (gabion.getGabion().getHealth() <= 0) {
						itg.remove();
						// debugging only
						try {
							this.numOfGabionsInRow.set(gabion.getGabion().getRowNum(),
									this.numOfGabionsInRow.get(gabion.getGabion().getRowNum()) - 1);
						} catch (IndexOutOfBoundsException e) {
							System.out.println(gabion.getGabion().getRowNum());
							System.out.println("BAD");
							e.printStackTrace();
						}

					}
				}
			}

			for (Iterator<ConcreteWallController> itc = concreteWalls.iterator(); itc.hasNext();) {
				ConcreteWallController concreteWall = itc.next();
				if (concreteWall.getRect().intersects(wave.getRect())) {
					itc.remove();
					itw.remove();
				}
			}

		}
		for (Iterator<RunOffController> itr = runOff.iterator(); itr.hasNext();) {
			RunOffController runOff = itr.next();
			for (Iterator<PlantController> itp = plants.iterator(); itp.hasNext();) {
				PlantController plant = itp.next();
				if (runOff.getRect().intersects(plant.getRect())) {
					// this.setIsRunOff(true, i);
					this.hittingPlant = true;
					plant.getPlant().changeHealth(plant.getPlant().getHealth() - 1);
					// System.out.println(plant.getPlant().getHealth());
					runOff.getRect().setRect(runOff.getRect().getX(), runOff.getRect().getY(),
							runOff.getRect().getWidth() - 10, runOff.getRect().getHeight());
					if (plant.getPlant().getHealth() <= 0) {
						itp.remove();
					}
				} else {
					// LOOK IT
					// THIS---------------------------------------------------------------------------------
					// this.setIsRunOff(false, i);
					this.hittingPlant = false;
					// System.out.println("not intersecting plant");

				}

			}

		}

		// boolean intersectingRow = false;
		// ArrayList<Integer> rowIndexes = new ArrayList<Integer>();
		// for (Iterator<RunOffController> itr = runOff.iterator();
		// itr.hasNext();) {
		// RunOffController runOff = itr.next();
		// for (int i = 0; i < plantRows.size(); i++) {
		// Rectangle2D row = plantRows.get(i);
		// if(row.getX() <= runOff.getRect().getX()+runOff.getRect().getWidth()
		// &&
		// runOff.getRect().getY() >= row.getY()
		// && runOff.getRect().getY()+runOff.getRect().getHeight() <=
		// row.getY()+row.getHeight()) {
		// intersectingRow = true;
		// rowIndexes.add(i);
		// }
		//
		// }
		// }
		//
		// if(intersectingRow) {
		// for(Iterator<PlantController>itp = plants.iterator(); itp.hasNext();)
		// {
		// PlantController plant = itp.next();
		// for (Integer rowIndex : rowIndexes) {
		// if(plant.getRect().getY() >= plantRows.get(rowIndex).getY() &&
		// plant.getPlant().getY() <= plantRows.get(rowIndex).getY() +
		// plantRows.get(rowIndex).getHeight()) {
		// this.hittingPlant = true;
		// plant.getPlant().changeHealth(plant.getPlant().getHealth()-1);
		//
		// if (plant.getPlant().getHealth()<=0) {
		// itp.remove();
		// }
		// System.out.println("itersecting");
		//
		// } else {
		// System.out.println("Row: " + rowIndex );
		// System.out.println("Row Bounds: " +
		// plantRows.get(rowIndex).getBounds());
		// System.out.println("Plant Bounds: " + plant.getRect().getBounds());
		// this.hittingPlant = false;
		// }
		// }
		// }
		// System.out.println(this.hittingPlant);
		// }
		//

	}

	public void handlePlacePlant(Point p) {
		if (pb.getPb().getNumberOfPlants() != 0) {
			for (int i = 0; i < plantRows.size(); i++) {
				Rectangle2D row = plantRows.get(i);
				if (row.contains(p)) {
					spawner.spawnPlants(i);
					if (this.currentGameState == GameState.TUTORIAL
							&& this.currentTutorialState == TutorialState.PLANTS) {
						if (i == spawner.getRowForRunOff()) {
							this.placedFirstPlant = true;
						} else {
							this.placedWrongPlant = true;
							this.plants.remove(this.plants.size() - 1);
							this.pb.getPb().setNumberOfPlants(this.pb.getPb().getNumberOfPlants() + 1);
						}
					}
				}
			}
		}
		this.renderDragPlant = false;
	}

	public void handlePlaceGabion(Point p) {
		// System.out.println(p.getX() + ", " + p.getY());

		// spawn gabion
		if (gb.getGb().getGabions() != 0 && this.ableToPlaceGabion) {
			for (int i = 0; i < waveRows.size(); i++) {
				Rectangle2D row = waveRows.get(i);
				if (row.contains(p) && this.numOfGabionsInRow.get(i) < 5) {
					double y = row.getCenterY() - ((gabionHeight) / 2);
					double x = ((gabionWidth + gbPadding) * this.numOfGabionsInRow.get(i)) + row.getX();

					Gabion gab = new Gabion((int) x, (int) y, i);
					Rectangle2D gabRect = new Rectangle.Double(x + gbPadding, y, gabionWidth, gabionHeight);
					gabions.add(new GabionController(gab, gabRect));
					gb.getGb().setGabions(gb.getGb().getGabions() - 1);
					this.numOfGabionsInRow.set(i, this.numOfGabionsInRow.get(i) + 1);
					if (this.currentGameState != GameState.MENU && this.currentGameState == GameState.TUTORIAL
							&& this.currentTutorialState == TutorialState.GABIONS) {
						// System.out.println("waves: " +
						// this.getNumOfWavesInRow().get(i));
						// System.out.println("Gabions: " +
						// this.numOfGabionsInRow.get(i));
						if (this.getNumOfWavesInRow().get(i) > 0 && this.numOfGabionsInRow.get(i) == 1) {
							if (this.gabions.size() == 2) {
								this.placedFirstGabion = true;
							}
						} else {
							this.gabions.remove(this.gabions.size() - 1);
							this.placedWrongGabion = true;
							gb.getGb().setGabions(gb.getGb().getGabions() + 1);
							this.numOfGabionsInRow.set(i, this.numOfGabionsInRow.get(i) - 1);
						}
					}
				}
			}
		}
		this.renderDragGabion = false;

	}

	public void handleCollectOyster(Point p, int i) {
		//int numOfClusters = 0;
//		numOfClusters = oysters.get(i).getOyster().getNumOfOystersInClump();
		Rectangle2D o = oysters.get(i).getRect();
		gb.getGb().build();
		// this.oysters.get(i).setCollected(true);
		this.animations.add(new AnimationController(this, this.bic, Animation.OYSTER, o, 0));
		oysters.get(i).getOyster().setVisible(false);

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
				this.waves.clear();
				for (int i = 0; i < this.numOfWavesInRow.size(); i++) {
					this.numOfWavesInRow.set(i, 0);
				}

			} else if (this.instructionButton.contains(p)) {

			} else if (this.creditButton.contains(p)) {

			}
		}
		for (int i = 0; i < oysters.size(); i++) {
			if (oysters.get(i).getRect().contains(p)) {
				handleCollectOyster(p, i);
				return;
			}
		}
		this.handleDrag(p);

	}

	public String calculateScore(int shoreHealth, int alphaWater) {
		double score = (((double)shoreHealth/(double)shore.getShore().getMaxHealth())*0.5) + (((255.0-(double)alphaWater)/255.0)*0.5);
		if (score == 1) {
			return "A+";
		} else if (score >= 0.9 ) {
			return "A";
		} else if (score >= 0.8) {
			return "B"; 
		} else if (score >= 0.7) {
			return "C";
		} else {
			return "D";
		}
	}
	

	public ArrayList<WaveController> getWaves() {
		return waves;
	}

	public ArrayList<Rectangle2D> getWaveRows() {
		return waveRows;
	}

	public ArrayList<OysterController> getOysters() {
		return oysters;
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

	public ArrayList<PlantController> getPlants() {
		return plants;
	}

	public ArrayList<Rectangle2D> getPlantRows() {
		return plantRows;
	}

	public PlantBuilder getPb() {
		return pb.getPb();
	}

	public void setPb(PlantBuilder pb) {
		this.pb.setPb(pb);
	}

	public boolean isRenderDragGabion() {
		return renderDragGabion;
	}

	public boolean isRenderDragPlant() {
		return renderDragPlant;
	}

	public ArrayList<RunOffController> getRunOff() {
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

	public void setCurrentGameState(GameState gs) {
		this.currentGameState = gs;
	}

	public Rectangle2D getUiPlant() {
		return uiPlant;
	}

}
