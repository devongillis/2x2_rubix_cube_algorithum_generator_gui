package rubix_2x2_cube_algorithum_generator_gui;

public class RubixCube {
	
	public char[][] front;
	public char[][] back;
	public char[][] left;
	public char[][] right;
	public char[][] top;
	public char[][] bottom;
	
	
	// to visualize the lay out the squares are arranged like a unfolded lower case
	// t pose with the front in the cross hairs, the chars are read left to right, 
	// top to bottom
	
	//     [to]
	// [le][fr][ri]
	//     [bo]
	//     [ba]
	
	public RubixCube () {
		
	}
	
	public void setConditions(char[][] front, char[][] back, char[][] left, char[][] right, char[][] top, char[][] bottom) {
		this.front = front;
		this.back = back;
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}
	
	// each face can be rotated based of a value range of 1,2,3 each representing a 90, 180, 270 degree
	// turn clockwise
	
	public void rotateFace(int face, int degree) {
		if(face == 0) {
			rotateFront(degree);
		}
		else if(face == 1) {
			rotateTop(degree);
		}
		else if(face == 2) {
			rotateRight(degree);
		}
	}
	
	public void rotateFront(int degree) { // degree is just 1, 2, 3
		char[] tempSide = new char[2];
		if(degree == 1) {
			tempSide[0] = top[1][0];
			tempSide[1] = top[1][1];
			
			top[1][0] = left[1][1];
			top[1][1] = left[0][1];
			
			left[1][1] = bottom[0][1];
			left[0][1] = bottom[0][0];
			
			bottom[0][1] = right[0][0];
			bottom[0][0] = right[1][0];
			
			right[0][0] = tempSide[0];
			right[1][0] = tempSide[1];
			
			char tempCorner = front[0][0];
			front[0][0] = front[1][0];
			front[1][0] = front[1][1];
			front[1][1] = front[0][1];
			front[0][1] = tempCorner;
		}
		if(degree == 2) {
			tempSide[0] = top[1][0];
			tempSide[1] = top[1][1];
			
			top[1][0] = bottom[0][1];
			top[1][1] = bottom[0][0];
			
			bottom[0][1] = tempSide[0];
			bottom[0][0] = tempSide[1];
			
			tempSide[0] = left[1][1];
			tempSide[1] = left[0][1];
			
			left[1][1] = right[0][0];
			left[0][1] = right[1][0];
			
			right[0][0] = tempSide[0];
			right[1][0] = tempSide[1];
			
			char tempCorner = front[0][0];
			front[0][0] = front[1][1];
			front[1][1] = tempCorner;
			
			tempCorner = front[0][1];
			front[0][1] = front[1][0];
			front[1][0] = tempCorner;
		}
		if(degree == 3) {
			tempSide[0] = top[1][0];
			tempSide[1] = top[1][1];
			
			top[1][0] = right[0][0];
			top[1][1] = right[1][0];
			
			right[0][0] = bottom[0][1];
			right[1][0] = bottom[0][0];
			
			bottom[0][1] = left[1][1];
			bottom[0][0] = left[0][1];
			
			left[1][1] = tempSide[0];
			left[0][1] = tempSide[1];
			
			char tempCorner = front[0][0];
			front[0][0] = front[0][1];
			front[0][1] = front[1][1];
			front[1][1] = front[1][0];
			front[1][0] = tempCorner;
		}
	}
	
