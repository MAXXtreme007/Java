package main;

import java.security.InvalidParameterException;

/*
 * The Business Logic
 *
 * PROPERTIES:
 * Include all the variables
 *
 * PROCESSING:
 * In this lab, all the processing is done in the business class.
 * The initial population, growth/decay rate, and time will be asked
 * in the presentation class which will then be given to the business class.
 *
 * To calculate the final population size, the program will use the formulas:
 * Growth: x(t)= a * (1 + r)^t
 * Decay: x(t)= a * (1 - r)^t
 * where
 * t is the time passed
 * x is the population size in relation to the amount of time passed
 * a is the initial population size
 * r is the rate (growth if r>1 and decay if 0<=r<=1)
 *
 * The final population size will then be delivered to the
 * presentation class where it will be displayed to the user.
 */

/**
 * The initial population, growth/decay rate, and time are defined in the
 * presentation class, and those values are used to calculate final population
 * in the business logic portion of the program.
 *
 * This program contains the business class along with an attached javadoc file.
 *
 * @author MAXXtreme
 *
 */

public final class Growth {
	// properties
	/**
	 * This variable is the size of the initial population.
	 */
	private double initialPopluation;
	/**
	 * This variable is the rate at which the population grows.
	 */
	private double growthRate;
	/**
	 * This variable is the time over which the population grows or decays.
	 */
	private double time;
	/**
	 * This variable is a place holder to multiply .
	 */
	private static final double HUNDRED = 100;

	// get & sets
	/**
	 * This method is used to set the initial population.
	 *
	 * @param pInitialPopulation The initial population size.
	 */
	public void setInitialPopulation(final double pInitialPopulation) {
		if (pInitialPopulation < 0) {
			throw new InvalidParameterException(
					"Initial population must "
					+ "be greater than zero!");
		} else {
			initialPopluation = pInitialPopulation;
		}
	}
	/**
	 * This method is used to get the initial population.
	 *
	 * @return initialPopulation
	 */
	public double getInitialPopulation() {
		return initialPopluation;
	}

	/**
	 * This method is used to get and set growth rate.
	 *
	 * @param pGrowthRate The rate at which the population grows.
	 */
	public void setGrowthRate(final double pGrowthRate) {
		if (pGrowthRate < 0) {
			throw new InvalidParameterException(
					"Growth must be greater than zero!");
		} else {
			growthRate = pGrowthRate / HUNDRED;
		}
	}
	/**
	 * This method is used to get the growth rate.
	 *
	 * @return growthRate
	 */
	public double getGrowthRate() {
		return growthRate;
	}

	/**
	 * This method is used to get and set time.
	 *
	 * @param pTime The time during which the population grows.
	 */
	public void setTime(final double pTime) {
		if (pTime < 0) {
			throw new InvalidParameterException(
					"Time must be greater than zero!");
		} else {
			time = pTime;
		}
	}
	/**
	 * This method is used to get the time.
	 *
	 * @return time
	 */
	public double getTime() {
		return time;
	}

	// processing
	/**
	 * This method calculates the final population size by multiplying the
	 * initial population size by the growth rate to the power of time.
	 *
	 * @return finalPopulation The final population size.
	 */
	double calculatePopulation() {
		double finalPopulation = initialPopluation
				* (Math.pow((1 + growthRate), time));
		return finalPopulation;
	}
}