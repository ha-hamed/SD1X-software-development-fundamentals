package HW1;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class WhackAMole {

	int score = 0;
	int molesLeft = 0;
	int attemptsLeft;
	char[][] moleGrid;
	
	public WhackAMole(int numAttempts, int gridDimension) {
		
		this.attemptsLeft = numAttempts;
		this.moleGrid = new char[gridDimension][gridDimension];
		
		for (char[] character : moleGrid)
			Arrays.fill(character, '*'); // fill the grid initially with *
		
	}
	
	/*
	 * Given a location
	 * place a mole at that location
	 * return true if you can
	 */
	public boolean place(int x, int y) {
		
		if (moleGrid[x][y] == 'M')
			return false;
		
		moleGrid[x][y] = 'M';
		molesLeft += 1;
		
		return true;
		
	}
	
	/*
	 * Given a location
	 * take a whack at that location
	 * if that location contains a mole, the score, number of attempts left, and 
	 * moles left is updated
	 * if that location does not contain a mole, only attemptsLeft is updated
	 */
	public void whack(int x, int y) {
		
		if (moleGrid[x][y] == 'M') {
			score++;
			molesLeft--;
			moleGrid[x][y] = 'W';
		}
		
		attemptsLeft--;
	}
	
	/*
	 * Print the grid without shown where the moles are
	 * For every spot that has recorded a "whacked mole", print out a W, M, or * otherwise
	 */
	public void printGridToUser() {
		
	   for (char[] moleGrid : moleGrid) {
	        for (char character : moleGrid) {
	            if (character == 'W') 
	                System.out.print(character + " ");
	            else if (character == 'M')
	                System.out.print(character + " ");
	            else
	            	System.out.print(character + " ");
	        }
	        System.out.print("\n");
	    }
	}
	
	/*
	 * Print the grid completely
	 * This is effectively dumping the 2nd array on to the screen. The places where the moles 
	 * are should be printed as an 'M'. The places where the moles have been whacked should be 
	 * printed as a 'W'. The places that don't have a mole should be printed as '*'.
	 */
	public void printGrid() {
		for (char[] moleGrid : moleGrid) {
            for (char character: moleGrid) {
                System.out.print(character + " ");
            }
            System.out.println();
        }	
	}
	
	/*
	 * Main method
	 * Begin by creating a 10 by 10 grid where you randomly place the moles. Place a total of 10 moles
	 * Now allow the user to enter the x and y coordinates of where they would like to have a whack.
	 * tell them they have a maximum of 50 attempts to get all the moles.
	 * At any point of the game, they can input coordinates -1, -1 in order to indicate that they 
	 * are giving up. If the user gives up they get to see the entire grid
	 * The game ends if the user is able to uncover all the moles or if the user runs out of attempts
	 */
	public static void main(String[] args) {
		
		int gridDimension = 10;
		WhackAMole whackAMole = new WhackAMole(50, gridDimension);
		
		// generate random number
		Random randomNum = new Random();
		
		for (int i = 0; i < gridDimension; i++)
			whackAMole.place(randomNum.nextInt(gridDimension), randomNum.nextInt(gridDimension));
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Starting WhackAMole game..you have 50 attempts to get all the moles. Good luck! \n");
		
		do {
		
			System.out.println("Score: " + whackAMole.score);
			System.out.println("Attempts left: " + whackAMole.attemptsLeft);

			// to know where the moles were placed in the grid
			// whackAMole.printGridToUser();

			System.out.println("Where do you like to whack, enter positive x and y seperated by space please");
			System.out.println("if you like to quit enter -1 for both x and y");
			System.out.print("Input: ");
			
			try {
				
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				
				if (x == -1 && y == -1) {
					System.out.println("You gave up ):");
					whackAMole.printGrid();
					return;
				}
				
				if (x >= 0 && x <= 9 && y >= 0 && y <= 9) 
					whackAMole.whack(x, y);
				else 
					System.out.println("Wrong inputs, please try again to input values between 0 to 9 as the grid dimenstion is 10 by 10");
			} catch(InputMismatchException e) {
		       System.out.println("Mismatch occured, try again please..");
		       scanner.nextLine();
			}
			
		}
		while (whackAMole.attemptsLeft > 0 && whackAMole.molesLeft > 0);

		scanner.close();
					
		if (whackAMole.attemptsLeft == 0) 
			System.out.println("Attempts left: " + whackAMole.attemptsLeft + "\n Game over! Perhaps next time you get luck! (: ");		
		else {
			System.out.println("No more moles left, YOU WON! :D ");
			whackAMole.printGrid();
		}
			
	}
	
}
