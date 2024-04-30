package calculator;

public class CalculatorModel {
    private double memory = 0.0;

    public double calculate(double operand1, double operand2, String operator) {
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
            case "sqrt": return Math.sqrt(operand1);
            default: throw new IllegalArgumentException("Invalid operator.");
        }
    }

    public void addToMemory(double value) {
        memory += value;
        System.out.println("memory value: " + memory);
    }

    public void subtractFromMemory(double value) {
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
