package calculator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CalculatorController {
    private CalculatorView view;
    private CalculatorModel model;
    private double currentOperand;
    private double result;
    private String pendingOperation;
    private JButton lastOperatorUsed; // This will hold the last operator button pressed


    public CalculatorController(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;
        this.initController();
    }

    private void initController() {
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

        view.getMemoryAddButton().addActionListener(e -> updateMemory(1));
        view.getMemorySubtractButton().addActionListener(e -> updateMemory(-1));
        view.getMemoryRecallButton().addActionListener(e -> recallMemory());
        view.getMemoryClearButton().addActionListener(e -> clearMemory());

        view.getDeleteButton().addActionListener(e -> deleteLastCharacter());
        view.getClearButton().addActionListener(e -> clearAll());
        
        view.getEqualsButton().addActionListener(e -> {
            computeResult();
            if (lastOperatorUsed != null) {
                updateButtonFont(lastOperatorUsed, false);  // Reset font
                lastOperatorUsed = null;
            }
            resetOperatorButton();
        });

        view.getClearButton().addActionListener(e -> {
            clearAll();
            if (lastOperatorUsed != null) {
                updateButtonFont(lastOperatorUsed, false);  // Reset font
                lastOperatorUsed = null;
            }
            resetOperatorButton();
        });

        
    }

    private void resetOperatorButton() {
        if (lastOperatorUsed != null) {
            updateButtonFont(lastOperatorUsed, false);  // Reset font
            lastOperatorUsed = null;
        }
    }
    
    private void updateButtonFont(JButton button, boolean isSelected) {
        if (isSelected) {
            button.setFont(new Font("Dialog", Font.BOLD, 16));  // Set font to bold and slightly larger
        } else {
            button.setFont(new Font("Dialog", Font.PLAIN, 12));  // Reset to default plain style
        }
    }
    
    private void setupOperatorButton(JButton button, String operator) {
        button.addActionListener(e -> {
            performOperation(operator);
            if (lastOperatorUsed != null && lastOperatorUsed != button) {
                updateButtonFont(lastOperatorUsed, false);  // Reset the last operator button font
            }
            updateButtonFont(button, true);  // Change font to indicate selection
            lastOperatorUsed = button;
        });
    }

    
    private void appendNumber(String number) {
        view.updateDisplay(view.getDisplay().getText() + number);
    }

    private void performOperation(String operator) {
        if (!view.getDisplay().getText().isEmpty()) {
            currentOperand = Double.parseDouble(view.getDisplay().getText());
            pendingOperation = operator;
            view.updateDisplay(""); // Optionally clear display after storing the operand
        }
    }

    private void computeResult() {
        if (pendingOperation != null && !view.getDisplay().getText().isEmpty()) {
            double secondOperand = Double.parseDouble(view.getDisplay().getText());
            result = model.calculate(currentOperand, secondOperand, pendingOperation);
            view.updateDisplay(String.valueOf(result));
            currentOperand = result;  // Update the current operand to the result for chaining operations
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
        if (!view.getDisplay().getText().isEmpty()) {
            double currentResult = Double.parseDouble(view.getDisplay().getText());
            if (multiplier == 1) {
                model.addToMemory(currentResult);
            } else {
                model.subtractFromMemory(currentResult);
            }
        }
    }

    private void recallMemory() {
        double memoryValue = model.getMemory();
        view.updateDisplay(String.valueOf(memoryValue));
        currentOperand = memoryValue; // Optionally set the recalled value as the current operand
    }

    private void clearMemory() {
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
        model.clearMemory();
        currentOperand = 0;
        pendingOperation = null;
        if (lastOperatorUsed != null) {
            updateButtonFont(lastOperatorUsed, false);  // Reset font
            lastOperatorUsed = null;
        }
        resetOperatorButton();  // Reset operator button font
    }

}
