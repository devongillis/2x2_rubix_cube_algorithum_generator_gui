package rubix_2x2_cube_algorithum_generator_gui;

import rubix_2x2_cube_algorithum_generator_gui.Resources.Image;

public class Main {
	
	public static Face front, back, left, right, top, bottom;
	
	public static Box[] coords = new Box[24];
	public static int pos = 0;
	
	public static RubixCube cube;
	public static boolean found = false;
	public static boolean endAfterFirst = true;
	
	public static void main(String[] args) {
		
		top = new Face(192, 0, Image.BLUE);
		coords[0] = new Box(192, 0, 0, 0, top);coords[1] = new Box(192 + 48, 0, 1, 0, top);
		coords[2] = new Box(192, 48, 0, 1, top);coords[3] = new Box(192 + 48, 48, 1, 1, top);
		
		left = new Face(192-48*2, 48*2, Image.ORANGE);
		coords[4] = new Box(192-48*2, 48*2, 0, 0, left);coords[5] = new Box(192 - 48, 48*2, 1, 0, left);
		coords[10] = new Box(192-48*2, 48*3, 0, 1, left);coords[11] = new Box(192 - 48, 48*3, 1, 1, left);
		
		front = new Face(192, 48*2, Image.WHITE);
		coords[6] = new Box(192, 48*2, 0, 0, front);coords[7] = new Box(192 + 48, 48*2, 1, 0, front);
		coords[12] = new Box(192, 48*3, 0, 1, front);coords[13] = new Box(192 + 48, 48*3, 1, 1, front);
		
		right = new Face(192+48*2, 48*2, Image.RED);
		coords[8] = new Box(192+48*2, 48*2, 0, 0, right);coords[9] = new Box(192+48*3, 48*2, 1, 0, right);
		coords[14] = new Box(192+48*2, 48*3, 0, 1, right);coords[15] = new Box(192+48*3, 48*3, 1, 1, right);
		
		bottom = new Face(192, 48*4, Image.GREEN);
		coords[16] = new Box(192, 48*4, 0, 0, bottom);coords[17] = new Box(192 + 48, 48*4, 1, 0, bottom);
		coords[18] = new Box(192, 48*5, 0, 1, bottom);coords[19] = new Box(192 + 48, 48*5, 1, 1, bottom);
		
		back = new Face(192, 48*6, Image.YELLOW);
		coords[20] = new Box(192, 48*6, 0, 0, back);coords[21] = new Box(192 + 48, 48*6, 1, 0, back);
		coords[22] = new Box(192, 48*7, 0, 1, back);coords[23] = new Box(192 + 48, 48*7, 1, 1, back);
		
		
		Renderer.init();
		Resources.loadImages();
	}
	
	public static void quit() {
		System.exit(0);
	}
	
	public static class Box{
		
		int posX, posY, x, y;
		Face face;
		
		public Box(int posX, int posY, int x, int y, Face face) {
			this.posX = posX;
			this.posY = posY;
			this.x = x;
			this.y = y;
			this.face = face;
		}
		
		public void switchColor(Image image) {
			if(pos != 10 && pos != 18 && pos != 20)
			face.images[x][y] = image;
		}
	}
	
	public static char getChar(Image image) {
		if(image == Image.BLUE) {
			return 'b';
		}
		else if(image == Image.ORANGE || image == Image.DARK_ORANGE) {
			return 'o';
		}
		else if(image == Image.YELLOW || image == Image.DARK_YELLOW) {
			return 'y';
		}
		else if(image == Image.GREEN || image == Image.DARK_GREEN) {
			return 'g';
		}
		else if(image == Image.RED) {
			return 'r';
		}
		else {
			return 'w';
		}
		
	}
	
