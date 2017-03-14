package bp;

public class Circuit implements ICircuit{
	private double voltage;
	private double amperage;
	private double resistance;
	
	public double getVoltage() {
		return voltage;
	}

	public double getAmperage() {
		return amperage;
	}

	public double getResistance() {
		return resistance;
	}

	public void setVoltage(double pVoltage) {
		voltage = pVoltage;
	}

	public void setAmperage(double pAmperage) {
		amperage = pAmperage;
	}

	public void setResistance(double pResistance) {
		resistance = pResistance;
	}

	public void calculateVoltage() {
		voltage = amperage * resistance;
	}

	public void calculateAmperage() {
		amperage = voltage / resistance;
	}

	public void calculateResistance() {
		resistance = voltage / amperage;
	}
}
