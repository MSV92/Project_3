package Parameters;

public class ParametersGA {
	
	public static double crossoverRate = 0.9;
	public static double mutationRate = 0.1;
	public static double mutationStep = 1;
	
	public static boolean elitism = true;
	public static int tournamentSize = 5; 
	
	public static int selectionMethod = 3;
	// 1 - roulette
	// 2 - ranking 
	// 3 - tournament
	
}
