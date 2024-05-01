package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CalculatorController {
    private CalculatorView view;
    private CalculatorModel model;
    private double currentOperand;
    private double result;
    private String pendingOperation;

    public CalculatorController(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;
        this.initController();
    }

    public void initController() {
        for (JButton button : view.getNumberButtons()) {
            button.addActionListener(e -> appendNumber(e.getActionCommand()));
        }

        view.getAddButton().addActionListener(e -> performOperation("+"));
        view.getSubtractButton().addActionListener(e -> performOperation("-"));
        view.getMultiplyButton().addActionListener(e -> performOperation("*"));
        view.getDivideButton().addActionListener(e -> performOperation("/"));
        view.getEqualsButton().addActionListener(e -> computeResult()); // Add this line for "=" button
        view.getSqrtButton().addActionListener(e -> performSingleOperandOperation("sqrt"));
        view.getSquareButton().addActionListener(e -> performSingleOperandOperation("sq"));

//        view.getMemoryAddButton().addActionListener(e -> updateMemory(1));
        view.getMemoryAddButton().addActionListener(e -> updateMemory(1)); // Add result to memory

        view.getMemorySubtractButton().addActionListener(e -> updateMemory(-1));
        view.getMemoryRecallButton().addActionListener(e -> recallMemory());
        view.getMemoryClearButton().addActionListener(e -> clearMemory());

        view.getDeleteButton().addActionListener(e -> deleteLastCharacter());
        view.getClearButton().addActionListener(e -> clearAll());
        
        view.getDecimalButton().addActionListener(e -> appendDecimal());
    }
    
    
      
    
    private void appendDecimal() {
        String currentDisplay = view.getDisplay().getText();
        if (!currentDisplay.contains(".") || currentDisplay.endsWith(" ")) {
            view.updateDisplay(currentDisplay + ".");
        }
    }

    private void appendNumber(String number) {
        view.updateDisplay(view.getDisplay().getText() + number);
    }

    private void performOperation(String operator) {
    	try {
            if (!view.getDisplay().getText().isEmpty()) {
                currentOperand = Double.parseDouble(view.getDisplay().getText());
                pendingOperation = operator;
                view.updateDisplay(""); // Optionally clear display after storing the operand
            }            
    	}
    	catch (Exception e){
    		System.out.println("Error here");
    	}

    }

    private void computeResult() {
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

    private void performSingleOperandOperation(String operation) {
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

    private void updateMemory(double multiplier) {
        if (result != 0) {  // Only check result, since it's the output of an operation
            if (multiplier == 1) {
                model.addToMemory(result); // Add result to memory
            } else {
                model.subtractFromMemory(result); // Subtract result from memory
            }
//            view.updateDisplay(String.valueOf(model.getMemory())); // Optionally display memory
        } else {
            view.updateDisplay("Error"); // Show error on the calculator display if result is zero
        }
    }


    private void recallMemory() {
        double memoryValue = model.getMemory();
        view.updateDisplay(String.valueOf(memoryValue));  // Display the memory value
        currentOperand = memoryValue;  // Set the recalled memory as the current operand for new calculations
        System.out.println("memory value: " + memoryValue);
    }


    private void clearMemory() {
    	System.out.println("HERE1");
        model.clearMemory();
    }

    private void deleteLastCharacter() {
        String currentDisplay = view.getDisplay().getText();
        if (!currentDisplay.isEmpty()) {
            view.updateDisplay(currentDisplay.substring(0, currentDisplay.length() - 1));
        }
    }

    private void clearAll() {
        view.updateDisplay("");
        model.clearMemory(); //removing this line so that memory persists
        currentOperand = 0;
        pendingOperation = null;
        result = 0; // bug was found here, result was not set to 0 
    }
}
