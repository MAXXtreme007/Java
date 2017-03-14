package console;

import java.util.Scanner;
import bp.Circuit;

/**
 * This is the presentation class. This program allows the user to compute the
 * voltage, amperage, or resistance of a given circuit. This program also has an
 * attached javadoc file.
 * 
 * @author MAXX
 *
 */

public final class Main {
	/**
	 * This class is to set the main class to private.
	 */
	private Main() {
	}

	/**
	 * Main entry point of the program.
	 * 
	 * @param args
	 *            This is the main method and contains all the user input fields
	 */
	public static void main(final String[] args) {
		// tell the user what the purpose of the program is
		System.out.println("The purpose of this program is to calculate "
				+ "the voltage, amperage, or resistance of a given circuit.");
		unknown();
	}

	/**
	 * This method asks the user which unknown to calculate for.
	 */
	public static void unknown() {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		Circuit myCircuit = new Circuit();
		/**
		 * This variable is a place holder for the number three.
		 */
		final int three = 3;

		// ask user which unknown he wishes to calculate
		System.out.println("Please enter the number corresponding to what you "
				+ "wish to calculate: 1) Voltage 2) Resistance 3) Amperage");

		// determine which variable to calculate
		myCircuit.setUnknown(keyboard.nextInt());

		// calculate the voltage given the amperage and resistance
		if (myCircuit.getUnknown() == 1) {
			voltage();
		}

		// calculate the amperage given the voltage and resistance
		if (myCircuit.getUnknown() == 2) {
			amperage();
		}

		// calculate the resistance given the voltage and amperage
		if (myCircuit.getUnknown() == three) {
			resistance();
		}

		// free up resources
		keyboard.close();
	}

	/**
	 * This is the method for calculating the voltage given the amperage and
	 * resistance.
	 */
	public static void voltage() {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		// call values from business class
		Circuit myCircuit = new Circuit();

		// tell user what he has chosen
		System.out.println("You have chosen to calculate the voltage.");

		// ask user for user-defined variables
		System.out.println("Please enter the amperage.");
		myCircuit.setAmperage(keyboard.nextInt());
		System.out.println("Please enter the resistance.");
		myCircuit.setResistance(keyboard.nextInt());

		// display output
		myCircuit.calculateVoltage();
		System.out.println(
				"Given an amperage of " + myCircuit.getAmperage() + " amps,"
				+ " and a resistance of " + myCircuit.getResistance() + " ohms,"
				+ " the voltage is " + myCircuit.getVoltage() + " volts.");

		// repeat program prompt
		repeat();

		// free up resources
		keyboard.close();
	}

	/**
	 * This is the method for calculating the amperage given the voltage and
	 * resistance.
	 */
	public static void amperage() {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		// call values from business class
		Circuit myCircuit = new Circuit();

		// tell user what he has chosen
		System.out.println("You have chosen to calculate the amperage.");

		// ask user for user-defined variables
		System.out.println("Please enter the voltage.");
		myCircuit.setVoltage(keyboard.nextInt());
		System.out.println("Please enter the resistance.");
		myCircuit.setResistance(keyboard.nextInt());

		// display output
		myCircuit.calculateAmperage();
		System.out.println(
				"Given an voltage of " + myCircuit.getVoltage() + " volts,"
				+ " and a resistance of " + myCircuit.getResistance() + " ohms,"
				+ " the amperage is " + myCircuit.getAmperage() + " amps.");

		// repeat program prompt
		repeat();

		// free up resources
		keyboard.close();
	}

	/**
	 * This is the method for calculating the resistance given the voltage and
	 * amperage.
	 */
	public static void resistance() {
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		// call values from business class
		Circuit myCircuit = new Circuit();

		// tell user what he has chosen
		System.out.println("You have chosen to calculate the resistance.");

		// ask user for user-defined variables
		System.out.println("Please enter the voltage.");
		myCircuit.setVoltage(keyboard.nextInt());
		System.out.println("Please enter the amperage.");
		myCircuit.setAmperage(keyboard.nextInt());

		// display output
		myCircuit.calculateResistance();
		System.out.println(
				"Given an voltage of " + myCircuit.getVoltage() + " volts,"
				+ " and an amperage of " + myCircuit.getAmperage() + " amps,"
				+ " the resistance is " + myCircuit.getResistance() + " ohms.");

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
			System.out.println("Please press 1 or 2.");
			repeat = keyboard.nextInt();
		}
		if (repeat == 1) {
			unknown();
		}
		if (repeat == 2) {
			System.out.println("Thank you for using this program.");
		}

		// free up resources
		keyboard.close();
	}
}