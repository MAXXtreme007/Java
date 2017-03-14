package main1;

import java.util.Scanner;

/*
 PURPOSE:
 This program will calculate the resistance
 of either a series or parallel circuit.

 INPUT:
 Allow the user to define the type of circuit (series or parallel) and the
 resistance of each resistor so that the total resistance can be calculated.
 First, the type of circuit needs to be defined. Once the user
 defines the circuit as a series or a parallel, the appropriate
 formulas can be utilized accordingly.
 Second, the number of resistors needs to be defined. Then the
 resistance of each resistor needs to be defined and stored in an array.

 PROCESSING:
 If the circuit is in series, the total resistance
 is simply the sum of the resistors.
 If the circuit is in parallel, the reciprocal of the total resistance
 is the sum of reciprocals of the individual resistances.

 OUTPUT:
 Display a message with the calculated unknown back to the user

 REPEAT LOOP:
 After the program has calculated the total resistance of the circuit,
 the program will ask the user if he wishes to repeat the program.
 If the user wishes to calculate another unknown, he will press 2.
 If he does not wish to calculate another, he will press 1.
 */

/**
 * This program allows the user to compute the total resistance of either a
 * series or parallel circuit.
 * 
 * This program also has an attached javadoc file.
 * 
 * @author MAXXtreme
 *
 */

public class Circuits1 {
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
				+ "This program will calculate the resistance "
				+ "of either a series or parallel circuit.");
		formula();
	}

	/**
	 * This is the method for determining which circuit formula to use.
	 */
	public static void formula() {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		
		// ask user which type of circuit he wishes to calculate
		System.out.println("What type of circuit do you wish to calculate?");
		System.out.println("Press 1 if you wish to calculate "
				+ "the resistance of a series circuit.");
		System.out.println("Press 2 if you wish to calculate "
				+ "the resistance of a parallel circuit.");

		// save formula for type of circuit
		int formula = keyboard.nextInt();

		// loop for wrong formula
		while (formula < 1 || formula > 2) {
			System.out.println("Error! Please press 1 or 2.");
			formula = keyboard.nextInt();
		}

		// solve resistance for a series circuit
		if (formula == 1) {
			series();
		}

		// solve resistance for a parallel circuit
		if (formula == 2) {
			parallel();
		}
		
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
			formula();
		}
		if (repeat == 2) {
			System.out.println("Thank you for using this program.");
		}
		
		// free up resources
		keyboard.close();
	}

	/**
	 * This is the method for calculating the resistance of a series circuit.
	 */
	public static void series() {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);

		// variables
		int numberOfRes; // number of resistors
		int counter = 0; // counter to display which resistor value to enter

		// tell user what he has chosen
		System.out.println("You have chosen to calculate the "
				+ "resistance of a series circuit.");
		System.out.println("Please enter the number of "
				+ "resistors in the circuit.");
		numberOfRes = keyboard.nextInt();
		System.out.println("Now please enter the resistance of each "
				+ "resistor, pressing enter after each value.");
		
		// create an array to store resistance values
		double[] resistors = new double[numberOfRes];
		
		// loop to display which resistor value to enter
		do {
			counter++;
			System.out.printf("Resistor ");
			System.out.println(numberOfRes - (numberOfRes - counter) + ") ");
			resistors[numberOfRes - 1] = keyboard.nextDouble();
			numberOfRes--;
		} while (numberOfRes > 0);

		// processing
		System.out.println("The total resistance of a series circuit "
				+ "is the sum resistance of all the resistors.");
		
		// total resistance accumulator and loop
		double totalRes = 0;
		for (double walker : resistors) {
			totalRes += walker;
		}
		
		// display output
		System.out.println("Therefore, the total resistance "
				+ "of the circuit is " + totalRes + " ohms.");
		
		// repeat program prompt
		repeat();
		
		// free up resources
		keyboard.close();
	}

	/**
	 * This is the method for calculating the resistance of a parallel circuit.
	 */
	public static void parallel() {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);

		// variables
		int numberOfRes; // number of resistors
		int counter = 0; // counter to display which resistor value to enter

		// tell user what he has chosen
		System.out.println("You have chosen to calculate the "
				+ "resistance of a parallel circuit.");
		System.out.println("Please enter the number of "
				+ "resistors in the circuit.");
		numberOfRes = keyboard.nextInt();
		System.out.println("Now please enter the resistance of each "
				+ "resistor, pressing enter after each value.");
		
		// create an array to store resistance values
		double[] resistors = new double[numberOfRes];
		
		// loop to display which resistor value to enter
		do {
			counter++;
			System.out.printf("Resistor ");
			System.out.println(numberOfRes - (numberOfRes - counter) + ") ");
			resistors[numberOfRes - 1] = 1.0 / keyboard.nextDouble();
			numberOfRes--;
		} while (numberOfRes > 0);

		// processing
		System.out.println("The total resistance of a parallel circuit "
				+ "is the inverse sum resistance of all the resistors.");
		
		// total resistance accumulator and loop
		double totalSum = 0;
		for (double walker: resistors) {
			totalSum += walker;
		}
		double totalRes = 1.0 / totalSum;
		
		// display output
		System.out.println("Therefore, the total resistance "
				+ "of the circuit is " + totalRes + " ohms.");
		
		// repeat program prompt
		repeat();
		
		// free up resources
		keyboard.close();
	}
}