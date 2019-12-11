package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testNearestNeighbors()
    {
    	
    }
 
    public void testGenetic()
    {
    	
    	//Test case 1
    	
    	try{
	        
	    	int[][] ex1 = new int[][]{
				{ 0, 10, 15, 20},
				{10,  0, 35, 25},
				{15, 35,  0, 30},
				{20, 25, 30,  0}
			};
	        
			
			ArrayList<String> names =  new ArrayList<String>( Arrays.asList("1", "2", "3", "4"));
			System.out.println("GENETIC TEST 1");
			System.out.println("Input:\n" + 
			"				{ 0, 10, 15, 20},\r\n" + 
			"				{10,  0, 35, 25},\r\n" + 
			"				{15, 35,  0, 30},\r\n" + 
			"				{20, 25, 30,  0}\nvertices:" + names +"\nstart at \"1\"\n");
			
	        GeneticAlgorithmManager genetic = new GeneticAlgorithmManager(5, 0.3, "1", ex1, names.size(), names, "1");
	        System.out.println("This algorithm is non-deterministic, so there is no expected output (except for the \ndistance being equal to or greater than the optimal solution of 80).");
	        System.out.println("The optimal solution is 1 -> 2 -> 4 -> 3 -> 2\nOUTPUT:");
	        
	        for(int i = 0; i < 5; i++) {
	        	genetic.iterateGeneration();
	        	System.out.println("Most fit of generation " + genetic.getGeneration() + ": " + genetic.getMostFit().toString());
	        }
	        
	        assertTrue(genetic.getMostFit().getFitness() >= 80);
	        System.out.println("Final distance: " + genetic.getMostFit().getFitness() + "\n\n\n");
		        
		 } catch(Exception e) {
			 assertTrue(false);
		 }
    	
    	
    	
    	//Test case 2
    	
    	try{
	        
	    	int[][] ex2 = new int[][]{
				{0, 4, 1, 3},
				{4, 0, 2, 1},
				{1, 2, 0, 5},
				{3, 1, 5, 0}
			};
	        
			ArrayList<String> names =  new ArrayList<String>( Arrays.asList("1", "2", "3", "4")); ;
			
			System.out.println("GENETIC TEST 2");
			System.out.println("Input:\n" + 
			"				{0, 4, 1, 3},\r\n" + 
			"				{4, 0, 2, 1},\r\n" + 
			"				{1, 2, 0, 5},\r\n" + 
			"				{3, 1, 5, 0}\nvertices:" + names +"\nstart at \"1\"\n");
			
	        GeneticAlgorithmManager genetic = new GeneticAlgorithmManager(5, 0.3, "1", ex2, names.size(), names, "1");
	        System.out.println("This algorithm is non-deterministic, so there is no expected output (except for the \ndistance being equal to or greater than the optimal solution of 7).");
	        System.out.println("The optimal solution is 1 -> 3 -> 2 -> 4 -> 1\nOUTPUT:");
	        
	        for(int i = 0; i < 5; i++) {
	        	genetic.iterateGeneration();
	        	System.out.println("Most fit of generation " + genetic.getGeneration() + ": " + genetic.getMostFit().toString());
	        }
	        assertTrue(genetic.getMostFit().getFitness() >= 7);
	        System.out.println("Final distance: " + genetic.getMostFit().getFitness());
		        
		 } catch(Exception e) {
			 assertTrue(false);
		 }


    }
}
