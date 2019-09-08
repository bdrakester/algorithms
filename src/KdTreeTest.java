import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.princeton.cs.algs4.Point2D;

class KdTreeTest {

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testInsert() {
       KdTree testTree = new KdTree();
       
       testTree.insert(new Point2D(0.7,0.2));
       testTree.insert(new Point2D(0.5,0.4));
       testTree.insert(new Point2D(0.2,0.3));
       testTree.insert(new Point2D(0.4,0.7));
       testTree.insert(new Point2D(0.9,0.6));
       
       assertTrue(testTree.contains(new Point2D(0.7,0.2)));
       assertTrue(testTree.contains(new Point2D(0.5,0.4)));
       assertTrue(testTree.contains(new Point2D(0.2,0.3)));
       assertTrue(testTree.contains(new Point2D(0.4,0.7)));
       assertTrue(testTree.contains(new Point2D(0.9,0.6)));
       assertFalse(testTree.contains(new Point2D(0.8,0.8)));
       
    }

}
