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

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JApplet;

import org.jgrapht.ext.JGraphXAdapter;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.graph.*;



/**
 * Creates and applet using the JGraphX library
 * @author Bryan ayala
 *
 */
public class ParentVizualizationApplet extends JApplet{
	
	private static final long serialVersionUID = 3L;

	
	//Global variable declaration
	private static final Dimension DEFAULT_SIZE = new Dimension(800, 650);
	protected String[]cities;
	protected int[][] adjacencyMatrix;
	int distanceTraveled = 0;
	protected DirectedWeightedMultigraph<String, Road>  populatedGraph; 
	protected ArrayList<String> path = new ArrayList<String>();
	
	
	/**
	 * Default constructor, not allowed. set to private to prevent compiling if used.
	 */
	private ParentVizualizationApplet() {
		
	}
	

	/**
	 * Constructor with adjacency matrix
	 * @param adjacencyMatrixmatrix - matrix of adjacent cities matching with the city name array
	 * @param cities - array with city names
	 */
	public ParentVizualizationApplet(int[][] adjacencyMatrixmatrix, String[] cities) {
		this.adjacencyMatrix = adjacencyMatrixmatrix;
		this.cities = cities;
	}
	
	
	/**
	 * Allows changes to the adjacency matrix after object has been initialized 
	 * @param adjacencyMatrixmatrix - matrix of adjacent cities matching with the city name array
	 * @param cities - array with city names
	 */
	public void setadjacencyMatrix(int[][] adjacencyMatrixmatrix, String[] cities) {
		this.adjacencyMatrix = adjacencyMatrixmatrix;
		this.cities = cities;
	}
	
	
	/**
	 * Gets the value of the total distance travel after applying algorithm
	 * @return the distance traveled by the algorithm
	 */
	public int getDistanceTraveled() {
		return distanceTraveled;
	}

	
	/**
	 * Returns number of cities traveled
	 * @return
	 */
	public int getNumberOfCities() {
		return cities.length;
	}
	
	
	/**
	 * Gets an array with the list of cities visited 
	 * @return
	 */
	public ArrayList<String> getPath(){
		return path;
	}
	
	public void setPath(ArrayList<String> path) {
		this.path = path;
	}
	/**
	 * Necessary to create applet with JGraphT. Styling must be applied here. 
	 */
	@Override
	public void init()
	{
		// Create a visualization using JGraph, via an adapter
		JGraphXAdapter<String, Road> jgxAdapter;
		jgxAdapter = new JGraphXAdapter<>(populatedGraph);
		mxGraphComponent component = new mxGraphComponent(jgxAdapter);
		component.setConnectable(false);
		component.getGraph().setAllowDanglingEdges(false);
		getContentPane().add(component);
		
		resize(DEFAULT_SIZE);
		
		// Positioning via jgraphx layouts. lays all the elements in the graphs around a circle
		mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
		
		// Center the circle
		int radius = DEFAULT_SIZE.height/ 2;
		layout.setX0(1);
		layout.setY0(1);
		layout.setRadius(radius);
		layout.setMoveCircle(true);
		
		layout.execute(jgxAdapter.getDefaultParent());
		
		//Separates edges
		mxParallelEdgeLayout layout1 = new mxParallelEdgeLayout(jgxAdapter);
		layout1.execute(jgxAdapter.getDefaultParent());
       
		
	}

}

