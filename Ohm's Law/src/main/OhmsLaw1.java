package main;

import java.util.Scanner;

/*
Purpose: We will calculate the amperage of a circuit given a known voltage and resistance.

Define variables to hold the amperage, voltage, and resistance.

Set Voltage = 13 volts
Set Resistance = 2 ohms
Set Amperage = Voltage / Resistance

Display a message with the amperage back to the user 
*/

/**
 * This class project computes the amperage from a given voltage and resistance.
 * 
 * @author MAXXtreme
 *
 */

public class OhmsLaw1 {
	public static void main(final String[] args) {
		//tell the user what the purpose of the program is
		System.out.println("Purpose: "
				+ "This program will calculate the amperage of a circuit "
				+ "given a user-defined voltage and resistance.");
		
		//give a default temporary value to the variables
		double voltage = 2;
		double resistance = 2;
		
		//create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);
		
		//ask user for voltage
		System.out.println("Please enter voltage: ");
		//save voltage
		voltage = keyboard.nextDouble();
		
		//ask user for resistance
		System.out.println("Please enter resistance: ");
		//save resistance
		resistance = keyboard.nextDouble();
		
		//formula to calculate amperage
		double amperage = voltage / resistance;
		
		System.out.println(
				"Given a voltage of " + voltage + ", "
				+ "and a resistance of " + resistance + ", "
				+ "the amperage is: " + amperage + ".");
		//free up resources
		keyboard.close();
	}
}
