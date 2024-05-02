package calculator;

/**
 * The CalculatorModel class represents the model component of the calculator application.
 * It handles arithmetic calculations and memory operations.
 */
public class CalculatorModel {
    private double memory = 0.0;

    /**
     * Performs arithmetic calculations based on the given operands and operator.
     *
     * @param operand1 The first operand.
     * @param operand2 The second operand.
     * @param operator The arithmetic operator ('+', '-', '*', '/', 'sq', or 'sqrt').
     * @return The result of the calculation.
     * @throws ArithmeticException If the result exceeds the maximum representable value for doubles,
     *                              if attempting to divide by zero, or if taking the square root of a negative number.
     * @throws IllegalArgumentException If the provided operator is invalid.
     */
    public double calculate(double operand1, double operand2, String operator) {
    	
        if (operand1 > Double.MAX_VALUE || operand2 > Double.MAX_VALUE || (operand1 + operand2) > Double.MAX_VALUE) {
            throw new ArithmeticException("Number too big");
        }
    	
        switch (operator) {
            case "+": return operand1 + operand2;
            case "-": return operand1 - operand2;
            case "*": return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Divide by zero error");
                }
                return operand1 / operand2;
            case "sq": return operand1 * operand1;
            case "sqrt": 
            	
	        	if (operand1 < 0) {
	                throw new ArithmeticException("Cannot take the square root of a negative number");
                }
            	return Math.sqrt(operand1);
            	
            default: throw new IllegalArgumentException("Invalid operator.");
        }
    }

    /**
     * Adds the given value to the memory.
     *
     * @param value The value to add to the memory.
     * @throws ArithmeticException If the value to add exceeds the maximum representable value for doubles.
     */
    public void addToMemory(double value) {
    	if (value >= Double.MAX_VALUE ) {
            throw new ArithmeticException("Number too big");
        }
        memory += value;
        System.out.println("memory value: " + memory);
    }

    /**
     * Subtracts the given value from the memory.
     *
     * @param value The value to subtract from the memory.
     * @throws ArithmeticException If the value to subtract exceeds the maximum representable value for doubles.
     */
    public void subtractFromMemory(double value) {
    	if (value >= Double.MAX_VALUE ) {
            throw new ArithmeticException("Number too big");
        }
        memory -= value;
        System.out.println("memory value: " + memory);
    }

    /**
     * Retrieves the current value stored in the memory.
     *
     * @return The value stored in the memory.
     */
    public double getMemory() {
        return memory;
    }

    /**
     * Clears the memory, resetting it to zero.
     */
    public void clearMemory() {
        memory = 0.0;
    }
}
