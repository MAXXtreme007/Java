package main;

/*
 Purpose:The program will calculate either
 voltage, resistance, or amperage of a circuit
 given an input of two of the three from the user.

 Allow the user to define two known variables
 so that the the unknown can be calculated.

 If the user needs to calculate voltage,
 the user will press 1 to declare it as the unknown variable.
 If the user declares voltage as the unknown variable,
 this formula will be used: voltage = amperage * resistance
 The user will then enter in the amperage and resistance values,
 so that voltage can be calculated.

 If the user needs to calculate the resistance,
 the user will press 2 to declare it as the unknown variable.
 If the user declares resistance as the unknown variable,
 this formula will be used: resistance = voltage / amperage
 The user will then enter in the voltage and amperage values,
 so that voltage can be calculated.

 If the user needs to calculate amperage,
 the user will press 3 to declare it as the unknown variable.
 If the user declares amperage as the unknown variable,
 this formula will be used: amperage = voltage / resistance
 The user will then enter in the voltage and resistance values,
 so that voltage can be calculated.

 Display a message with the calculated unknown back to the user

 After the program has calculated the unknown variable for the user,
 the program will ask the user if he wishes to repeat the program.
 If the user wishes to calculate another unknown, he will press 2.
 If he does not wish to calculate another, he will press 1.

 Also with this lab, this program has a generated javadoc file
 */

/**
 * This class project allows the user to input two known values so that the
 * unknown value can be calculated.
 * 
 * This program contains the business class
 * along with an attached javadoc file.
 * 
 * @author MAXXtreme
 *
 */

public class OhmsLawBusiness {
	int formula;
	double voltage;
	double resistance;
	double amperage;

	/**
	 * This is the method for calculating the voltage of a circuit.
	 */
	public double calculateVoltage() {
		voltage = amperage * resistance;
		return voltage;
	}
	
	/**
	 * This is the method for calculating the resistance of a circuit.
	 */
	public double calculateResistance() {
		resistance = voltage / amperage;
		return resistance;
	}
	
	/**
	 * This is the method for calculating the amperage of a circuit.
	 */
	public double calculateAmperage() {
		amperage = voltage / resistance;
		return amperage;
	}
}
