import java.util.ArrayList;
import java.util.Arrays;

public class Perceptron {

	private double threshold = 0;
	private double learningRate = -1.1;
//	private int accumulatedWeight = 0;

	private int numOfSynapses;
	
	private ArrayList<Synapse> synapses = new ArrayList<>();

	public Perceptron(int numOfSynapses) {
		this.numOfSynapses = numOfSynapses;
		for (int i = 0; i < numOfSynapses; i++) {
			Synapse temp1 = new Synapse(false);
			synapses.add(temp1);
		}
	}
	
	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public double getLearningRate() {
		return learningRate;
	}

	public void setLearningRate(double learningRate) {
		this.learningRate = learningRate;
	}

	public int getNumOfSynapses() {
		return numOfSynapses;
	}

	public void setNumOfSynapses(int numOfSynapses) {
		this.numOfSynapses = numOfSynapses;
	}

	public Synapse getSynapse(int synapseNum) {
		return synapses.get(synapseNum);
	}
	
	public void setSynapse(int synapseNum, Synapse newSynapse) {
		synapses.set(synapseNum, newSynapse);
	}

	public ArrayList<Synapse> getSynapses() {
		return synapses;
	}

	public void setSynapses(ArrayList<Synapse> synapses) {
		this.synapses = synapses;
	}

	public boolean isFiring() {
		double accumulatedWeight = 0;
		for (Synapse s : synapses) {
			accumulatedWeight += s.getAccumulatedWeight();
		}
		return accumulatedWeight >= threshold;
	}
	
	public void train(boolean expectedValue) {
		for (Synapse temp1 : synapses) {
			temp1.setWeight(temp1.getWeight() * learningRate);
//			System.out.println(toString());
		}
	}
	
	public String toString() {
//		System.out.println("accumulatedWeight: " + accumulatedWeight);
//		for(Synapse s : synapses) {
//			System.out.println("weight: " + s.getWeight());
//			System.out.println("synapse is activated: " + s.isActivated());
//		}
		String string = "[";
		for (Synapse s : synapses) {
			string += s.isActivated() + ",";
		}
		string += (" = " + isFiring() + "]");
		return string;
	}

}
