package bp;

import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.Database;
import db.Parameter;

/**
 * This is the business logic class for the toy. This program allows the user to
 * compute the voltage, amperage, or resistance of a given circuit.
 * 
 * @author MAXX
 */
public class Toy implements IToy, IPermanentStorage {

	/**
	 * Main entry point of the program.
	 * 
	 * @param args
	 *            The main method and contains all the mutators and accessors
	 */
	public static void main(final String[] args) {

	}

	// **********Properties************
	/**
	 * This variable saves properties for circuit 1.
	 */
	private Circuit circuit1 = new Circuit();
	/**
	 * This variable saves properties for circuit 2.
	 */
	private Circuit circuit2 = new Circuit();
	/**
	 * This variable saves the inspector name.
	 */
	private String inspector;
	/**
	 * type filter text This variable saves the toy ID.
	 */
	private int toyID;
	/**
	 * This variable saves the date and time.
	 */
	private Date inspectionDateTime;

	// **********Assessors************
	/**
	 * This variable is an accessor for toy ID.
	 * 
	 * @return toyID of the circuit.
	 */
	public final int getToyID() {
		return toyID;
	}

	/**
	 * This variable is an accessor for inspector.
	 * 
	 * @return inspector of the circuit.
	 */
	public final String getInspector() {
		return inspector;
	}

	/**
	 * This variable is an accessor for time and date.
	 * 
	 * @return inspectionDateTime of inspection.
	 */
	public final Date getInspectionDateTime() {
		return inspectionDateTime;
	}

	/**
	 * This variable is an accessor for getting circuit 1 information.
	 * 
	 * @return circuit1 information.
	 */
	public final Circuit getCircuit1() {
		return circuit1;
	}

	/**
	 * This variable is an accessor for getting circuit 2 information.
	 * 
	 * @return circuit2 information.
	 */
	public final Circuit getCircuit2() {
		return circuit2;
	}

	// **********Mutators************
	/**
	 * This variable is a mutator for toy ID.
	 * 
	 * @param pToyID
	 *            to set.
	 */
	public final void setToyID(final int pToyID) {
		toyID = pToyID;
	}

	/**
	 * This variable is a mutator for inspector.
	 * 
	 * @param pInspector
	 *            to set.
	 */
	public final void setInspector(final String pInspector) {
		inspector = pInspector;
	}

	/**
	 * This variable is a mutator for inspection date and time.
	 * 
	 * @param pInspectionDateTime
	 *            to set.
	 */
	public final void setInspectionDateTime(final Date pInspectionDateTime) {
		inspectionDateTime = pInspectionDateTime;
	}

	/**
	 * This variable is a mutator for circuit 1 information.
	 * 
	 * @param pCircuit1
	 *            to set.
	 */
	public final void setCircuit1(final Circuit pCircuit1) {
		circuit1 = pCircuit1;
	}

	/**
	 * This variable is a mutator for circuit 2 information.
	 * 
	 * @param pCircuit2
	 *            to set.
	 */
	public final void setCircuit2(final Circuit pCircuit2) {
		circuit2 = pCircuit2;
	}

	@Override
	public final int getRowID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public final void save() {
		Database myDb = new Database();
		// get a list of parameters ready
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		// add the parameters
		params.add(new Parameter<Integer>(toyID));
		params.add(new Parameter<String>(inspector));
		params.add(new Parameter<Date>(inspectionDateTime));

		myDb.executeSql("usp_SaveToy", params);
		circuit1.save();
		circuit2.save();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public final void delete() {
		Database myDb = new Database();
		// get a list of parameters ready
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		toyID = getToyID();

		// add the parameters
		params.add(new Parameter<Integer>(toyID));

		// delete data from database
		myDb.executeSql("usp_DeleteToy", params);
	}

	@Override
	public final void load(final int pToyID) {
		Database myDb = new Database();
		// get a list of parameters ready
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		// add the parameters
		params.add(new Parameter<Integer>(pToyID));

		ResultSet rsToy = myDb.getResultSet("usp_GetToy", params);

		try {
			if (rsToy.next()) {
				toyID = rsToy.getInt("ToyID");
				inspector = rsToy.getString("Inspector");
				inspectionDateTime = rsToy.getTimestamp("InspectionDateTime");

				// load circuit 1 properties
				circuit1.load(pToyID, 1);
				// load circuit 2 properties
				circuit2.load(pToyID, 2);
			} else {
				throw new InvalidParameterException("ToyID not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}