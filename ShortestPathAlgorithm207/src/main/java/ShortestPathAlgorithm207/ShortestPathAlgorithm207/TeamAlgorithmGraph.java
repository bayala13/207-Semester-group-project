package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

import org.jgrapht.graph.DirectedWeightedMultigraph;

/**
 * Populate graph using algorithm created by team members
 * @author Bryan ayala
 *
 */
public class TeamAlgorithmGraph extends ParentVizualizationApplet{
	
	private static final long serialVersionUID = 3L;

	public TeamAlgorithmGraph (int[][] adjacencyMatrixmatrix, String[] cities) {
		super(adjacencyMatrixmatrix, cities);
		populateGraph();
	}
	
	
	private boolean populateGraph() {
		populatedGraph = new DirectedWeightedMultigraph<>(Road.class);
		
		try {
			for(int i = 0; i < cities.length; i++) {
				populatedGraph.addVertex(cities[i]); 
			}
			
			for(int j = 0; j < adjacencyMatrix.length; j++) {
				
				for (int i = 0; i < cities.length; i++) {
					if(adjacencyMatrix[j][i] > 0) {
						populatedGraph.addEdge(cities[j], cities[i]);
						populatedGraph.setEdgeWeight(populatedGraph.getEdge(cities[j], cities[i]), adjacencyMatrix[j][i]);
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
