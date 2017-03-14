package main;

import java.security.InvalidParameterException;

/**
 * This program allows the user to compute a user defined nth term
 * in the Padovan Sequence.
 * 
 * @author MAXXtreme
 */

public class Padovan {
	// properties
	/**
	 * This variable is a place holder for the number three.
	 */
	private final int three = 3;
	/**
	 * This variable is a place holder for the max number allowed to calculate.
	 */
	private final int maxValueToCalculate = 79;
	/**
	 * This variable tells the program which term to calculate.
	 */
	private long termNumber;

	// mutators and accessors
	/**
	 * This method is used to set the term number.
	 *
	 * @param pTermNumber
	 *            Determines which term number the program is on.
	 */
	public final void setTermNumber(final long pTermNumber) {
		if (pTermNumber < 0) {
			throw new InvalidParameterException(
					"Please enter a term number greater than zero.");
		} else if (pTermNumber >= maxValueToCalculate) {
			throw new InvalidParameterException(
					"Please enter a term number less than 79.");
		} else {
			termNumber = pTermNumber;
		}
	}

	/**
	 * This method is used to get the term number.
	 *
	 * @return termNumber
	 */
	public final double getTermNumber() {
		return termNumber;
	}

	// processing
	/**
	 * This method is the base case for the recursion method in a Padovan
	 * Sequence.
	 * 
	 * @return getPadovanValue(termNumber) This value is the base case for the
	 *         recursion method in the Padovan Sequence.
	 */
	public final long getPadovanValue() {
		return getPadovanValue(termNumber);
	}

	/**
	 * This method is to calculate the value of the terms in a Padovan Sequence.
	 * 
	 * @param pTermNumber
	 *            Determines which term number the program is on.
	 * 
	 * @return getPadovanValue(termNumber) The sum of the terms in a Padovan
	 *         Sequence.
	 */
	public final int getPadovanValue(final long pTermNumber) {
		if (pTermNumber == 1
				|| pTermNumber == 2
				|| pTermNumber == three) {
			return 1;
		} else {
			return getPadovanValue(pTermNumber - 2)
					+ getPadovanValue(pTermNumber - three);
		}
	}
}