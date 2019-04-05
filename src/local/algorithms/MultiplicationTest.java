package local.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for Multiplication class
 * @author Brian Drake
 */
class MultiplicationTest {

	  public MultiplicationTest() {

	    }

	    /**
	     * Test of add method, of class Multiplication.
	     */
	    @Test
	    public void testAdd() {
	       Multiplication test = new Multiplication();
	       String[] testAddX = {"1", "34", "43", "567", "765", "9999", "9999", "53","97"};
	       String[] testAddY = {"2", "5", "22",  "45",  "543", "99",   "9999", "58","93"};
	       String[] testAddRes={"3", "39","65", "612", "1308", "10098", "19998", "111", "190"};
	       String result;
	       
	       for (int i = 0; i < testAddX.length; i++){
	           result = test.add(testAddX[i],testAddY[i]);
	           assertEquals(result,testAddRes[i]);
	       }  
	    }
	    
	    /**
	     * Test the subtract method.
	     */
	    @Test
	    public void testSubtract() {
	        Multiplication test = new Multiplication();
	        String subTests[][] = {
	            {"5","3","2"},
	            {"34","6","28"},
	            {"56","22","34"}
	        };
	        String result;
	        
	        for (String[] subTest : subTests) {
	            result = test.subtract(subTest[0], subTest[1]);
	            assertEquals(result,subTest[2]);
	        }
	    }
	    
	    /**
	     * Test the karatsubaMultiply method.
	     */
	    @Test
	    public void testKaratsubaMultiply() {
	        Multiplication test = new Multiplication();
	        String multTests[][] = {
	            {"3", "2", "6"},
	            {"34", "6", "204"},
	            {"56","12","672"},
	            {"78","34","2652"},
	            {"134","46","6164"},
	            {"5678","1234","7006652"},
	            {"14","346","4844"},
	            {"53","97","5141"},
	            {"58","93","5394"},
	            {"111","190","21090"},
	            {"201","452","90852"},
	            {"5358","9793","52470894"},
	            {"31415926","27182818","853973398759468"},
	            {"53589793","28459045","1525114330527685"},
	            {"2384626433832795","2353602874713526","5612463629786730108279808885170"},
	            {"3141592653589793","2718281828459045","8539734222673565677848730527685"},
	            {"31415926535897932384626433832795","27182818284590452353602874713526","853973422267356706546355086954637031247909995660108279808885170"},
	            {"02884197169399375105820974944592","62497757247093699959574966967627","180255854545876931315273184330549073013374895871952806582723184"},
	            {"3141592653589793238462643383279502884197169399375105820974944592", "2718281828459045235360287471352662497757247093699959574966967627","8539734222673567065463550869546574495034888535765114961879601127067743044893204848617875072216249073013374895871952806582723184"}
	        };
	        String result;
	        
	        for(String[] multTest : multTests){
	            result = test.karatsubaMultiply(multTest[0], multTest[1]);
	            assertEquals(result,multTest[2]);
	        }
	    }
}
