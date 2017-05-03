
public class AlgorithmPS {
	
	Solution[] population = new Solution[GlobalParameters.populationSize];
	Solution[] localBest = new Solution[GlobalParameters.populationSize];
	Solution globalBest = new Solution();

	public AlgorithmPS() {
		
		for (int i = 0; i < GlobalParameters.populationSize; i++) {
			population[i] = new Solution(1);
			localBest[i] = population[i].myCopy();
		}
	}
	
	protected void executePS(){
		
		globalBest = best();

		// Main loop of PSO
		for (int gen = 0; gen < GlobalParameters.numGenerations; gen++) {

			for (int i = 0; i < GlobalParameters.populationSize; i++) {

				for (int j = 0; j < GlobalParameters.vectorLength; j++) {
					// 3 componentes
					population[i].velocity[j] = (ParametersPS.W * population[i].velocity[j])
							+ (ParametersPS.C1 * Math.random() * (localBest[i].structure[j] - population[i].structure[j]))
							+ (ParametersPS.C2 * Math.random() * (globalBest.structure[j] - population[i].structure[j]));

					population[i].structure[j] += population[i].velocity[j];
					
					if (population[i].structure[j] > ParametersPS.MAX){
						population[i].structure[j] = ParametersPS.MAX;
						population[i].velocity[j] = (-1.0) * population[i].velocity[j];
					}
					
					else if (population[i].structure[j] < ParametersPS.MIN){
						population[i].structure[j] = ParametersPS.MIN;
						population[i].velocity[j] = (-1.0) * population[i].velocity[j];
					}

				} // end of loop over all positions of an individual
				
				if (population[i].betterOrEqual(localBest[i]))
					localBest[i] = population[i].myCopy();
				
				if (population[i].betterOrEqual(globalBest))
					globalBest = population[i].myCopy();

			} // end of update of each individual

		} // end of main loop of PSO
		
	}
	
	protected Solution best() {
		Solution result = population[0].myCopy();
		for (int i = 1; i < GlobalParameters.populationSize; i++) {
			if (population[i].betterOrEqual(result))
				result = population[i].myCopy();
		}
		return result;
	}

}
