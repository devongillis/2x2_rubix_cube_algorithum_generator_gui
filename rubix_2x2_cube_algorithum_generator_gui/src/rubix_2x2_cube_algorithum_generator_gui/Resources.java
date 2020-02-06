package rubix_2x2_cube_algorithum_generator_gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import rubix_2x2_cube_algorithum_generator_gui.Renderer;

public class Resources {
	
	public static boolean allLoaded = false;
	private static HashMap<Image, BufferedImage> images = new HashMap<Image, BufferedImage>();
	
	
	public static void loadImages() {
		images.put(Image.RED, loadBufferedImage("red.png"));
		images.put(Image.BLUE, loadBufferedImage("blue.png"));
		images.put(Image.WHITE, loadBufferedImage("white.png"));
		images.put(Image.GREEN, loadBufferedImage("green.png"));
		images.put(Image.YELLOW, loadBufferedImage("yellow.png"));
		images.put(Image.ORANGE, loadBufferedImage("orange.png"));
		images.put(Image.POINTER, loadBufferedImage("pointer.png"));
		
		images.put(Image.DARK_ORANGE, loadBufferedImage("dark_orange.png"));
		images.put(Image.DARK_GREEN, loadBufferedImage("dark_green.png"));
		images.put(Image.DARK_YELLOW, loadBufferedImage("dark_yellow.png"));
		allLoaded = true;
	}
	
	public static BufferedImage getImage(Image ID) {
		return images.get(ID);
	}
	
	private static BufferedImage loadBufferedImage(String resource) {
		BufferedImage sheet = null;
		try {
			sheet = ImageIO.read(Resources.class.getResource("/rubix_2x2_cube_algorithum_generator_gui/" + resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedImage image = Renderer.canvas.getGraphicsConfiguration().createCompatibleImage(sheet.getWidth(), sheet.getHeight(), sheet.getTransparency());
		image.getGraphics().drawImage(sheet, 0, 0, sheet.getWidth(), sheet.getHeight(), null);
		return image;
	}
	
	public static enum Image{
		RED,
		BLUE,
		ORANGE,
		WHITE,
		GREEN,
		YELLOW,
		POINTER,
		DARK_ORANGE,
		DARK_GREEN,
		DARK_YELLOW
	}
}
