package view;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import control.GameLoopController;

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
 * @author Robert Lee
 * 
 *
 */


public class Game extends JPanel{

	private static final long serialVersionUID = 1L;
	GameLoopController glc = new GameLoopController();
	static Scale scale = new Scale(800, 600, 10);
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(scale.getWidth(), scale.getHeight());
                frame.setFocusable(true);
                frame.getContentPane().add(game);
                frame.setVisible(true);
                            
                
            }
        });
		// loop
		game.start();
	}
	/**
	 * Main game loop. Calls repaint ever tic and the game loop
	 * controller loop function to start all of the models logic methods
	 */
	
	public void start() {
		while(true) {
			repaint();
			glc.loop();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
        glc.render(g, scale.getGridSize());
        scale.render(g);
	}
	
}
