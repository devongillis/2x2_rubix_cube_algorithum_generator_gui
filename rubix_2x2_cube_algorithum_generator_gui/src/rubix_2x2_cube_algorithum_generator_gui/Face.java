package rubix_2x2_cube_algorithum_generator_gui;

import java.awt.Graphics;

import rubix_2x2_cube_algorithum_generator_gui.Resources.Image;





public class Face {
	
	public int posX, posY;
	public Image[][] images = new Image[2][2]; // the image used for rendering
	public int size = 48;
	
	public Face(int posX, int posY, Image image) {
		this.posX = posX;
		this.posY = posY;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				images[i][j] = image;
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				g.drawImage(Resources.getImage(images[i][j]), posX + i * size, posY + j * size, size, size, null);
			}
		}
	}
}
