package lab8;


import calculator.CalculatorModel;
import static org.junit.jupiter.api.Assertions.*;
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
    //----- model tests end -----
    
    
    //----- UI testing start -----
//    
//    @Test
//    public void testButtonResponse() {
//        // Simulate button clicks for "1", "2", "+", "3", "="
//        clickButton("1");
//        clickButton("2");
//        clickButton("+");
//        clickButton("3");
//        clickButton("=");
//        
//        // Verify the display shows "15"
//        String displayText = getDisplayText();
//        assertEquals("15", displayText, "The display should show 15 after the sequence 1, 2, +, 3, =");
//    }
//
//    @Test
//    public void testMemoryFunctions() {
//        // Simulate button clicks for "8", "M+", "C", "MR"
//        clickButton("8");
//        clickButton("M+");
//        clickButton("C"); // Assuming "C" is the clear button
//        clickButton("MR");
//        
//        // Verify the display shows "8" after recall
//        String displayText = getDisplayText();
//        assertEquals("8", displayText, "The display should show 8 after recalling memory");
//    }

    //----- UI testing end -----
    
//    public void enterLargeNumber(CalculatorModel calculator) {
//        // This example assumes that your calculator can handle doubles and uses a large value to test overflow.
//        // Adjust the size of the number based on the data type used by your calculator.
//        calculator.pressNumber(Double.MAX_VALUE);
//    }
//    
//    public void performOperationThatCausesOverflow(CalculatorModel calculator) {
//        // First, enter a large number
//        calculator.pressNumber(Double.MAX_VALUE);
//        // Choose an operation that would likely cause overflow
//        calculator.pressOperation("*");
//        // Enter another large number
//        calculator.pressNumber(Double.MAX_VALUE);
//        // Perform the operation
//        calculator.pressOperation("=");
//    }
//
//    
//    public void enterInvalidCharacters(CalculatorModel calculator) {
//        // Since we're simulating, let's assume entering a character in a numeric-only calculator
//        // This could be implemented by catching an exception or checking for an error state
//        try {
//            calculator.pressNumber(Double.parseDouble("a"));  // This will throw a NumberFormatException
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid input handled: " + e.getMessage());
//        }
//    }
//    
//    public String getDisplayText(CalculatorModel calculator) {
//        // Assume Calculator has a method to get the current result or display text
//        return String.valueOf(calculator.getResult());
//    }


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