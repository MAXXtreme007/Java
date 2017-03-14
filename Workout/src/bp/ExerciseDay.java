package bp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import db.Database;
import db.Parameter;

/**
 * The purpose of this program is to save the given information on anaerobic
 * exercises and aerobic exercise as an array. Multiple workouts will be saved
 * through add them to a list of workouts.
 * 
 * @author Noah Galey
 *
 */
public class ExerciseDay {
	/**
	 * The variable of the date of the exercise entered by the user.
	 */
	private Date exerciseDate;
	/**
	 * The variable of the name of the person completing the exercises entered
	 * by the user.
	 */
	private String personName;
	/**
	 * An array list of the anaerobic workouts added to the list of completed
	 * workouts.
	 */
	private List<AnaerobicWorkout> anaerobicWorkouts = new ArrayList<>();
	/**
	 * An array list of the aerobic workouts added to the list of completed
	 * workouts.
	 */
	private List<AerobicWorkout> aerobicWorkouts = new ArrayList<>();

	/**
	 * 
	 * @return exerciseDate The day of the exercise.
	 */
	public final Date getExerciseDate() {
		return exerciseDate;
	}

	/**
	 * 
	 * @param pExerciseDate
	 *            ExerciseDate
	 */
	public final void setExerciseDate(final Date pExerciseDate) {
		exerciseDate = pExerciseDate;
		for (int n = 0; n < anaerobicWorkouts.size(); ++n) {
			anaerobicWorkouts.get(n).setExerciseDate(pExerciseDate);
		}
		for (int n = 0; n < aerobicWorkouts.size(); ++n) {
			aerobicWorkouts.get(n).setExerciseDate(pExerciseDate);
		}

	}

	/**
	 * 
	 * @return totalTime The total amount of time exercised.
	 */
	public final int getTotalTime() {
		int totalTime = 0;

		for (int n = 0; n < aerobicWorkouts.size(); ++n) {
			totalTime += aerobicWorkouts.get(n).getTotalTime();
		}

		return totalTime;
	}

	/**
	 * 
	 * @return personName The name of the person entered for the exercise.
	 */
	public final String getPersonName() {
		return personName;
	}

	/**
	 * 
	 * @param pPersonName
	 *            PersonName
	 */
	public final void setPersonName(final String pPersonName) {
		personName = pPersonName;
		for (int n = 0; n < anaerobicWorkouts.size(); ++n) {
			anaerobicWorkouts.get(n).setPersonName(pPersonName);
		}
		for (int n = 0; n < aerobicWorkouts.size(); ++n) {
			aerobicWorkouts.get(n).setPersonName(pPersonName);
		}
	}

	/**
	 * 
	 * @return anaerobicWorkouts An array list of the anaerobic workouts.
	 */
	public final List<AnaerobicWorkout> getAnaerobicWorkouts() {
		return anaerobicWorkouts;
	}

	/**
	 * 
	 * @param pAnaerobicWorkouts
	 *            Anaerobic Workouts
	 */
	public final void setAnaerobicWorkouts(
			final List<AnaerobicWorkout> pAnaerobicWorkouts) {
		anaerobicWorkouts = pAnaerobicWorkouts;
	}

	/**
	 * 
	 * @return aerobicWorkouts An array list of the aerobic workouts.
	 */
	public final List<AerobicWorkout> getAerobicWorkouts() {
		return aerobicWorkouts;
	}

	/**
	 * 
	 * @param pAerobicWorkouts
	 *            Aerobic Workouts
	 */
	public final void setAerobicWorkouts(
			final List<AerobicWorkout> pAerobicWorkouts) {
		aerobicWorkouts = pAerobicWorkouts;
	}

	/**
	 * 
	 * @param indexOfWorkoutToGet
	 *            IndexOfWorkoutToGet
	 * @return anaerboicWorkouts Getting the indexofworkoutstoget from the
	 *         anaerobic workouts.
	 */
	public final AnaerobicWorkout getAnaerobicWorkout(
			final int indexOfWorkoutToGet) {
		return anaerobicWorkouts.get(indexOfWorkoutToGet);
	}

	/**
	 * 
	 * @param indexOfWorkoutToGet
	 *            IndexOfWorkoutToGet
	 * @return aerboicWorkouts Getting the indexofworkoutstoget from the aerobic
	 *         workouts.
	 */
	public final AerobicWorkout getAerobicWorkout(
			final int indexOfWorkoutToGet) {
		return aerobicWorkouts.get(indexOfWorkoutToGet);
	}

