package GeneticVisualizer;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
        primaryStage.setTitle("LineChart Experiments");

        NumberAxis xAxis = new NumberAxis(0, 15, 5);
        xAxis.setLabel("Generation");

        NumberAxis yAxis = new NumberAxis(1051, 1800, 50);
        yAxis.setLabel("Fitness");

        final LineChart lineChart = new LineChart(xAxis, yAxis);

        
        
        final XYChart.Series average1 = new XYChart.Series();
        average1.setName("average1");
        final XYChart.Series alpha1 = new XYChart.Series();
        alpha1.setName("alpha1");

        final XYChart.Series average2 = new XYChart.Series();
        average2.setName("average2");
        final XYChart.Series alpha2 = new XYChart.Series();
        alpha2.setName("alpha2");
        
        final XYChart.Series average3 = new XYChart.Series();
        average3.setName("average3");
        final XYChart.Series alpha3 = new XYChart.Series();
        alpha3.setName("alpha3");



        
        Button button = new Button("Generate");
        
        //TextField generations = new TextField("Generations to iterate");
        final Slider generations = new Slider(1, 15, 5); 
        generations.setShowTickLabels(true);
        generations.setShowTickMarks(true);
        
        //TextField population = new TextField("Members of population");
        final Slider population = new Slider(1, 30, 5); 
        population.setShowTickLabels(true);
        population.setShowTickMarks(true);
        
        //TextField mutation = new TextField("Mutation chance");
        final Slider mutation = new Slider(0, 1, 0.1); 
        mutation.setShowTickLabels(true);
        mutation.setShowTickMarks(true);
        
        button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent value) {
			   /*
			    * 		
			    GeneticAlgorithmManager manager = new GeneticAlgorithmManager(15, 0.5);  
				for(int j = 0; j < 15; j++) {
				System.out.println("Generation: " + manager.getGeneration() + "\n");
				System.out.println("Most fit: " + manager.getMostFit().getFitness() + "\n");
				System.out.println("Population: " + manager.population + "\n\n");
				manager.iterateGeneration();
			}
			    */
				
				average1.getData().clear();
				 alpha1.getData().clear();
				average2.getData().clear();
				 alpha2.getData().clear();
				average3.getData().clear();
				 alpha3.getData().clear();
				
				/*GeneticAlgorithmManager manager = new GeneticAlgorithmManager(Integer.parseInt(population.getText()), Double.parseDouble(mutation.getText()));  
				GeneticAlgorithmManager manager1 = new GeneticAlgorithmManager(Integer.parseInt(population.getText()), Double.parseDouble(mutation.getText()));  
				GeneticAlgorithmManager manager2 = new GeneticAlgorithmManager(Integer.parseInt(population.getText()), Double.parseDouble(mutation.getText()));*/  

				GeneticAlgorithmManager manager = new GeneticAlgorithmManager((int) population.getValue(), mutation.getValue());  
				GeneticAlgorithmManager manager1 = new GeneticAlgorithmManager((int) population.getValue(), mutation.getValue());  
				GeneticAlgorithmManager manager2 = new GeneticAlgorithmManager((int) population.getValue(), mutation.getValue()); 
				
				for(int i = 0; i < generations.getValue()+1; i++ ) {
					
					average1.getData().add(new XYChart.Data(manager.generation, manager.getAverageFitness())); 
					alpha1.getData().add(new XYChart.Data(manager.generation, manager.getMostFit().getFitness()));
					manager.iterateGeneration();
					
					average2.getData().add(new XYChart.Data(manager1.generation, manager1.getAverageFitness())); 
					alpha2.getData().add(new XYChart.Data(manager1.generation, manager1.getMostFit().getFitness()));
					manager1.iterateGeneration();
					
					average3.getData().add(new XYChart.Data(manager2.generation, manager2.getAverageFitness())); 
					alpha3.getData().add(new XYChart.Data(manager2.generation, manager2.getMostFit().getFitness()));
					manager2.iterateGeneration();
					
				}
				
				lineChart.getData().add(average1);
				lineChart.getData().add(alpha1);
				lineChart.getData().add(average2);
				lineChart.getData().add(alpha2);
				lineChart.getData().add(average3);
				lineChart.getData().add(alpha3);
			}
		});
        

        Label label = new Label("Generations: ");
        Label label1 = new Label("Population: ");
        Label label2 = new Label("Mutation rate: ");
        
        HBox hbox = new HBox(button, label, generations, label1, population, label2, mutation);
        VBox vbox = new VBox(lineChart, hbox);
        Scene scene = new Scene(vbox, 400, 400);
        

        
        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(800);

        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}