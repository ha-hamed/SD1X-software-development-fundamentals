package HW2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SquarelotronTest {
	
	private Squarelotron squareTwo = new Squarelotron(2);
	private Squarelotron squareFour = new Squarelotron(4);

	@Test
	public void testSquarelotron(){
				
		assertEquals(2, squareTwo.size);
		assertEquals(4, squareFour.size);

		int [][] expectedTwo = new int[][] {
			{1, 2},
			{3, 4}
		};
		assertArrayEquals(expectedTwo, squareTwo.squarelotron);

		int [][] expectedFour = new int[][] {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10, 11, 12},
			{13, 14, 15, 16}
		};
		assertArrayEquals(expectedFour, squareFour.squarelotron);
	}

	@Test
	void testUpsideDownFlip() {
		
		int[][] expectedTwo = {
				{3, 4},
				{1, 2}
		};
		assertArrayEquals(expectedTwo, squareTwo.upsideDownFlip(1).squarelotron);
		
		int [][] expectedFour = {
			{13, 14, 15, 16},
			{9, 6, 7, 12},
			{5, 10, 11, 8},
			{1, 2, 3, 4}
		};
		assertArrayEquals(expectedFour, squareFour.upsideDownFlip(1).squarelotron);
		
	}
	
	@Test
	void testMainDiagonalFlip() {
		
		int[][] expectedTwo = {
				{1, 3},
				{2, 4}
				
		};
		assertArrayEquals(expectedTwo, squareTwo.mainDiagonalFlip(1).squarelotron);
		
		int [][] expectedFour = {
			{1, 5, 9, 13},
			{2, 6, 7, 14},
			{3, 10, 11, 15},
			{4, 8, 12, 16}
		};
		assertArrayEquals(expectedFour, squareFour.mainDiagonalFlip(1).squarelotron);
	}
	
	@Test
	void testRotateRight() {
		
		int [][] expectedTwo = new int [][] {
			{1, 2},
			{3, 4}
			
		};
		squareTwo.rotateRight(4);
		assertArrayEquals(expectedTwo, squareTwo.squarelotron);
		
		int [][] expectedFour = {
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{9, 10, 11, 12},
				{13, 14, 15, 16}

			};
		squareFour.rotateRight(4);
		assertArrayEquals(expectedFour, squareFour.squarelotron);
	}

}
