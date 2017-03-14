package main;

import java.util.Scanner;

/*
 * The Presentation Class
 * 
 * INPUT:
 * In this lab, the presentation class receives all user input.
 * First, this program will ask the user for the total number of flips given.
 * Second, the program will ask the user for how many heads he wishes to get.
 * 
 * OUTPUT:
 * The presentation class will take all the user values for the
 * number of flips and number of heads and give them to the
 * business class to be calculated. After the probability is calculated,
 * this presentation class will display a message with the calculated
 * resistance back to the user.
 */

/**
 * This presentation portion of the program records the values of how many
 * tosses there will be and how many heads the user wishes to get, and then the
 * business portion of the program will calculate the probability of that event
 * happening. After the probability is calculated, this presentation class
 * displays the value to the user.
 * 
 * This program contains the presentation class along with an attached javadoc
 * file.
 * 
 * @author MAXXtreme
 *
 */

public class Presentation {
	/**
	 * Main entry point of the program.
	 * 
	 * @param args
	 * 
	 *            This is the main method and contains all the user input fields
	 */
	public static void main(final String[] args) {
		// tell the user what the purpose of the program is
		System.out.println("Purpose: "
				+ "This program will calculate the probability "
				+ "of getting a user-defined number of heads "
				+ "from a certain number of tosses.");
		inputs();
	}

	/**
	 * This is the method allows the user to input the values for the number of
	 * flips and the desired amount of heads.
	 */
	public static void inputs() {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		Probability coinToss = new Probability();

		// ask the user for the number of flips and heads
		System.out.println("Please enter how many times "
				+ "you will flip the coin.");
		coinToss.totalFlips = keyboard.nextInt();
		// error if flips is less than zero
		while (coinToss.totalFlips < 0) {
			System.out
					.println("Error! Please enter a value greater than zero.");
			System.out.println("Please enter how many times "
					+ "you will flip the coin.");
			coinToss.totalFlips = keyboard.nextInt();
		}
		System.out.println("Please enter how many heads you wish to get.");
		coinToss.headsFlipped = keyboard.nextInt();
		// error if heads is less than zero
		while (coinToss.headsFlipped < 0) {
			System.out.println("Error! Please enter a value greater than 0.");
			System.out.println("Please enter how many heads you wish to get.");
			coinToss.headsFlipped = keyboard.nextInt();
		}
		// error if heads is greater than flips
		while (coinToss.headsFlipped > coinToss.totalFlips) {
			System.out.println("Error! Please enter less heads than flips.");
			System.out.println("Please enter how many heads you wish to get.");
			coinToss.headsFlipped = keyboard.nextInt();
		}
		// display output
		System.out.println("The probability of getting "
				+ coinToss.headsFlipped + " heads from " + coinToss.totalFlips
				+ " flips is " + coinToss.calculateProbability() + "%.");

		// repeat program prompt
		repeat();

		// free up resources
		keyboard.close();
	}

	/**
	 * This is the method for repeating the program.
	 */
	public static void repeat() {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);

		// repeat program prompt
		System.out.println("Would you like to repeat this program?");
		System.out.println("If yes, press 1");
		System.out.println("If no, press 2");
		int repeat = keyboard.nextInt();

		// loop for wrong input
		while (repeat < 1 || repeat > 2) {
			System.out.println("Error! Please press 1 or 2.");
			repeat = keyboard.nextInt();
		}
		if (repeat == 1) {
			inputs();
		}
		if (repeat == 2) {
			System.out.println("Thank you for using this program.");
		}

		// free up resources
		keyboard.close();
	}
}