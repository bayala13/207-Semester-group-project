package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

import java.util.ArrayList;

import org.jgrapht.graph.DirectedWeightedMultigraph;

/**
 * Populate graph using algorithm created by team members
 * @author Bryan ayala
 *
 */



public class TeamAlgorithmGraph extends ParentVizualizationApplet{
	
	
	
	private static final long serialVersionUID = 3L;
	private int distanceTraveled = 0;
	static final String[] cities = {"Rockville", "Silver Spring", "Philadelphia", "Pittsburgh", "Baltimore", "Cleveland", "New York City"};
	private int originIndex = 0;
	
	
	
	public TeamAlgorithmGraph (int[][] adjacencyMatrixmatrix, String[] cities, String origin, int generations, int populationSize, double mutationChance) {
		super(adjacencyMatrixmatrix, cities);
		populateGraph(origin, generations, populationSize, mutationChance);
	}
	
	
	
	
	
	private boolean populateGraph(String origin, int generations, int populationSize, double mutationChance) {
		
		int currentCity = 0;
		GeneticAlgorithmManager manager = new GeneticAlgorithmManager(populationSize, mutationChance);  
		populatedGraph = new DirectedWeightedMultigraph<>(Road.class);
		int[][] newMatrix = new int[adjacencyMatrix.length][adjacencyMatrix[0].length];
		

		
		for(int j = 0; j < generations; j++) {
			manager.iterateGeneration();
		}
		
		
		
		newMatrix = manager.getMostFit().get2DPath();
		distanceTraveled = manager.getMostFit().getFitness();
		
		
		
		
		// Set the vertices for the graph
		for(int i = 0; i < cities.length; i++) {
			populatedGraph.addVertex(cities[i]); 
			if(cities[i].contentEquals(origin)) {
				currentCity = i;
				originIndex = currentCity;
			}
		}
		
		
		
		// populates new graph with only the shortest path edges
		for(int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < cities.length; j++) {
				if(newMatrix[i][j] > 0) {
					populatedGraph.addEdge(cities[j], cities[i]);
					populatedGraph.setEdgeWeight(populatedGraph.getEdge(cities[j], cities[i]), newMatrix[i][j]);
				}
			}
		}
		
		
		
		
		return true;
		
		
	}

		
	
}
