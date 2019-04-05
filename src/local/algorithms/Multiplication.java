package local.algorithms;

/**
 * The Multiplication program implements integer multiplication using the 
 * grade school, recursive and Karatsuba algorithms.
 * @author Brian Drake
 */
public class Multiplication {

    /**
     * This method multiplies two integers using the grade school method 
     * @param x 
     * @param y 
     * @return product of x and y
     */
    public long gradeSchoolMultiply(long x, long y){
        return 0L;
    }
    
    /**
     * This method multiplies two integers using the recursive method
     * @param x
     * @param y
     * @return product of x and y
     */
    public long recursiveMultiply(long x, long y){
        return 0L;
    }
    
    /**
     * The method multiplies two integers using the karatsuba method. 
     * @param x
     * @param y
     * @return product of x and y
     */
    public String karatsubaMultiply(String x, String y){
        String a,b,c,d;
        int lenX = x.length();
        int lenY = y.length();
        int n, nOver2;
        
        if (lenX == 1 && lenY == 1){
            Long xLong = Long.valueOf(x);
            Long yLong = Long.valueOf(y);
            
            return String.valueOf(xLong * yLong);
        }
        
        // Make x and y equal length by padding 0s to front if neccessary
        if (lenX > lenY){
            y = padZeros(y, lenX-lenY);
        }
        
        else if(lenX < lenY){
            x = padZeros(x, lenY-lenX);
        }
        
        // Pad a zero to front of both if number of digits is odd
        if ( (x.length() % 2) != 0){
            x = padZeros(x,1);
            y = padZeros(y,1);
        }
        n = x.length();
        nOver2 = n/2;
        
        a = x.substring(0,nOver2);
        b = x.substring(nOver2, n);
        c = y.substring(0,nOver2);
        d = y.substring(nOver2,n);
        
        String ac = this.karatsubaMultiply(a,c);
        String bd = this.karatsubaMultiply(b,d);
        String a_plus_b = this.add(a,b);
        String c_plus_d = this.add(c,d);
        String a_plus_b_x_c_plus_d = this.karatsubaMultiply(a_plus_b, c_plus_d);
        String ad_plus_bc = this.subtract(this.subtract(a_plus_b_x_c_plus_d, ac),bd);
       
        String term1 = this.mulitplyTenTo(ac, n);
        String term2 = this.mulitplyTenTo(ad_plus_bc, nOver2);
       
        String product = this.add(this.add(term1,term2), bd);
       
        product = this.removeLeadingZeros(product);
        return product;
    }
    
    /**
     * This method returns the sum of x and y 
     * @param x String containing the first integer 
     * @param y String containing the second integer
     * @return String The sum of x and y
     */
    public String add(String x, String y){
        int lenX = x.length();
        int lenY = y.length();
        int n;
        
        if (lenX > lenY){
            y = padZeros(y, lenX - lenY);
            n = lenX;
        }
        else if(lenX < lenY){
            x = padZeros(x, lenY - lenX);
            n = lenY;
        }
        else{
            n = lenX;
        }
        
        String result = "";
        int a,b,c;
        int carry = 0;
        
        for (int i = n-1; i >= 0; i--){
            a = Character.getNumericValue(x.charAt(i));
            b = Character.getNumericValue(y.charAt(i));
            c = a + b + carry;
           
            if (c >= 10){
                carry = 1;
                c = c - 10;
            }
            else{
                carry = 0;
            }
            
            result = String.valueOf(c) + result;
        }
        
        if(carry == 1){
            result = "1" + result;
        }
        return result;
    }
    
    /**
     * Returns x minus y 
     * @param x String The first integer
     * @param y String the second integer
     * @return String The difference between x and y
     */
    public String subtract(String x, String y){
        int lenX = x.length();
        int lenY = y.length();
        int n;
        
        if (lenX > lenY){
            y = padZeros(y, lenX - lenY);
            n = lenX;
        }
        else if(lenX < lenY){
            x = padZeros(x, lenY - lenX);
            n = lenY;
        }
        else{
            n = lenX;
        }
        
        String result = "";
        int a,b,c;
        int borrow = 0;
        
        for (int i = n-1; i >= 0; i--){
            a = Character.getNumericValue(x.charAt(i));
            b = Character.getNumericValue(y.charAt(i));
            
            a = a - borrow;
            
            if (a >= b){
                c = a - b;
                borrow = 0;
            }
            /* if b > a need to borrow */
            else{
                c = (10 + a) - b;
                borrow = 1;
            }
            
            result = String.valueOf(c) + result;
        }
        
        // Remove any leading 0s 
        result = this.removeLeadingZeros(result);
        
        return result;
    }
    
    /**
     * Returns a string without any leading zeros
     * @param x The string 
     * @return String containing x without zeros at the beginning
     */
    private String removeLeadingZeros(String x){
        int i = 0;
        while(i < (x.length() - 1) && x.charAt(i) == '0'){
            i++;
        }
        if (i > 0){
            x = x.substring(i);
        }
        
        return x;
    }
    
