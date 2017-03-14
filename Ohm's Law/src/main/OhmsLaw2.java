package main;

import java.util.Scanner;

/*
Purpose:The program will calculate the amperage of a circuit
given an input of voltage and resistance from the user.

Allow the user to define variables to hold the voltage and resistance
so that the amperage can be calculated.

Display a message with the amperage back to the user 
*/

/**
 * This class project allows the user to input a specific voltage and resistance
 * so that the amperage can be calculated.
 * @author MAXXtreme
 *
 */

public class OhmsLaw2 {
/**
 * 
 * @param args This is the main method and contains all the user input fields
 */
	public static void main(final String[] args) {
		//tell the user what the purpose of the program is
		System.out.println("Purpose: "
				+ "This program will calculate the amperage of a circuit "
				+ "given a user-defined voltage and resistance.");
		
		//give a default temporary value to the variables
		//so the program can replace the temporary value
		//with the user's input values
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
