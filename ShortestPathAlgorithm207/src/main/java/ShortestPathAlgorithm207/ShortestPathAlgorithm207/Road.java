package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * Custom implementation of a DefaultWeightedEdge class to display weight in edge rather 
 * than origin and destination like in the default
 * @author Bryan Ayala
 *
 */
public class Road extends DefaultWeightedEdge{
	
	private static final long serialVersionUID = 3L;

	@Override
	public String toString() {
		return Double.toString(super.getWeight());
	}
	
}