package bp;

import java.util.Date;
/**
 * 
 * @author Noah Galey
 *
 */
public abstract class Workout {
	/**
	 * This is the variable that sets the name of the exercise 
	 * entered by the user, depending on the workout type.
	 */
	protected String exerciseName;
	/**
	 * This is the variable that sets the average heart rate 
	 * entered by the user, depending on the workout type.
	 */
	protected int averageHeartRate;
	/**
	 * This is the variable that sets the notes 
	 * entered by the user, depending on the workout type.
	 */
	protected String notes;
	/**
	 * This is the time of the exercise which is entered by the user 
	 * in the format of MM:dd:yyyy.
	 */
	private Date exerciseDate;
	/**
	 * This is the Name of the person who is doing the exercises,
	 *  which is entered by the user.
	 */
	private String personName;
	/**
	 * 
	 * @return exerciseName Name of the exercise 
	 */
	public final String getExerciseName() {
		return exerciseName;
	}
	/**
	 * 
	 * @param pExerciseName ExerciseName
	 */
	public final void setExerciseName(final String pExerciseName) {
		exerciseName = pExerciseName;
	}
	/**
	 * 
	 * @return averageHeartRate Average Heart Rate of the user
	 */
	public final int getAverageHeartRate() {
		return averageHeartRate;
	}
	/**
	 * 
	 * @param pAverageHeartRate AverageHeartRate
	 */
	public final void setAverageHeartRate(final int pAverageHeartRate) {
		averageHeartRate = pAverageHeartRate;
	}
	/**
	 * 
	 * @return notes The notes entered by the user
	 */
	public final String getNotes() {
		return notes;
	}
	/**
	 * 
	 * @param pNotes Notes
	 */
	public final void setNotes(final String pNotes) {
		notes = pNotes;
	}
	/**
	 * 
	 * @return exerciseDate The date of the exercise given by the user
	 */
	public final Date getExerciseDate() {
		return exerciseDate;
	}
	/**
	 * 
	 * @param pExerciseDate ExerciseDate
	 */
	public final void setExerciseDate(final Date pExerciseDate) {
		exerciseDate = pExerciseDate;
	}
	/**
	 * 
	 * @return personName The name of a person entered by the user
	 */
	public final String getPersonName() {
		return personName;
	}
	/**
	 * 
	 * @param pPersonName PersonName
	 */
	public final void setPersonName(final String pPersonName) {
		personName = pPersonName;
	}
	
}