	public void rotateTop(int degree) { // degree is just 1, 2, 3
		char[] tempSide = new char[2];
		if(degree == 1) {
			tempSide[0] = back[1][0];
			tempSide[1] = back[1][1];
			
			back[1][0] = left[0][1];
			back[1][1] = left[0][0];
			
			left[0][1] = front[0][1];
			left[0][0] = front[0][0];
			
			front[0][1] = right[0][1];
			front[0][0] = right[0][0];
			
			right[0][1] = tempSide[0];
			right[0][0] = tempSide[1];
			
			char tempCorner = top[0][0];
			top[0][0] = top[1][0];
			top[1][0] = top[1][1];
			top[1][1] = top[0][1];
			top[0][1] = tempCorner;
		}
		if(degree == 2) {
			tempSide[0] = back[1][0];
			tempSide[1] = back[1][1];
			
			back[1][0] = front[0][1];
			back[1][1] = front[0][0];
			
			front[0][1] = tempSide[0];
			front[0][0] = tempSide[1];
			
			tempSide[0] = left[0][1];
			tempSide[1] = left[0][0];
			
			left[0][1] = right[0][1];
			left[0][0] = right[0][0];
			
			right[0][1] = tempSide[0];
			right[0][0] = tempSide[1];
			
			char tempCorner = top[0][0];
			top[0][0] = top[1][1];
			top[1][1] = tempCorner;
			
			tempCorner = top[0][1];
			top[0][1] = top[1][0];
			top[1][0] = tempCorner;
		}
		if(degree == 3) {
			tempSide[0] = back[1][0];
			tempSide[1] = back[1][1];
			
			back[1][0] = right[0][1];
			back[1][1] = right[0][0];
			
			right[0][1] = front[0][1];
			right[0][0] = front[0][0];
			
			front[0][1] = left[0][1];
			front[0][0] = left[0][0];
			
			left[0][1] = tempSide[0];
			left[0][0] = tempSide[1];
			
			char tempCorner = top[0][0];
			top[0][0] = top[0][1];
			top[0][1] = top[1][1];
			top[1][1] = top[1][0];
			top[1][0] = tempCorner;
		}
	}

	public void rotateRight(int degree) { // degree is just 1, 2, 3
		char[] tempSide = new char[2];
		if(degree == 1) {
			tempSide[0] = top[1][1];
			tempSide[1] = top[0][1];
			
			top[1][1] = front[1][1];
			top[0][1] = front[0][1];
			
			front[1][1] = bottom[1][1];
			front[0][1] = bottom[0][1];
			
			bottom[1][1] = back[1][1];
			bottom[0][1] = back[0][1];
			
			back[1][1] = tempSide[0];
			back[0][1] = tempSide[1];
			
			char tempCorner = right[0][0];
			right[0][0] = right[1][0];
			right[1][0] = right[1][1];
			right[1][1] = right[0][1];
			right[0][1] = tempCorner;
		}
		if(degree == 2) {
			tempSide[0] = top[1][1];
			tempSide[1] = top[0][1];
			
			top[1][1] = bottom[1][1];
			top[0][1] = bottom[0][1];
			
			bottom[1][1] = tempSide[0];
			bottom[0][1] = tempSide[1];
			
			tempSide[0] = back[1][1];
			tempSide[1] = back[0][1];
			
			back[1][1] = front[1][1];
			back[0][1] = front[0][1];
			
			front[1][1] = tempSide[0];
			front[0][1] = tempSide[1];
			
			char tempCorner = right[0][0];
			right[0][0] = right[1][1];
			right[1][1] = tempCorner;
			
			tempCorner = right[0][1];
			right[0][1] = right[1][0];
			right[1][0] = tempCorner;
		}
		if(degree == 3) {
			tempSide[0] = top[1][1];
			tempSide[1] = top[0][1];
			
			top[1][1] = back[1][1];
			top[0][1] = back[0][1];
			
			back[1][1] = bottom[1][1];
			back[0][1] = bottom[0][1];
			
			bottom[1][1] = front[1][1];
			bottom[0][1] = front[0][1];
			
			front[1][1] = tempSide[0];
			front[0][1] = tempSide[1];
			
			char tempCorner = right[0][0];
			right[0][0] = right[0][1];
			right[0][1] = right[1][1];
			right[1][1] = right[1][0];
			right[1][0] = tempCorner;
		}
	}
}
