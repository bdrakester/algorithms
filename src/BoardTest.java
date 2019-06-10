//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BoardTest {


	@Test
	void testEqualsObject() {
		int[][] blocks1 = {
				{0,1,3},
				{4,2,5},
				{7,8,6}
		};
		int[][] blocks2 = {
				{0,1,3},
				{4,2,5},
				{7,8,6}
		};
		int[][] blocks3 = {
				{1,0,3},
				{4,2,5},
				{7,8,6}
		};
		
		Board board1 = new Board(blocks1);
		Board board2 = new Board(blocks2);
		Board board3 = new Board(blocks3);
		
		Assertions.assertTrue(board1.equals(board1));
		Assertions.assertTrue(board1.equals(board2));
		Assertions.assertFalse(board1.equals(board3));	
	}
	
	@Test 
	void testIsGoal() {
		int[][] blocks1 = {
				{0,1,3},
				{4,2,5},
				{7,8,6}
		};
		int[][] blocks2 = {
				{1,2,3},
				{4,5,6},
				{7,8,0}
		};
		int[][] blocks3 = {
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,0}
		};
		int[][] blocks4 = {
				{1,2,3,4},
				{5,6,0,8},
				{9,10,11,12},
				{13,14,15,7}
		};
		Board board1 = new Board(blocks1);
		Board board2 = new Board(blocks2);
		Board board3 = new Board(blocks3);
		Board board4 = new Board(blocks4);
		
		Assertions.assertFalse(board1.isGoal());
		Assertions.assertTrue(board2.isGoal());
		Assertions.assertTrue(board3.isGoal());
		Assertions.assertFalse(board4.isGoal());
		
	}
	
	@Test 
	void testHamming() {
		int[][] blocks1 = {
				{0,1,3},
				{4,2,5},
				{7,8,6}
		};
		int[][] blocks2 = {
				{1,2,3},
				{4,5,6},
				{7,8,0}
		};
		int[][] blocks3 = {
				{1,2,3,4},
				{5,6,0,8},
				{9,10,11,12},
				{13,14,15,7}
		};
		Board board1 = new Board(blocks1);
		Board board2 = new Board(blocks2);
		Board board3 = new Board(blocks3);
		
		Assertions.assertEquals(4, board1.hamming());
		Assertions.assertEquals(0, board2.hamming());
		Assertions.assertEquals(1,board3.hamming());	
	}
	
	@Test
	void testManhattan() {
		int[][] blocks1 = {
				{0,1,3},
				{4,2,5},
				{7,8,6}
		};
		int[][] blocks2 = {
				{1,0,3},
				{4,2,5},
				{7,8,6}
		};
		int[][] blocks3 = {
				{4,1,3},
				{0,2,5},
				{7,8,6}
		};
		int[][] blocks4 = {
				{1,2,3},
				{4,0,5},
				{7,8,6}
		};
		int[][] blocks5 = {
				{1,2,3},
				{4,5,6},
				{7,8,0}
		};
		
		Board board1 = new Board(blocks1);
		Board board2 = new Board(blocks2);
		Board board3 = new Board(blocks3);
		Board board4 = new Board(blocks4);
		Board board5 = new Board(blocks5);
		
		Assertions.assertEquals(4, board1.manhattan());
		Assertions.assertEquals(3, board2.manhattan());
		Assertions.assertEquals(5, board3.manhattan());
		Assertions.assertEquals(2, board4.manhattan());
		Assertions.assertEquals(0, board5.manhattan());
		
		
	}
	

}
