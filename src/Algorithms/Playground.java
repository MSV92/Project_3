package Algorithms;
import Parameters.GlobalParameters;

public class Playground {

	public static void main(String[] args) {

		AlgorithmGA awesomeAlgorithm = new AlgorithmGA();

		awesomeAlgorithm.executeGA();

		System.out.println("Best solution found: fitness = " + awesomeAlgorithm.bestIndividual.fitness);
		for (int i = 0; i < GlobalParameters.vectorLength; i++) {
			System.out.print(awesomeAlgorithm.bestIndividual.structure[i] + "\n");
		}

		AlgorithmPS briliantAlgorithm = new AlgorithmPS();

		briliantAlgorithm.executePS();

		System.out.println("Best solution found: fitness = " + briliantAlgorithm.globalBest.fitness);
		for (int i = 0; i < GlobalParameters.vectorLength; i++) {
			System.out.print(briliantAlgorithm.globalBest.structure[i] + "\n");
		}

	}

}
