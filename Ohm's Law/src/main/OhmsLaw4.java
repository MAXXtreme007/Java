package main;

import java.util.Scanner;

/*
 Purpose:The program will calculate either
 voltage, resistance, or amperage of a circuit
 given an input of two of the three from the user.

 Allow the user to define two known variables
 so that the the unknown can be calculated.
 
 If the user needs to calculate voltage,
 the user will press 1 to declare it as the unknown variable.
 If the user declares voltage as the unknown variable,
 this formula will be used: voltage = amperage * resistance
 The user will then enter in the amperage and resistance values,
 so that voltage can be calculated.
 
  If the user needs to calculate the resistance,
 the user will press 2 to declare it as the unknown variable.
 If the user declares resistance as the unknown variable,
 this formula will be used: resistance = voltage / amperage
 The user will then enter in the voltage and amperage values,
 so that voltage can be calculated.
 
  If the user needs to calculate amperage,
 the user will press 3 to declare it as the unknown variable.
 If the user declares amperage as the unknown variable,
 this formula will be used: amperage = voltage / resistance
 The user will then enter in the voltage and resistance values,
 so that voltage can be calculated.

 Display a message with the calculated unknown back to the user
 
 After the program has calculated the unknown variable for the user,
 the program will ask the user if he wishes to repeat the program.
 If the user wishes to calculate another unknown, he will press 2.
 If he does not wish to calculate another, he will press 1.
 
 Also with this lab, this program has a generated javadoc file
 */

/**
 * This class project allows the user to input two known values so that the
 * unknown value can be calculated.
 * 
 * Also with this lab, the program has an attached javadoc file
 * 
 * @author MAXXtreme
 *
 */

public class OhmsLaw4 {
	/**
	 * Main entry point of the program
	 * 
	 * @param args
	 * 
	 * This is the main method and contains all the user input fields
	 */
	
	public static void main(final String[] args) {
		// give user a choice if he wishes to repeat the program
		int userChoice;
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		do {
			// tell the user what the purpose of the program is
			System.out.println("Purpose: "
					+ "This program will calculate the the unknown "
					+ "value of a circuit given two user-defined values.");
			// variables
			double voltage;
			double resistance;
			double amperage;
			int formula;
			
			// ask user which unknown he wishes to calculate
			System.out.println("Please enter the number "
					+ "corresponding to what you wish to calculate: "
					+ "1) Voltage " + "2) Resistance " + "3) Amperage");

			// save formula
			formula = keyboard.nextInt();

			// solve for voltage
			if (formula == 1) {
				// tell user what he has chosen
				System.out.println("You have chosen to calculate voltage.");

				// ask user for amperage
				System.out.println("Please enter amperage: ");
				// save amperage
				amperage = keyboard.nextDouble();

				// ask user for resistance
				System.out.println("Please enter resistance: ");
				// save resistance
				resistance = keyboard.nextDouble();

				// formula
				voltage = amperage * resistance;
				System.out.println("Given an amperage of " + amperage + ", "
						+ "and a resistance of " + resistance + ", "
						+ "the voltage is: " + voltage + ".");
			}

			// solve for resistance
			if (formula == 2) {
				// tell user what he has chosen
				System.out.println("You have chosen to calculate resistance.");
				
				// ask user for voltage
				System.out.println("Please enter voltage: ");
				// save voltage
				voltage = keyboard.nextDouble();
				
				// ask user for amperage
				System.out.println("Please enter amperage: ");
				// save amperage
				amperage = keyboard.nextDouble();

				// formula
				resistance = voltage / amperage;
				System.out.println("Given an voltage of " + voltage + ", "
						+ "and an amperage of " + amperage + ", "
						+ "the resistance is: " + resistance + ".");
			}
			
			// solve for amperage
			if (formula == 3) {
				// tell user what he has chosen
				System.out.println("You have chosen to calculate amperage.");

				// ask user for voltage
				System.out.println("Please enter voltage: ");
				// save voltage
				voltage = keyboard.nextDouble();

				// ask user for resistance
				System.out.println("Please enter resistance: ");
				// save resistance
				resistance = keyboard.nextDouble();
				
				// formula
				amperage = voltage / resistance;
				System.out.println("Given a voltage of " + voltage + ", "
						+ "and a resistance of " + resistance + ", "
						+ "the amperage is: " + amperage + ".");
			}

			// give user a choice if he wishes to repeat the program
			System.out.println("Do you wish to repeat this program?");
			System.out.println("If no, press 1. If yes, press 2.");
			// save user's choice
			userChoice = keyboard.nextInt();
			
		} while (userChoice == 2);
		
		if (userChoice == 1) {
			System.out.println("Thank you for using this program.");
		}
		
		// free up resources
		keyboard.close();
		
	}
}
