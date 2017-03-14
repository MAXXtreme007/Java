package bp;

/**
 * This interface defines the methods needed to save data to permanent storage.
 * 
 * @author cberkstresser
 * 
 */
public interface IPermanentStorage {
	// **********Assessors************
	/**
	 * Gets the database's internal key.
	 * @return The database's row identifier.
	 */
	int getRowID();

	// **********Mutators************

	// **********Methods************

	/**
	 * Saves the values stored in the class to permanent storage.
	 */
	void save();

	/**
	 * Clears the values loaded in the class.
	 */
	void clear();

	/**
	 * Deletes the currently loaded values from permanent storage.
	 */
	void delete();

	/**
	 * Loads a saved circuit from permanent storage.
	 * 
	 * @param rowID
	 *            The unique identifier of the class item to load.
	 */
	void load(int rowID);
}
