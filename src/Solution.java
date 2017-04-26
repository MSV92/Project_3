public class Solution implements Comparable{

	protected double[] structure;
	protected double fitness;
	protected boolean alreadyCalculated;

	public Solution() {

		structure = new double[GlobalParameters.vectorLength];

		for (int i = 0; i < structure.length - 1; i++) {
			structure[i] = Math.random() * Math.PI;
		}

		alreadyCalculated = false;
	}

	public Solution myCopy() {
		Solution newSolution = new Solution();
		for (int i = 0; i < GlobalParameters.vectorLength; i++) {
			newSolution.structure[i] = this.structure[i];
		}
		return newSolution;
	}

	public double fitness() {

		if (!alreadyCalculated) {

			for (int i = 0; i < GlobalParameters.vectorLength; i++) {
				fitness += Math.sin(structure[i])
						* Math.pow(Math.sin(Math.pow(structure[i], 2) * i / Math.PI), GlobalParameters.m * 2);
			}

			fitness *= -1.0;

			alreadyCalculated = true;
		}

		return fitness;

	}

	public void print() {
		printStructure();
		System.out.println("Fitness = " + fitness);
	}

	public void printStructure() {
		for (int i = 0; i < GlobalParameters.vectorLength; i++) {
			System.out.print((int) structure[i] + " ");
		}
		System.out.println();
	}

	// betterOrEqual
	public boolean betterOrEqual(Solution s) {
		if (GlobalParameters.Maximization)
			return (this.fitness() >= s.fitness());
		else
			return (this.fitness() <= s.fitness());

	}
	
	public int compareTo(Object beingCompared) {
		Solution converted = (Solution) beingCompared;
		if (this.fitness() > converted.fitness()) {
			return 1;
		}

		else if (this.fitness() < converted.fitness) {
			return -1;
		}
		return 0;
	}
}
