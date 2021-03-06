package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Manager for the genetic TSP algorithm.
 * @author Eugene Domrachev
 */

public class GeneticAlgorithmManager {
	
	
	final int populationSize;
	final double mutationRate;
	final static int selectionSize = 5; //1/x for elite/mid size
	static ArrayList<Route> population;
	int generation;
	Route mostFit;
	
	
	/**
	 * Constructor
	 * @param populationSize - size of the population 
	 * @param mutationRate - rate at witch mutations happen
	 */
	GeneticAlgorithmManager(int populationSize, double mutationRate, String startVertex){
		
		generation = 0;
		this.mutationRate = mutationRate;
		this.populationSize  = populationSize;
		population = new ArrayList<Route>();
		
		for(int i = 0; i < populationSize; i++) {
			population.add(Route.createRandomIndividual(startVertex));
		}
		
	}
	
	
	/**
	 * Testing Constructor
	 * @param populationSize - size of the population 
	 * @param mutationRate - rate at witch mutations happen
	 */
	GeneticAlgorithmManager(int populationSize, double mutationRate, String startVertex, int[][] distances, int size, ArrayList<String> vertices, String startPoint){
		
		generation = 0;
		this.mutationRate = mutationRate;
		this.populationSize = populationSize;
		population = new ArrayList<Route>();
		
		Route r = new Route(distances, vertices, startPoint);
		
		for(int i = 0; i < populationSize; i++) {
			population.add(Route.createRandomIndividual(startPoint));
		}
		
	}
	
	/**
	 * Get the most fit individual of the population.
	 * @return most fit individual
	 */
	int getGeneration() {
		return generation;
	}
	
	
	/**
	 * Get the most fit individual of the population.
	 * @return most fit individual
	 */
	Route getMostFit() {
		return population.get(0);
	}
		
	
	/**
	 * Get the most fit individual from the passed list. In this implementation (TSP), we are looking to reduce distance
	 * traveled, so fitness is valued if it is lower.
	 * @param list population to draw from
	 * @return most fit individual
	 */
	Route getMostFitIndividualOf(ArrayList<Route> list){
		
		Route temp = list.get(0);
		int fitness = temp.getFitness();
		
		for(Route r : list) {
			if(r.getFitness() < fitness) {
				temp = r;
				fitness = r.getFitness();
			}
		}
		
		return temp;
		
	}
		
	
	/**
	 * Simulate the crossover/breeding process of the current generation held in population.
	 */
	void crossPopulation(){
			
		ArrayList<Route> newPopulation = new ArrayList<Route>();
		ArrayList<Route> elites = getElites();
		ArrayList<Route> mids = getMids();
		
		int newSize = 0;
		double random;
		int randIndex;
		
		
		while(newSize < populationSize) {
			
			random = Math.random();
			randIndex = Route.getRandom(0, populationSize/selectionSize - 1);
			
			if(random < 0.3) {
				newPopulation.add(elites.get(0).crossover(getMostFitIndividualOf(getRandomSelection())));
			} else if (random < 0.7) {
				newPopulation.add(elites.get(randIndex).crossover(getMostFitIndividualOf(getRandomSelection())));
			} else {
				newPopulation.add(mids.get(randIndex).crossover(getMostFitIndividualOf(getRandomSelection())));
			}

			newSize++;
		}
		
				
		population = newPopulation;
		
		
	}
	
	
	
	/**
	 * Iterate through the population and decide whether or not to mutate them.
	 */
	void mutatePopulation() {
		for(Route r : population) {
			if(Math.random() < mutationRate) {
				r.mutate();
			}
		}
	}
	
	
	/**
	 * Step through a generation of crossing and mutating.
	 */	
	void iterateGeneration() {
		
		crossPopulation();
		mutatePopulation();
		generation++;
		Collections.sort(population);
		
	}
	
	
	/**
	 * 
	 * @return a sorted selected rout
	 */
	ArrayList<Route> getRandomSelection(){
		
		ArrayList<Route> selected = new ArrayList<Route>();
		
		for(int i = 0; i < populationSize / selectionSize; i++) {
			
			int index = Route.getRandom(0, populationSize - 1);
			
			if(!selected.contains(population.get(index))) {
				selected.add(population.get(index));
			}
			
		}
		
		Collections.sort(selected);
		
		return selected;
		
		
	}
	
	
	/**
	 * Get the most fit ~x% of the generation.
	 * @return elites the most fit in the generation
	 */
	
	ArrayList<Route> getElites(){
		
		ArrayList<Route> elites = new ArrayList<Route>();
		for(int i = 0; i < populationSize / selectionSize; i++) {
			elites.add(population.get(i));
		}
		return elites;
		
		
	}
	
	
	ArrayList<Route> getMids(){
		
		ArrayList<Route> mids = new ArrayList<Route>();
		for(int i = (selectionSize - 2) * populationSize / selectionSize; i < populationSize; i++) {
			mids.add(population.get(i));
		}
		return mids;
		
		
	}
	
		
	 public String toString() {
		 String s = "";
		 for(Route r : population) {
			 s += "\n " + r;
		 }
		 return s;
	 }
	 
	
}
