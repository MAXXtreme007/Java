package main;

import java.util.Scanner;

/*
 //GET INPUT
 //Get Length
 //Save Length
 //Get Width
 //Save Width
 //Get Depth
 //Save Depth

 //DO PROCESSING
 //Set Volume = (L x W x D) / 27

 //DISPLAY OUTPUT
 //Display Volume
 */

/**
 * 1-26-15 This class project computes the volume in square yards from a given length, width, and depth in feet.
 * 
 * @author MAXXtreme
 *
 */

public class Challenge1 {

	public static void main(String[] args) {
		// tell the user what the purpose of the program is
		System.out.println("Purpose: "
						+ "This program will calculate the volume of concrete in sq. yards "
						+ "given the measurements in feet.");
		// give a default temporary value to the variables
		// so the program can replace the temporary value
		// with the user's input values
		double length;
		double width;
		double depth;
		// GET INPUT
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);

		// ask user for length
		System.out.println("Please enter length in feet: ");
		// save length
		length = keyboard.nextDouble();

		// ask user for width
		System.out.println("Please enter width in feet: ");
		// save width
		width = keyboard.nextDouble();

		// ask user for depth
		System.out.println("Please enter depth in feet: ");
		// save depth
		depth = keyboard.nextDouble();

		
		// DO PROCESSING
		// Set Volume = (L x W x D) / 27
		double volume = length * width * depth / 27;

		// DISPLAY OUTPUT
		// Display Volume
		System.out.println(
				"Given a length of " + length + " feet, "
				+ "and a width of " + width + " feet, "
				+ "and a depth of " + depth + " feet, "
				+ "the volume is: " + volume + " cubic yards.");
		//free up resources
		keyboard.close();
	}
}
