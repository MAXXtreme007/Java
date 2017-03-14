package main;

/*
 * The Business Logic
 * 
 * PROPERTIES:
 * Include all the variables
 * 
 * PROCESSING:
 * In this lab, all the processing is done in the business class.
 * The amount of tosses will be asked in the presentation class which
 * will then be given to the business class. The same process will occur
 * for the number of heads the user wishes to get.
 * 
 * To calculate the probability, the program will use the probability formula.
 * totalFlips! / [(totalFlips - headsFlipped)! * headsFlipped!] * 0.5^totalFlips
 * 
 * To convert the chance of occurrence (fraction) to appear as a percentage,
 * the decimal is multiplied by 100 to display the answer as a percentage.
 * 
 * This probability will then be delivered to the presentation class where that
 * probability will be displayed to the user.
 */

/**
 * This business portion of the program computes the probability of coin tosses.
 * First, it receives the values from the presentation class, and then uses
 * those values to calculate the probability of that event happening.
 * 
 * This program contains the business class along with an attached javadoc file.
 * 
 * @author MAXXtreme
 *
 */

public final class Probability {
	// fundamental properties
	/**
	 * This variable asks the user how many coin tosses there will be.
	 */
	public int totalFlips;
	/**
	 * This variable asks the user how many heads there will be.
	 */
	public int headsFlipped;
	/**
	 * This variable converts the probability fraction into a percentage by
	 * multiplying the calculated value by 100.
	 */
	static final double MAKE_FRACTION_INTO_PERCENTAGE = 100;
	/**
	 * This variable is the probability of a coin landing on heads.
	 */
	static final double PROBALITY_OF_HEADS = 0.5;
	// derived properties
	/**
	 * This variable is the probability of an event happening.
	 */
	public double probability;

	// processing
	/**
	 * This method is to provide the formula of calculating factorials.
	 * 
	 * @param numberToFactorial
	 *            This is the variable to calculate the factorial.
	 * 
	 * @return runningProduct The calculated product of the factorial.
	 */
	private int factorial(final double numberToFactorial) {
		int runningProduct = 1;
		for (int n = 1; n <= numberToFactorial; ++n) {
			runningProduct *= n;
		}
		return runningProduct;
	}

	/**
	 * This method is to calculate the probability of an event happening.
	 * 
	 * @return probability The probability of an event happening.
	 */
	double calculateProbability() {
		probability = MAKE_FRACTION_INTO_PERCENTAGE
				* (factorial(totalFlips)
						/ (factorial(totalFlips - headsFlipped) 
								* factorial(headsFlipped)) * (Math
							.pow(PROBALITY_OF_HEADS, totalFlips)));
		return probability;
	}
}