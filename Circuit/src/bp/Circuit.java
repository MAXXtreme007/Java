package bp;

import java.security.InvalidParameterException;

/**
 * This is the business logic class. This program allows the user to compute the
 * voltage, amperage, or resistance of a given circuit. This program also has an
 * attached javadoc file.
 * 
 * @author MAXX
 *
 */

public class Circuit implements ICircuit {
	// **********Properties************
	/**
	 * This variable is a place holder for the number three.
	 */
	private final int three = 3;
	/**
	 * This variable stores the value of the voltage.
	 */
	private double voltage;
	/**
	 * This variable stores the value of the amperage.
	 */
	private double amperage;
	/**
	 * This variable stores the value of the resistance.
	 */
	private double resistance;
	/**
	 * This variable asks remembers what variable to calculate.
	 */
	private double unknown;

	// **********Assessors************
	/**
	 * This variable is an accessor for voltage.
	 * 
	 * @return voltage of the circuit.
	 */
	public final double getVoltage() {
		return voltage;
	}

	/**
	 * This variable is an accessor for amperage.
	 * 
	 * @return amperage of the circuit.
	 */
	public final double getAmperage() {
		return amperage;
	}

	/**
	 * This variable is an accessor for resistance.
	 * 
	 * @return resistance of the circuit.
	 */
	public final double getResistance() {
		return resistance;
	}

	/**
	 * This variable to used to ask the user which unknown to calculate for the
	 * console.
	 *
	 * @return unknown asks the user which unknown to calculate.
	 */
	public final double getUnknown() {
		return unknown;
	}

	// **********Mutators************
	/**
	 * This variable is a mutator for voltage.
	 * 
	 * @param pVoltage
	 *            to set.
	 */
	public final void setVoltage(final double pVoltage) {
		if (pVoltage < 0) {
			throw new InvalidParameterException("Please enter a positive "
					+ "voltage.");
		} else {
			voltage = pVoltage;
		}
	}

	/**
	 * This variable is a mutator for amperage.
	 * 
	 * @param pAmperage
	 *            to set.
	 */
	public final void setAmperage(final double pAmperage) {
		if (pAmperage < 0) {
			throw new InvalidParameterException("Please enter a positive "
					+ "amperage.");
		} else {
			amperage = pAmperage;
		}
	}

	/**
	 * This variable is a mutator for resistance.
	 * 
	 * @param pResistance
	 *            to set.
	 */
	public final void setResistance(final double pResistance) {
		if (pResistance < 0) {
			throw new InvalidParameterException("Please enter a positive "
					+ "resistance.");
		} else {
			resistance = pResistance;
		}
	}

	/**
	 * This method asks the user which unknown to calculate in the console.
	 *
	 * @param pUnknown
	 *            asks the user which unknown to calculate.
	 */
	public final void setUnknown(final int pUnknown) {
		if (pUnknown < 1 || pUnknown > three) {
			throw new InvalidParameterException("Please enter 1, 2, or 3.");
		} else {
			unknown = pUnknown;
		}
	}

	// **********Methods************
	/**
	 * This method calculates the voltage given the amperage and resistance.
	 * 
	 * return voltage The calculated voltage given the user-defined amperage and
	 * resistance.
	 */
	public final void calculateVoltage() {
		voltage = amperage * resistance;
	}

	/**
	 * This method calculates the amperage given the voltage and resistance.
	 * 
	 * return amperage The calculated amperage given the user-defined voltage
	 * and resistance.
	 */
	public final void calculateAmperage() {
		amperage = voltage / resistance;
	}

	/**
	 * This method calculates the resistance given the voltage and amperage.
	 * 
	 * return resistance The calculated resistance given the user-defined
	 * voltage and amperage.
	 */
	public final void calculateResistance() {
		resistance = voltage / amperage;
	}
}