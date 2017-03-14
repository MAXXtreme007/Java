package bp;

/**
 * This interface provides the functionality that we will implement for
 * unordered lists. For the purposes of our list, we will store integer values.
 * 
 * ***DO NOT CHANGE THIS INTERFACE.
 * 
 * @author cberkstresser
 * @version 1.0
 */
public interface ISortedList {
	/**
	 * 
	 * @return The current size of the list.
	 */
	int getSizeOfList();

	/**
	 * 
	 * @return True if duplicate values are allowed in the list.
	 */
	boolean areDuplicatesAllowed();

	/**
	 * Sets the duplicatesAllowed private placeholder so we know whether
	 * duplicates are allowed in the list.
	 * 
	 * @param pDuplicatesAllowed
	 *            True if duplicates are allowed, false otherwise.
	 */
	void setDuplicatesAllowed(boolean pDuplicatesAllowed);

	/**
	 * Clears all values in the list and sets the sizeOfList private placeholder
	 * to zero.
	 */
	void clear();

	/**
	 * Inserts a value into the list and increments the sizeOfList private
	 * placeholder.
	 * 
	 * @param pValueToInsert
	 *            The integer value to insert into the list.
	 */
	void insert(int pValueToInsert);

	/**
	 * Deletes the first occurrence of a value from the list. If the list does
	 * not allow duplicates, this will be equivalent to deleteAll(). This will
	 * also decrement the sizeOfList private placeholder if and only if the
	 * search value was found and deleted.
	 * 
	 * @param pValueToDelete
	 *            The value to find and delete from the list.
	 */
	void delete(int pValueToDelete);

	/**
	 * Deletes all occurrences of a value from the list. If the list does not
	 * allow duplicates, this will be equivalent to delete(). This will also
	 * decrement the sizeOfList private placeholder based on the number of found
	 * and deleted occurrences of the search value.
	 * 
	 * @param pValueToDelete
	 *            The value to find and delete from the list.
	 */
	void deleteAll(int pValueToDelete);

	/**
	 * Initializes the list with random data to a given size.
	 * 
	 * @param pSizeOfList
	 *            The size to which to initialize the list.
	 */
	void initializeWithRandomData(int pSizeOfList);

	/**
	 * Searches the list for a particular value.
	 * 
	 * @param pValueToFind
	 *            The value for which to search the list.
	 * @return The index position in the array of the value if found. Returns -1
	 *         otherwise.
	 */
	int find(int pValueToFind);

	/**
	 * Searches the list for a particular value.
	 * 
	 * @param pValueToFind
	 *            The value for which to search the list.
	 * @return The index positions in the array of the value if found. Returns
	 *         an empty array otherwise.
	 */
	int[] findAll(int pValueToFind);

	/**
	 * 
	 * @return Override the toString method to return a pretty comma separated
	 *         representation of the values within the array.
	 */
	String toString();

	/**
	 * Hmmm. This is interesting. I wonder what it is?
	 */
	default void bubbleSort() {
		throw new UnsupportedOperationException("Bubble sort not implemented yet!");
	}

	/**
	 * Hmmm. This is interesting. I wonder what it is?
	 */
	default void selectionSort() {
		throw new UnsupportedOperationException("Selection sort not implemented yet!");
	}
}