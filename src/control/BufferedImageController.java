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
	String[] animationPaths = {"img/cordgrass.png", "img/crab.png", "img/oyster.png", "img/sky.jpg", 
			"img/sun.png", "img/wave.png", "img/handVector.jpg"};
	ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> animations = new ArrayList<BufferedImage>();
	
	public void loadBufferedImage(){
		for(int i = 0; i < animationPaths.length; i++) {
			images.add(this.createImage(animationPaths[i]));
		}
		this.createAnimation(6, 2, 325, 433);
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
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
	
	public void createAnimation(int index, int frameCount, int imgWidth, int imgHeight) {
		BufferedImage img = this.images.get(index);
		ArrayList<BufferedImage> animation = new ArrayList<BufferedImage>();
		for (int i = 0; i < frameCount; i++) {
			animation.add(img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight));
		}
		this.images.remove(index);
		this.images.addAll(animation);
		
	}
	
	public BufferedImage getImageAtIndex(int i) {
		return images.get(i);
	}
}
