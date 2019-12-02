

package ShortestPathAlgorithm207.ShortestPathAlgorithm207;


import com.mxgraph.layout.*;
import com.mxgraph.swing.*;



import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * creates a graph based on an adjacency matrix using the JGrapht library
 * @author Bryan ayala
 *
 */
public class AppGraphandNearestAlgorithmDriver implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 800);
    static int[][] adjacencyMatrix;
    static String[] cities;
    MinCircuitFinder solver;
    
    
    AppGraphandNearestAlgorithmDriver(){
    	
    	cities = new String[] {"Rockville", "Silver Spring", "Philadelphia", "Pittsburgh", "Baltimore", "Cleveland", "New York City"};
    	adjacencyMatrix = new int[][]{
			    			{0  , 13 , 142, 225, 40 , 352, 227},
			    			{13 , 0  , 136, 237, 34 , 363, 222},
			    			{141, 135, 0  , 305, 101, 432, 97 },
			    			{226, 237, 304, 0  , 248, 133, 371},
			    			{40 , 34 , 106, 248, 0  , 374, 192},
			    			{352, 364, 431, 133, 375, 0  , 462},
			    			{228, 222, 97 , 370, 118, 462, 0  }
    					};
    					
    	solver = new MinCircuitFinder(cities, adjacencyMatrix);
    	
    }
    
    
    
    
    
    /**
     * allow running JGraphx applet as an application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
    	
    	
    	// Applet initialization ________________________________________________
    	

    	
        AppletCreationTGraphX applet = new AppletCreationTGraphX(adjacencyMatrix, cities);
        applet.init();
        
        
        // ________________________________________________________________________
        
        
        
        

        // Creating UI using swing ---------------------------------------------------------------
        
        //JFrame
        JFrame frame = new JFrame("Graph representation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DEFAULT_SIZE);

       
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Algorithm options:");
        JButton button1 = new JButton("Nearest neighbor algorithm"); //no event listeners yet
        button1.setActionCommand("nearest_neighbor");
        JButton button2 = new JButton("Group algorithm");
        button1.setActionCommand("group_algorithm");
        
        //Components added to panel using flow layout
        panel.add(label); 
        panel.add(button1);
        panel.add(button2);

        //Adding Components to JFrame
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, applet);
        
        //display content of JFrame
        frame.setVisible(true);
        
        // ---------------------------------------------------------------------------------------
        
    }
    
    




	public void actionPerformed(ActionEvent ae) {
		
        String action = ae.getActionCommand();
        
        if (action.equals("nearest_neighbor")) {
            solver.nearestNeighbor();
        } else if(action.equals("group_algorithm")) {
        	solver.groupAlgorithm();
        }
        
		
	}

   
}