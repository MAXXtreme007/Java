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
 * This program contains the presentation class along with an attached javadoc
 * file.
 * 
 * @author MAXXtreme
 *
 */

public class OhmsLawPresentation {
	/**
	 * Main entry point of the program
	 * 
	 * @param args
	 * 
	 * This is the main method and contains all the user input fields
	 */
	public static void main(final String[] args) {
		// tell the user what the purpose of the program is
		System.out.println("Purpose: "
				+ "This program will calculate the the unknown "
				+ "value of a circuit given two user-defined values.");
		formula();
	}
	
	 /**
	  * This is the method for determining which circuit formula to use.
	 */
	public static void formula () {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		OhmsLawBusiness myCircuit = new OhmsLawBusiness();

		// ask user which unknown he wishes to calculate
		System.out.println("Please enter the number "
				+ "corresponding to what you wish to calculate: "
				+ "1) Voltage " + "2) Resistance " + "3) Amperage");

		// save formula for type of circuit
		myCircuit.formula = keyboard.nextInt();

		// loop for wrong formula
		while (myCircuit.formula < 1 || myCircuit.formula > 3) {
			System.out.println("Error! Please press 1, 2, or 3.");
			myCircuit.formula = keyboard.nextInt();
		}
		
		if (myCircuit.formula == 1) {
			voltage();
		}
		
		if (myCircuit.formula == 2) {
			resistance();	
		}
		
		if (myCircuit.formula == 3) {
			amperage();
		}
		
		// free up resources
		keyboard.close();
	}
	
	/**
	 * This is the method for calculating the voltage of a circuit.
	 */
	public static void voltage () {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		OhmsLawBusiness myCircuit = new OhmsLawBusiness();
				
		// tell user what he has chosen
		System.out.println("You have chosen to calculate voltage.");

		// ask user for amperage
		System.out.println("Please enter amperage: ");
		// save amperage
		myCircuit.amperage = keyboard.nextDouble();

		// ask user for resistance
		System.out.println("Please enter resistance: ");
		// save resistance
		myCircuit.resistance = keyboard.nextDouble();

		// display output
		System.out.println("Given an amperage of " + myCircuit.amperage + ", "
				+ "and a resistance of " + myCircuit.resistance + ", "
				+ "the voltage is: " + myCircuit.calculateVoltage() + ".");
		
		// free up resources
		keyboard.close();
	}

	/**
	 * This is the method for calculating the resistance of a circuit.
	 */
	public static void resistance () {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		OhmsLawBusiness myCircuit = new OhmsLawBusiness();
		// tell user what he has chosen
		System.out.println("You have chosen to calculate resistance.");

		// ask user for voltage
		System.out.println("Please enter voltage: ");
		// save voltage
		myCircuit.voltage = keyboard.nextDouble();

		// ask user for amperage
		System.out.println("Please enter amperage: ");
		// save amperage
		myCircuit.amperage = keyboard.nextDouble();

		// display output
		System.out.println("Given an voltage of " + myCircuit.voltage + ", "
				+ "and an amperage of " + myCircuit.amperage + ", "
				+ "the resistance is: " + myCircuit.calculateResistance() + ".");
		
		// free up resources
		keyboard.close();
	}

	/**
	 * This is the method for calculating the amperage of a circuit.
	 */
	public static void amperage () {
	// create new scanner for keyboard input
	Scanner keyboard = new Scanner(System.in);
	OhmsLawBusiness myCircuit = new OhmsLawBusiness();
	// tell user what he has chosen
	System.out.println("You have chosen to calculate amperage.");

	// ask user for voltage
	System.out.println("Please enter voltage: ");
	// save voltage
	myCircuit.voltage = keyboard.nextDouble();

	// ask user for resistance
	System.out.println("Please enter resistance: ");
	// save resistance
	myCircuit.resistance = keyboard.nextDouble();

	// display output
	System.out.println("Given a voltage of " + myCircuit.voltage + ", "
			+ "and a resistance of " + myCircuit.resistance + ", "
			+ "the amperage is: " + myCircuit.calculateAmperage() + ".");
	
	// free up resources
	keyboard.close();
	}
}