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
 * the user to save information about the competitive exercises he performs.
 * 
 * @author MAXX
 */
public class ExerciseCompetitive extends Exercise {
	/**
	 * This is the variable for competitive time of the exercise.
	 */
	private double competitiveTime;
	/**
	 * This is the variable for whether a user wins a competition or not.
	 */
	private int victorious;

	// **********Assessors************
	/**
	 * This variable is an accessor for the competitive exercise time.
	 * 
	 * @return competitiveTime.
	 */
	public final double getCompetitiveTime() {
		return competitiveTime;
	}
	/**
	 * This variable is an accessor for whether the user wins or not.
	 * 
	 * @return victorious.
	 */
	public final int getVictorious() {
		return victorious;
	}
	
	// **********Mutators************
	/**
	 * This variable is a mutator for the competitive exercise time.
	 * 
	 * @param pCompetitiveTime
	 *            to set.
	 */
	public final void setCompetitiveTime(final double pCompetitiveTime) {
		competitiveTime = pCompetitiveTime;
	}
	/**
	 * This variable is a mutator for whether the user wins or not.
	 * 
	 * @param pVictorious
	 *            to set.
	 */
	public final void setVictorious(final int pVictorious) {
		victorious = pVictorious;
	}
	
	/**
	 * This method saves all the competitive exercise data to the database.
	 */
	@Override
	public final void save() {
		// save data to database
		Database myDb = new Database(Exercise.serverName,
				Exercise.databaseName , Exercise.userName, Exercise.password);
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		params.add(new Parameter<Date>(
				Exercise.convertToDate(getExerciseDate())));
		params.add(new Parameter<String>(getPersonName()));
		params.add(new Parameter<String>(getExerciseName()));
		params.add(new Parameter<Double>(getExerciseTime()));
		params.add(new Parameter<Integer>(getVictorious()));

		myDb.executeSql("usp_SaveExerciseCompetitive", params);
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
		ResultSet rsCompetitive = myDb.getResultSet(
				"usp_GetExerciseCompetitive", params);

		try {
			// load competitive properties
			if (rsCompetitive.next()) {
				setExerciseDate(pDate);
				setPersonName(pName);
				setExerciseName(pExerciseName);
				setExerciseTime(rsCompetitive.getInt("Time"));
			} else {
				throw new InvalidParameterException("Day not found");
			}
		} catch (SQLException e) {
			new SQLException("Error loading competitive exercises. "
					+ "Please try again later.");
		}
	}
}