	/**
	 * 
	 * @param pAnaerobicWorkout
	 *            Anaerobic workouts
	 */
	public final void addAnaerobicWorkout(
			final AnaerobicWorkout pAnaerobicWorkout) {
		anaerobicWorkouts.add(pAnaerobicWorkout);
	}

	/**
	 * 
	 * @param pAerboicWorkout
	 *            AerobicWorkout
	 */
	public final void addAerobicWorkout(final AerobicWorkout pAerboicWorkout) {
		aerobicWorkouts.add(pAerboicWorkout);
	}

	/**
	 * 
	 * @param pIndexToRemove
	 *            IndexToRemove
	 */
	public final void removeAerobicWorkout(final int pIndexToRemove) {
		aerobicWorkouts.remove(pIndexToRemove);
	}

	/**
	 * 
	 * @param pIndexToRemove
	 *            IndexToRemove
	 */
	public final void removeAnaerobicWorkout(final int pIndexToRemove) {
		anaerobicWorkouts.remove(pIndexToRemove);
	}

	/**
	 * The method to save all the parameters to the database in correct order.
	 */
	public final void save() {
		// save self
		Database myDb = new Database();
		List<Parameter> params = new ArrayList<>();

		params.add(new Parameter<Date>(exerciseDate));
		params.add(new Parameter<String>(personName));
		params.add(new Parameter<Integer>(getTotalTime()));

		myDb.executeSql("usp_SaveExerciseDay", params);

		// save all aerobic exercises
		for (int n = 0; n < aerobicWorkouts.size(); ++n) {
			aerobicWorkouts.get(n).save();
		}
		// save all anaerobic exercises
		for (int n = 0; n < anaerobicWorkouts.size(); ++n) {
			anaerobicWorkouts.get(n).save();
		}

	}
	/**
	 * This is the method to load from the database an exercise.
	 * @param pPersonName Person Name
	 * @param pExerciseDate Exercise Date
	 */
	public final void load(final String pPersonName, final Date pExerciseDate) {
		Database myDb = new Database();
		List<Parameter> params = new ArrayList<>();

		params.add(new Parameter<Date>(pExerciseDate));
		params.add(new Parameter<String>(pPersonName));

		ResultSet exerciseDay = myDb.getResultSet("usp_GetExerciseDay", params);

		try {
			if (exerciseDay.next()) {
				exerciseDate = exerciseDay.getDate("ExerciseDate");
				personName = exerciseDay.getString("PersonName");

				// add all aerobic workouts
				ResultSet rsAerobics = myDb.getResultSet(
						"usp_GetExercisesAerobic", params);
				while (rsAerobics.next()) {
					AerobicWorkout aerobic = new AerobicWorkout();
					aerobic.setAverageHeartRate(rsAerobics
							.getInt("AverageHeartRate"));
					aerobic.setDistance(rsAerobics.getDouble("Distance"));
					aerobic.setStartTime(rsAerobics.getTime("StartTime"));
					aerobic.setEndTime(rsAerobics.getTime("EndTime"));
					aerobic.setNotes(rsAerobics.getString("Notes"));
					aerobic.setExerciseName(rsAerobics
							.getString("ExerciseName"));
					
					aerobic.setExerciseDate(pExerciseDate);
					aerobic.setPersonName(pPersonName);

					addAerobicWorkout(aerobic);
				}
				
				// TODO: add all anaerobic workouts
				ResultSet rsAnaerobics = myDb.getResultSet(
						"usp_GetExercisesAnaerobic", params);
				while (rsAnaerobics.next()) {
					AnaerobicWorkout anaerobic = new AnaerobicWorkout();
					anaerobic.setAverageHeartRate(rsAnaerobics
							.getInt("AverageHeartRate"));
					anaerobic.setWeight(rsAnaerobics.getInt("Weight"));
					anaerobic.setReps(rsAnaerobics.getInt("Reps"));
					anaerobic.setSets(rsAnaerobics.getInt("Sets"));
					anaerobic.setDifferential(rsAnaerobics
							.getInt("Differential"));
					anaerobic.setNotes(rsAnaerobics.getString("Notes"));
					anaerobic.setExerciseName(rsAnaerobics
							.getString("ExerciseName"));

					anaerobic.setExerciseDate(pExerciseDate);
					anaerobic.setPersonName(pPersonName);

					addAnaerobicWorkout(anaerobic);
				}

			} else {
				JOptionPane
				.showMessageDialog(
						null,
						"Exercise Date and Person Name not found!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
