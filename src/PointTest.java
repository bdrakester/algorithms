import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.StdDraw;

class PointTest {
	Point p1, p2, p3, p4, p4_2, p5, p6;
	
	@BeforeEach
	void setUp() throws Exception {
		p1 = new Point(3,-4);
		p2 = new Point(1,1);
		p3 = new Point(3,2);
		p4 = new Point(5,2);
		p4_2 = new Point(5,2);
		p5 = new Point(-5,4);
		p6 = new Point(3,6);
		
		
        StdDraw.setScale(-10, 10);
        StdDraw.setPenRadius(0.03);
        StdDraw.clear(StdDraw.BLACK);       
        StdDraw.setPenColor(StdDraw.GREEN);
		
		p1.draw();
		p2.draw();
		p3.draw();
		p4.draw();
		p4_2.draw();
		p5.draw();
		p6.draw();	
		
		StdDraw.setPenRadius(0.01);
		//p1.drawTo(p6);
	}
	
	@AfterAll
	public static void cleanUp() {
	    while(true) {}
	}

	@Test
	void testCompareTo() {
		assertEquals(-1, p1.compareTo(p2));
		assertEquals(-1, p5.compareTo(p6));
		assertEquals(1, p2.compareTo(p1));
		assertEquals(1, p6.compareTo(p5));
		assertEquals(0, p4.compareTo(p4_2));
		assertEquals(0, p4_2.compareTo(p4));
		assertEquals(0, p5.compareTo(p5));
		assertEquals(-1, p3.compareTo(p5));
		assertEquals(1, p5.compareTo(p3));
	}
	
	@Test
	void testSlopeTo() {
		assertEquals(0.5, p2.slopeTo(p3));
		assertEquals(0.25, p5.slopeTo(p6));
		assertEquals(0.0, p3.slopeTo(p4));
		assertEquals(Double.POSITIVE_INFINITY, p6.slopeTo(p3));
		assertEquals(Double.NEGATIVE_INFINITY, p4.slopeTo(p4));
		assertEquals(Double.NEGATIVE_INFINITY, p4.slopeTo(p4_2));
	}
	
	@Test
	void testSlopeOrder() {
		assertEquals(1, p2.slopeOrder().compare(p3, p1));
		assertEquals(-1, p2.slopeOrder().compare(p1, p3));
		assertEquals(0, p3.slopeOrder().compare(p6, p1));
		assertEquals(0, p3.slopeOrder().compare(p6, p1));
		assertEquals(-1, p4.slopeOrder().compare(p4, p1));
		assertEquals(0, p4.slopeOrder().compare(p4, p4_2));
	}
	

}
