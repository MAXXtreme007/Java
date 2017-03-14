package bp;

/**
 * This interface provides variables needed to calculate the probability of a
 * coin toss.
 * 
 * @author MAXXtreme
 * 
 */
public interface IProbability {
	// **********Assessors************
	/**
	 * @return the number of tosses there will be
	 */
	int getTotalFlips();

	/**
	 * @return the number of heads there will be
	 */
	int getHeadsFlipped();

	// **********Mutators************
	/**
	 * Sets the total flips.
	 * 
	 * @param totalFlips
	 *            the number of flips to set
	 */
	void setTotalFlips(int totalFlips);

	/**
	 * Sets the heads flips.
	 * 
	 * @param headsFlipped
	 *            the number of heads to set
	 */
	void setHeadsFlipped(int headsFlipped);

	// **********Methods************
	/**
	 * Sets the probability using the number of flips and number of heads
	 * properties.
	 */
	void calculateProbability();

	// **********Tests************
	/**
	 * Check to see if the number of flips and heads to get are in sync.
	 * 
	 * @return True if number of flips and heads to get are in sync.
	 */
	boolean isValid();
}
