package bp;

import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.Parameter;

/**
 * This is the business logic class for the circuit. This program allows the
 * user to compute the voltage, amperage, or resistance of a given circuit.
 * 
 * @author MAXX
 */
public class Circuit implements ICircuit, IPermanentStorage {
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
	/**
	 * This variable stores the toy ID.
	 */
	private int toyID;
	/**
	 * This variable stores the circuit ID.
	 */
	private int circuitID;
	/**
	 * This variable stores the location.
	 */
	private String location;

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

	/**
	 * This variable stores the toy ID.
	 * 
	 * @return toyID of the circuit.
	 */
	public final int getToyID() {
		return toyID;
	}

	/**
	 * This variable stores the circuit ID.
	 * 
	 * @return circuitID of the circuit.
	 */
	public final int getCircuitID() {
		return circuitID;
	}

	/**
	 * This variable stores the location.
	 * 
	 * @return location of the circuit.
	 */
	public final String getLocation() {
		return location;
	}

	/**
	 * This variable stores the location.
	 * 
	 * @return location of the circuit.
	 */
	public final String getManufactureLocation() {
		return location;
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
			throw new InvalidParameterException("Please enter a "
					+ "positive voltage.");
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
			throw new InvalidParameterException("Please enter a "
					+ "positive amperage.");
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
			throw new InvalidParameterException("Please enter a "
					+ "positive resistance.");
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

	/**
	 * This method asks the user what the toy ID is.
	 *
	 * @param pToyID
	 *            asks the user what the toy ID is.
	 */
	public final void setToyID(final int pToyID) {
		toyID = pToyID;
	}

	/**
	 * This method asks the user what the circuit ID is.
	 *
	 * @param pCircuitID
	 *            asks the user what the circuit ID is.
	 */

	public final void setCircuitID(final int pCircuitID) {
		circuitID = pCircuitID;
	}

	/**
	 * This method asks the user which manufacturing location.
	 *
	 * @param pLocation
	 *            asks the user which manufacturing location.
	 */
	public final void setLocation(final String pLocation) {
		location = pLocation;
	}

	/**
	 * This method asks the user which manufacturing location.
	 *
	 * @param pManufacturer
	 *            asks the user which manufacturing location.
	 */
	public final void setManufactureLocation(final String pManufacturer) {
		location = pManufacturer;
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

	@Override
	public final boolean isValid() {
		return false;
	}

	@Override
	public final int getRowID() {
		return 0;
	}

	/**
	 * This method saves all the user inputed data to the database.
	 */
	public final void save() {
		Database myDib = new Database();
		// get a list of parameters ready
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		// add the parameters
		params.add(new Parameter<Integer>(getToyID()));
		params.add(new Parameter<Integer>(getCircuitID()));
		params.add(new Parameter<String>(location));
		params.add(new Parameter<Double>(voltage));
		params.add(new Parameter<Double>(amperage));
		params.add(new Parameter<Double>(resistance));

		myDib.executeSql("usp_SaveCircuit", params);
	}

	/**
	 * This method clears all the user inputed data in the gui.
	 */
	public void clear() {
		// TODO Auto-generated method stub
	}

	/**
	 * This method deletes the user inputed data to the database.
	 */
	public void delete() {
		// TODO Auto-generated method stub
	}

	/**
	 * This method loads data from the database given the toy ID.
	 * 
	 * @param pToyID
	 *            tells database which toy ID to load.
	 * 
	 * @param i
	 *            tells database which row ID to load.
	 */
	public final void load(final int pToyID, final int i) {
		Database myDb = new Database();
		// get a list of parameters ready
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		// add the parameters
		params.add(new Parameter<Integer>(pToyID));
		params.add(new Parameter<Integer>(i));

		ResultSet rsCircuit = myDb.getResultSet("usp_GetCircuit", params);
		try {
			if (rsCircuit.next()) {
				location = rsCircuit.getString("ManufactureLocation");
				voltage = rsCircuit.getDouble("Voltage");
				resistance = rsCircuit.getDouble("Resistance");
			} else {
				throw new InvalidParameterException("CircuitID not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method loads information from the database given a row ID.
	 * 
	 * @param rowID
	 *            gets information from database.
	 */
	public void load(final int rowID) {
		// TODO Auto-generated method stub

	}
}