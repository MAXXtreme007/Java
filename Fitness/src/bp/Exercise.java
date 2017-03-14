package bp;

import java.time.LocalDate;
import java.util.Date;

/**
 * This is the business logic class for the fitness program. This program allows
 * the user to save various information about the exercises he performs.
 * 
 * @author MAXX
 */
public abstract class Exercise {
	
	// server 1: 192.168.1.1
	// server 2:
	public static String serverName="192.168.1.1";
	public static String databaseName = "qc";
	public static String userName = "root";
	public static String password = "test";
	
	/**
	 * This is for deprecating the date.
	 * 
	 * @param date of the exercises.
	 * @return date of the exercises.
	 */
	@SuppressWarnings("deprecation")
	public static Date convertToDate(final LocalDate date) {
		return new Date(date.getMonthValue() + "/" + date.getDayOfMonth() 
				+ "/" + date.getYear());
	}
	/**
	 * This variable is the time of the exercise which is entered by the user in
	 * the format of MM:dd:yyyy.
	 */
	private LocalDate exerciseDate;
	/**
	 * This variable is the name of the person who is doing the exercises.
	 */
	private String personName;
	/**
	 * This variable is the name of the exercises - characterized by type.
	 */
	private String exerciseName;
	/**
	 * This variable is the total time the exercises took.
	 */
	private double totalTime;
	

	// **********Assessors************
	/**
	 * This variable is an accessor for exercise date.
	 * 
	 * @return exerciseDate.
	 */
	public final LocalDate getExerciseDate() {
		return exerciseDate;
	}
	/**
	 * This variable is an accessor for person's name.
	 * 
	 * @return personName.
	 */
	public final String getPersonName() {
		return personName;
	}
	/**
	 * This variable is an accessor for exercise's name.
	 * 
	 * @return exerciseName.
	 */
	public final String getExerciseName() {
		return exerciseName;
	}
	
	/**
	 * This variable is an accessor for date and time.
	 * 
	 * @return time.
	 */
	public final double getExerciseTime() {
		return totalTime;
	}

	// **********Mutators************
	/**
	 * This variable is a mutator for exercise date.
	 * 
	 * @param pExerciseDate
	 *            to set.
	 */
	public final void setExerciseDate(final LocalDate pExerciseDate) {
		exerciseDate = pExerciseDate;
	}
	/**
	 * This variable is a mutator for person's name.
	 * 
	 * @param pPersonName
	 *            to set.
	 */
	public final void setPersonName(final String pPersonName) {
		personName = pPersonName;
	}
	/**
	 * This variable is a mutator for exercise's name.
	 * 
	 * @param pExerciseName
	 *            to set.
	 */
	public final void setExerciseName(final String pExerciseName) {
		exerciseName = pExerciseName;
	}
	/**
	 * This variable is a mutator for date and time.
	 * 
	 * @param pTime
	 *            to set.
	 */
	public final void setExerciseTime(final double pTime) {
		totalTime = pTime;
	}

	/**
	 * This method saves all the user inputed data to the database.
	 */
	public abstract void save();
}
