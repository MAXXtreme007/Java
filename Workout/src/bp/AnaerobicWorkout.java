package bp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.Database;
import db.Parameter;
/**
 * 
 * @author Noah Galey
 *
 */
public class AnaerobicWorkout extends Workout {
	/**
	 * This is the variable for sets that the user enters.
	 */
	private int sets;
	/**
	 * This is the variable for repetitions of the workout 
	 * being done that is given by the user.
	 */
	private int reps;
	/**
	 * This is the amount of weight given by the user for each rep in pounds.
	 */
	private int weight;
	/**
	 * This is the variable for the differential.
	 *  An amount subtracted from the last set given by the user.
	 */
	private int differential;
	/**
	 * 
	 * @return sets The amount of sets given by the user.
	 */
	public final int getSets() {
		return sets;
	}
	/**
	 * 
	 * @param pSets Sets
	 */
	public final void setSets(final int pSets) {
		sets = pSets;
	}
	/**
	 * 
	 * @return reps The amount of reps given by the user.
	 */
	public final int getReps() {
		return reps;
	}
	/**
	 * 
	 * @param pReps Reps
	 */
	public final void setReps(final int pReps) {
		reps = pReps;
	}
	/**
	 * 
	 * @return weight The amount of weight given by the user.
	 */
	public final int getWeight() {
		return weight;
	}
	/**
	 * 
	 * @param pWeight Weight
	 */
	public final void setWeight(final int pWeight) {
		weight = pWeight;
	}
	/**
	 * 
	 * @return differential The differential amount given by the user. 
	 */
	public final int getDifferential() {
		return differential;
	}
	/**
	 * 
	 * @param pDifferential Differential
	 */
	public final void setDifferential(final int pDifferential) {
		differential = pDifferential;
	}
	/**
	 * The method to save all the parameters to the database in correct order.
	 */
	public final void save() {
		
		// save self
		Database myDb = new Database();
		List<Parameter> params = new ArrayList<>();
		
		params.add(new Parameter<Date>(getExerciseDate()));
		params.add(new Parameter<String>(getPersonName()));
		params.add(new Parameter<String>(exerciseName));
		params.add(new Parameter<Integer>(averageHeartRate));
		params.add(new Parameter<String>(notes));
		params.add(new Parameter<Integer>(sets));
		params.add(new Parameter<Integer>(reps));
		params.add(new Parameter<Integer>(weight));
		params.add(new Parameter<Integer>(differential));
		
		myDb.executeSql("usp_SaveExerciseAnaerobic", params);
		
	}	
	
}
