package CircuitGUI;

import java.security.InvalidParameterException;

public class Circuit {
	// properties
	/**
	 * This variable tells the program which type of circuit to calculate.
	 */
	private int formula;
	/**
	 * This variable allows the user to define the voltage of a circuit.
	 */
	private double voltage;
	/**
	 * This variable allows the user to define the amperage of a circuit.
	 */
	private double amperage;
	/**
	 * This variable allows the user to define the resistance of a circuit.
	 */
	private double resistance;
	
	// mutators and accessors
	/**
	 * This method is used to set the circuit type.
	 *
	 * @param pFormula
	 *            Determines which formula to use.
	 */
	public final void setFormula(final int pFormula) {
		if (pFormula < 1 || pFormula > 2) {
			throw new InvalidParameterException("Please enter 1 or 2.");
		} else {
			formula = pFormula;
		}
	}

	/**
	 * This method is used to get the formula.
	 *
	 * @return formula
	 */
	public final double getFormula() {
		return formula;
	}
	
	/**
	 * This method is used to set the voltage.
	 *
	 * @param pVoltage
	 *            Limits the voltage value to be greater than zero.
	 */
	public final void setVoltage(final int pVoltage) {
		if (pVoltage < 0) {
			throw new InvalidParameterException("Please enter a voltage greater than zero.");
		} else {
			voltage = pVoltage;
		}
	}

	/**
	 * This method is used to get the voltage.
	 *
	 * @return voltage
	 */
	public final double getVoltage() {
		return voltage;
	}
	
	/**
	 * This method is used to set the amperage.
	 *
	 * @param pAmperage
	 *            Limits the amperage value to be greater than zero.
	 */
	public final void setAmperage(final int pAmperage) {
		if (pAmperage < 0) {
			throw new InvalidParameterException("Please enter an amperage greater than zero.");
		} else {
			amperage = pAmperage;
		}
	}

	/**
	 * This method is used to get the amperage.
	 *
	 * @return amperage
	 */
	public final double getAmperage() {
		return amperage;
	}
	
	/**
	 * This method is used to set the resistance.
	 *
	 * @param pResistance
	 *            Limits the resistance value to be greater than zero.
	 */
	public final void setResistance(final int pResistance) {
		if (pResistance < 0) {
			throw new InvalidParameterException("Please enter a resistance greater than zero.");
		} else {
			resistance = pResistance;
		}
	}

	/**
	 * This method is used to get the resistance.
	 *
	 * @return resistance
	 */
	public final double getResistance() {
		return resistance;
	}
	
	// processing
	/**
	 * This method is to calculate the voltage of a circuit.
	 * 
	 * @return voltage The voltage of the circuit.
	 */
	final double calculateVoltage() {
		voltage = amperage * resistance;
		return voltage;
	}
	/**
	 * This method is to calculate the amperage of a circuit.
	 * 
	 * @return amperage The amperage of the circuit.
	 */
	final double calculateAmperage() {
		amperage = voltage / resistance;
		return amperage;
	}
	/**
	 * This method is to calculate the resistance of a circuit.
	 * 
	 * @return resistance The resistance of the circuit.
	 */
	final double calculateResistance() {
		resistance = voltage / amperage;
		return resistance;
	}
}
