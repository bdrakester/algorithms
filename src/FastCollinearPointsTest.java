import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FastCollinearPointsTest {
	private Point[] input8;
	
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
	}

	@Test
	void testInput8() {
		FastCollinearPoints myFCP = new FastCollinearPoints(input8);
		
		System.out.println("\ntestInput8:");
		for (LineSegment segment: myFCP.segments()) {
			System.out.println(segment);
		}
	}

}
