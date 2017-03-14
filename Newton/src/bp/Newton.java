package bp;

import java.security.InvalidParameterException;

/*
 * The Business Logic
 * 
 * PROPERTIES:
 * Include all the variables
 * 
 * PROCESSING:
 * In this lab, all the processing is done in the business class.
 * The mass and velocity will be asked in the presentation class which
 * will then be given to the business class.
 * 
 * To calculate the momentum of the object, the program will use the formula:
 * momentum = mass * velocity
 * To calculate the kinetic energy, the program will use the formula:
 * energy = 0.5 * mass * velocity * velocity
 * 
 * Both the momentum and kinetic energy will then be delivered to the 
 * presentation class where they will be displayed to the user.
 */

/**
 * The mass and velocity of the object are defined in the presentation class,
 * and those values are used to calculate both the momentum and kinetic energy
 * of the object in the business logic portion of the program.
 * 
 * This program contains the business class along with an attached javadoc file.
 * 
 * @author MAXXtreme
 *
 */

public final class Newton {
	// properties
	/**
	 * This variable asks the user for the mass of an object.
	 */
	private double mass;
	/**
	 * This variable asks the user for the velocity of an object.
	 */
	private double velocity;
	/**
	 * This variable is a place holder for one half.
	 */
	static final double HALF = 0.5;

	// **********Assessors************
	/**
	 * This variable to used to get the mass of the object.
	 *
	 * @return mass of the object.
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * This variable to used to get the velocity of the object.
	 *
	 * @return velocity of the object.
	 */
	public double getVelocity() {
		return velocity;
	}

	// **********Mutators************
	/**
	 * This variable is a mutator for mass.
	 * 
	 * @param pMass
	 *            to set.
	 */
	public void setMass(final double pMass) {
		if (pMass < 0) {
			throw new InvalidParameterException("Mass must be positive!");
		} else {
			mass = pMass;
		}
	}

	/**
	 * This method is used to get and set velocity.
	 * 
	 * @param pVelocity
	 *            to set.
	 */
	public void setVelocity(final double pVelocity) {
		if (pVelocity < 0) {
			throw new InvalidParameterException("Velocity must be positive!");
		} else {
			velocity = pVelocity;
		}
	}

	// processing
	/**
	 * This method calculates momentum by multiplying mass by velocity.
	 * 
	 * @return momentum The momentum of a given object.
	 */
	public double calculateMomentum() {
		double momentum = mass * velocity;
		return momentum;
	}

	/**
	 * This method calculates momentum by using the formula 1/2 * mass *
	 * velocity * velocity.
	 * 
	 * @return energy The energy of a given object.
	 */
	public double calculateEnergy() {
		double energy = HALF * mass * velocity * velocity;
		return energy;
	}
}