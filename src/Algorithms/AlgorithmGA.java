package Algorithms;
import java.util.Arrays;
import java.util.Random;

import org.jfree.chart.demo.XYSeriesDemo;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;

import Parameters.GlobalParameters;
import Parameters.ParametersGA;
import Parameters.*;
public class AlgorithmGA {

	Solution[] population = new Solution[GlobalParameters.populationSize];
	Solution[] newPopulation = new Solution[GlobalParameters.populationSize];
	public Solution bestIndividual = new Solution();
	
	public AlgorithmGA() {
		for (int i = 0; i < GlobalParameters.populationSize; i++) {
			population[i] = new Solution();
		}

	}

	public void executeGA() {
		XYSeries series = new XYSeries("FITNESS");
		bestIndividual = bestIndividual(this.population);

		for (int gen = 0; gen < GlobalParameters.numGenerations; gen++) {

			// selection phase
			int counterNewPopInds = 0;

			if (ParametersGA.elitism)
				newPopulation[counterNewPopInds++] = bestIndividual.myCopy();

			Solution parent1 = new Solution();
			Solution parent2 = new Solution();
			Solution son = new Solution();

			while (counterNewPopInds < GlobalParameters.populationSize) {
				if (ParametersGA.selectionMethod == 1) {
					double sumFitness = 0.0;
					for (int i = 0; i < GlobalParameters.populationSize; i++) {
						sumFitness += population[i].fitness();
					}

					parent1 = roulette(sumFitness);
					parent2 = roulette(sumFitness);

				}

				else if (ParametersGA.selectionMethod == 2) {
					parent1 = rankingSelection();
					parent2 = rankingSelection();

				}

				else {

					parent1 = tournament();
					parent2 = tournament();
				}

				if (Math.random() <= ParametersGA.crossoverRate) {
					son = geometricCrossover(parent1, parent2);
					geometricMutation(son);
					newPopulation[counterNewPopInds++] = son;
				}

				else {
					geometricMutation(parent1);
					newPopulation[counterNewPopInds++] = parent1;
					if (counterNewPopInds < GlobalParameters.populationSize) {
						geometricMutation(parent2);
						newPopulation[counterNewPopInds++] = parent2;
					}

				}

			}
			for (int i = 0; i < GlobalParameters.populationSize; i++) {
				population[i] = newPopulation[i].myCopy();

			}
			bestIndividual = bestIndividual(this.population);
			series.add(gen, bestIndividual.fitness);

		}
		final XYSeriesDemo demo = new XYSeriesDemo("XY Series Demo", series);
	    demo.pack();
	    RefineryUtilities.centerFrameOnScreen(demo);
	    demo.setVisible(true);

	}

	private Solution geometricCrossover(Solution parent1, Solution parent2) {

		Solution son = new Solution();
		for (int i = 0; i < GlobalParameters.vectorLength; i++) {
			double R;
			double aux;
			do {
				R = Math.random();
				aux = (R * parent1.structure[i]) + ((1.0 - R) * parent2.structure[i]);

			} while (aux < 0.0 || aux > Math.PI);
			son.structure[i] = aux;
		}
		return son;
	}

	private Solution geometricMutation(Solution son) {

		for (int i = 0; i < GlobalParameters.vectorLength; i++) {
			if (Math.random() <= ParametersGA.mutationRate) {

				double R;
				double aux;
				do {
					R = (Math.random() * 2 * ParametersGA.mutationStep - ParametersGA.mutationStep);
					aux = son.structure[i] + R;
				} while (aux < 0.0 || aux > Math.PI);

				son.structure[i] = aux;

			}
		}
		return son;
	}

	private Solution rankingSelection() {
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

	private Solution roulette(double sumOfFitness) {
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

	private Solution tournament() {
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
