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

    /**
     * Set up the environment for each test.
     * This method initializes the necessary components like the view, model, and controller
     * to ensure that each test starts with a fresh instance.
     */
    @BeforeEach
    public void setUp() {
        view = new CalculatorView(); // Instantiate the view part of MVC
        model = new CalculatorModel(); // Instantiate the model part of MVC
        controller = new CalculatorController(view, model); // Instantiate the controller with dependencies
    }
    
    //----- Model tests start -----

    /**
     * Test to ensure that addition is performed correctly.
     * Checks if the model correctly calculates the sum of two numbers.
     */
    @Test
    void testAddition() {
        assertEquals(8, calculator.calculate(5, 3, "+"), "Addition test failed");
    }
    
    /**
     * Test to ensure that division is performed correctly.
     * Checks if the model correctly calculates the division of two numbers.
     */
    @Test
    void testDivision() {
        assertEquals(5, calculator.calculate(10, 2, "/"), "Division test failed");
    }

    /**
     * Test to check the behavior when attempting to divide by zero.
     * Expects an ArithmeticException to be thrown with a specific error message.
     */
    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calculate(5, 0, "/");
        });
        assertEquals("Divide by zero error", exception.getMessage(), "Divide by zero test failed");
    }

    /**
     * Test to check the calculation of the square root of a negative number.
     * Expects an ArithmeticException to ensure the model correctly handles this error scenario.
     */
    @Test
    void testSquareRootOfNegativeNumber() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.calculate(-4, 0, "sqrt");
        });
        assertEquals("Cannot take the square root of a negative number", exception.getMessage(), "Square root of negative number test failed");
    }

    /**
     * Test to ensure that multiplication is performed correctly.
     * Validates that the model correctly multiplies two numbers.
     */
    @Test
    void testMulitplaction() {
        assertEquals(100, calculator.calculate(10, 10, "*"), "Multiplication test failed");
    }
    
    /**
     * Test to ensure that subtraction is performed correctly.
     * Checks if the model correctly calculates the difference between two numbers.
     */
    @Test
    void testSubtraction() {
        assertEquals(24, calculator.calculate(30, 6, "-"), "Subtraction test failed");
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
    
    /**
     * Test memory operations including add, subtract, and clear functionality.
     * This test checks if the memory operations correctly update the internal state of memory.
     */
    @Test
    public void testMemoryOperations2() {
        CalculatorModel model = new CalculatorModel();
        model.addToMemory(10); // Add 10 to memory
        assertEquals(10.0, model.getMemory(), "Memory should be 10 after adding 10");

        model.subtractFromMemory(5); // Subtract 5 from memory
        assertEquals(5.0, model.getMemory(), "Memory should be 5 after subtracting 5");

        model.clearMemory(); // Clear the memory
        assertEquals(0.0, model.getMemory(), "Memory should be 0 after clearing");

        model.addToMemory(8); // Add 8 to memory
        assertEquals(8.0, model.getMemory(), "Memory should be 8 after adding 8");
    }
    //----- model tests end -----
    
  
//    
//    //----- Boundary testing start -----
    @Test
    public void testOverflow() {

        Exception exception = assertThrows(ArithmeticException.class, () -> {
        	calculator.addToMemory(Double.MAX_VALUE + 1);
        });
        
        // Verify the display handles the overflow appropriately (e.g., error message)
        assertEquals("Number too big", exception.getMessage(), "Number too big");
    }
    
    
//no pasa
    @Test
    public void testOverflow2() {
    	
    	Exception exception = assertThrows(ArithmeticException.class, () -> {
    	  	  // Perform an operation that should cause an overflow
        	calculator.calculate(Double.MAX_VALUE,Double.MAX_VALUE,"+" );
        });
    	
        // Verify the display handles the overflow appropriately (e.g., error message)
        assertEquals("Number too big", exception.getMessage(), "Number too big");
        
    }

    /**
     * Test to verify the model's response to overflow scenarios, specifically when adding a large number to memory.
     * This test ensures that the model throws an appropriate exception when overflow conditions are met.
     */
    @Test
    public void testOverflow3() {
        CalculatorModel calculator = new CalculatorModel();

        // Simulate an overflow by adding the maximum double value
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.addToMemory(Double.MAX_VALUE);
            calculator.addToMemory(1); // This operation should cause an overflow
        });

        // Verify that the appropriate exception is thrown indicating an overflow
        assertEquals("Number too big", exception.getMessage(), "Expected exception for number too big was not thrown");
    }
    
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
    
    @Test
    void testClearMemory() {
        // Initially add some value to memory to ensure it's not zero
        model.addToMemory(100.0);

        // Ensure memory is not zero before clearing
        assertNotEquals(0.0, model.getMemory(), "Memory should not be zero before clearing.");

        // Execute
        controller.clearMemory();

        // Verify that memory is cleared
        assertEquals(0.0, model.getMemory(), "Memory should be zero after clearing.");
    }
    
    @Test
    void testDeleteLastCharacter() {
        // Set up a non-empty display
        JTextField display = view.getDisplay();
        display.setText("12345");

        // Execute
        controller.deleteLastCharacter();

        // Verify
        assertEquals("1234", display.getText(), "Display should have last character deleted.");
    }

    @Test
    void testDeleteLastCharacterWhenEmpty() {
        // Set up an empty display
        JTextField display = view.getDisplay();
        display.setText("");

        // Execute
        controller.deleteLastCharacter();

        // Verify
        assertEquals("", display.getText(), "Display should remain empty after attempting to delete the last character.");
    }
    

    @Test
    void testClearAllResetsDisplayAndAllInternalStates() {
        // Setup initial states
        JTextField display = view.getDisplay();
        display.setText("12345");
        model.addToMemory(50.0); // Add something to memory
        setInternalStates(10.0, "+", 20.0); // Set internal states

        // Execute
        controller.clearAll();

        // Verify Display is cleared
        assertEquals("", display.getText(), "Display should be cleared.");

        // Verify internal states are reset
        assertEquals(0.0, getCurrentOperand(), "Current operand should be reset to 0.");
        assertNull(getPendingOperation(), "Pending operation should be null.");
        assertEquals(0.0, getResult(), "Result should be reset to 0.");

        // Verify memory is cleared
        assertEquals(0.0, model.getMemory(), "Memory should be cleared.");
    }
    
    // Utility methods for setting and getting private fields if direct access is not allowed
    private void setInternalStates(double currentOperand, String operation, double result) {
        // Reflectively set internal states or add test-specific methods in your controller for testing
    	  try {
    	        // Set the currentOperand field
    	        Field currentOperandField = CalculatorController.class.getDeclaredField("currentOperand");
    	        currentOperandField.setAccessible(true);
    	        currentOperandField.set(controller, currentOperand);

    	        // Set the pendingOperation field
    	        Field pendingOperationField = CalculatorController.class.getDeclaredField("pendingOperation");
    	        pendingOperationField.setAccessible(true);
    	        pendingOperationField.set(controller, operation);

    	        // Set the result field
    	        Field resultField = CalculatorController.class.getDeclaredField("result");
    	        resultField.setAccessible(true);
    	        resultField.set(controller, result);
    	    } catch (NoSuchFieldException | IllegalAccessException e) {
    	        e.printStackTrace();
    	    }
    }


    private double getCurrentOperand() {
        try {
            Field field = CalculatorController.class.getDeclaredField("currentOperand");
            field.setAccessible(true);
            return (double) field.get(controller);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return Double.NaN; // Not a Number to indicate error
        }
    }

    private String getPendingOperation() {
        try {
            Field field = CalculatorController.class.getDeclaredField("pendingOperation");
            field.setAccessible(true);
            return (String) field.get(controller);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null; // Indicate error
        }
    }

    private double getResult() {
        try {
            Field field = CalculatorController.class.getDeclaredField("result");
            field.setAccessible(true);
            return (double) field.get(controller);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return Double.NaN; // Not a Number to indicate error
        }
    }
    
    
    
    // controller testing end
    
    
    //view testing start
    
    @Test
    void testGetDecimalButton() {
        assertNotNull(view.getDecimalButton(), "Decimal button should not be null.");
    }

    @Test
    void testGetEqualsButton() {
        assertNotNull(view.getEqualsButton(), "Equals button should not be null.");
    }

    @Test
    void testGetNumberButtons() {
        assertNotNull(view.getNumberButtons(), "Number buttons should not be null.");
        assertEquals(10, view.getNumberButtons().length, "There should be 10 number buttons.");
    }

    @Test
    void testGetAddButton() {
        assertNotNull(view.getAddButton(), "Add button should not be null.");
    }

    @Test
    void testGetSubtractButton() {
        assertNotNull(view.getSubtractButton(), "Subtract button should not be null.");
    }

    @Test
    void testGetMultiplyButton() {
        assertNotNull(view.getMultiplyButton(), "Multiply button should not be null.");
    }

    @Test
    void testGetDivideButton() {
        assertNotNull(view.getDivideButton(), "Divide button should not be null.");
    }

    @Test
    void testGetSqrtButton() {
        assertNotNull(view.getSqrtButton(), "Sqrt button should not be null.");
    }

    @Test
    void testGetSquareButton() {
        assertNotNull(view.getSquareButton(), "Square button should not be null.");
    }

    @Test
    void testGetMemoryAddButton() {
        assertNotNull(view.getMemoryAddButton(), "Memory Add button should not be null.");
    }

    @Test
    void testGetMemorySubtractButton() {
        assertNotNull(view.getMemorySubtractButton(), "Memory Subtract button should not be null.");
    }

    @Test
    void testGetMemoryRecallButton() {
        assertNotNull(view.getMemoryRecallButton(), "Memory Recall button should not be null.");
    }

    @Test
    void testGetMemoryClearButton() {
        assertNotNull(view.getMemoryClearButton(), "Memory Clear button should not be null.");
    }

    @Test
    void testGetDeleteButton() {
        assertNotNull(view.getDeleteButton(), "Delete button should not be null.");
    }

    @Test
    void testGetClearButton() {
        assertNotNull(view.getClearButton(), "Clear button should not be null.");
    }

    @Test
    void testGetDisplay() {
        assertNotNull(view.getDisplay(), "Display should not be null.");
    }

    @Test
    void testUpdateDisplay() {
        JTextField display = view.getDisplay();
        view.updateDisplay("Test");
        assertEquals("Test", display.getText(), "Display should be updated with 'Test'.");
    }
    
    //view testing end
    
    


    //----- Boundary testing end -----
    
 
    

}