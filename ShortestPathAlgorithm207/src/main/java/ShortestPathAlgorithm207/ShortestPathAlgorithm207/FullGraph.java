/**
 * 
 * 
 * This program makes use of the JgraphT graphing library. 
 * 
 * JGraphT may be used under the terms of the
 * Eclipse Public License (EPL):
 * http://www.eclipse.org/org/documents/epl-v10.php
 *  
 *  As such the same license extends to this programs and related classes in this package
 */
 
package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

import org.jgrapht.graph.DirectedWeightedMultigraph;

/**
 * Populate graph with all vertices and edges. No sorting or reduced paths
 * @author Bryan ayala
 *
 */
public class FullGraph extends ParentVizualizationApplet{
	
	private static final long serialVersionUID = 3L;

	public FullGraph (int[][] adjacencyMatrixmatrix, String[] cities) {
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
