
/**
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