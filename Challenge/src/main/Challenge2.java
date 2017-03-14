package main;

import java.util.Scanner;

/*
 //GET INPUT
 //Ask user if he wishes to calculate the area of a square or a circle
 //If user chooses a square
	 //Get Length
	 //Save Length
	 //Get Width
	 //Save Width
 //If user chooses a circle
	 //Get Radius
	 //Save Radius

 //DISPLAY OUTPUT
 //Display Area
 */

/**
 * 1-30-15 This class project computes the area of either a square or circle.
 * 
 * @author MAXXtreme
 *
 */

public class Challenge2 {

	public static void main(String[] args) {
		// tell the user what the purpose of the program is
		System.out.println("Purpose: "
						+ "This program will calculate the area of either a square or a circle.");
		// variables
		double length;
		double width;
		double radius;
		int formula;
		double area;

		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);

		// ask user which unknown he wishes to calculate
		System.out.println("Please enter the number "
			+ "corresponding to what you wish to calculate: "
			+ "1) Square " + "2) Circle");

		// save formula
		formula = keyboard.nextInt();

		if (formula == 1) {
			// tell user what he has chosen
			System.out.println("You have chosen to calculate the area of a square.");

			// ask user for length
			System.out.println("Please enter length: ");
			// save amperage
			length = keyboard.nextDouble();

			// ask user for width
			System.out.println("Please enter width: ");
			// save width
			width = keyboard.nextDouble();

			// formula
			area = length * width;
			System.out.println("Given a length of " + length + ", "
				+ "and a width of " + width + ", "
				+ "the area is: " + area + ".");
		}

		if (formula == 2) {
			// tell user what he has chosen
			System.out.println("You have chosen to calculate the area of a circle.");
						
			// ask user for radius
			System.out.println("Please enter the radius: ");
			// save radius
			radius = keyboard.nextDouble();
			
			// formula
			area = Math.PI * radius * radius;
			// display volume
			System.out.println("Given a radius of " + radius + ", + "
					+ "the area is: " + area + ".");
		}
		
		//free up resources
		keyboard.close();
	}
}
