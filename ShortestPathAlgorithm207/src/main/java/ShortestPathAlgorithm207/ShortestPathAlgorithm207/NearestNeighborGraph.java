package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

import java.util.ArrayList;


import org.jgrapht.graph.DirectedWeightedMultigraph;

/**
 * 
 * @author KevinAlexander
 *
 */


public class NearestNeighborGraph extends ParentVizualizationApplet{

	
	

	private static final long serialVersionUID = 3L;
	private int distanceTraveled = 0;
	private int originIndex = 0;

	public NearestNeighborGraph (int[][] adjacencyMatrix, String[] cities, String origin) {
		super(adjacencyMatrix, cities);
		
		populateGraph(origin);
	}
	

	/**
	 * gets the value of the total distance travel after applying algorithm
	 * @return
	 */
	public int getDistanceTraveled() {
		return distanceTraveled;
	}
	

	/**
	 * Finds the shortest path starting from one city, passing through every city in the map, and returning
	 * @param origin - starting city
	 * @return - true if successful in creating graph
	 */
	private boolean populateGraph(String origin) {
		
		populatedGraph = new DirectedWeightedMultigraph<>(Road.class);

		int currentCity = 0;
		int nextCity = 0;
		ArrayList<Integer> visited = new ArrayList<>();
		int[][] newMatrix = new int[adjacencyMatrix.length][adjacencyMatrix[0].length];
		int shortestRoad = -1;

		
		try {
			

			//sets graph vertices	
			for(int i = 0; i < cities.length; i++) {
				populatedGraph.addVertex(cities[i]); 
				if(cities[i].contentEquals(origin)) {
					currentCity = i;
					originIndex = currentCity;
				}
			}
			

			/******************************
			 * Nearest neighbor algorithm
			 ******************************/

			//finding shortest path
			for(int i = 0; i < cities.length; i++) {//loops once per city
				for(int j = 0; j < adjacencyMatrix[currentCity].length; j++) {//loops once per edge from current city to neighbor cities
					// checks for the shortest road available to a neighbor city
					if((adjacencyMatrix[currentCity][j] < shortestRoad || shortestRoad == -1) && adjacencyMatrix[currentCity][j] != 0) {
						if(!visited.contains(j)){ // checks if city has been visited already
							shortestRoad = adjacencyMatrix[currentCity][j]; 
							nextCity = j;
						}
					} 
				}
				visited.add(currentCity);
				newMatrix[currentCity][nextCity] = shortestRoad; // leaves only the shortest road available
				currentCity = nextCity;
				distanceTraveled += shortestRoad;
				shortestRoad= -1;
			}
			
			// Going back to the origin city
			newMatrix[currentCity][originIndex] = adjacencyMatrix[currentCity][originIndex];
			distanceTraveled += adjacencyMatrix[currentCity][originIndex];
			

			//*******************************
			

			// populates new graph with only the shortest path edges
			for(int j = 0; j < adjacencyMatrix.length; j++) {
				for (int i = 0; i < cities.length; i++) {
					if(newMatrix[j][i] > 0) {
						populatedGraph.addEdge(cities[j], cities[i]);
						populatedGraph.setEdgeWeight(populatedGraph.getEdge(cities[j], cities[i]), newMatrix[j][i]);
					}
				}
			}

			return true;
		}
		catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		
	}

}
