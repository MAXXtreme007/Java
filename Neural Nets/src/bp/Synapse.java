package bp;
public class Synapse {
	private boolean activated;
	private double weight = .1;
	
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
		return weight * (activated ? 1 : 0);
	}
	public void addWeight(double learningRate) {
		weight += (activated ? learningRate : 0);
	}
}
