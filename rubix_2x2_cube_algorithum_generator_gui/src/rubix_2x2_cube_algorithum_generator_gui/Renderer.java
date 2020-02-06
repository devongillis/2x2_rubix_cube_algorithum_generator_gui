package rubix_2x2_cube_algorithum_generator_gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.VolatileImage;

import rubix_2x2_cube_algorithum_generator_gui.Resources.Image;

//import input.InterfaceGUI;

public class Renderer {
	
	private static Frame frame;
	public static Canvas canvas;
	
	private static int canvasWidth = 0;
	private static int canvasHeight = 0;
	
	private static final int GAME_WIDTH = 480; // 256 * 7; // how many units you want displayed horizontally
	private static final int GAME_HEIGHT = 432; // 232 * 7; // how many units you want displayed vertically
	
	private static final float screenRatio = 0.8f;
	
	private static long lastFpsCheck = 0;
	private static int currentFPS = 0;
	private static int frameCount = 0;
	private static int targetFPS = 30;
	private static int targetTime = 1000000000 / targetFPS;
	
	private static boolean continu = true;
	
	private static void getBestSize() {
		Toolkit tookKit = Toolkit.getDefaultToolkit();
		Dimension screenSize = tookKit.getScreenSize();
		
		float w = (float)screenSize.width/GAME_WIDTH;
		float h = (float)screenSize.height/GAME_HEIGHT;
		
		if(w < h) {
			canvasWidth = (int) (GAME_WIDTH * w * screenRatio);
			canvasHeight = (int) (GAME_HEIGHT * w * screenRatio);
		}
		else {
			canvasWidth = (int) (GAME_WIDTH * h * screenRatio);
			canvasHeight = (int) (GAME_HEIGHT * h * screenRatio);
		}
	}
	
	public static void init() {
		getBestSize();
		
		frame = new Frame();
		canvas = new Canvas();
		
		canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
	
		frame.add(canvas);
		
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		
		
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Main.quit();
			}
		});
		
		frame.setVisible(true);
		
		canvas.addKeyListener(new Input());
		
		startRendering();
	}
	
	private static void startRendering() {
		Thread thread = new Thread() {
			public void run() {
				
				GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
				VolatileImage vImage = gc.createCompatibleVolatileImage(GAME_WIDTH, GAME_HEIGHT);
				
				while(continu) {
					long startTime = System.nanoTime();
					
					//FPS counter
					frameCount++;
					if(System.nanoTime() > lastFpsCheck + 1000000000) {
						lastFpsCheck = System.nanoTime();
						currentFPS = frameCount;
						frameCount = 0;
					}
					
					if(vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
						vImage = gc.createCompatibleVolatileImage(GAME_WIDTH, GAME_HEIGHT);
					}
					
					Graphics g = vImage.getGraphics();
					
					g.setColor(new Color(255, 242, 180));
					g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
					//RENDER STUFF
					
					// call update
					// input stuff
					if(Input.getKeyTyped(KeyEvent.VK_RIGHT)) {
						Main.pos += 1;
						if(Main.pos > 23) {
							Main.pos = 0;
						}
					}
					else if(Input.getKeyTyped(KeyEvent.VK_LEFT)) {
						Main.pos -= 1;
						if(Main.pos < 0) {
							Main.pos = 23;
						}
					}
					else if(Input.getKeyTyped(KeyEvent.VK_B)) {
						Main.coords[Main.pos].switchColor(Image.BLUE);
					}
					else if(Input.getKeyTyped(KeyEvent.VK_W)) {
						Main.coords[Main.pos].switchColor(Image.WHITE);
					}
					else if(Input.getKeyTyped(KeyEvent.VK_O)) {
						Main.coords[Main.pos].switchColor(Image.ORANGE);
					}
					else if(Input.getKeyTyped(KeyEvent.VK_G)) {
						Main.coords[Main.pos].switchColor(Image.GREEN);
					}
					else if(Input.getKeyTyped(KeyEvent.VK_R)) {
						Main.coords[Main.pos].switchColor(Image.RED);
					}
					else if(Input.getKeyTyped(KeyEvent.VK_Y)) {
						Main.coords[Main.pos].switchColor(Image.YELLOW);
					}
					else if(Input.getKeyTyped(KeyEvent.VK_ENTER)){
						Main.startSolving();
						frame.dispose();
						return;
						//continu = false;
						//thread.destroy();;
					}
					
					// call render
					Main.front.render(g);
					Main.back.render(g);
					Main.left.render(g);
					Main.right.render(g);
					Main.top.render(g);
					Main.bottom.render(g);
					
					g.drawImage(Resources.getImage(Image.DARK_ORANGE), 192-48*2, 48*3, 48, 48, null);
					g.drawImage(Resources.getImage(Image.DARK_GREEN), 192, 48*5, 48, 48, null);
					g.drawImage(Resources.getImage(Image.DARK_YELLOW), 192, 48*6, 48, 48, null);
					
					g.drawImage(Resources.getImage(Image.POINTER), Main.coords[Main.pos].posX + 16, Main.coords[Main.pos].posY + 16, 16, 16, null);
					
					
					// Draw FPS counter
					g.setColor(Color.lightGray);
					g.drawString(String.valueOf(currentFPS), 2, GAME_HEIGHT - 2);
					
					g.dispose();
					
					g = canvas.getGraphics();
					g.drawImage(vImage, 0, 0, canvasWidth, canvasHeight, null);
					
					g.dispose();
					
					long totalTime = System.nanoTime() - startTime;
					if(totalTime < targetTime) {
						try {
							Thread.sleep((targetTime - totalTime) / 1000000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
				}
			}
		};
		thread.setName("Rendering Thread");
		thread.start();
	}
	
}
