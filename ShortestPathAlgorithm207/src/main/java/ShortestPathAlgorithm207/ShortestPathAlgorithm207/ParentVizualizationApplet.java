
package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

import java.awt.Dimension;

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
	private static final Dimension DEFAULT_SIZE = new Dimension(800, 700);
	protected String[]cities;
	protected int[][] adjacencyMatrix;
	protected DirectedWeightedMultigraph<String, Road>  populatedGraph; 

	/**
	 * Default constructor, not meant to be used. set to private to prevent compiling if used.
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
	protected void setadjacencyMatrix(int[][] adjacencyMatrixmatrix, String[] cities) {
		this.adjacencyMatrix = adjacencyMatrixmatrix;
		this.cities = cities;
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
		setPreferredSize(DEFAULT_SIZE);
		mxGraphComponent component = new mxGraphComponent(jgxAdapter);
		component.setConnectable(false);
		component.getGraph().setAllowDanglingEdges(false);
		getContentPane().add(component);
		resize(DEFAULT_SIZE);
		
		
		// Positioning via jgraphx layouts. lays all the elements in the graphs around a circle
		mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
		
		// Center the circle
		int radius = 300;
		layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
		layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
		layout.setRadius(radius);
		layout.setMoveCircle(true);
		
		layout.execute(jgxAdapter.getDefaultParent());
		
		//Separates edges
		mxParallelEdgeLayout layout1 = new mxParallelEdgeLayout(jgxAdapter);
		layout1.execute(jgxAdapter.getDefaultParent());
       
		
	}

}

