package main;

import java.util.Scanner;

/*
 PURPOSE:
 The program will calculate the probability of coin tosses.

 INPUT:
 Ask the user for the number of times the coin is flipped
 Save flips
 Ask the user for the number of heads to get
 Save heads

 PROCESSING:
 Return (1/2)^flips * (flips! / (heads! * (flips - heads)!)

 OUTPUT:
 Display a message with the probability of achieving that happening
 */

/**
 * 3-9-15 This class project computes the probability of coin tosses.
 *
 * @author MAXXtreme
 *
 */

public class Challenge6 {
	/**
	 * Main entry point of the program.
	 * 
	 * @param args
	 * 
	 * This is the main method and contains all the user input fields
	 */

	public static void main(final String[] args) {
		Scanner keyboard = new Scanner(System.in);

		// INPUT
		System.out.println("Purpose: This program will calculate the probability of coin tosses.");
		// ask user for number of times the coin is flipped
		System.out.println("How many times would you like to flip the coin?");
		double flips = keyboard.nextDouble();
		// ask user for number of heads to get
		System.out.println("How many heads are you wanting to get?");
		double heads = keyboard.nextDouble();
		// limit the number of heads to be equal to the number of flips
		if (heads > flips) {
			heads = flips;
		}
		
		// PROCESSING
		double probability = 100 * (Factorial(flips) / (Factorial(flips - heads) * Factorial(heads)) * (Math.pow(0.5, flips)));
		
		// OUTPUT
		System.out.println("The probability of getting " + heads + " heads from " + flips + " number of flips, is " + probability + "%.");

		// free up resources
		keyboard.close();
	}
	
	public static int Factorial(double numberToFactorial) {
		int runningProduct = 1;
		for (int n = 1; n <= numberToFactorial; ++n) {
			runningProduct *= n;
		}
		return runningProduct;
	}
}