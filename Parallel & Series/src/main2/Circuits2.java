package main2;

/*
 * The Business Logic
 * 
 * PROPERTIES:
 * Include all the variables and initialize arrays
 * 
 * PROCESSING:
 * In this lab, all the processing is done in the business class.
 * If the circuit is in series, the total resistance
 * is simply the sum of the resistors.
 * If the circuit is in parallel, the reciprocal of the total resistance
 * is the sum of reciprocals of the individual resistances.
 */

/**
 * This program allows the user to compute the total resistance of either a
 * series or parallel circuit.
 * 
 * This program contains the business class
 * along with an attached javadoc file.
 * 
 * @author MAXXtreme
 *
 */

public class Circuits2 {
	// properties
	/**
	 * This variable tells the program which type of circuit to calculate.
	 */
	int formula;
	/**
	 * This variable tells the program how many resistors there are.
	 */
	int numberOfRes;
	/**
	 * This variable initializes a counter
	 * to display which resistor value to enter.
	 */
	int counter = 0;
	/**
	 * This array holds the values of all the resistor values.
	 */
	double[] resistors;
	/**
	 * This variable is an accumulator variable
	 * for total resistance of series circuit.
	 */
	double totalRes = 0;
	/**
	 * This variable is an accumulator variable
	 * for total resistance of parallel circuit.
	 */
	double totalSum = 0;
	
	// processing
	/**
	 * This method is to calculate the resistance of a series circuit.
	 * 
	 * @return totalRes The total sum for series circuits.
	 */
	final double calculateSeries() {
		// total resistance accumulator and loop for series circuit
		for (double walker : resistors) {
			totalRes += walker;
		}
		return totalRes;
	}
		
	/**
	 * This method is to calculate the resistance of a parallel circuit.
	 * 
	 * @return totalSum The total sum for parallel circuits.
	 */
	final double calculateParallel() {
		// total resistance accumulator and loop for parallel circuit
		for (double walker : resistors) {
			totalRes += walker;
		}
		totalSum = 1.0 / totalRes;
		return totalSum;
	}
	
	/**
	 * This method is to set the values of the resistors.
	 */
	final void initializeResistors() {
		resistors = new double[numberOfRes];
	}
}