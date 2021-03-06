package main;

import java.util.Scanner;

/*
 Purpose:The program will calculate the sum of
 every integer between a given starting number
 and a given ending number from the user.

 Ask the user if he wishes to calculate the total sum of
 the numbers by using a loop method or by a simple formula
 of addition.

 Allow the user to define two integers—one starting number
 and one ending number. Either integer may be the minimum
 or the maximum, but the summation must calculate the
 integers between the two. i.e. the summation may be
 "forwards" or "backwards".

 Display a message with the calculated total sum back to the user.

 Also with this lab, this program has a generated javadoc file.
 */

/**
 * 2-13-15 This class project computes the total sum of two numbers given a
 * starting integer and an ending integer.
 *
 * @author MAXXtreme
 *
 */

public class Integers {
	/**
	 * Main entry point of the program.
	 * 
	 * @param args
	 * 
	 * This is the main method and contains all the user input fields
	 */

	public static void main(final String[] args) {
		Scanner keyboard = new Scanner(System.in);

		// ask which method to use: loop or add all
		System.out.println("Method: "
				+ "Would you like to calculate the total sum "
				+ "through a loop method or a simple formula of addition?");
		System.out.println("To calculate using a loop, press 1.");
		System.out.println("To calculate using addition, press 2.");
		// calculation method
		int calcMethod = keyboard.nextInt();

		// error method
		if (calcMethod != 1 && calcMethod != 2) {
			System.out.println("Error! Please press 1 or 2.");
			calcMethod = keyboard.nextInt();
		}

		// loop method
		if (calcMethod == 1) {
			// tell user what method is being used
			System.out.println("You have chosen to use the loop method.");
			// ask user for first integer to add
			System.out.println("Please enter your first integer.");
			double firstSumNumber = keyboard.nextDouble();
			// ask user for second integer to add
			System.out.println("Please enter your second integer.");
			double lastSumNumber = keyboard.nextDouble();
			// ask user for the value he wishes to add by
			System.out.println("Please enter the value "
					+ "you wish to add by.");
			double skipCounter = keyboard.nextDouble();
			System.out.println("You have chosen to add "
					+ "all the integers in multiples of " + skipCounter
					+ ", from the values of " + firstSumNumber + " to "
					+ lastSumNumber + ".");
			double runningTotal = 0;

			// for (start; end; increment)
			if (firstSumNumber < lastSumNumber) {
				for (double x = firstSumNumber; x <= lastSumNumber; x = x
						+ skipCounter) {
					if (x + skipCounter <= lastSumNumber) {
						System.out.print(x + " + ");
						runningTotal += x;
					}
					if (x + skipCounter > lastSumNumber) {
						runningTotal += x;
						System.out.println(x + " = ");
						System.out.println(runningTotal);
					}
				}

			}
			if (firstSumNumber > lastSumNumber) {
				for (double x = firstSumNumber; x >= lastSumNumber; x = x
						- skipCounter) {
					if (x - skipCounter >= lastSumNumber) {
						System.out.print(x + " + ");
						runningTotal += x;
					}
					if (x - skipCounter < lastSumNumber) {
						runningTotal += x;
						System.out.println(x + " = ");
						System.out.println(runningTotal);
					}
				}
			}
			if (firstSumNumber == lastSumNumber) {
				System.out.println("You entered the same integer for "
						+ "both the first integer and the last. "
						+ "Thus, the total sum of integers between "
						+ "your two integers is the same integer: "
						+ firstSumNumber + ".");
			}

			// addition method
		} else if (calcMethod == 2) {
			// tell user what method is being used
			System.out.println("You have chosen to "
					+ "use the addition method.");
			// ask user for first integer to add
			System.out.println("Please enter your first integer.");
			double firstSumNumber = keyboard.nextDouble();
			// ask user for second integer to add
			System.out.println("Please enter your second integer.");
			double lastSumNumber = keyboard.nextDouble();
			System.out.println("You have chosen to add "
					+ "all the integers between the values of "
					+ firstSumNumber + " to " + lastSumNumber + ".");
			double runningTotal = 0;

			// use summation formula to add integers
			if (firstSumNumber < lastSumNumber) {
				runningTotal = ((lastSumNumber * (lastSumNumber + 1)) / 2)
						- ((firstSumNumber * (firstSumNumber + 1)) / 2)
						+ firstSumNumber;
				System.out.println(runningTotal);
			}
			if (firstSumNumber > lastSumNumber) {
				runningTotal = ((firstSumNumber * (firstSumNumber + 1)) / 2)
						- ((lastSumNumber * (lastSumNumber + 1)) / 2)
						+ lastSumNumber;
				System.out.println(runningTotal);
			}
			if (firstSumNumber == lastSumNumber) {
				System.out.println("You entered the same integer for "
						+ "both the first integer and the last. "
						+ "Thus, the total sum of integers between "
						+ "your two integers is the same integer: "
						+ firstSumNumber + ".");
			}
		}
		// free up resources
		keyboard.close();
	}
}