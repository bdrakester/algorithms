import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FastCollinearPointsTest {
	private Point[] input8, input6;
	
	@BeforeEach
	void setUp() throws Exception {
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
	

	@Test
	void testInput8() {
		FastCollinearPoints myFCP = new FastCollinearPoints(input8);
		
		System.out.println("\ntestInput8:");
		for (LineSegment segment: myFCP.segments()) {
			System.out.println(segment);
		}
	}
	
	@Test
	void testInput6() {
		FastCollinearPoints myFCP = new FastCollinearPoints(input6);
		
		System.out.println("\ntestInput6:");
		for (LineSegment segment: myFCP.segments()) {
			System.out.println(segment);
		}
	}

}
