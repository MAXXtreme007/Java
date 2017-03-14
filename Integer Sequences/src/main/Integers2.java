package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 PURPOSE
 The program will calculate the sum of
 every integer from a given file

 GET INPUT
 The program will get the input from
 a text file named: input.txt
 This will require no input from the user

 PROCESSING
 Create a for loop that will continue adding integers
 until it reaches the last integer

 DISPLAY OUTPUT
 Rather than displaying a message with the calculated
 total sum back to the user, this program will output
 the calculated total sum to an output.txt file.

 Also with this lab, this program has a generated javadoc file.
 */

/**
 * 2-27-15 This class project computes the total sum of all integers in a given
 * input.txt file, and will output the total sum to an output.txt file.
 *
 * @author MAXXtreme
 *
 */

public class Integers2 {
	/**
	 * Main entry point of the program.
	 * 
	 * @param args
	 * 
	 * This is the main method and contains all the user input fields
	 * 
	 * @throws FileNotFoundException
	 * 
	 * This makes an exception if there is no file found.
	 */

	public static void main(final String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("input.txt"));
		PrintWriter output = new PrintWriter("output.txt");

		// get number of pairs from input.txt and display output
		int numberOfPairs = input.nextInt();
		output.println("You have " + numberOfPairs
				+ " pairs of numbers to calculate the summation.");
		output.println("The summations of the pairs of numbers are: ");

		// processing
		for (int x = 0; x < numberOfPairs; x++) {
			// get integers to calculate from input.txt
			double firstSumNumber = input.nextDouble();
			double lastSumNumber = input.nextDouble();
			double totalSum = 0;

			for (double walker = firstSumNumber;
					walker <= lastSumNumber; walker++) {
				totalSum += walker;
			}

			// display output
			output.println(x + 1 + ") " + totalSum);
		}

		// free up resources
		input.close();
		output.close();
	}
}