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
 * This business logic class saves all the day's exercises he performs as data
 * in an array list. This allows for multiple workouts to be saved before being
 * saved to the database.
 * 
 * @author MAXX
 */
public class ExerciseDay {
	/**
	 * This variable is the time of the exercise which is entered by the user in
	 * the format of MM:dd:yyyy.
	 */
	private Date exerciseDate;
	/**
	 * This variable is the name of the person who is doing the exercises.
	 */
	private String personName;
	/**
	 * This variable is the average heart rate after an exercise.
	 */
	private int restingHeartRate;
	/**
	 * This variable is the weight of the user at the beginning of the day.
	 */
	private double weight;
	/**
	 * This variable is the total time of all the exercises of the day.
	 */
	private double time;
	/**
	 * This variable tests to see if the date is in a correct format.
	 */
	private ResultSet valid;
	/**
	 * An array list of the cardio workouts added to the list of today's
	 * workouts.
	 */
	private List<ExerciseCardio> exerciseCardios = new ArrayList<>();
	/**
	 * An array list of the competitive workouts added to the list of today's
	 * workouts.
	 */
	private List<ExerciseCompetitive> exerciseCompetitives = new ArrayList<>();
	/**
	 * An array list of the strength workouts added to the list of today's
	 * workouts.
	 */
	private List<ExerciseStrength> exerciseStrengths = new ArrayList<>();

