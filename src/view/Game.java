package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import control.BufferedImageController;
import control.GameLoopController;
import model.GameState;

// look up layouts with swing
// grid bag
// concurrency
// components 
// drag and drop == components and mouse events
// event listeners
// dont use custom painting
/**
 * 
 * @author Jackson Jorss
 * @author Jael Flaquer
 * @author Ben Clark
 * @author Robert Ley
 * 
 * image credits: handVector: Designed by Alvaro_cabrera - Freepik.com
 */


public class Game extends JPanel implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static final int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	boolean started = false;
	boolean init = false;
	boolean dragging = false;
	boolean isPaused = false;
	boolean gameLost = false;
	private boolean restart = false;
	
	private long savedTime = 0;
	
	transient MouseAdapter ma = new MouseAdapter() {
		@Override
        public void mouseReleased(MouseEvent e) {
            if (dragging && !isPaused) {
            	if (glc.isRenderDragGabion()) {
            		glc.handlePlaceGabion(e.getPoint());
            		
            	}
            	if (glc.isRenderDragPlant()) {
            		glc.handlePlacePlant(e.getPoint());
            	}
            	dragging = false;
            } 
            //System.out.println("DRAGGGINGGG");
            
        }
		@Override
		public void mousePressed(MouseEvent e) {
			if (!isPaused) {
				glc.handlePressed(e.getPoint());
				//System.out.println("mouse pressed");
			}
			setMouseCords(e.getPoint());
		}
	
	};
	
	transient MouseAdapter mad = new MouseAdapter() {
		
		@Override
		public void mouseDragged(MouseEvent e) {
			setMouseCords(e.getPoint());
			//System.out.println("dragging");
		}
	};
	
	private Point mouseCords = new Point(0,0);
	
	private int framePerSecond = 0;
	
	private GameState oldState;
	
	
	static Scale scale = new Scale(WIDTH, HEIGHT, 8);
	GameLoopController glc = new GameLoopController(this, scale);
	
	
	public Game() {
		super();
		JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setFocusable(true);
        frame.getContentPane().add(this);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        //frame.setVisible(true);
        frame.setVisible(true);
        frame.addKeyListener(new KeyListener() {

        	@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					System.exit(0);
					
				}
				if (e.getKeyCode() == KeyEvent.VK_P && !isGameLost()) {
					System.out.println("PauSED");
					togglePaused();
				}
				if (e.getKeyCode() == KeyEvent.VK_R && !isPaused()) {
					setRestart(true);
				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					save();
				}
				if (e.getKeyCode() == KeyEvent.VK_L) {
					load();
				}
				System.out.println("IM PRESsING A KEY");
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        scale = new Scale((int)this.getBounds().getWidth(), (int)this.getBounds().getHeight(), 8);
        System.out.println("started");
        this.mouseClick(this);
		this.mouseMotion(this);
	}
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		
		Game game = new Game();
		game.started = true;
		System.out.println(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
//		EventQueue.invokeAndWait(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//                    ex.printStackTrace();
//                }
//                
//                JFrame frame = new JFrame("Game");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setSize(WIDTH, HEIGHT);
//                frame.setFocusable(true);
//                frame.getContentPane().add(game);
//                frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
//                frame.setUndecorated(true);
//                //frame.setVisible(true);
//                frame.setVisible(true);
//                frame.addKeyListener(new KeyListener() {
//
//                	@Override
//        			public void keyPressed(KeyEvent e) {
//        				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//        					System.exit(0);
//        					
//        				}
//        				if (e.getKeyCode() == KeyEvent.VK_P && !game.gameOver) {
//        					System.out.println("PauSED");
//        					
//        					if (game.isPaused) {
//            					game.isPaused = false;
//            				} else {
//            					game.isPaused = true;
//            				}
//        					
//        				}
//        				if (e.getKeyCode() == KeyEvent.VK_R && !game.isPaused) {
//        					game.restart = true;
//        				}
//        				System.out.println("IM PRESING A KEY");
//        			}
//
//					@Override
//					public void keyReleased(KeyEvent arg0) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void keyTyped(KeyEvent arg0) {
//						// TODO Auto-generated method stub
//						
//					}
//                	
//                });
//                scale = new Scale((int)game.getBounds().getWidth(), (int)game.getBounds().getHeight(), 8);
//                
//                System.out.println("started");
//                
//                
//            }
//            
//        });
		// loop
		
		System.out.println("called first");
		game.start();
		
	}
	
	
	public void mouseClick(Game game) {
		game.addMouseListener(ma);
	}
	public void mouseMotion(Game game) {
		game.addMouseMotionListener(mad);
	}
	
	
	
	
	/**
	 * Main game loop. Calls repaint ever tic and the game loop
	 * controller loop function to start all of the models logic methods
	 */
	
	public void start() {
		while(true) {
			switch(glc.getCurrentGameState()) {
			case LOADING:
				this.setInit(true);
				repaint();
				this.framePerSecond++;
				glc.loop();
				if (this.framePerSecond*50 >=1000 ) {
					//System.out.println("Frames per second: " + this.framePerSecond);
					this.framePerSecond = 0;
					
				}
				break;
			case GAME:
				repaint();
				this.framePerSecond++;
				
				glc.loop();
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (this.framePerSecond*50 >=1000 ) {
					System.out.println("Frames per second: " + this.framePerSecond);
					this.framePerSecond = 0;
					
				}
				break;
			case MENU:
				repaint();
				this.framePerSecond++;
				
				glc.loop();
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (this.framePerSecond*50 >=1000 ) {
					System.out.println("Frames per second: " + this.framePerSecond);
					this.framePerSecond = 0;
					
				}
				break;
			case PAUSED:
				repaint();
				this.framePerSecond++;
				if (this.framePerSecond*50 >=1000 ) {
					//System.out.println("Frames per second: " + this.framePerSecond);
					this.framePerSecond = 0;
					
				}
				break;
			case TUTORIAL:
				repaint();
				this.framePerSecond++;
				glc.loop();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (this.framePerSecond*50 >=1000 ) {
					//System.out.println("Frames per second: " + this.framePerSecond);
					this.framePerSecond = 0;
					
				}
				break;
			case OVER:
				repaint();
				this.framePerSecond++;
				if (this.framePerSecond*50 >=1000 ) {
					//System.out.println("Frames per second: " + this.framePerSecond);
					this.framePerSecond = 0;
					
				}
				break;
			default:
				break;
			
			}
			//System.out.println("Frames per second: " + this.framePerSecond);
//			if (this.started) {
//				this.setInit(true);
//				repaint();
//				this.framePerSecond++;
//				if (!this.isPaused && !this.gameOver) {
//					glc.loop();
//				}
//				try {
//					Thread.sleep(50);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			if (this.framePerSecond*50 >=1000 ) {
//				System.out.println("Frames per second: " + this.framePerSecond);
//				this.framePerSecond = 0;
//				
//			}
		}
		
		
	}

	/**
	 * Override paintComponet from JPanel. Takes in Graphics g.
	 * Uses g to call all render functions and handles scaling
	 * @param g Graphics object that is needed to render shapes on screen.
	 */
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.init && glc.getCurrentGameState()!= GameState.LOADING) {
        	
        	glc.render(g);
//        	if (this.gameLost) {
//        		if (this.fontSize >= WIDTH * 0.1) {
//        			this.increase = false;
//        		} else if (this.fontSize <= WIDTH * 0.08) {
//        			this.increase = true;
//        		}
//        		if (this.increase) {
//        			this.fontSize++;
//        		} else {
//        			this.fontSize--;
//        		}
//        		Font f = new Font("Arial", 1, this.fontSize);
//        		Font f2 = new Font("Arial", 1, this.fontSize/2);
//            	g.setFont(f);
//            	g.setColor(Color.WHITE);
//            	//g.drawString("GAME OVER",(scale.getWidth()/2) - f.getSize()*2 , scale.getHeight()/2);
//            	g.setFont(f2);
//            	//g.drawString("PRESS R TO RESTART",(scale.getWidth()/2) - f2.getSize()*2 , (scale.getHeight()/2) + f.getSize());
//        	}
        } else {
        	Font f = new Font("Arial", Font.BOLD, 50);
        	g.setFont(f);
        	g.drawString("LOADING...", this.getWidth()/2, this.getHeight()/2);
        }
        
        //scale.render(g);
	}
	
	@SuppressWarnings("static-access")
	public Scale getScale() {
		return this.scale;
	}
	
	public void setInit(boolean newInit) {
		if (newInit != this.init || this.restart) {
			this.restart = false;
			glc = new GameLoopController(this, scale);
			this.gameLost = false;
			glc.init();
			this.init = newInit;
			System.out.println("Initialized");
		}
	}

	public void save(){
		try {
			FileOutputStream fos = new FileOutputStream("saveStateGame.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            glc.save();
            glc.getBic().save();
            this.savedTime = System.currentTimeMillis();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void load() {
		 try {
//			 FileInputStream fis = new FileInputStream("saveStateGame.ser");
//	         ObjectInputStream ois = new ObjectInputStream(fis);
//	         Game lGame = (Game) ois.readObject();
//	         ois.close();
	         //this.glc = null;
	         
//	         FileInputStream fis2 = new FileInputStream("saveStateBIC.ser");
//	         ObjectInputStream ois2 = new ObjectInputStream(fis2);
//	         BufferedImageController Lbic = (BufferedImageController) ois2.readObject();
//	         ois2.close();
	         
	         FileInputStream fis3 = new FileInputStream("saveStateGLC.ser");
	         ObjectInputStream ois3 = new ObjectInputStream(fis3);
	         this.glc = (GameLoopController) ois3.readObject();
	         this.glc.getBic().loadBufferedImage();
	         this.glc.setGame(this);
	         //this.glc.getTimer().setTime(System.currentTimeMillis() - this.savedTime);
	         this.glc.getTimer().setStartGameTime((System.currentTimeMillis() - this.savedTime) + glc.getTimer().getStartGameTime());
	         System.out.println("--------------------------------------------------");
	         System.gc();
	         ois3.close();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
          
	}
	
	public Point getMouseCords() {
		return mouseCords;
	}

	public void setMouseCords(Point mouseCords) {
		this.mouseCords = mouseCords;
	}

	public boolean isDragging() {
		return dragging;
	}
	
	public void setDragging(boolean d) {
		this.dragging = d;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public boolean isGameLost() {
		return gameLost;
	}

	public void setGameLost(boolean gameLost) {
		this.gameLost = gameLost;
	}

	public boolean isRestart() {
		return restart;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
		if (restart) {
			glc.setCurrentGameState(GameState.LOADING);
			glc.init();
			this.gameLost = false;
		}
	}

	public int getFramePerSecond() {
		return framePerSecond;
	}

	public void setFramePerSecond(int framePerSecond) {
		this.framePerSecond = framePerSecond;
	}
	
	public void togglePaused() {
		if(this.isPaused) {
			this.isPaused = false;
			glc.setCurrentGameState(this.oldState);
		} else {
			this.isPaused = true;
			this.oldState = glc.getCurrentGameState();
			glc.setCurrentGameState(GameState.PAUSED);
		}
	}
}
