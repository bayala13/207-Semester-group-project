package ShortestPathAlgorithm207.ShortestPathAlgorithm207;

import junit.framework.TestCase;

public class NearestNeighborGraphTest extends TestCase {
	
	NearestNeighborGraph testGraph;

	protected void setUp() throws Exception {
		testGraph = null;
	}

	protected void tearDown() throws Exception {
		testGraph = null;
	}

	public void testNearestNeighborGraphA() {
		String origin = "Rockville";
		String cities[] = {"Rockville", "Silver Spring", "Philadelphia", "Pittsburgh", "Baltimore", "Cleveland", "New York City"};
		int[][] distanceMatrix = new int[][]{
		   {0  , 13 , 142, 225, 40 , 352, 227},
		   {13 , 0  , 136, 237, 34 , 363, 222},
		   {141, 135, 0  , 305, 101, 432, 97 },
		   {226, 237, 304, 0  , 248, 133, 371},
		   {40 , 34 , 106, 248, 0  , 374, 192},
		   {352, 364, 431, 133, 375, 0  , 462},
		   {228, 222, 97 , 370, 118, 462, 0  }
		  };

		
		testGraph = new NearestNeighborGraph(distanceMatrix, cities, origin);
		
		assertEquals("Rockville", testGraph.path.get(0));
		assertEquals("Silver Spring", testGraph.path.get(1));
		assertEquals("Baltimore", testGraph.path.get(2));
		assertEquals("Philadelphia", testGraph.path.get(3));
		assertEquals("New York City", testGraph.path.get(4));
		assertEquals("Pittsburgh", testGraph.path.get(5));
		assertEquals("Cleveland", testGraph.path.get(6));
		assertEquals("Rockville", testGraph.path.get(7));
		
		assertEquals(1105, testGraph.getDistanceTraveled(), 0.001);
	}
	
	public void testNearestNeighborGraphB() {
		String origin = "city 3";
		String cities[] = {"city 1", "city 2", "city 3", "city 4", "city 5"};
		int[][] distanceMatrix = new int[][]{
			   {0  , 132 , 217, 164, 58},
			   {132 , 0  , 290, 201, 79},
			   {217, 290, 0  , 113, 303},
			   {164, 201, 113, 0  , 196},
			   {58 , 79 , 303, 196, 0  }
		};

		
		testGraph = new NearestNeighborGraph(distanceMatrix, cities, origin);
		
		assertEquals("city 3", testGraph.path.get(0));
		assertEquals("city 4", testGraph.path.get(1));
		assertEquals("city 1", testGraph.path.get(2));
		assertEquals("city 5", testGraph.path.get(3));
		assertEquals("city 2", testGraph.path.get(4));
		assertEquals("city 3", testGraph.path.get(5));
		
		assertEquals(704, testGraph.getDistanceTraveled(), 0.001);
		
	}
}
