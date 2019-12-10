/**
 * 
 * This program makes use of the JgraphT graphing library. 
 * 
 * JGraphT may be used under the terms of the
 * Eclipse Public License (EPL):
 * http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * As such the same license extends to this programs and related classes in this package
 */
package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

import java.util.ArrayList;

import org.jgrapht.graph.DirectedWeightedMultigraph;

/**
 * Populates a graph with paths using the nearest neighbor algorithm
 * @author Bryan Ayala
 */
public class NearestNeighborGraph extends ParentVizualizationApplet{

	private static final long serialVersionUID = 3L;
	

	/**
	 * constructor - calls super constructor and populates graph with a graph representation after nearest neighbor algorithm
	 * @param adjacencyMatrix - the adjacency matrix to be graphed
	 * @param cities - List of cities that goes with the matrix
	 * @param origin - city to start travel;
	 */
	public NearestNeighborGraph (int[][] adjacencyMatrix, String[] cities, String origin) {
		super(adjacencyMatrix, cities);
		populateGraph(origin);
	}
	

	/**
	 * Finds the shortest path starting from one city, passing through every city in the map and returning back to the origin city
	 * @param origin - starting city
	 * @return - true if successful in creating graph
	 */
	private boolean populateGraph(String origin) {
		
		super.populatedGraph = new DirectedWeightedMultigraph<>(Road.class);	

		int[][] algorithmMatrix = nearestneighborAlgorithm(findOriginIndex(origin));	
		
		//sets graph vertices	
		for(int i = 0; i < cities.length; i++) {
			populatedGraph.addVertex(cities[i]); 
		}
		
		
		// populates new graph with only the shortest path edges
		for(int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < cities.length; j++) {
				if(algorithmMatrix[i][j] > 0) {
					super.populatedGraph.addEdge(cities[i], cities[j]);
					super.populatedGraph.setEdgeWeight(populatedGraph.getEdge(cities[i], cities[j]), algorithmMatrix[i][j]);
				
				}
			}
		}
		
		recordPath(algorithmMatrix, origin);
		
		return true;
	}
	
	private int[][] nearestneighborAlgorithm(int originIndex) {
		
		ArrayList<Integer> visited = new ArrayList<>();
		int[][] algorithmMatrix = new int[super.adjacencyMatrix.length][super.adjacencyMatrix[0].length];
		int currentCity = 0;
		int nextCity = 0;
		int shortestRoad = -1;

		for(int i = 0; i < cities.length - 1; i++) {//loops once per city
			shortestRoad= -1;
			for(int j = 0; j < adjacencyMatrix[currentCity].length; j++) {//loops once per edge from current city to neighbor cities
				// checks for the shortest road available to a neighbor city
				if((adjacencyMatrix[currentCity][j] < shortestRoad || shortestRoad == -1) && adjacencyMatrix[currentCity][j] != 0) {
					if(!visited.contains(j)){ // checks if city has been visited already
						shortestRoad = adjacencyMatrix[currentCity][j]; 
						nextCity = j;
					}
				} 
			}
			visited.add(currentCity); // adds the city with the shortest distance to the circuit path
			algorithmMatrix[currentCity][nextCity] = shortestRoad; // leaves only the shortest road available
			currentCity = nextCity;
			distanceTraveled += shortestRoad;
		}
		
		// Going back to the origin city
		algorithmMatrix[currentCity][originIndex] = adjacencyMatrix[currentCity][originIndex];
		distanceTraveled += adjacencyMatrix[currentCity][originIndex];
		
		
		return algorithmMatrix;
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
		while(super.getPath().size() < cities.length+1) {
			super.getPath().add(cities[currentCity]);
			for(int i = 0 ; i < cities[currentCity].length(); i++) {
				if(algorithmMatrix[currentCity][i] > 0) {
					currentCity = i;
					break;
				}
			}
		}
	}

	
	
	
}
