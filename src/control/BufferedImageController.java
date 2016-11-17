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
	String[] animationPaths = {"img/cordgrass.png", "img/oyster.png", "img/sky.jpg", "img/sun.png", "img/wave.png"};
	ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	public void loadBufferedImage(){
		for(String path : animationPaths) {
			images.add(this.createImage(path));
		}
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
	public ArrayList<BufferedImage> getImages() {
		return images;
	}
}
