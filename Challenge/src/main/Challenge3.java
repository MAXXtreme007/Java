package main;

import java.util.Scanner;

/*
 //Ask for hourly rate
 //Save rate
 //Ask for how many hours worked
 //Save hours worked

 //DO PROCESSING
 //Work through this
 //(rate * hours) + (1.5 * rate * hours)

 //DISPLAY OUTPUT
 //Display the weekly gross pay
 */

/**
 * 2-2-15 This class project computes the gross pay.
 * 
 * @author MAXXtreme
 *
 */

public class Challenge3 {

	public static void main(String[] args) {
		// tell the user what the purpose of the program is
		System.out.println("Purpose: "
				+ "This program will calculate the gross pay "
				+ "given the hourly rate and the total hours worked.");
		// variables
		double rate;
		double hours;

		// GET INPUT
		// create new scanner for keyboard input
		Scanner keyboard = new Scanner(System.in);

		// ask user for rate
		System.out.println("Please enter your hourly rate: ");
		// save rate
		rate = keyboard.nextDouble();

		// ask user for hours
		System.out.println("Please enter number of hours worked: ");
		// save hours
		hours = keyboard.nextDouble();

		// DO PROCESSING
		// Calculate gross pay
		double gross;
		if (hours <= 40) {
			gross = rate * hours;
		} else {
			gross = 1.5 * rate * hours;
		}

		// DISPLAY OUTPUT
		// Display Volume
		System.out.printf("Given an hourly rate of $%.2f per hour "
				+ "and a total hours this week of " + hours + " hours, "
				+ "the gross pay for the week is: $%.2f.", rate, gross);
		// free up resources
		keyboard.close();
	}
}
