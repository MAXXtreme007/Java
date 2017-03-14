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
public class AerobicWorkout extends Workout {
	/**
	 * A constant variable for the number 1000.0.
	 */
	private static final double ONE_THOUSAND = 1000.0;
	/**
	 * A constant variable for the number 60.0.
	 */
	private static final double SIXTY = 60.0;
	/**
	 * This is the variable for distance that
	 *  the user will enter given in meters.
	 */
	private double distance;
	/**
	 * This is the Start Time of when the exercise began,
	 *  which is entered by the user in the format of HH:mm:ss.
	 */
	private Date startTime;
	/**
	 * This is the End Time of when the exercise was completed,
	 *  which is entered by the user in the format of HH:mm:ss.
	 */
	private Date endTime;
	/**
	 * 
	 * @return distance The distance of the exercise entered by the user.
	 */
	public final double getDistance() {
		return distance;
	}
	/**
	 * 
	 * @param pDistance Distance
	 */
	public final void setDistance(final double pDistance) {
		distance = pDistance;
	}
	/**
	 * 
	 * @return startTime The beginning time of the exercise given by the user.
	 */
	public final Date getStartTime() {
		return startTime;
	}
	/**
	 * 
	 * @param pStartTime StartTime
	 */
	public final void setStartTime(final Date pStartTime) {
		startTime = pStartTime;
	}
	/**
	 * 
	 * @return endTime The ending time of the exercise given by the user.
	 */
	public final Date getEndTime() {
		return endTime;
	}
	/**
	 * 
	 * @param pEndTime EndTime
	 */
	public final void setEndTime(final Date pEndTime) {
		endTime = pEndTime;
	}
	/**
	 * 
	 * @return totalTimeMillis The total time 
	 * in milliseconds then converted to minutes.
	 */
	public final int getTotalTime() {
		long totalTimeMillis = endTime.getTime() - startTime.getTime();
		return (int) (totalTimeMillis / ONE_THOUSAND / SIXTY);
	}
//	public final void setTotalTime() {
//		totalTime += (int) (endTime.getTime() - startTime.getTime());
//	}
	/**
	 * The function to save all the
	 *  information from the aerobic workout to the database. 
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
		params.add(new Parameter<Double>(distance));
		params.add(new Parameter<Date>(startTime));
		params.add(new Parameter<Date>(endTime));
		
		myDb.executeSql("usp_SaveExerciseAerobic", params);
		
	}
	
}
