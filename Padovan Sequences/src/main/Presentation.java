package main;
import java.util.Scanner;

/*
 * Business Logic
 * 
 * PROPERTIES:
 * Include all the variables
 * Add mutators and accessors to keep variables private
 * 
 * PROCESSING:
 * Use recursion to calculate the sum of all terms to the nth term.
 * This is done by initializing the first three terms: 1, 1, 1
 * The formula to calculate the nth term is as follows:
 * P(n) = P(n-2) + P(n-3)
 * 
 * Presentation Class
 * 
 * INPUT:
 * Allow the user to determine what term to calculate.
 * This term is the nth term which will then be used to calculate
 * the sum using the Padovan Sequence formula. 
 * 
 * OUTPUT:
 * Display a message with the calculated sum back to the user.
 */

/**
 * This program allows the user to compute a user defined nth term
 * in the Padovan Sequence.
 * 
 * @author MAXXtreme
 */

public class Presentation {
	/**
	 * Main entry point of the program.
	 * 
	 * @param args
	 * 
	 * This is the main method and contains all the user input fields
	 */
	public static void main(final String[] args) {
		// tell the user what the purpose of the program is
		System.out.println("Purpose: "
				+ "This program will calculate the sum of "
				+ "all terms to a user defined nth term.");
		sequence();
	}
	
	/**
	 * This method is used to ask the user which term to calculate as well as
	 * displaying the calculated sum.
	 */
	public static void sequence() {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		// call values from business class
		Padovan seq = new Padovan();
		
		// ask user which term to calculate
		System.out.println("What term of the Padovan Sequence "
				+ "would you like to calculate?");
		seq.setTermNumber(keyboard.nextLong());

		// display output
		System.out.println("The value of the " + seq.getTermNumber()
						+ " term of the Padovan sequence is "
						+ seq.getPadovanValue() + ".");
		keyboard.close();
	}
}