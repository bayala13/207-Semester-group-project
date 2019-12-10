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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.ArrayList;



/**
 * Creates a graph based on an adjacency matrix using the JGrapht library
 * @author Bryan ayala and Eugene
 *
 */

public class AppShortestPathDriver {

    private static final long serialVersionUID = 3L;
    
    //Set dimensions for top level jFrame
    private static final Dimension DEFAULT_SIZE = new Dimension(1200, 800);


    /**
     * Allows running several instances of JGraphx applets as a single application.
     * @param args command line arguments
     */
    public static void main(String[] args) {
    
    	//Adjacency matrix and list of cities, based on project description
    	final String[] cities = {"Rockville", "Silver Spring", "Philadelphia", "Pittsburgh", "Baltimore", "Cleveland", "New York City"};

    	final int[][] distanceMatrix = new int[][]{
			{0  , 13 , 142, 225, 40 , 352, 227},
			{13 , 0  , 136, 237, 34 , 363, 222},
			{141, 135, 0  , 305, 101, 432, 97 },
			{226, 237, 304, 0  , 248, 133, 371},
			{40 , 34 , 106, 248, 0  , 374, 192},
			{352, 364, 431, 133, 375, 0  , 462},
			{228, 222, 97 , 370, 118, 462, 0  }
		};
		
    	// Creates a new applet using the JGraphx library that displays the full graph
		final ParentVizualizationApplet fullGraphApplet = new FullGraph(distanceMatrix, cities);
		fullGraphApplet.init();
		
		
        /*************************************
        // Creates UI using swing
         *************************************/
        
        //Initializes JFrame
        final JFrame frame = new JFrame("Nearest Neighbor algorithm representation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DEFAULT_SIZE);
        frame.setPreferredSize(DEFAULT_SIZE);
        

        //Draws Left Text area ands adds them to a box with a label
        final JTextArea leftTextArea = new JTextArea("Please run algorithm to display information", 11,32);
        leftTextArea.setLineWrap(true);
        leftTextArea.setWrapStyleWord(true);
        leftTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel leftJLabel = new JLabel("Nearest neighbor algorithm");
        leftJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Box leftTextBox = Box.createVerticalBox();
        leftTextBox.add(leftJLabel);
        leftTextBox.add(leftTextArea);
        
       
        //Draws Right Text area ands adds them to a box with a label
        final JTextArea rightTextArea = new JTextArea("Please run algorithm to display information",11,32);
        rightTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        rightTextArea.setLineWrap(true);
        rightTextArea.setWrapStyleWord(true);
        JLabel rightJLabel = new JLabel("Modified genetic algorithm");
        rightJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Box rightTextBox = Box.createVerticalBox();
        rightTextBox.add(rightJLabel);
        rightTextBox.add(rightTextArea);
        
        
        //Adds both labeled text areas to app
        final JPanel textBoxes = new JPanel();
        textBoxes.setLayout(new BoxLayout(textBoxes, BoxLayout.Y_AXIS));
        textBoxes.add(leftTextBox);
        textBoxes.add(rightTextBox);        
        
    
        //Draws generations slider Sliders
        final JSlider generationsSlider = new JSlider(new DefaultBoundedRangeModel(10, 0, 0, 20));//<< changes generation ranges
        JLabel generationsLabel = new JLabel("Generations: ");
        generationsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JPanel generationsBox = new JPanel();
        generationsBox.setLayout(new BoxLayout(generationsBox, BoxLayout.Y_AXIS));
        generationsBox.add(generationsLabel);
        generationsBox.add(generationsSlider);
        
        generationsSlider.setMajorTickSpacing(5);
        generationsSlider.setPaintTicks(true);
        generationsSlider.setPaintLabels(true);
        generationsSlider.setSnapToTicks(true);
        
        
        //Population size slider
        final JSlider populationSizeSlider = new JSlider(new DefaultBoundedRangeModel(15, 0, 0, 30));//<< changes population ranges
        JLabel populationLabel = new JLabel("Population size:  ");
        populationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JPanel populationBox = new JPanel();
        populationBox.setLayout(new BoxLayout(populationBox, BoxLayout.Y_AXIS));
        populationBox.add(populationLabel);
        populationBox.add(populationSizeSlider);
        
        populationSizeSlider.setMajorTickSpacing(5);
        populationSizeSlider.setPaintTicks(true);
        populationSizeSlider.setPaintLabels(true);
        populationSizeSlider.setSnapToTicks(true);
       
        
        //Mutation slider
        final JSlider mutationrateSlider = new JSlider(new DefaultBoundedRangeModel(5, 0, 1, 10)); //<< changes mutation rate ranges
        JLabel mutationrateLabel = new JLabel("Mutation Rate: ");
        mutationrateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        final JPanel mutationrateBox = new JPanel();
        mutationrateBox.setLayout(new BoxLayout(mutationrateBox, BoxLayout.Y_AXIS));
        mutationrateBox.add(mutationrateLabel);
        mutationrateBox.add(mutationrateSlider);
        
        mutationrateSlider.setMajorTickSpacing(1);
        mutationrateSlider.setPaintTicks(true);
        mutationrateSlider.setPaintLabels(true);
        mutationrateSlider.setSnapToTicks(true);
        
        //adding all sliders to a single horizontal box
        final JPanel slidersBox = new JPanel();
        slidersBox.add(generationsBox);
        slidersBox.add(populationBox);
        slidersBox.add(mutationrateBox);
        
        //Card layout to switch between graphs
        final JPanel cards = new JPanel(new CardLayout());
        final CardLayout cardLayout = (CardLayout) cards.getLayout();
        final JPanel card1 = new JPanel();
        final JPanel card2 = new JPanel();
        final JPanel card3 = new JPanel();   
        
        
        //Adds full graph to card number 1, 
        //Other cards are added inside button event handlers to redraw each time button is clicked
        card1.add(fullGraphApplet);
        cards.add(card1, "Full graph");

        JButton button1 = new JButton( new AbstractAction("Complete Graph") {// button with even listener
            @Override
            public void actionPerformed( ActionEvent e ) {   
            	cardLayout.show(cards, "Full graph");
            }
        });

        
        JButton button2 = new JButton( new AbstractAction("Nearest neighbor algorithm") {// button with even listener
            @Override
            public void actionPerformed( ActionEvent e ) {
            	card2.removeAll();
            	JPanel wrapper = new JPanel(new BorderLayout());
            	ParentVizualizationApplet nearestNeighborGraphApplet = new NearestNeighborGraph(distanceMatrix, cities, "Rockville");
            	nearestNeighborGraphApplet.init();
            	
            	wrapper.add(BorderLayout.EAST, textBoxes);
                wrapper.add(BorderLayout.WEST, nearestNeighborGraphApplet);
            	card2.add(wrapper);
                cards.add(card2, "Nearest neighbor");
                leftTextArea.setText(updateTextAreas(nearestNeighborGraphApplet));
            	cardLayout.show(cards, "Nearest neighbor");
            }
        });
        
        
        JButton button3 = new JButton( new AbstractAction("Genetic algorithm") {// button with even listener
            @Override
            public void actionPerformed( ActionEvent e ) {
            	card3.removeAll(); // clears card 
            
            	JPanel wrapper = new JPanel(new BorderLayout());
            	
            	
            		
            	int generations = generationsSlider.getValue();
            	int populationSize = populationSizeSlider.getValue();
            	double mutation = mutationrateSlider.getValue()/10.0;

            	ParentVizualizationApplet teamAlgorithmGraphApplet = new TeamAlgorithmGraph(
            			distanceMatrix, cities, "Rockville", generations, populationSize, mutation);
                teamAlgorithmGraphApplet.init();
                
                
                
            	wrapper.add(BorderLayout.SOUTH, slidersBox);
            	wrapper.add(BorderLayout.CENTER, textBoxes);
                wrapper.add(BorderLayout.WEST, teamAlgorithmGraphApplet);
                card3.add(wrapper);
            	rightTextArea.setText(updateTextAreas(teamAlgorithmGraphApplet));
                cards.add(card3, "Genetic algorithm");

            	cardLayout.show(cards, "Genetic algorithm");
            	
            }
        });
        
        
        //creates panel for buttons at the bottom of the screen
        JPanel buttonsPanel = new JPanel();
        JLabel label = new JLabel("Options: ");
        buttonsPanel.add(label); // Components Added using Flow Layout 
        buttonsPanel.add(button1);
        buttonsPanel.add(button2);
        buttonsPanel.add(button3);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, cards);
        frame.getContentPane().add(BorderLayout.SOUTH, buttonsPanel);
        
        //display content of JFrame
        frame.setVisible(true);
    }  
    
    
    /**
     * Constructs text in textboxes
     * @param applet - object to get length and cities traveled from
     * @return - a string with all prompts answered
     */
    private static String updateTextAreas(ParentVizualizationApplet applet) {
    	int distanceTraveled = applet.distanceTraveled;
    	int citiesTraveled = applet.cities.length;
    	ArrayList<String> path = applet.path;
    	
    	
    	StringBuilder text = new StringBuilder();
    	text.append("Best path: ");
    	for(String current: path) {
    		text.append(current + ", ");
    	}
    	text.setLength(text.length() - 2);
    	text.append("\nDistance traveled: " + distanceTraveled + "\n");
    	text.append("Total time: " + BigDecimal.valueOf(CalculationUtil.timeSpentOnRoad(distanceTraveled)).setScale(2, BigDecimal.ROUND_HALF_UP) + " hours\n");
    	text.append("Cost of fuel: $" + BigDecimal.valueOf(CalculationUtil.costOfFuel(distanceTraveled)).setScale(2, BigDecimal.ROUND_HALF_UP) + "\n");
    	text.append("Driver Salary: $" + BigDecimal.valueOf(CalculationUtil.driverSalary(distanceTraveled)).setScale(2, BigDecimal.ROUND_HALF_UP) + "\n");
    	text.append("Helper Salary: $" + BigDecimal.valueOf(CalculationUtil.helpersSalary(distanceTraveled)).setScale(2, BigDecimal.ROUND_HALF_UP)+ "\n");
    	text.append("Hotel stay costs: $" + BigDecimal.valueOf(CalculationUtil.costOfHotel(distanceTraveled, citiesTraveled)).setScale(2, BigDecimal.ROUND_HALF_UP) + "\n");
    	text.append("Food expenses: $" + BigDecimal.valueOf(CalculationUtil.costOfFood(distanceTraveled, citiesTraveled)).setScale(2, BigDecimal.ROUND_HALF_UP) + "\n");
    	text.append("Maintenance cost: $" + BigDecimal.valueOf(CalculationUtil.costOfmaintenance(distanceTraveled)).setScale(2, BigDecimal.ROUND_HALF_UP) + "\n");
    	text.append("Total costs: $" + BigDecimal.valueOf(CalculationUtil.totalCost(distanceTraveled, citiesTraveled)).setScale(2, BigDecimal.ROUND_HALF_UP));
    
		return text.toString();
    	
    }
}