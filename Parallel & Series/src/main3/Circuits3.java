package main3;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * This program allows the user to compute the total resistance of either a
 * series or parallel circuit using an array list.
 * 
 * @author MAXXtreme
 */

public class Circuits3 {
	// properties
	/**
	 * This variable tells the program which type of circuit to calculate.
	 */
	private int formula;
	/**
	 * This variable initializes an array list.
	 */
	private ArrayList<Double> resistance = new ArrayList<Double>();

	// mutators and accessors
	/**
	 * This method is used to set the circuit type.
	 *
	 * @param pFormula Determines which formula to use.
	 */
	public final void setFormula(final int pFormula) {
		if (pFormula < 1 || pFormula > 2) {
			throw new InvalidParameterException(
					"Please enter 1 or 2.");
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
	 * This method is used to set the resistor values.
	 * 
	 * @param pResistance The resistance of each resistor.
	 */
	public final void setResistance(final double pResistance) {
		if (pResistance < 0) {
			throw new InvalidParameterException(
					"Resistance values must be greater than zero!");
		} else {
			resistance.add(pResistance);
		}
	}
	
	/**
	 * This method is used to get the resistor values.
	 * 
	 * @return resistance
	 */
	public final ArrayList<Double> getResistance() {
		return resistance;
	}
	
	// processing
	/**
	 * This method is to calculate the resistance of a series circuit.
	 * 
	 * @return totalRes The total resistance sum for series circuits.
	 */
	final double calculateSeries() {
		// variables
		double totalRes = 0;
		// total resistance accumulator and loop for parallel circuit
		for (double walker : resistance) {
			totalRes += walker;
		}
		return totalRes;
	}
		
	/**
	 * This method is to calculate the resistance of a parallel circuit.
	 * 
	 * @return totalSum The total resistance sum for parallel circuits.
	 */
	final double calculateParallel() {
		// variables
		double totalRes = 0;
		double totalSum = 0;
		// total resistance accumulator and loop for parallel circuit
		for (double walker : resistance) {
			totalRes += (1.0 / walker);
		}
		totalSum = (1.0 / totalRes);
		return totalSum;
	}
}