package lab8;


import calculator.CalculatorModel;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class Lab8Tests {

    private final CalculatorModel calculator = new CalculatorModel();

    
     //----- model tests start -----
    @Test
    void testAddition() {
        assertEquals(8, calculator.calculate(5, 3, "+"), "Addition test failed");
    }

    @Test
    void testDivision() {
        assertEquals(5, calculator.calculate(10, 2, "/"), "Division test failed");
    }

    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calculate(5, 0, "/");
        });
        assertEquals("Divide by zero error", exception.getMessage(), "Divide by zero test failed");
    }

    @Test
    void testSquareRootOfNegativeNumber() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calculate(-4, 0, "sqrt");
        });
        assertEquals("Cannot take the square root of a negative number", exception.getMessage(), "Square root of negative number test failed");
    }
    //----- model tests end -----
    
    
    //----- UI testing start -----
    
    @Test
    public void testButtonResponse() {
        // Simulate button clicks for "1", "2", "+", "3", "="
        clickButton("1");
        clickButton("2");
        clickButton("+");
        clickButton("3");
        clickButton("=");
        
        // Verify the display shows "15"
        String displayText = getDisplayText();
        assertEquals("15", displayText, "The display should show 15 after the sequence 1, 2, +, 3, =");
    }

    @Test
    public void testMemoryFunctions() {
        // Simulate button clicks for "8", "M+", "C", "MR"
        clickButton("8");
        clickButton("M+");
        clickButton("C"); // Assuming "C" is the clear button
        clickButton("MR");
        
        // Verify the display shows "8" after recall
        String displayText = getDisplayText();
        assertEquals("8", displayText, "The display should show 8 after recalling memory");
    }

    //----- UI testing start -----

}