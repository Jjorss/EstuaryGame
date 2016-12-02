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
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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


public class Game extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static final int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	boolean started = false;
	boolean init = false;
	boolean dragging = false;
	boolean isPaused = false;
	boolean gameOver = false;
	private boolean restart = false;
	boolean increase = true;
	
	private Point mouseCords = new Point(0,0);
	
	private int fontSize = 190;
	
	
	static Scale scale = new Scale(WIDTH, HEIGHT, 8);
	GameLoopController glc = new GameLoopController(this, scale);
	
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		
		Game game = new Game();
		game.started = true;
		System.out.println(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		EventQueue.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                
                JFrame frame = new JFrame("Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(WIDTH, HEIGHT);
                frame.setFocusable(true);
                frame.getContentPane().add(game);
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
        				if (e.getKeyCode() == KeyEvent.VK_P && !game.gameOver) {
        					System.out.println("PauSED");
        					
        					if (game.isPaused) {
            					game.isPaused = false;
            				} else {
            					game.isPaused = true;
            				}
        					
        				}
        				if (e.getKeyCode() == KeyEvent.VK_R && !game.isPaused) {
        					game.restart = true;
        				}
        				System.out.println("IM PRESING A KEY");
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
                scale = new Scale((int)game.getBounds().getWidth(), (int)game.getBounds().getHeight(), 8);
                
                System.out.println("started");
                
                
            }
            
        });
		// loop
		
		game.mouseClick(game);
		game.mouseMotion(game);
		System.out.println("called first");
		game.start();
		
	}
	
	public void mouseClick(Game game) {
		game.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseReleased(MouseEvent e) {
                if (game.dragging && !game.isPaused) {
                	if (glc.isRenderDragGabion()) {
                		glc.handlePlaceGabion(e.getPoint());
                	}
                	if (glc.isRenderDragPlant()) {
                		glc.handlePlacePlant(e.getPoint());
                	}
                	game.dragging = false;
                } 
                
            }
			@Override
			public void mousePressed(MouseEvent e) {
				if (!game.isPaused) {
					glc.handlePressed(e.getPoint());
					//System.out.println("mouse pressed");
				}
			}
		
		});
	}
	public void mouseMotion(Game game) {
		game.addMouseMotionListener(new MouseAdapter() {
					
			@Override
			public void mouseDragged(MouseEvent e) {
				game.setMouseCords(e.getPoint());
				//System.out.println("dragging");
			}
		});
	}
	
	
	
	
	/**
	 * Main game loop. Calls repaint ever tic and the game loop
	 * controller loop function to start all of the models logic methods
	 */
	
	public void start() {
		while(true) {
			if (this.started) {
				this.setInit(true);
				repaint();
				if (!this.isPaused && !this.gameOver) {
					glc.loop();
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
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
        	if (this.gameOver) {
        		if (this.fontSize >= 210) {
        			this.increase = false;
        		} else if (this.fontSize <= 190) {
        			this.increase = true;
        		}
        		if (this.increase) {
        			this.fontSize++;
        		} else {
        			this.fontSize--;
        		}
        		Font f = new Font("Arial", 1, this.fontSize);
        		Font f2 = new Font("Arial", 1, this.fontSize/2);
            	g.setFont(f);
            	g.setColor(Color.WHITE);
            	g.drawString("GAME OVER",(scale.getWidth()/2) - f.getSize()*2 , scale.getHeight()/2);
            	g.setFont(f2);
            	g.drawString("PRESS R TO RESTART",(scale.getWidth()/2) - f2.getSize()*2 , (scale.getHeight()/2) + f.getSize());
        	}
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
			this.gameOver = false;
			this.init = newInit;
			glc.init();
			System.out.println("Initialized");
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

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isRestart() {
		return restart;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}
	
}
