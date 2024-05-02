package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * The CalculatorController class handles the user interactions and logic for the calculator application.
 * It connects the view (UI) with the model (data and operations).
 */
public class CalculatorController {
    private CalculatorView view;
    private CalculatorModel model;
    private double currentOperand;
    private double result;
    private String pendingOperation;

    /**
     * Constructor for the CalculatorController class.
     *
     * @param view  The view component of the calculator.
     * @param model The model component of the calculator.
     */
    public CalculatorController(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;
        this.initController();
    }

    /**
     * Initializes the controller by setting up action listeners for the calculator buttons.
     */
    public void initController() {
        // Add action listeners to number buttons
        for (JButton button : view.getNumberButtons()) {
            button.addActionListener(e -> appendNumber(e.getActionCommand()));
        }

        // Add action listeners to operation buttons
        view.getAddButton().addActionListener(e -> performOperation("+"));
        view.getSubtractButton().addActionListener(e -> performOperation("-"));
        view.getMultiplyButton().addActionListener(e -> performOperation("*"));
        view.getDivideButton().addActionListener(e -> performOperation("/"));
        view.getEqualsButton().addActionListener(e -> computeResult());
        view.getSqrtButton().addActionListener(e -> performSingleOperandOperation("sqrt"));
        view.getSquareButton().addActionListener(e -> performSingleOperandOperation("sq"));

        // Memory operations
        view.getMemoryAddButton().addActionListener(e -> updateMemory(1));
        view.getMemorySubtractButton().addActionListener(e -> updateMemory(-1));
        view.getMemoryRecallButton().addActionListener(e -> recallMemory());
        view.getMemoryClearButton().addActionListener(e -> clearMemory());

        // Other operations
        view.getDeleteButton().addActionListener(e -> deleteLastCharacter());
        view.getClearButton().addActionListener(e -> clearAll());

        // Decimal button
        view.getDecimalButton().addActionListener(e -> appendDecimal());
    }

    /**
     * Appends a decimal point to the display if it's allowed.
     */
    public void appendDecimal() {
        String currentDisplay = view.getDisplay().getText();
        if (!currentDisplay.contains(".") || currentDisplay.endsWith(" ")) {
            view.updateDisplay(currentDisplay + ".");
        }
    }

    /**
     * Appends a number to the display.
     *
     * @param number The number to append.
     */
    public void appendNumber(String number) {
        view.updateDisplay(view.getDisplay().getText() + number);
    }

    /**
     * Performs a binary operation (+, -, *, /).
     *
     * @param operator The operator to perform.
     */
    public void performOperation(String operator) {
        try {
            if (!view.getDisplay().getText().isEmpty()) {
                currentOperand = Double.parseDouble(view.getDisplay().getText());
                pendingOperation = operator;
                view.updateDisplay(""); // Optionally clear display after storing the operand
            }
        } catch (Exception e) {
            System.out.println("Error here");
        }
    }

    /**
     * Computes the result of the pending operation.
     */
    public void computeResult() {
        try {
            double secondOperand = Double.parseDouble(view.getDisplay().getText());
            result = model.calculate(currentOperand, secondOperand, pendingOperation);
            view.updateDisplay(String.valueOf(result));
            currentOperand = result;  // Update the current operand to the result for chaining operations
        } catch (ArithmeticException e) {
            view.updateDisplay("Divide by zero error"); // Display divide by zero error message
            result = 0; // Reset result
            currentOperand = 0; // Reset current operand
            pendingOperation = null; // Clear operation to prevent chaining after error
        }
    }

    /**
     * Performs a single operand operation (sqrt, sq).
     *
     * @param operation The operation to perform.
     */
    public void performSingleOperandOperation(String operation) {
        if (!view.getDisplay().getText().isEmpty()) {
            currentOperand = Double.parseDouble(view.getDisplay().getText());
            double result = switch (operation) {
                case "sqrt" -> Math.sqrt(currentOperand);
                case "sq" -> currentOperand * currentOperand;
                default -> throw new IllegalArgumentException("Unknown operation " + operation);
            };
            view.updateDisplay(String.valueOf(result));
            currentOperand = result;  // Update the current operand with the result
        }
    }

    /**
     * Updates the memory with the result of the last operation.
     *
     * @param multiplier The multiplier for adding or subtracting from memory.
     */
    public void updateMemory(double multiplier) {
        if (result != 0) {
            if (multiplier == 1) {
                model.addToMemory(result); // Add result to memory
            } else {
                model.subtractFromMemory(result); // Subtract result from memory
            }
        } else {
            view.updateDisplay("Error"); // Show error if result is zero
        }
    }

    /**
     * Recalls the value stored in memory and sets it as the current operand.
     */
    public void recallMemory() {
        double memoryValue = model.getMemory();
        view.updateDisplay(String.valueOf(memoryValue));  // Display the memory value
        currentOperand = memoryValue;  // Set the recalled memory as the current operand for new calculations
        System.out.println("memory value: " + memoryValue);
    }

    /**
     * Clears the memory.
     */
    public void clearMemory() {
        model.clearMemory();
    }

    /**
     * Deletes the last character from the display.
     */
    public void deleteLastCharacter() {
        String currentDisplay = view.getDisplay().getText();
        if (!currentDisplay.isEmpty()) {
            view.updateDisplay(currentDisplay.substring(0, currentDisplay.length() - 1));
        }
    }

    /**
     * Clears the display and resets variables.
     */
    public void clearAll() {
        view.updateDisplay("");
        model.clearMemory();
        currentOperand = 0;
        pendingOperation = null;
        result = 0;
    }
}
