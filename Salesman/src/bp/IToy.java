package bp;

import java.util.Date;

/**
 * This interface provides the basic layout of a toy circuit.
 * 
 * @author cberkstresser
 * @version 1.1
 * 
 */
public interface IToy {
	// **********Assessors************
	/**
	 * 
	 * @return The ToyID of the toy.
	 */
	int getToyID();

	/**
	 * 
	 * @return The inspector performing quality control on this toy.
	 */
	String getInspector();

	/**
	 * 
	 * @return The inspection date/time this toy was inspected.
	 */
	Date getInspectionDateTime();

	/**
	 * The first circuit of the toy.
	 * 
	 * @return The first circuit of the toy.
	 */
	Circuit getCircuit1();

	/**
	 * The second circuit of the toy.
	 * 
	 * @return The second circuit of the toy.
	 */
	Circuit getCircuit2();

	// **********Mutators************
	/**
	 * Sets the ToyID in the class.
	 * 
	 * @param toyID
	 *            The Toy's ID to set.
	 */
	void setToyID(int toyID);

	// **********Methods************
	/**
	 * Sets the inspector of the toy.
	 * 
	 * @param inspector
	 *            The inspector's unique identifier/name.
	 */
	void setInspector(String inspector);

	/**
	 * Sets the inspection date/time of this toy.
	 * 
	 * @param inspectionDateTime
	 *            The date/time of inspection.
	 */
	void setInspectionDateTime(Date inspectionDateTime);

	/**
	 * Sets the first circuit of the toy.
	 * 
	 * @param circuit1
	 *            The first circuit of the toy.
	 */
	void setCircuit1(Circuit circuit1);

	/**
	 * Sets the second circuit of the toy.
	 * 
	 * @param circuit1
	 *            The second circuit of the toy.
	 */
	void setCircuit2(Circuit circuit2);
}
