package main;

import java.util.Scanner;

/*
 * The Presentation Class
 * 
 * INPUT:
 * In this lab, the presentation class receives all user input.
 * The user will define both the mass and velocity of a given object.
 * 
 * OUTPUT:
 * The presentation class will take the calculated momentum and 
 * kinetic energy from the business class, which will then be 
 * displayed back to the user in the presentation class.
 */

/**
 * The mass and velocity of the object are defined in the presentation class,
 * and those values are used to calculate both the momentum and 
 * kinetic energy of the object in the business logic portion of the program.
 * The calculated momentum and kinetic energy are then displayed to the user
 * in the presentation class.
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
	 * This is the main method and contains all the user input fields
	 */
	public static void main(final String[] args) {
		// tell the user what the purpose of the program is
		System.out.println("Purpose: "
				+ "This program will calculate the momentum and kinetic "
				+ "energy of an object given the mass and velocity.");
		inputs();
	}

	/**
	 * This is the method allows the user to input the mass and velocity.
	 */
	public static void inputs() {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		Newton values = new Newton();

		// ask the user for the mass of the object
		System.out.println("What is the mass of the object?");
		values.setMass(keyboard.nextDouble());
		// ask the user for the velocity of the object
		System.out.println("What is the velocity of the object?");
		values.setVelocity(keyboard.nextDouble());
		
		// display output
		System.out.println();
		System.out.println("Given an object with a mass of " + values.getMass()
				+ " and a velocity of " + values.getVelocity());
		System.out.println("Momentum is " + values.calculateMomentum());
		System.out.println("Kinetic energy is " + values.calculateEnergy());

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