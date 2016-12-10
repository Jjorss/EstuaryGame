package control;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class BufferedImageController implements Serializable {

	private transient ArrayList<BufferedImage> images ;//= new ArrayList<BufferedImage>();

	public BufferedImageController() {
		images = new ArrayList<BufferedImage>();
	}

	int imgWidth;
	int imgHeight;
	private String[] animationPaths = {"img/cordgrass.png", "img/BlueCrab1.png", "img/oysterclump2-01.png", 
			"img/sky.jpg", "img/sun.png", "img/gabion2_1-01.png", "img/gabion1.png", 
			"img/gabion2.png", "img/gabion3.png", "img/gabion4.png", "img/gabion5.png",
			"img/wave1.png", "img/wave2.png", "img/wave3.png", "img/gabionfull.png", "img/hand.png",
			"img/cordgrass2.png", "img/cordgrass3.png", "img/BlueCrab2.png","img/BlueCrab3.png",
			"img/concreteWall-01.png", "img/runOff.png", "img/tutorialButton.png", "img/playButton.png", 
			"img/creditsButton.png", "img/a.png", "img/aPlus.png", "img/b.png", "img/c.png", "img/d.png",
			"img/youWin.png", "img/gabion2_2-01.png", "img/gabion2_3-01.png", "img/youLose.png", "img/fail.png"};


	/**
	 * load buffered image
	 */
	public void loadBufferedImage(){
		if (images == null) {
			images = new ArrayList<BufferedImage>();
		}
		for(int i = 0; i < animationPaths.length; i++) {
			images.add(this.createImage(animationPaths[i]));
		}
	}

	/**
	 * Takes path of an image file and returns that file as a buffered image
	 * @param string path of the file
	 * @return buffered image of the inputed image file
	 */
	public BufferedImage createImage(String path){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(path));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
//			System.out.println("Rendering in gray skeleton");
			bufferedImage = new BufferedImage(50,50,BufferedImage.TYPE_INT_RGB);
			Color c = Color.LIGHT_GRAY;
			for (int i = 0; i < bufferedImage.getWidth(); i++) {
				for (int j = 0; j < bufferedImage.getHeight(); j++) {
					bufferedImage.setRGB(i, j, c.getRGB());
				}
			}
			return bufferedImage;
		}

	}

	/**
	 * Gets a specified image from a buffered image array
	 * @param int i index of image to return
	 * @return buffered image
	 */
	public BufferedImage getImageAtIndex(int i) {
		return images.get(i);
	}

}
