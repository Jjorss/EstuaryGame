package view;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import control.GameLoopController;


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

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        glc.render(g, scale.getGridSize());
        scale.render(g);
	}
	
}
