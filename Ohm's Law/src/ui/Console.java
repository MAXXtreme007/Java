package ui;
import bp.Circuit;
public class Console {

	public static void main(String[] args) {
		Circuit myCircuit = new Circuit();
		myCircuit.calculateAmperage();
		System.out.println("The purpose of this program is to calculate the voltage, amperage, or resistance of a given circuit.");
	}
}
