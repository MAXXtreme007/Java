package main;

import java.util.Scanner;

/*
 * The Presentation Class
 * 
 * INPUT:
 * In this lab, the presentation class receives all user input.
 * The user will define the initial population, growth/decay rate, and time.
 * 
 * OUTPUT:
 * The presentation class will take the calculated final population size 
 * and display the value to the user.
 */

/**
 * The initial population, growth/decay rate, and time are defined in the
 * presentation class. Those values are used to calculate the final population
 * size in the business logic portion of the program which is then displayed to
 * the user in the presentation class.
 *
 * This program contains the presentation class along with an attached javadoc
 * file.
 *
 * @author MAXXtreme
 *
 */

public class Presentation {
	/**
	 * This variable is a place holder to multiply for a percentage.
	 */
	private static final double HUNDRED = 100;

	/**
	 * Main entry point of the program.
	 *
	 * @param args This is the main method and contains all the user
	 * input fields.
	 */
	public static void main(final String[] args) {
		// tell the user what the purpose of the program is
		System.out.println("Purpose: "
				+ "This program will calculate "
				+ "the population size after a "
				+ "given amount of time with "
				+ "either a growth or decay rate.");
		inputs();
	}

	/**
	 * This is the method allows the user to
	 * input the initial population, rate, and time.
	 */
	public static void inputs() {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		Growth values = new Growth();

		// ask the user for the initial population size
		System.out.println("What is the initial population size?");
		values.setInitialPopulation(keyboard.nextDouble());
		// ask the user for the growth rate
		System.out.println("For growth,"
				+ " enter a value greater than one.");
		System.out.println("For decay,"
				+ " enter a value between zero and one.");
		System.out.println("Please enter"
				+ " the growth/decay rate as a percent.");
		values.setGrowthRate(keyboard.nextDouble());
		// ask the user for the growth rate
		System.out.println("How long does the population "
				+ "experience growth/decay?");
		values.setTime(keyboard.nextDouble());

		// display output
		System.out.println(
				"Given a population with"
				+ " an initial population size of "
				+ values.getInitialPopulation()
				+ ", at a rate of "
				+ (values.getGrowthRate() * HUNDRED)
				+ "%, over a time of "
				+ values.getTime()
				+ " units, the final population size is "
				+ values.calculatePopulation() + "."
		);

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
		System.out.println();
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