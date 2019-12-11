package GeneticVisualizer;

import java.util.Arrays;

public class GeneticAlgorithmDriver {
	
	/*public static void main(String[] args) {
		
		
		
		
		
		/*
		 * Which values can be changed? Which are necessary?
		 * 
		 * Mutation rate of a route
		 * Crossover rates
		 * 		- alpha only
		 * 		- alpha crossed with best of a random group
		 * 		- random elite crossed with best of a random group
		 * 		- random beta crossed with best of a random group
		 * 		- beta only 
		 * 
		 * 
		 */
		
		
			/*double sum = 0;
			long startTime = System.nanoTime();
			for(int i = 0; i < 100; i++) {
				sum += test();
			}
			long endTime   = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println("True average: " + (sum/100) );
			System.out.println("Sec spent calculating: " + totalTime/1000000000.0);
			
		GeneticAlgorithmManager manager = new GeneticAlgorithmManager(15, 0.5);  
		
		for(int j = 0; j < 15; j++) {
			System.out.println("Generation: " + manager.getGeneration() + "\n");
			System.out.println("Most fit: " + manager.getMostFit().getFitness() + "\n");
			System.out.println("Population: " + manager.population + "\n\n");
			manager.iterateGeneration();
		}
		
		System.out.println(manager.getGeneration() + " "  +manager.getMostFit()+ " ");
		
		int[][] path = manager.getMostFit().get2DPath();
		
		for(int[] row : path) {
			for(int elem : row) {
				System.out.print(elem + " ");
			}
			System.out.println();
		}
	}*/
	
	
	
	
	
	
	
	static Double test() {
		
		int sum = 0;
		int bestever = Integer.MAX_VALUE;
		int best = Integer.MAX_VALUE;
		
		for(int i = 0; i < 100; i++) {
			
			GeneticAlgorithmManager manager = new GeneticAlgorithmManager(15, 0.2);  

			for(int j = 0; j < 10; j++) {
				manager.iterateGeneration();
			}
			
			best = manager.getMostFit().getFitness();
			sum += best;
			
			if(best < bestever) {
				bestever = best;
			}
			
		}
		
		double avg = (double) sum / 100;
		
		return avg;
		
	}
	
	
	
	
}
