package calculator;

public class CalculatorModel {
    private double memory = 0.0;

    public double calculate(double operand1, double operand2, String operator) {
    	
        if (operand1 >= Double.MAX_VALUE || operand2 >= Double.MAX_VALUE) {
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

    public void addToMemory(double value) {
    	if (value >= Double.MAX_VALUE ) {
            throw new ArithmeticException("Number too big");
        }
        memory += value;
        System.out.println("memory value: " + memory);
    }

    public void subtractFromMemory(double value) {
    	if (value >= Double.MAX_VALUE ) {
            throw new ArithmeticException("Number too big");
        }
        memory -= value;
        System.out.println("memory value: " + memory);
    }

    public double getMemory() {
        return memory;
    }

    public void clearMemory() {
        memory = 0.0;
    }
}
