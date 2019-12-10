package ShortestPathAlgorithm207.ShortestPathAlgorithm207;


import java.util.ArrayList;


import org.jgrapht.graph.DirectedWeightedMultigraph;

/**
 * Populate graph using algorithm created by team members
 * @author Eugene
 *
 */
public class TeamAlgorithmGraph extends ParentVizualizationApplet{
	
	private static final long serialVersionUID = 3L;
	
	public TeamAlgorithmGraph (int[][] adjacencyMatrixmatrix, String[] cities, String origin, int generations, int populationSize, double mutationChance) {
		super(adjacencyMatrixmatrix, cities);
		populateGraph(origin, generations, populationSize, mutationChance);
	}
	
	/**
	 * Populates graph vertices and edges after genetic algorithm runs
	 * @param origin
	 * @param generations
	 * @param populationSize
	 * @param mutationChance
	 * @return
	 */
	private boolean populateGraph(String origin, int generations, int populationSize, double mutationChance) {
		
		super.populatedGraph = new DirectedWeightedMultigraph<>(Road.class);
		GeneticAlgorithmManager manager = new GeneticAlgorithmManager(populationSize, mutationChance);  
		
		int[][] algorithmMatrix = new int[adjacencyMatrix.length][adjacencyMatrix[0].length];
		
	
		for(int j = 0; j < generations; j++) {
			manager.iterateGeneration();
		}
		
		
		algorithmMatrix = manager.getMostFit().get2DPath();
		distanceTraveled = manager.getMostFit().getFitness();
		
		
		// Set the vertices for the graph
		for(int i = 0; i < cities.length; i++) {
			populatedGraph.addVertex(cities[i]); 
		}
		
		
		// populates new graph with only the shortest path edges
		for(int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < cities.length; j++) {
				if(algorithmMatrix[i][j] > 0) {
					super.populatedGraph.addEdge(cities[j], cities[i]);
					super.populatedGraph.setEdgeWeight(populatedGraph.getEdge(cities[j], cities[i]), algorithmMatrix[i][j]);
				}
			}
		}
		
		
		recordPath(algorithmMatrix, origin);
		
		return true;
		
	}	
	
	private int findOriginIndex(String origin){
		int originIndex = 0;
		for(int i = 0; i < cities.length; i++) {
			if(cities[i].contentEquals(origin)) {
				originIndex = i;
				break;
			}
		}
		return originIndex;
	}
	
	private void recordPath(int[][] algorithmMatrix, String origin){
		int currentCity= findOriginIndex(origin);
		while(super.path.size() < cities.length+1) {
			path.add(0, cities[currentCity]);
			for(int i = 0 ; i < cities[currentCity].length(); i++) {
				if(algorithmMatrix[currentCity][i] > 0) {
					currentCity = i;
					break;
				}
			}
		}
	}

}
