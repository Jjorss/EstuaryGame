package control;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class BufferedImageController {

	int imgWidth;
	int imgHeight;
	private String[] animationPaths = {"img/cordgrass.png", "img/BlueCrab1.png", "img/oysterclump2-01.png", 
			"img/sky.jpg", "img/sun.png", "img/gabionwater2_2-01.png", "img/gabion1.png", 
			"img/gabion2.png", "img/gabion3.png", "img/gabion4.png", "img/gabion5.png",
			"img/wave1.png", "img/wave2.png", "img/wave3.png", "img/gabionfull.png", "img/hand.png",
			"img/cordgrass2.png", "img/cordgrass3.png", "img/BlueCrab2.png","img/BlueCrab3.png",
			"img/concreteWall-01.png", "img/runOff.png", "img/tutorialButton.png", "img/playButton.png", 
			"img/creditsButton.png"};
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> animations = new ArrayList<BufferedImage>();
	private ArrayList<BufferedImage> stringImages = new ArrayList<BufferedImage>();
	
	
	public void loadBufferedImage(){
		for(int i = 0; i < animationPaths.length; i++) {
			images.add(this.createImage(animationPaths[i]));
		}
		//this.createAnimation(6, 2, 325, 433);
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
	}
	public BufferedImage createImage(String path){
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(path));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Rendering in gray skeleton");
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
	
//	public void createAnimation(int index, int frameCount, int imgWidth, int imgHeight) {
//		BufferedImage img = this.images.get(index);
//		ArrayList<BufferedImage> animation = new ArrayList<BufferedImage>();
//		for (int i = 0; i < frameCount; i++) {
//			animation.add(img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight));
//		}
//		this.images.remove(index);
//		this.images.addAll(animation);
//		
//	}
	
	public BufferedImage getImageAtIndex(int i) {
		return images.get(i);
	}
	public ArrayList<BufferedImage> getStringImages() {
		return stringImages;
	}
}
