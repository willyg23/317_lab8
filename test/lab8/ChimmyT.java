package lab8;


import calculator.CalculatorController;
import calculator.CalculatorModel;
import calculator.CalculatorView;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextField;

//import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;


class ChimmyT {

    private final CalculatorModel calculator = new CalculatorModel();



    private CalculatorController controller;
    private CalculatorView view;
    private CalculatorModel model;

    @BeforeEach
    public void setUp() {
        view = new CalculatorView(); // Actual view object
        model = new CalculatorModel(); // Actual model object
        controller = new CalculatorController(view, model);
    }
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
        assertEquals(10.0, model.getMemory(), 10);
        
        model.subtractFromMemory(5);
        assertEquals(5.0, model.getMemory(), 5);
        
        model.clearMemory();
        assertEquals(0.0, model.getMemory(), 0.0);
        
        model.addToMemory(8);
        model.getMemory();
        assertEquals(0.0, model.getMemory(), 8);
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
    
    

    
    //controller testing cuh
//    private final CalculatorView view = new CalculatorView();
//    private final CalculatorController controller = new CalculatorController(view ,calculator);
    
    @Test
    public void testAppendDecimalNoExistingDecimal() {
        // Setup
        JTextField display = view.getDisplay();
        display.setText("123");

        // Execute
        controller.appendDecimal();

        // Verify
        assertEquals("123.", display.getText());
    }

    @Test
    public void testAppendDecimalExistingDecimal() {
        // Setup
        JTextField display = view.getDisplay();
        display.setText("123.45");

        // Execute
        controller.appendDecimal();

        // Verify
        assertEquals("123.45", display.getText()); // Should not change
    }

    @Test
    public void testAppendDecimalAfterSpace() {
        // Setup
        JTextField display = view.getDisplay();
        display.setText("123.45 ");

        // Execute
        controller.appendDecimal();

        // Verify
        assertEquals("123.45 .", display.getText()); // Should append a new decimal after the space
    }
    
  

    @Test
    void testAppendNumberToEmptyDisplay() {
        // Set up the display initially empty
        JTextField display = view.getDisplay();
        display.setText("");

        // Execute
        controller.appendNumber("5");

        // Verify
        assertEquals("5", display.getText(), "Display should show '5' after appending to empty display.");
    }

    @Test
    void testAppendNumberToNonEmptyDisplay() {
        // Set up the display with initial content
        JTextField display = view.getDisplay();
        display.setText("123");

        // Execute
        controller.appendNumber("4");

        // Verify
        assertEquals("1234", display.getText(), "Display should show '1234' after appending '4' to '123'.");
    }
    
    @Test
    void testPerformOperationClearsDisplay() {
        // Set up the display with initial content
        JTextField display = view.getDisplay();
        display.setText("42");

        // Execute
        controller.performOperation("+");

        // Verify
        assertEquals("", display.getText(), "Display should be cleared after performing operation.");
    }

    @Test
    void testPerformOperationSequence() throws NoSuchFieldException, IllegalAccessException {
        // Prepare the scenario
        JTextField display = view.getDisplay();
        display.setText("12"); // Set first operand
        
        // Use reflection to directly manipulate internal state
        Field currentOperandField = CalculatorController.class.getDeclaredField("currentOperand");
        currentOperandField.setAccessible(true);
        currentOperandField.set(controller, 12.0);

        Field pendingOperationField = CalculatorController.class.getDeclaredField("pendingOperation");
        pendingOperationField.setAccessible(true);
        pendingOperationField.set(controller, "+");

        display.setText("3"); // Set second operand

        // Call computeResult directly for the purpose of the test
        controller.computeResult(); // This assumes you can directly call computeResult or adjust accordingly

        // Verify the result
        assertEquals("15.0", display.getText(), "Display should show the result of 12 + 3.");
    }
    
