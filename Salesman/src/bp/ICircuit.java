package bp;

/**
 * This interface provides the basic Ohm's law structure of a circuit.
 * 
 * @author cberkstresser
 * 
 */
public interface ICircuit {
	// **********Assessors************

	/**
	 * 
	 * @return the ToyID of the circuit
	 */
	int getToyID();

	/**
	 * 
	 * @return the CircuitID of the circuit
	 */
	int getCircuitID();

	/**
	 * 
	 * @return the voltage of the circuit
	 */
	double getVoltage();

	/**
	 * 
	 * @return the amperage of the circuit
	 */
	double getAmperage();

	/**
	 * 
	 * @return the resistance of the circuit
	 */
	double getResistance();

	/**
	 * 
	 * @return the location where this circuit was manufactured.
	 */
	String getManufactureLocation();

	// **********Mutators************
	/**
	 * Sets the ToyID.
	 * 
	 * @param toyID
	 *            the toy's ID to set
	 */
	void setToyID(int toyID);

	/**
	 * Sets the CircuitID.
	 * 
	 * @param circuitID
	 *            the circuit's ID to set
	 */
	void setCircuitID(int circuitID);

	/**
	 * Sets the voltage.
	 * 
	 * @param voltage
	 *            the voltage to set
	 */
	void setVoltage(double voltage);

	/**
	 * Sets the amperage.
	 * 
	 * @param amperage
	 *            the amperage to set
	 */
	void setAmperage(double amperage);

	/**
	 * Sets the resistance.
	 * 
	 * @param resistance
	 *            the resistance to set
	 */
	void setResistance(double resistance);

	/**
	 * Sets the location where the circuit was manufactured.
	 * 
	 * @param manufactureLocation
	 *            location where circuit was manufactured.
	 */
	void setManufactureLocation(String manufactureLocation);

	// **********Methods************
	/**
	 * Sets the voltage of the circuit using the amperage and resistance
	 * properties.
	 */
	void calculateVoltage();

	/**
	 * Sets the amperage of the circuit using the voltage and resistance
	 * properties.
	 */
	void calculateAmperage();

	/**
	 * Sets the resistance of the circuit using the voltage and amperage
	 * properties.
	 */
	void calculateResistance();

	/**
	 * Determines whether the voltage, amperage, and resistance are non-zero and
	 * that the values of each are consistant.
	 * 
	 * @return True if the voltage, amperage, and resistance are consistent.
	 */

	// **********Tests************
	/**
	 * Check to see if the voltage, amperage, and resistance are in sync.
	 * 
	 * @return True if voltage, amperage, and resistnace are in sync.
	 */
	boolean isValid();
}
