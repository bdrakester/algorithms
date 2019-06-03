import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BruteCollinearPointsTest {
	private Point[] testPoints;
	private Point[] input8;
	private Point[] input6;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Point[] testPoints = { 
				new Point(3,-4),
				new Point(1,1),
				new Point(3,2),
				new Point(5,2),
				new Point(5,2),
				new Point(-5,4),
				new Point(3,6)
		};
		this.testPoints = testPoints;
		
		Point[] input8 = {
				new Point(10000,0),
				new Point(0, 10000),
				new Point(3000, 7000),
				new Point(7000, 3000),
				new Point(20000, 21000),
				new Point(3000, 4000),
				new Point(14000,15000),
				new Point(6000,7000)
		};
		this.input8 = input8;
		
		Point[] input6 = {
				new Point(19000, 10000),
				new Point(18000, 10000),
				new Point(32000, 10000),
				new Point(21000, 10000),
				new Point(1234, 5678),
				new Point(14000, 10000)
		};
		this.input6 = input6;
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testBruteCollinearPoints8() {
		BruteCollinearPoints testBCP = new BruteCollinearPoints(input8);
		assertEquals(2, testBCP.numberOfSegments());
		
		assertEquals("(10000, 0) -> (0, 10000)", testBCP.segments()[0].toString());
		assertEquals("(3000, 4000) -> (20000, 21000)", testBCP.segments()[1].toString());
		
		System.out.println("Testing input8 ...");
		for(LineSegment segment : testBCP.segments()) {
			System.out.println(segment);
		}
	}
	
	@Test
	void test14() {
		Point[] input = {
				new Point(29888, 5740),
				new Point(9362, 27085),
				new Point(9362, 27085)
		};
		assertThrows(IllegalArgumentException.class, () -> new BruteCollinearPoints(input));

		
	}
	/*
	@Test
	void testBruteCollinearPoints6() {
		BruteCollinearPoints testBCP = new BruteCollinearPoints(input6);
		//assertEquals(1, testBCP.numberOfSegments());
		
		//assertEquals("(14000, 10000) -> (32000, 10000)", testBCP.segments()[0].toString());
		
		System.out.println("Testing input6 ...");
		for(LineSegment segment : testBCP.segments()) {
			System.out.println(segment);
		}
	}
	*/





}
