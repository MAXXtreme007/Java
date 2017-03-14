package main3;
import java.util.Scanner;

/*
 * Business Logic
 * 
 * PROPERTIES:
 * Include all the variables and initialize array lists
 * Add mutators and accessors to keep variables private
 * 
 * PROCESSING:
 * If the circuit is in series, the total resistance
 * is simply the sum of the resistors.
 * If the circuit is in parallel, the reciprocal of the total resistance
 * is the sum of reciprocals of the individual resistances.
 * 
 * Presentation Class
 * 
 * INPUT:
 * Allow the user to define the type of circuit (series or parallel) and the
 * resistance of each resistor so that the total resistance can be calculated.
 * The type of circuit needs to be defined. Once the user
 * defines the circuit as a series or a parallel, the appropriate
 * formulas can be utilized accordingly.
 * 
 * OUTPUT:
 * Display a message with the calculated resistance back to the user.
 */

/**
 * This program allows the user to compute the total resistance of either a
 * series or parallel circuit using an array list.
 * 
 * @author MAXXtreme
 */

public class Presentation3 {
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
		Circuits3 myCircuit = new Circuits3();

		// ask user which type of circuit he wishes to calculate
		System.out.println("What type of circuit do you wish to calculate?");
		System.out.println("Press 1 if you wish to calculate "
				+ "the resistance of a series circuit.");
		System.out.println("Press 2 if you wish to calculate "
				+ "the resistance of a parallel circuit.");

		// save formula for type of circuit
		myCircuit.setFormula(keyboard.nextInt());

		// solve resistance for a series circuit
		if (myCircuit.getFormula() == 1) {
			series();
		}

		// solve resistance for a parallel circuit
		if (myCircuit.getFormula() == 2) {
			parallel();
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
		// call values from business class
		Circuits3 series = new Circuits3();
		// variable for user input
		double userInput;
		// initialize counter variable
		int counter = 0;
		
		// tell user what he has chosen
		System.out.println("You have chosen to calculate the "
				+ "resistance of a series circuit.");
		System.out.println("Please begin entering resistor values.");
		System.out.println("Once you are finished, please enter zero.");

		// enter resistor values
		do {
			counter++;
			System.out.println("Please enter the resistance of resistor "
			+ counter + ": ");
			userInput = keyboard.nextDouble();
			series.setResistance(userInput);
		} while (userInput != 0);
		// remove entry when entry equals 0
		if (userInput == 0) {
			series.getResistance().remove(series.getResistance().size() - 1);
		}
				
		// display output
		int size = series.getResistance().size();
		System.out.println("The total resistance of the " + size
				+ " resistors in this series circuit is " 
				+ series.calculateSeries() + " ohms.");

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
		// call values from business class
		Circuits3 parallel = new Circuits3();
		// variable for user input
		double userInput;
		// initialize counter variable
		int counter = 0;
		
		// tell user what he has chosen
		System.out.println("You have chosen to calculate the "
				+ "resistance of a parallel circuit.");
		System.out.println("Please begin entering resistor values.");
		System.out.println("Once you are finished, please enter zero.");
		
		// enter resistor values
		do {
			counter++;
			System.out.println("Please enter the resistance of resistor "
			+ counter + ": ");
			userInput = keyboard.nextDouble();
			parallel.setResistance(userInput);
		} while (userInput != 0);
		// remove entry when entry equals 0
		if (userInput == 0) {
			parallel.getResistance()
					.remove(parallel.getResistance().size() - 1);
		}
		
		// display output
		int size = parallel.getResistance().size();
		System.out.println("The total resistance of the " + size
				+ " resistors in this parallel circuit is "
				+ parallel.calculateParallel() + " ohms.");

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
			formula();
		}
		if (repeat == 2) {
			System.out.println("Thank you for using this program.");
		}

		// free up resources
		keyboard.close();
	}
}