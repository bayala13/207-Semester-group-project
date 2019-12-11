package GeneticVisualizer;

import java.util.ArrayList;
import java.util.Arrays;



public class Route implements Comparable<Route>{
	
	
	
	final static int SIZE = 8; // Number of cities + 1, rockville comes twice
	final static double mutationChance = 0.15;
	final static ArrayList<String> cities = new ArrayList<String>(Arrays.asList("Rockville", "Silver Spring", "Philadelphia", "Pittsburgh", "Baltimore", "Cleveland", "New York City"));
	final static int[][] distanceMatrix = new int[][]{
														{0  , 13 , 142, 225, 40 , 352, 227},
														{13 , 0  , 136, 237, 34 , 363, 222},
														{141, 135, 0  , 305, 101, 432, 97 },
														{226, 237, 304, 0  , 248, 133, 371},
														{40 , 34 , 106, 248, 0  , 374, 192},
														{352, 364, 431, 133, 375, 0  , 462},
														{228, 222, 97 , 370, 118, 462, 0  }
													};
	
	String[] composition;
	
	Route(){
		composition = new String[SIZE];
		composition[0] = "Rockville";
		composition[SIZE-1] = "Rockville";
	}
	
	Route(String[] passed){
		composition = passed;
	}
	
	Route(Route other){
		composition = other.composition;
	}
	
	
	
	
	
	
	
	
	/**
	 * Create a randomized route.
	 * @return randomized individual
	 */
	
	public static Route createRandomIndividual() {
		
		Route temp = new Route();
		int place;
		
		for(String s : cities) {

			while(true) {
				
				place = getRandom(1, SIZE-1);
				
				if(s.equals("Rockville")) {
					break;
				}
				
				if(temp.composition[place] == null) {
					temp.composition[place] = s;
					break;
				}
				
			}
			
		}
		
		return temp;
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param source source town
	 * @param destination destination town
	 * @return the weight of the edge connecting source to destination
	 */
	
	int getWeightOf(String source, String destination) {
		try{
			return distanceMatrix[cities.indexOf(source)][cities.indexOf(destination)];
		} catch (ArrayIndexOutOfBoundsException e ) { return 1000; }
	}
	
	
	
	
	
	
	
	/**
	 * Get an array of the weights of the composition
	 * @return weights of each edge in an array
	 */
	
	int[] getWeights() {
		
		int[] weights = new int[SIZE-1];
		String temp = "Rockville";
		
		
		for(int i = 0; i < composition.length; i++) {
			
			if(i != 0) {
				weights[i-1] = getWeightOf(temp, composition[i]);
			}
			
			temp = composition[i];
			
		}
		
		return weights;
		
	}
	
	

	
	
	
	
	/**
	 * Get the overall fitnes of a chromosome
	 * @return sum of the weights of the composition
	 */
	
	public int getFitness() {
		
		int sum = 0;
		
		for(int weight : getWeights()) {
			sum += weight;
		}
		
		return sum;
		
	}
	
	
	
	
	
	
	/**
	 * Crossover/breed two routes
	 * @return the child of the two routes
	 */
	
	public Route crossover(Route o) {
		
		int transmutationLength = getRandom(1, SIZE-2);
		int startPoint = getRandom(1, SIZE - transmutationLength - 1);
		Route child = new Route();
		


		
		
		
		for(int i = 0; i < transmutationLength; i++) {
			child.composition[startPoint + i] = o.composition[startPoint + i];
		}
		
		
		
		
		for(int i = 1; i < SIZE-1; i++) {
			
			if(child.composition[i] == null) {
				
				for(String s : this.composition) {
										
					if(!contains(child.composition, s)) {
						child.composition[i] = s;
						break;
					}
					
				}
				
			}
			
		}
		
		
		return child;
		
	}
	
	
	
	
	
	
	/**
	 * Mutate the genes of the route. 
	 */
	
	public void mutate(){
		
		
		for(int i = 1; i < SIZE - 1; i++) {
			if(Math.random() < mutationChance) {
				int spot = getRandom(1, (SIZE-2)/2); //reducing the transplant range helps with retaining genetic consistency
				String temp = composition[spot];
				composition[spot] = composition[i];
				composition[i] = temp;
			}
		}
		
	}
	
	
	
	
	
	
	
	
	/**
	 * Return a new 2d array of weights according to this route.
	 */
	
	public int[][] get2DPath(){
		
		// we're trying to convert the String[] composition into a 2d array of according weights.
		int[] weights = getWeights();
		int[][] path = new int[SIZE-1][SIZE-1];
		
		for(int i = 0; i < SIZE - 1; i++) {
			int current = cities.indexOf(composition[i]);
			int next = cities.indexOf(composition[i+1]);
			path[current][next] = weights[i];
		}
		
		return path;
		
	}
	
	
	
	
	
	
	
	
	/*
	 * Utility methods
	 */
	
	boolean equals(Route o){
		for(int i = 0; i < SIZE; i++) {
			if(!this.composition[i].equals(o.composition[i])) {
				return false;
			}
		}
		return true;
	}
	
	
	
	static int getRandom(int min, int max) {
		return (int) ((Math.random() * ((max - min) + 1)) + min);
	}
	
	
	 static boolean contains(String[] arr, String target) {
		for(String s : arr){
			if(s != null && s.equals(target)) {
				return true;
			}
		}
		return false;
	}
	 
	 
	 public String toString() {
		 return "\n" + Arrays.toString(composition) + " \nfitness: " + getFitness();
	 }

	@Override
	public int compareTo(Route arg0) {
		
		if(this.getFitness() < arg0.getFitness()) {
			return -1;
		} else if (this.getFitness()  == arg0.getFitness()) {
			return 0;
		}
		
		return 1;
		
	}
	
	
}
