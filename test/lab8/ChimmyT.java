package lab8;


import calculator.CalculatorController;
import calculator.CalculatorModel;
import calculator.CalculatorView;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;


class ChimmyT {

    private final CalculatorModel calculator = new CalculatorModel();


     //----- model tests start -----
    
    //pass
    @Test
    void testAddition() {
        assertEquals(8, calculator.calculate(5, 3, "+"), "Addition test failed");
    }
    
    //pass
    @Test
    void testDivision() {
        assertEquals(5, calculator.calculate(10, 2, "/"), "Division test failed");
    }

    //pass
    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calculate(5, 0, "/");
        });
        assertEquals("Divide by zero error", exception.getMessage(), "Divide by zero test failed");
    }

    //pass
    @Test
    void testSquareRootOfNegativeNumber() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calculate(-4, 0, "sqrt");
        });
        assertEquals("Cannot take the square root of a negative number", exception.getMessage(), "Square root of negative number test failed");
    }
  
  //pass
    @Test
    void testMulitplaction() {
    	assertEquals(100, calculator.calculate(10, 10, "*"));
    }
    
    //pass
    @Test
    void testSubtraction() {
    	assertEquals(24, calculator.calculate(30, 6, "-"));
    }
    
    @Test
    public void testMemoryOperations() {
        CalculatorModel model = new CalculatorModel();
        model.addToMemory(10);
        assertEquals(10.0, model.getMemory(), 0.0001);
        
        model.subtractFromMemory(5);
        assertEquals(5.0, model.getMemory(), 0.0001);
        
        model.clearMemory();
        assertEquals(0.0, model.getMemory(), 0.0001);
    }
    //----- model tests end -----
    
  
//    
//    //----- Boundary testing start -----
    @Test
    public void testOverflow() {
        // Enter a very large number
        
        
        
        Exception exception = assertThrows(ArithmeticException.class, () -> {
        	calculator.addToMemory(Double.MAX_VALUE);
        });
        
        // Verify the display handles the overflow appropriately (e.g., error message)
        assertEquals("Number too big", exception.getMessage(), "Number too big");
    }

    @Test
    public void testOverflow2() {
    	
    	Exception exception = assertThrows(ArithmeticException.class, () -> {
    	  	  // Perform an operation that should cause an overflow
        	calculator.calculate(Double.MAX_VALUE,Double.MAX_VALUE,"+" );
        });
    	
        // Verify the display handles the overflow appropriately (e.g., error message)
        assertEquals("Number too big", exception.getMessage(), "Number too big");
        
    }
    
  
    
//    @Test
//    public void testInvalidInput() {
//        // Simulate button clicks or keypresses for invalid characters
//        enterInvalidCharacters();
//
//        // Verify the display does not change or shows an error
//        String displayText = getDisplayText();
//        assertEquals("Error", displayText, "The display should show an error message or remain unchanged after invalid input");
//    }

    //----- Boundary testing end -----
    
 
    

}