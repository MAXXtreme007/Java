package ProbabilityGUI;

import java.security.InvalidParameterException;

public class Probability {
	// properties
	private int numberOfFlips;
	private int totalHeadsFlipped = -1;

	public int getNumberOfFlips() {
		return numberOfFlips;
	}

	public void setNumberOfFlips(int pNumberOfFlips) {
		if (pNumberOfFlips < 0) {
			throw new InvalidParameterException(
					"Number of flips must be positive!");
		} else {
			numberOfFlips = pNumberOfFlips;
		}
	}

	// methods
	int factorial(int numberToFactorial) {
		int runningProduct = 1;
		for (int n = 2; n <= numberToFactorial; ++n) {
			runningProduct *= n;
		}
		return runningProduct;
	}

	double getProbability() {
		double factor1 = Math.pow(.5, numberOfFlips);
		double factor2 = factorial(numberOfFlips)
				/ (factorial(numberOfFlips - totalHeadsFlipped) * factorial(totalHeadsFlipped));
		return factor1 * factor2;
	}

	public int getTotalHeadsFlipped() {
		return totalHeadsFlipped;
	}

	public void setTotalHeadsFlipped(int pTotalHeadsFlipped) {
		if (pTotalHeadsFlipped < 0) {
			throw new InvalidParameterException(
					"Number of heads flipped must be positive!");
		} else if (pTotalHeadsFlipped > numberOfFlips) {
			throw new InvalidParameterException(
					"Number of heads flipped must less than total flips!");
		} else {
			totalHeadsFlipped = pTotalHeadsFlipped;
		}
	}
}
