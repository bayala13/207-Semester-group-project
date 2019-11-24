

package ShortestPathAlgorithm207.ShortestPathAlgorithm207;


import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import javax.swing.*;
import java.awt.*;

/**
 * creates a graph based on an adjacency matrix using the JGrapht library
 * @author Bryan ayala
 *
 */
public class AppGraphandNearestAlgorithmDriver {

    private static final long serialVersionUID = 1L;

    //set dimensions for Gui JFrame
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 800);

    

    /**
     * allow running JGraphx applet as an application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
    	
    	
    	// create addjacency matrix
    	
    	String[] cities = {"Rockville", "Silver Spring", "Philadelphia", "Pittsburgh", "Baltimore", "Cleveland", "New York City"};
    	int[][] matrix = {
    			{0,13,142,225,40,352,227},
    			{13,0,136,237,34,363,222},
    			{141,135,0,305,101,432,97},
    			{226,237,304,0,248,133,371},
    			{40,34,106,248,0,374,192},
    			{352,364,431,133,375,0,462},
    			{228,222,97,370,118,462,0}
    			};


    	
    	
    	// creates new applet using JGraphx library
        AppletCreationTGraphX applet = new AppletCreationTGraphX(matrix, cities);
        applet.init();
        

        /*************************************
        // Creating UI using swing
         *************************************/
        
        //Creating the JFrame
         
        JFrame frame = new JFrame("Graph representation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DEFAULT_SIZE);

       
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("options:");
        JButton button1 = new JButton("Nearest neighbot algorithm"); //no event listeners yet
        JButton button2 = new JButton("Group algorithm");
        panel.add(label); // Components Added using Flow Layout
        panel.add(label); // Components Added using Flow Layout

        panel.add(button1);
        panel.add(button2);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, applet);
        
        //display content of JFrame
        frame.setVisible(true);
    }

   
}