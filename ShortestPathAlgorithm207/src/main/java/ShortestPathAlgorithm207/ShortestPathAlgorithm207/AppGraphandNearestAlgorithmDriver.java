

package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


/**
 * Creates a graph based on an adjacency matrix using the JGrapht library
 * @author Bryan ayala
 *
 */
public class AppGraphandNearestAlgorithmDriver {

    private static final long serialVersionUID = 3L;

    //Set dimensions for Gui JFrame
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 800);

    /**
     * Allow running JGraphx applet as an application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
    
    	// Creates adjacency matrix
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
    	
    	// Creates new applets using JGraphx library
        ParentVizualizationApplet fullGraphApplet = new FullGraph(matrix, cities);
        fullGraphApplet.init();
        
        ParentVizualizationApplet nearestNeighborGraphApplet = new NearestNeighborGraph(matrix, cities, "Rockville");
        nearestNeighborGraphApplet.init();
        
        ParentVizualizationApplet teamAlgorithmGraphApplet = new TeamAlgorithmGraph(matrix, cities);
        teamAlgorithmGraphApplet.init();

        /*************************************
        // Creates UI using swing
         *************************************/
        
        // Creates the JFrame
         
        JFrame frame = new JFrame("Graph representation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DEFAULT_SIZE);

       
        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel();
        final JPanel cards = new JPanel(new CardLayout());
        
        JPanel card1 = new JPanel();
        JPanel card2 = new JPanel();
        JPanel card3 = new JPanel();
        final CardLayout cardLayout = (CardLayout) cards.getLayout();
        
        card1.add(fullGraphApplet);
        cards.add(card1, "full Graph");
        card2.add(nearestNeighborGraphApplet);
        cards.add(card2, "nearest neighbor");
        card3.add(teamAlgorithmGraphApplet);
        cards.add(card3, "Team algorithm");
        
        
        JLabel label = new JLabel("Options: ");
        
        JButton button1 = new JButton( new AbstractAction("complete Graph") {// button with even listener
            @Override
            public void actionPerformed( ActionEvent e ) {
            	cardLayout.show(cards, "full Graph");
            }
        });

        JButton button2 = new JButton( new AbstractAction("Nearest neighbot algorithm") {// button with even listener
            @Override
            public void actionPerformed( ActionEvent e ) {
            	cardLayout.show(cards, "nearest neighbor");
            }
        });
        
        JButton button3 = new JButton( new AbstractAction("Team algorithm") {// button with even listener
            @Override
            public void actionPerformed( ActionEvent e ) {
            	cardLayout.show(cards, "Team algorithm");
            }
        });
        
        
        panel.add(label); // Components Added using Flow Layout
        
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, cards);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        
        //display content of JFrame
        frame.setVisible(true);
    }  
}