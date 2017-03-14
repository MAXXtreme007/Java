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
 * the user to save information about the strength exercises he performs.
 * 
 * @author MAXX
 */
public class ExerciseStrength extends Exercise {
	/**
	 * This is the variable for strength time of the exercise.
	 */
	private double strengthTime;
	/**
	 * This is the variable for number of reps the user did.
	 */
	private int reps;
	/**
	 * This is the variable for number of sets the user did.
	 */
	private int sets;
	/**
	 * This is the variable for weight the user lifted.
	 */
	private double weightLifted;

	// **********Assessors************
	/**
	 * This variable is an accessor for the strength exercise time.
	 * 
	 * @return strengthTime.
	 */
	public final double getStrengthTime() {
		return strengthTime;
	}
	/**
	 * This variable is an accessor for number of reps the user did.
	 * 
	 * @return reps.
	 */
	public final int getReps() {
		return reps;
	}

	/**
	 * This variable is an accessor for number of sets the user did.
	 * 
	 * @return sets.
	 */
	public final int getSets() {
		return sets;
	}

	/**
	 * This variable is an accessor for weight the user lifted.
	 * 
	 * @return weight.
	 */
	public final double getWeightLifted() {
		return weightLifted;
	}

	// **********Mutators************
	/**
	 * This variable is a mutator for the strength exercise time.
	 * 
	 * @param pStrengthTime
	 *            to set.
	 */
	public final void setCompetitiveTime(final double pStrengthTime) {
		strengthTime = pStrengthTime;
	}
	/**
	 * This variable is a mutator for number of reps the user did.
	 * 
	 * @param pReps
	 *            to set.
	 */
	public final void setReps(final int pReps) {
		reps = pReps;
	}

	/**
	 * This variable is a mutator for number of sets the user did.
	 * 
	 * @param pSets
	 *            to set.
	 */
	public final void setSets(final int pSets) {
		sets = pSets;
	}

	/**
	 * This variable is a mutator for weight the user lifted.
	 * 
	 * @param pWeightLifted
	 *            to set.
	 */
	public final void setWeightLifted(final double pWeightLifted) {
		weightLifted = pWeightLifted;
	}

	/**
	 * This method saves all the user inputed strength exercise data to the
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
		params.add(new Parameter<Integer>(sets));
		params.add(new Parameter<Integer>(reps));
		params.add(new Parameter<Double>(weightLifted));

		myDb.executeSql("usp_SaveExerciseStrength", params);
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
		ResultSet rsStrength = myDb.getResultSet("usp_GetExerciseStrength",
				params);

		try {
			// load strength properties
			if (rsStrength.next()) {
				setExerciseDate(pDate);
				setPersonName(pName);
				setReps(rsStrength.getInt("Reps"));
				setSets(rsStrength.getInt("Sets"));
				setWeightLifted(rsStrength.getInt("Weight"));
				setExerciseName(pExerciseName);
				setExerciseTime(rsStrength.getInt("Time"));
				// setNotes(rsStrength.getText);
			} else {
				throw new InvalidParameterException("Day not found");
			}
		} catch (SQLException e) {
			new SQLException(
					"Error loading strength exercises. "
					+ "Please try again later.");
		}
	}
}