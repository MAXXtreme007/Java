package main;

import java.util.Scanner;

/*
 PURPOSE:
 The program will calculate the area 
 of a function between a given start and end point.

 INPUT:
 Ask the user for the starting point of the function sin(x)
 Save start
 Ask the user for the ending point of the function sin(x)
 Save end
 Ask for number of slices to chop the range
 Save slices

 PROCESSING:
 Define a running area = 0
 Loop from start to end by increments of "slice"
 Save the area of the current slice to the running area
 (slice width * sin(x) at that point)

 OUTPUT:
 Display a message with the calculated total sum back to the user
 */

/**
 * 2-23-15 This class project computes the area of a function between a given
 * start and end point.
 *
 * @author MAXXtreme
 *
 */

public class Challenge5 {
	/**
	 * Main entry point of the program.
	 * 
	 * @param args
	 * 
	 * This is the main method and contains all the user input fields
	 */

	public static void main(final String[] args) {
		Scanner keyboard = new Scanner(System.in);

		// ask user if he wishes to calculate sin or cos
		System.out.println("Would you like to calculate the "
				+ "area of the function of sin(x) or cos(x)?");
		System.out.println("Press 1 to calculate the "
				+ "area of a sin(x) function.");
		System.out.println("Press 2 to calculate the "
				+ "area of a cos(x) function.");

		// variables
		int funcMethod = keyboard.nextInt();
		double startPoint;
		double endPoint;
		double slices;

		// sine function
		if (funcMethod == 1) {
			// input from user
			System.out.println("You have chosen to use the sin(x) function.");
			System.out.println("At what point would you "
					+ "like to begin calculating the area at?");
			startPoint = keyboard.nextDouble();
			System.out.println("At what point would you "
					+ "like to end calculating the area at?");
			endPoint = keyboard.nextDouble();
			System.out.println("How many slices would you "
					+ "like to use to calculate the area?");
			slices = keyboard.nextDouble();
			System.out.println("Would you like to "
					+ "calculate using a loop, math, or both?");

			// calculation method
			int calcMethod;
			System.out.println("Press 1 if you would like "
					+ "to calculate using a loop.");
			System.out.println("Press 2 if you would like "
					+ "to calculate using math.");
			System.out.println("Press 3 if you would like "
					+ "to calculate using both.");
			calcMethod = keyboard.nextInt();

			// loop method
			if (calcMethod == 1 || calcMethod == 3) {
				// calculation criteria
				if (calcMethod != 3) {
					System.out.println("You have chosen to calculate "
							+ "the area of sin(x) "
							+ "from the starting point of " + startPoint
							+ " to the ending point of " + endPoint + " using "
							+ slices + " slices, using a loop method.");
				}
				if (calcMethod == 3) {
					System.out.println("You have chosen to calculate "
							+ "the area of sin(x) "
							+ "from the starting point of " + startPoint
							+ " to the ending point of " + endPoint + " using "
							+ slices + " slices,"
							+ " using both math and a loop.");
				}

				// calculate area
				double walker;
				double sliceWidth = (endPoint - startPoint) / slices;
				double sliceArea;
				double totalArea = 0;

				// for (start; end; increment)
				for (walker = startPoint; walker <= endPoint; walker += sliceWidth) {
					sliceArea = sliceWidth * Math.sin(walker);
					totalArea += sliceArea;
				}

				// display output
				if (calcMethod != 3) {
					System.out.println("Given that criteria "
							+ "with a loop method, the area "
							+ "of the function is " + totalArea);
				}
				if (calcMethod == 3) {
					System.out.println("The loop method " + "output is "
							+ totalArea);
				}
			}

			// math method
			if (calcMethod == 2 || calcMethod == 3) {
				// calculation criteria
				if (calcMethod != 3) {
					System.out.println("You have chosen to calculate "
							+ "the area of sin(x) "
							+ "from the starting point of " + startPoint
							+ " to the ending point of " + endPoint + " using "
							+ slices + " slices, using math.");
				}

				// calculate area
				double totalArea = (Math.cos(startPoint))
						- (Math.cos(endPoint));

				// display output
				if (calcMethod != 3) {
					System.out.println("Given that criteria using math, "
							+ "the area of the function is " + totalArea);
				}
				if (calcMethod == 3) {
					System.out.println("The math method " + "output is "
							+ totalArea);
				}
			}
		}

		// cosine function
		if (funcMethod == 2) {
			System.out.println("You have chosen to use the cos(x) function.");
			System.out.println("At what point would you "
					+ "like to begin calculating the area at?");
			startPoint = keyboard.nextDouble();
			System.out.println("At what point would you "
					+ "like to end calculating the area at?");
			endPoint = keyboard.nextDouble();
			System.out.println("How many slices would you "
					+ "like to use to calculate the area?");
			slices = keyboard.nextDouble();
			System.out.println("Would you like to "
					+ "calculate using a loop, math, or both?");

			// calculation method
			int calcMethod;
			System.out.println("Press 1 if you would like "
					+ "to calculate using a loop.");
			System.out.println("Press 2 if you would like "
					+ "to calculate using math.");
			System.out.println("Press 3 if you would like "
					+ "to calculate using both.");
			calcMethod = keyboard.nextInt();

			// loop method
			if (calcMethod == 1 || calcMethod == 3) {
				// calculation criteria
				if (calcMethod != 3) {
					System.out.println("You have chosen to calculate "
							+ "the area of cos(x) "
							+ "from the starting point of " + startPoint
							+ " to the ending point of " + endPoint + " using "
							+ slices + " slices, using a loop method.");
				}
				if (calcMethod == 3) {
					System.out.println("You have chosen to calculate "
							+ "the area of cos(x) "
							+ "from the starting point of " + startPoint
							+ " to the ending point of " + endPoint + " using "
							+ slices + " slices,"
							+ " using both math and a loop.");
				}

				// calculate area
				double walker;
				double sliceWidth = (endPoint - startPoint) / slices;
				double sliceArea;
				double totalArea = 0;

				// for (start; end; increment)
				for (walker = startPoint; walker <= endPoint; walker += sliceWidth) {
					sliceArea = sliceWidth * Math.cos(walker);
					totalArea += sliceArea;
				}

				// display output
				if (calcMethod != 3) {
					System.out.println("Given that criteria "
							+ "with a loop method, "
							+ "the area of the function is " + totalArea);
				}
				if (calcMethod == 3) {
					System.out.println("The loop method " + "output is "
							+ totalArea);
				}
			}

			// math method
			if (calcMethod == 2 || calcMethod == 3) {
				// calculation criteria
				if (calcMethod != 3) {
					System.out.println("You have chosen to calculate "
							+ "the area of cos(x) "
							+ "from the starting point of " + startPoint
							+ " to the ending point of " + endPoint + " using "
							+ slices + " slices, using math.");
				}

				// calculate area
				double totalArea = (Math.sin(startPoint))
						- (Math.sin(endPoint));

				// display output
				if (calcMethod != 3) {
					System.out.println("Given that criteria using math, "
							+ "the area of the function is " + totalArea);
				}
				if (calcMethod == 3) {
					System.out.println("The math method " + "output is "
							+ totalArea);
				}
			}
		}

		// free up resources
		keyboard.close();
	}
}