    /**
     * Returns x multiplied by 10^n
     * @param x The integer to be multiplied
     * @param n The power of 10 
     * @return String containing x multiplied by 10^n
     */
    private String mulitplyTenTo(String x, int n){
       char[] zeros = new char[n];
       for (int index = 0; index < n; index++){
           zeros[index] = '0';
       }
       String TenToN = new String(zeros);
       String result = x + TenToN;
       
       return result;
    }
    
    /**
     * Adds n zeros to beginning of the x
     * @param x String The integer to be padded
     * @param n Integer The number of zeros to pad
     * @return String The integer x with n zeros added to the beginning
     */
    private String padZeros(String x, int n){
        char [] zeros = new char[n];
        for (int index = 0; index < n; index++){
            zeros[index] = '0';
        }
        String pad = new String(zeros);
        String result = pad + x;
        
        return result;
    }
    
    /**
     * Test the Karatsuba multiplication function
     * @param x String The first integer
     * @param y String The second integer
     * @param expectedResult The expected result of the multiplication
     * @return Boolean True if the result matches the expected result
     */
    public static boolean testKaratsuba(String x, String y, String expectedResult){
        Multiplication test = new Multiplication();
        String result = test.karatsubaMultiply(x,y);
        
        System.out.println("\nKaratsuba Test Case: " + x + " * " + y);
        System.out.println("Result = " + result);
        System.out.println("Expected = " + expectedResult);
        if(result.equals(expectedResult)){
            System.out.println("Success!");
            return true;
        }
        else{
            System.out.println("Failure :(");
            return false;
        }
        
    }
    
    /**
     * Test the addition method.
     * @param x
     * @param y
     * @param expectedResult
     * @return 
     */
    public static boolean testAdd(String x, String y, String expectedResult){
       Multiplication test = new Multiplication();
       String result = test.add(x,y);
       
       System.out.println("\nAdd Test Case: " + x + " + " + y);
       System.out.println("Result = " + result);
       System.out.println("Expected = " + expectedResult);
       
       if(result.equals(expectedResult)){
           System.out.println("Success!");
           return true;
       }
       
       else{
           System.out.println("Failure :(");
           return false;
       }
       
    }
    
    /**
     * Tests the subtract method.
     * @param x
     * @param y
     * @param expectedResult
     * @return 
     */
    public static boolean testSub(String x, String y, String expectedResult){
       Multiplication test = new Multiplication();
       String result = test.subtract(x,y);
       
       System.out.println("\nSubtract Test Case: " + x + " - " + y);
       System.out.println("Result = " + result);
       System.out.println("Expected = " + expectedResult);
       
       if(result.equals(expectedResult)){
           System.out.println("Success!");
           return true;
       }
       
       else{
           System.out.println("Failure :(");
           return false;
       }
       
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome...");
        
        
        int addSuccess = 0;
        int addFailures = 0;
        //String[] testAddX = {"3383", "7471"};
        //String[] testAddY = {"2795", "3526"};
        //String[] testAddRes={"6178", "10997"};
        
        String[] testAddX = {"1", "34", "43", "567", "765", "9999", "9999", "53","97"};
        String[] testAddY = {"2", "5", "22",  "45",  "543", "99",   "9999", "58","93"};
        String[] testAddRes={"3", "39","65", "612", "1308", "10098", "19998", "111", "190"};
        
        for (int i = 0; i < testAddX.length; i++){
            if( testAdd(testAddX[i],testAddY[i],testAddRes[i]) ){
                addSuccess++;
            }
            else{
                addFailures++;
            }
        }
        
        System.out.println("");
        
        
        // Test cases = [i][0] = x, [i][1] = y, [i][2] = expected result
        String subTests[][] = {
            {"5","3","2"},
            {"34","6","28"},
            {"56","22","34"}
        };
        int subSuccess = 0;
        int subFailure = 0;
        
        for (int i = 0; i < subTests.length; i++){
            if( testSub(subTests[i][0], subTests[i][1], subTests[i][2]) ){
                subSuccess++;
            }
            else{
                subFailure++;
            }
        }
        System.out.println("");
        
        
        // Test cases - [i][0] = x, [i][1] = y, [i][2] = expected result
        String multTests2[][] = {
            {"6178","10997","67939466"},
            {"2795", "3526", "9855170"},
            {"3383","7471","25274393"},
            {"2384626433832795","2353602874713526","5612463629786730108279808885170"},
            {"23846264","33832795","806785761427880"},
            {"33832795","74713526","2527767408885170"}
        };
        
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
        
        int karatsubaSuccess = 0;
        int karatsubaFailure = 0;
        
        for (int i = 0; i < multTests.length; i++){
            if(testKaratsuba(multTests[i][0], multTests[i][1], multTests[i][2])){
                karatsubaSuccess++;
            }
            else{
                karatsubaFailure++;
            }
            
        }
        System.out.println("");
        
        
        System.out.println("Test Addition - Successes: " + addSuccess + "  Failures: " + addFailures);
        System.out.println("Test Subtraction - Successes: " + subSuccess + "  Failures: " + subFailure);
        System.out.println("Test Karatsuba - Successes: " + karatsubaSuccess + "  Failures: " + karatsubaFailure);
    }
    

    
}

