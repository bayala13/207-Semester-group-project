package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

import java.awt.Dimension;

import javax.swing.JApplet;

import org.jgrapht.ext.JGraphXAdapter;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.io.*;
import org.jgrapht.traverse.*;

import java.io.*;
import java.net.*;
import java.util.*;


/**
 * Creates and applet using the JGraphX library
 * @author Bryan ayala
 *
 */
public class AppletCreationTGraphX extends JApplet{

	private static final long serialVersionUID = 1L;

	private static final Dimension DEFAULT_SIZE = new Dimension(800, 700);

	String[]cities;
	int[][] adjacencyMatrix;
	DirectedWeightedMultigraph<String, Road>  populatedGraph = new DirectedWeightedMultigraph<>(Road.class); 

	public AppletCreationTGraphX() {
		
		cities = new String[] {"null","null"};
		adjacencyMatrix = new int[2][2];
	}

	public AppletCreationTGraphX(int[][] adjacencyMatrixmatrix, String[] cities) {
		this.adjacencyMatrix = adjacencyMatrixmatrix;
		this.cities = cities;
	}


	private boolean populategraph() {
			
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
	
	//override necessary to create applet
	@Override
	public void init()
	{
		// create a JGraphT graph using populategraph()
		
		System.out.print(populategraph());
		System.out.print(populatedGraph);

		// create a visualization using JGraph, via an adapter
		JGraphXAdapter<String, Road> jgxAdapter;
		jgxAdapter = new JGraphXAdapter<>(populatedGraph);
		setPreferredSize(DEFAULT_SIZE);
		mxGraphComponent component = new mxGraphComponent(jgxAdapter);
		component.setConnectable(false);
		component.getGraph().setAllowDanglingEdges(false);
		getContentPane().add(component);
		resize(DEFAULT_SIZE);
		
		
		// positioning via jgraphx layouts. lays all th elements in the graps around a circle
        mxCircleLayout layout = new mxCircleLayout(jgxAdapter);

        
        // center the circle
        int radius = 300;
        layout.setX0((DEFAULT_SIZE.width / 2.0) - radius);
        layout.setY0((DEFAULT_SIZE.height / 2.0) - radius);
        layout.setRadius(radius);
        layout.setMoveCircle(true);

        layout.execute(jgxAdapter.getDefaultParent());
        // that's all there is to it!...

		
	}

}