    @Test
    void testComputeResultValidOperation() {
        // Set up a scenario for addition
        JTextField display = view.getDisplay();
        display.setText("3"); // Second operand
        controller.performOperation("+"); // Set operation and first operand
        display.setText("2"); // Set second operand for operation

        // Execute
        controller.computeResult();

        // Verify
        assertEquals("5.0", display.getText(), "Display should show the result of 3 + 2.");
    }

    @Test
    void testComputeResultHandlesDivideByZero() {
        // Set up a scenario for division by zero
        JTextField display = view.getDisplay();
        display.setText("0"); // Second operand
        controller.performOperation("/"); // Set operation and first operand
        display.setText("0"); // Attempt to divide by zero

        // Execute
        controller.computeResult();

        // Verify
        assertEquals("Divide by zero error", display.getText(), "Display should show an error message for divide by zero.");
    }
    
    @Test
    void testSquareRootOperation() {
        // Set up
        JTextField display = view.getDisplay();
        display.setText("9"); // Set the number for square root

        // Execute
        controller.performSingleOperandOperation("sqrt");

        // Verify
        assertEquals("3.0", display.getText(), "Display should show the square root of 9.");
    }

    @Test
    void testSquareOperation() {
        // Set up
        JTextField display = view.getDisplay();
        display.setText("5"); // Set the number to square

        // Execute
        controller.performSingleOperandOperation("sq");

        // Verify
        assertEquals("25.0", display.getText(), "Display should show 5 squared.");
    }

    @Test
    void testUnknownOperationHandling() {
        // Set up
        JTextField display = view.getDisplay();
        display.setText("10");

        // Execute & Verify
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.performSingleOperandOperation("xyz");
        });

        assertEquals("Unknown operation xyz", exception.getMessage(), "Should throw an exception for unknown operations.");
    }
    

    @Test
    void testAddingToMemory() {
        // Setting up the necessary state
        setControllerResult(5.0); // Assume result is 5.0

        // Execute
        controller.updateMemory(1);

        // Verify
        assertEquals(5.0, model.getMemory(), "Memory should have 5.0 added.");
    }

    @Test
    void testSubtractingFromMemory() {
        // Setting up the necessary state
        setControllerResult(5.0); // Assume result is 5.0
        model.addToMemory(10.0); // Initial memory state

        // Execute
        controller.updateMemory(-1);

        // Verify
        assertEquals(5.0, model.getMemory(), "Memory should have 5.0 subtracted, leaving 5.0.");
    }

    @Test
    void testErrorHandlingForZeroResult() {
        // Set up
        JTextField display = view.getDisplay();
        setControllerResult(0); // Set result to 0

        // Execute
        controller.updateMemory(1);

        // Verify
        assertEquals("Error", display.getText(), "Display should show an error message when result is zero.");
    }

    // Utility method to set the private result field via reflection or a test-specific method
    private void setControllerResult(double result) {
        // Reflectively set the result or use a method if available in your testing framework
        try {
            java.lang.reflect.Field resultField = CalculatorController.class.getDeclaredField("result");
            resultField.setAccessible(true);
            resultField.set(controller, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void testMemoryRecallDisplaysCorrectValue() {
        // Setting up the memory value
        model.addToMemory(100.0); // Assume we added 100 to memory earlier

        // Execute
        controller.recallMemory();

        // Verify the display shows the memory value
        assertEquals("100.0", view.getDisplay().getText(), "Display should show the memory value.");
    }

    @Test
    void testCurrentOperandSetAfterMemoryRecall() {
        // Setting up the memory value
        model.addToMemory(150.0); // Assume we added 150 to memory earlier

        // Execute
        controller.recallMemory();

        // Check currentOperand via reflection or add method
        assertEquals(150.0, getCurrentOperandThroughReflection(), 0.001, "Current operand should be set to the recalled memory value.");
    }

    // Utility method to get the private currentOperand field via reflection
    private double getCurrentOperandThroughReflection() {
        try {
            java.lang.reflect.Field currentOperandField = CalculatorController.class.getDeclaredField("currentOperand");
            currentOperandField.setAccessible(true);
            return (double) currentOperandField.get(controller);
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Just to handle errors
        }
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