	// **********Assessors************
	/**
	 * This variable is an accessor for exercise date.
	 * 
	 * @return exerciseDate.
	 */
	public final Date getExerciseDate() {
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
	 * This variable is an accessor for resting heart rate.
	 * 
	 * @return restingHeartRate.
	 */
	public final int getRestingHeartRate() {
		return restingHeartRate;
	}

	/**
	 * This variable is an accessor for person's weight.
	 * 
	 * @return weight.
	 */
	public final double getWeight() {
		return weight;
	}

	/**
	 * This variable is an accessor for date and time.
	 * 
	 * @return time.
	 */
	public final double getTime() {
		return time;
	}

	/**
	 * This variable is an accessor for the validity date checker.
	 * 
	 * @return valid.
	 */
	public final ResultSet getValid() {
		return valid;
	}

	// **********Mutators************
	/**
	 * This variable is a mutator for exercise date.
	 * 
	 * @param pExerciseDate
	 *            to set.
	 */
	public final void setExerciseDate(final Date pExerciseDate) {
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
	 * This variable is a mutator for resting heart rate.
	 * 
	 * @param pRestingHeartRate
	 *            to set.
	 */
	public final void setRestingHeartRate(final int pRestingHeartRate) {
		restingHeartRate = pRestingHeartRate;
	}

	/**
	 * This variable is a mutator for person's weight.
	 * 
	 * @param pWeight
	 *            to set.
	 */
	public final void setWeight(final double pWeight) {
		weight = pWeight;
	}

	/**
	 * This variable is a mutator for date and time.
	 * 
	 * @param pTime
	 *            to set.
	 */
	public final void setTime(final double pTime) {
		time = pTime;
	}

	/**
	 * This variable is a mutator for the validity date checker.
	 * 
	 * @param pValid
	 *            to set.
	 */
	public final void setValid(final ResultSet pValid) {
		valid = pValid;
	}

	// CARDIO
	/**
	 * This list returns all the cardio exercises in the list.
	 * 
	 * @return exerciseCardios
	 */
	public final List<ExerciseCardio> getExerciseCardios() {
		return exerciseCardios;
	}

	/**
	 * This variable returns the selected cardio exercise.
	 * 
	 * @param indexPosition of the cardio exercise.
	 * 
	 * @return indexPosition of the cardio exercise.
	 */
	public final ExerciseCardio getExerciseCardio(
			final int indexPosition) {
		return exerciseCardios.get(indexPosition);
	}

	/**
	 * This variable sets the selected cardio exercise.
	 * 
	 * @param pExerciseCardios sets the cardio exercise.
	 */
	public final void setExerciseCardios(
			final List<ExerciseCardio> pExerciseCardios) {
		exerciseCardios = pExerciseCardios;
	}

	/**
	 * This variable adds the selected cardio exercise to the list.
	 * 
	 * @param pExerciseCardios adds the cardio exercise to the list.
	 */
	public final void addExerciseCardio(final ExerciseCardio pExerciseCardios) {
		exerciseCardios.add(pExerciseCardios);
	}

	/**
	 * This variable removes the selected cardio exercise to the list.
	 * 
	 * @param indexPosition removes the cardio exercise from the list.
	 */
	public final void removeExerciseCardio(final int indexPosition) {
		exerciseCardios.remove(indexPosition);
	}

	// COMPETITIVE
	/**
	 * This list returns all the competitive exercises in the list.
	 * 
	 * @return exerciseCompetitives
	 */
	public final List<ExerciseCompetitive> getExerciseCompetitives() {
		return exerciseCompetitives;
	}

	/**
	 * This variable returns the selected competitive exercise.
	 * 
	 * @param indexPosition of the competitive exercise.
	 * 
	 * @return exerciseCompetitives.get(indexPosition)
	 */
	public final ExerciseCompetitive getExerciseCompetitive(
			final int indexPosition) {
		return exerciseCompetitives.get(indexPosition);
	}

	/**
	 * This variable sets the selected competitive exercise.
	 * 
	 * @param pExerciseCompetitives sets the competitive exercise.
	 */
	public final void setExerciseCompetitives(
			final List<ExerciseCompetitive> pExerciseCompetitives) {
		exerciseCompetitives = pExerciseCompetitives;
	}

	/**
	 * This variable adds the selected competitive exercise to the list.
	 * 
	 * @param pExerciseCompetitives adds the competitive exercise to the list.
	 */
	public final void addExerciseCompetitive(
			final ExerciseCompetitive pExerciseCompetitives) {
		exerciseCompetitives.add(pExerciseCompetitives);
	}

	/**
	 * This variable removes the selected competitive exercise to the list.
	 * 
	 * @param indexPosition removes the competitive exercise from the list.
	 */
	public final void removeExerciseCompetitive(final int indexPosition) {
		exerciseCompetitives.remove(indexPosition);
	}

	// STRENGTH
	/**
	 * This list returns all the strength exercises in the list.
	 * 
	 * @return exerciseStrengths
	 */
	public final List<ExerciseStrength> getExerciseStrengths() {
		return exerciseStrengths;
	}

	/**
	 * This variable returns the selected strength exercise.
	 * 
	 * @param indexPosition of the selected strength exercise.
	 * 
	 * @return indexPosition of the selected strength exercise.
	 */
	public final ExerciseStrength getExerciseStrength(
			final int indexPosition) {
		return exerciseStrengths.get(indexPosition);
	}

	/**
	 * This variable sets the selected strength exercise.
	 * 
	 * @param pExerciseStrengths sets to the strength list.
	 */
	public final void setExerciseStrengths(
			final List<ExerciseStrength> pExerciseStrengths) {
		exerciseStrengths = pExerciseStrengths;
	}

	/**
	 * This variable adds the selected strength exercise to the list.
	 * 
	 * @param pExerciseStrengths adds to strength list.
	 */
	public final void addExerciseStrength(
			final ExerciseStrength pExerciseStrengths) {
		exerciseStrengths.add(pExerciseStrengths);
	}

	/**
	 * This variable removes the selected strength exercise to the list.
	 * 
	 * @param indexPosition of the strength exercise.
	 */
	public final void removeExerciseStrength(final int indexPosition) {
		exerciseStrengths.remove(indexPosition);
	}

	// **********Methods************
	/**
	 * This method returns the total time by cycling through all the exercise
	 * times and giving the sum.
	 * 
	 * @return totalTime The calculated sum of all the exercise times.
	 */
	public final int getTotalTime() {
		int totalTime = 0;
		for (ExerciseCardio exc : exerciseCardios) {
			totalTime += exc.getExerciseTime();
		}
		return totalTime;
	}

	/**
	 * This method saves all the user inputed exercise day data to the database.
	 */
	public final void save() {
		// save data to database
		Database myDb = new Database(Exercise.serverName,
				Exercise.databaseName, Exercise.userName, Exercise.password);
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();

		params.add(new Parameter<Date>(getExerciseDate()));
		params.add(new Parameter<String>(getPersonName()));
		params.add(new Parameter<Integer>(getRestingHeartRate()));
		params.add(new Parameter<Double>(weight));

		myDb.executeSql("usp_SaveExerciseDay", params);

		// save all cardio exercises
		for (int i = 0; i < exerciseCardios.size(); i++) {
			exerciseCardios.get(i).save();
		}
		// save all competitive exercises
		for (int i = 0; i < exerciseCompetitives.size(); i++) {
			exerciseCompetitives.get(i).save();
		}
		// save all strength exercises
		for (int i = 0; i < exerciseStrengths.size(); i++) {
			exerciseStrengths.get(i).save();
		}
	}

	/**
	 * This method loads data from the database given the person's name and
	 * date.
	 * 
	 * @param pLocalDate tells database which date to load.
	 * 
	 * @param pName tells database which person to load.
	 */
	public final void load(final LocalDate pLocalDate, final String pName) {
		// establish connection to database
		Database myDb = new Database(Exercise.serverName,
				Exercise.databaseName, Exercise.userName, Exercise.password);
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		params.add(new Parameter<Date>(Exercise.convertToDate(pLocalDate)));
		params.add(new Parameter<String>(pName));
		ResultSet rsDay = myDb.getResultSet("usp_GetExerciseDay", params);

		try {
			if (rsDay.next()) {
				exerciseDate = rsDay.getTimestamp("exerciseDate");
				personName = rsDay.getString("personName");
				restingHeartRate = rsDay.getInt("restingHeartRate");
				weight = rsDay.getInt("weight");

				ResultSet rsCardio = myDb.getResultSet(
						"usp_GetExercisesCardio", params);
				ResultSet rsStrength = myDb.getResultSet(
						"usp_GetExercisesStrength", params);
				ResultSet rsCompetitive = myDb.getResultSet(
						"usp_GetExercisesCompetitive", params);

				exerciseCardios.clear();
				// load cardio properties
				while (rsCardio.next()) {
					ExerciseCardio cardio = new ExerciseCardio();
					cardio.load(pLocalDate, pName,
							rsCardio.getString("ExerciseName"));
					exerciseCardios.add(cardio);
				}

				exerciseStrengths.clear();
				// load strength properties
				while (rsStrength.next()) {
					ExerciseStrength strength = new ExerciseStrength();
					strength.load(pLocalDate, pName,
							rsStrength.getString("ExerciseName"));
					addExerciseStrength(strength);
				}

				exerciseCompetitives.clear();
				// load competitive properties
				while (rsCompetitive.next()) {
					ExerciseCompetitive competitive = new ExerciseCompetitive();
					competitive.load(pLocalDate, pName,
							rsCompetitive.getString("ExerciseName"));
					addExerciseCompetitive(competitive);
				}

			} else {
				throw new InvalidParameterException("Day not found");
			}

		} catch (SQLException e) {
			new SQLException("The day's exercises were not found. "
					+ "Please try again later.");
		}
	}

	/**
	 * This method deletes the user inputed data to the database.
	 */
	public final void delete() {
		Database myDb = new Database(Exercise.serverName,
				Exercise.databaseName, Exercise.userName, Exercise.password);
		// get a list of parameters ready
		@SuppressWarnings("rawtypes")
		List<Parameter> params = new ArrayList<>();
		exerciseDate = getExerciseDate();
		personName = getPersonName();

		// add the parameters
		params.add(new Parameter<Date>(getExerciseDate()));
		params.add(new Parameter<String>(getPersonName()));

		// delete data from database
		myDb.executeSql("usp_DeleteDay", params);
	}
}
