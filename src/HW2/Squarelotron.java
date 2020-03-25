package HW2;

public class Squarelotron {
	
	public int[][] squarelotron;
	public int size;
	
	public Squarelotron(int n) {
		
		// sets the size instance variable to be n
		this.size = n;
		int value = 1;
		
		// fill the 2 dimensional array with the numbers 1 to n squared, in order
		squarelotron = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.squarelotron[i][j] = value;
				value++;
			}
		}
		
	}
	
	/*
	 * Extract method to check the on ring functionality 
	 */
	protected boolean isOnRing(int i, int j, int ring) {
		
		boolean isOn = (i == ring - 1 || i == size - ring || j == ring -1 || j == size - ring);
		boolean isOut = (i <= ring - 2 || i >= size + 1 - ring || j <= ring - 2 || j >= size + 1 - ring);
		
		return isOn && !isOut;
			
	}
	
	/*
	 * performs the Upside-Down flip of the squarelotron, and returns the new squarelotron
	 * The original squarelotron should not be modified
	 */
	public Squarelotron upsideDownFlip(int ring) {
		
		Squarelotron upsideDownFlip = new Squarelotron(this.size);
		
		for ( int i = 0; i < size; i++) 
			for (int j = 0; j < size; j++)
				if (isOnRing(i, j, ring))
					upsideDownFlip.squarelotron[i][j] = 
						this.squarelotron[size - 1 - i][j];
		
		return upsideDownFlip;
	}
	
	/*
	 * performs the Main Diagonal Flip of the squrelotron, and returns new squarelotron.
	 * The original squarelotron should not be modified
	 */
	public Squarelotron mainDiagonalFlip(int ring) {
		
		Squarelotron mainDiagonalFlip = new Squarelotron(this.size);
		
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j ++)
				if (isOnRing(i, j, ring))
					mainDiagonalFlip.squarelotron[i][j] = this.squarelotron[j][i];
		
		return mainDiagonalFlip;
					
	}
	
	/*
	 * indicates the number of times the entire squarelotron should be rotated 90 degress clockwise.
	 * Any integer, including zero and negative integers, is allowed as the argument
	 * This method modifies the internal representation of the squarelotron; it does not create a new 
	 * squarelotron
	 */
	public void rotateRight(int numberOfTurns) {
		
		// using modulus to obtain the number of turns 
		numberOfTurns = (numberOfTurns % 4 + 4) % 4;
		Squarelotron newSquarelotron = new Squarelotron(size);
		
		for (int numTurns = 0; numTurns < numberOfTurns; numTurns++) {
			for (int i = 0; i < size; i ++)
				for (int j = 0; j < size; j++) {
					newSquarelotron.squarelotron[j][size - 1 - i] = squarelotron[i][j];
				}
			this.squarelotron = newSquarelotron.squarelotron;
		}
	}
}
