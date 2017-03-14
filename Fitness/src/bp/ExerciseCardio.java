package bp;

import java.security.InvalidParameterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.Database;
import db.Parameter;

/**
 * This business logic class extends the functionality of exercise by allowing
 * the user to save information about the cardio exercises he performs.
 * 
 * @author MAXX
 */
public class ExerciseCardio extends Exercise {
	/**
	 * This is the variable for cardio time of the exercise.
	 */
	private double cardioTime;
	/**
	 * This is the variable for distance that the person went.
	 */
	private double distance;
	/**
	 * This is the variable for the maximum heart rate.
	 */
	private int maxHeartRate;

	// **********Assessors************
	/**
	 * This variable is an accessor for distance traveled.
	 * 
	 * @return distance.
	 */
	public final double getDistance() {
		return distance;
	}

	/**
	 * This variable is an accessor for the maximum heart rate.
	 * 
	 * @return maxHeartRate.
	 */
	public final int getMaxHeartRate() {
		return maxHeartRate;
	}

	/**
	 * This variable is an accessor for the cardio exercise time.
	 * 
	 * @return exerciseTime.
	 */
	public final double getCardioTime() {
		return cardioTime;
	}

	// **********Mutators************
	/**
	 * This variable is a mutator for distance traveled.
	 * 
	 * @param pDistance
	 *            to set.
	 */
	public final void setDistance(final double pDistance) {
		distance = pDistance;
	}

	/**
	 * This variable is a mutator for the maximum heart rate.
	 * 
	 * @param pMaxHeartRate
	 *            to set.
	 */
	public final void setMaxHeartRate(final int pMaxHeartRate) {
		maxHeartRate = pMaxHeartRate;
	}
	
	/**
	 * This variable is a mutator for the cardio exercise time.
	 * 
	 * @param pCardioTime
	 *            to set.
	 */
	public final void setCardioTime(final double pCardioTime) {
		cardioTime = pCardioTime;
	}

	/**
	 * This method saves all the user inputed cardio exercise data to the
	 * database.
	 */
	@Override
	public final void save() {
		// save data to database
		Database myDb = new Database(Exercise.serverName, 
				Exercise.databaseName, Exercise.userName, Exercise.password);
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		params.add(new Parameter<Date>(
				Exercise.convertToDate(getExerciseDate())));
		params.add(new Parameter<String>(getPersonName()));
		params.add(new Parameter<String>(getExerciseName()));
		params.add(new Parameter<Double>(getExerciseTime()));
		params.add(new Parameter<Double>(distance));
		params.add(new Parameter<Integer>(getMaxHeartRate()));

		myDb.executeSql("usp_SaveExerciseCardio", params);
	}

	/**
	 * This method loads data from the database given the person's name and
	 * date.
	 * 
	 * @param pDate
	 *            tells database which date to load.
	 * 
	 * @param pName
	 *            tells database which person to load.
	 *
	 * @param pExerciseName
	 *            tells database which row ID to load.				
	 */
	public final void load(final LocalDate pDate, 
			final String pName, final String pExerciseName) {
		// establish connection to database
		Database myDb = new Database(Exercise.serverName, 
				Exercise.databaseName, Exercise.userName, Exercise.password);
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		params.add(new Parameter<Date>(Exercise.convertToDate(pDate)));
		params.add(new Parameter<String>(pName));
		params.add(new Parameter<String>(pExerciseName));
		ResultSet rsCardio = myDb.getResultSet("usp_GetExerciseCardio", params);

		try {
			// load cardio properties
			if (rsCardio.next()) {
				setExerciseDate(pDate);
				setPersonName(pName);
				setExerciseName(pExerciseName);
				setExerciseTime(rsCardio.getDouble("Time"));
				setDistance(rsCardio.getDouble("Distance"));
				setMaxHeartRate(rsCardio.getInt("MaxHeartRate"));
			} else {
				throw new InvalidParameterException("Day not found");
			}
		//BERKSTRESSER: THIS CATCH IS PREVENTING A PROBLEM THAT SHOULD CRASH
		} catch (Exception e) {
			new SQLException("Error loading cardio exercises. "
					+ "Please try again later.");
		}
	}
}
