public class Synapse {
	private boolean activated;
	private double weight = 0.1;
	
	public Synapse(boolean pActivated) {
		activated = pActivated;
	}
	
	public Synapse(int pActivated) {
		if (pActivated == 0) {
			activated = false;
		} else {
			activated = true;
		}
	}
	
	public boolean isActivated() {
		return activated;
	}
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getAccumulatedWeight() {
		int multiplier = 0;
		if (activated) {
			multiplier = 1;
		}
		return weight * multiplier;
	}
	
	public void addAccumulatedWeight(double learningRate) {
		weight += (activated ? learningRate : 0);
	}
}