	public static void startSolving() {
		Thread thread = new Thread() {
			public void run() {
				// first convert the gui into proper values for computing
				cube = new RubixCube();
				//boolean found = false;
				
				char[][] front = new char[2][2];
				char[][] back = new char[2][2];
				char[][] left = new char[2][2];
				char[][] right = new char[2][2];
				char[][] top = new char[2][2];
				char[][] bottom = new char[2][2];
				
				front[0][0] = getChar(Main.front.images[0][0]); front[0][1] = getChar(Main.front.images[1][0]);
				front[1][0] = getChar(Main.front.images[0][1]); front[1][1] = getChar(Main.front.images[1][1]);
				
				back[0][0] = getChar(Main.back.images[0][0]); back[0][1] = getChar(Main.back.images[1][0]);
				back[1][0] = getChar(Main.back.images[0][1]); back[1][1] = getChar(Main.back.images[1][1]);
				
				left[0][0] = getChar(Main.left.images[0][0]); left[0][1] = getChar(Main.left.images[1][0]);
				left[1][0] = getChar(Main.left.images[0][1]); left[1][1] = getChar(Main.left.images[1][1]);
				
				right[0][0] = getChar(Main.right.images[0][0]); right[0][1] = getChar(Main.right.images[1][0]);
				right[1][0] = getChar(Main.right.images[0][1]); right[1][1] = getChar(Main.right.images[1][1]);
				
				top[0][0] = getChar(Main.top.images[0][0]); top[0][1] = getChar(Main.top.images[1][0]);
				top[1][0] = getChar(Main.top.images[0][1]); top[1][1] = getChar(Main.top.images[1][1]);
				
				bottom[0][0] = getChar(Main.bottom.images[0][0]); bottom[0][1] = getChar(Main.bottom.images[1][0]);
				bottom[1][0] = getChar(Main.bottom.images[0][1]); bottom[1][1] = getChar(Main.bottom.images[1][1]);
				
				cube.setConditions(front, back, left, right, top, bottom);
				
				
				int cap = 10;
				long time = System.nanoTime();
				
				for(int i = 1; i <= cap; i++) {
					int[][] number = new int[i][2]; // array of x number of moves each with 2 categories which are face and angle
					solve(number.length, number, -1);
				}
				
				if(!found) {
					System.out.println("did not find solution within 10 turns, now using 11");
					int[][] number = new int[11][2];
					solve(number.length, number, -1);
				}
				
				long duration = System.nanoTime() - time;
				float seconds = (float) (duration/1000000000);
				System.out.println(seconds + " seconds " + duration);
				
				
												printLayout(cube.top, true);
				printLayer(cube.left[0], false);printLayer(cube.front[0], false);printLayer(cube.right[0], false);System.out.println();
				printLayer(cube.left[1], false);printLayer(cube.front[1], false);printLayer(cube.right[1], false);System.out.println();
												printLayout(bottom, true);
												printLayout(back, true);
			}
		};
		thread.start();
	}
	
	public static void printLayout(char[][] face, boolean space) {
		for(int i = 0; i < 2; i++) {
			if(space) {
				System.out.print("  ");
			}
			for(int j = 0; j < 2; j++) {
				System.out.print(face[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void printLayer(char[] layer, boolean space) {
		for(int i = 0; i < 2; i++) {
			System.out.print(layer[i]);
		}
	}
	
	public static void solve(int digitsLeft, int[][] number, int previousFace) {
		if(found && endAfterFirst) {
			return;
		}
		digitsLeft -= 1;
		for(int face = 0; face < 3; face++) {
			for(int angle = 1; angle <= 3; angle++) {
				if(face != previousFace) {
					number[number.length - 1 - digitsLeft][0] = face;
					number[number.length - 1 - digitsLeft][1] = angle;
					if(digitsLeft != 0) {
						solve(digitsLeft, number, face);
					}
					else {
						// found our final number now use it to turn puzzle
						for(int i = 0; i < number.length; i++) {
							cube.rotateFace(number[i][0], number[i][1]);
						}
						// now check if the sequence of turns is a solution
						if(checkIfSolved() /*checkIfConditionsMet()*/) {
							found = true;
							for(int i = 0; i < number.length; i++) {
								System.out.print(number[i][0] + "" + number[i][1] + " ");
							}
							System.out.print("     ");
							for(int i = 0; i < number.length; i++) {
								char letter;
								char action;
								if(number[i][0] == 0) {
									letter = 'F';
								}
								else if(number[i][0] == 1) {
									letter = 'U';
								}
								else {
									letter = 'R';
								}
								
								if(number[i][1] == 1) {
									action = ' ';
								}
								else if(number[i][1] == 2) {
									action = '2';
								}
								else {
									action = '\'';
								}
								
								System.out.print(letter + "" + action + " ");
							}
							System.out.println();
						}
						// undo the number
						for(int i = number.length - 1; i >= 0; i--) {
							cube.rotateFace(number[i][0], 4 - number[i][1]);
						}
					}
				}
			}
		}
	}
	
	public static boolean checkIfSolved() {
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				if(cube.front[i][j] != 'w') {
					return false;
				}
				if(cube.left[i][j] != 'o') {
					return false;
				}
				if(cube.right[i][j] != 'r') {
					return false;
				}
				if(cube.top[i][j] != 'b') {
					return false;
				}
				if(cube.bottom[i][j] != 'g') {
					return false;
				}
				if(cube.back[i][j] != 'y') {
					return false;
				}
			}
		}
		return true;
	}
	
}