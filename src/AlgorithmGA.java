import java.util.Arrays;
import java.util.Random;

public class AlgorithmGA {
	
	Solution[] population = new Solution[GlobalParameters.populationSize];
	Solution[] newPopulation = new Solution[GlobalParameters.populationSize];
//	Solution bestFound = new Solution();
	
	AlgorithmGA() {
		for (int i = 0; i < GlobalParameters.populationSize; i++) {
			population[i] = new Solution();
		}
		
		
	}
	
	

	Solution bestFound = bestIndividual(population);
	
	private Solution rankingSelection(Solution[] population) {
		Solution[] toSort = new Solution[GlobalParameters.populationSize];
		for (int i = 0; i < GlobalParameters.populationSize; i++) {
			toSort[i] = population[i].myCopy();
		}
		Arrays.sort(toSort);

		double accumulatedProbabilities = 0.0;
		double[] sliceSize = new double[GlobalParameters.populationSize];
		for (int i = 0; i < GlobalParameters.populationSize; i++) {
			accumulatedProbabilities += ((i + 1) / GlobalParameters.populationSize);
			sliceSize[i] = accumulatedProbabilities;
		}

		double lottery = Math.random();

		for (int i = 0; i < GlobalParameters.populationSize; i++) {
			if (i < (GlobalParameters.populationSize - 2) && lottery >= sliceSize[i] && lottery < sliceSize[i + 1])
				return population[i].myCopy();
		}

		return population[GlobalParameters.populationSize - 1].myCopy();
	}

	private Solution roulette(Solution[] population, double sumOfFitness) {
		// My pizza
		double[] sliceSize = new double[GlobalParameters.populationSize];
		double accumulatedProbability = 0.0;
		for (int i = 0; i < GlobalParameters.populationSize; i++) {
			accumulatedProbability += (population[i].fitness() / sumOfFitness);
			sliceSize[i] = accumulatedProbability;
		}

		double lottery = Math.random();
		for (int i = 0; i < GlobalParameters.populationSize; i++) {
			if (i < (GlobalParameters.populationSize - 2) && lottery >= sliceSize[i] && lottery < sliceSize[i + 1])
				return population[i].myCopy();
		}

		return population[GlobalParameters.populationSize - 1].myCopy();

	}

	private Solution tournament(Solution[] population) {
		Random r = new Random();
		
		Solution[] competitors = new Solution[ParametersGA.tournamentSize];
		for (int i = 0; i < ParametersGA.tournamentSize; i++) {
			int index = r.nextInt(GlobalParameters.populationSize);
			competitors[i] = population[index].myCopy();
		}

		return bestIndividual(competitors);
	}
	
	private Solution bestIndividual(Solution[] population) {
		Solution bestFound = population[0].myCopy();
		for (int i = 1; i < population.length; i++) {
			if (population[i].betterOrEqual(bestFound))
				bestFound = population[i].myCopy();
		}
		return bestFound;
	}
}
