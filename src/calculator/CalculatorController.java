package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CalculatorController {
    private CalculatorView view;
    private CalculatorModel model;
    private double currentOperand;
    private String pendingOperation;

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
        view.getSqrtButton().addActionListener(e -> performSingleOperandOperation("sqrt"));
        view.getSquareButton().addActionListener(e -> performSingleOperandOperation("sq"));

        view.getMemoryAddButton().addActionListener(e -> updateMemory(1));
        view.getMemorySubtractButton().addActionListener(e -> updateMemory(-1));
        view.getMemoryRecallButton().addActionListener(e -> recallMemory());
        view.getMemoryClearButton().addActionListener(e -> clearMemory());

        view.getDeleteButton().addActionListener(e -> deleteLastCharacter());
        view.getClearButton().addActionListener(e -> clearAll());
    }

    private void appendNumber(String number) {
        view.updateDisplay(view.getDisplay().getText() + number);
    }

    private void performOperation(String operator) {
        currentOperand = Double.parseDouble(view.getDisplay().getText());
        pendingOperation = operator;
        view.updateDisplay("");
    }

    private void performSingleOperandOperation(String operation) {
        currentOperand = Double.parseDouble(view.getDisplay().getText());
        double result = switch (operation) {
            case "sqrt" -> Math.sqrt(currentOperand);
            case "sq" -> currentOperand * currentOperand;
            default -> throw new IllegalArgumentException("Unknown operation " + operation);
        };
        view.updateDisplay(String.valueOf(result));
        currentOperand = result;  // Update the current operand with the result
    }

    private void updateMemory(double multiplier) {
        double currentResult = Double.parseDouble(view.getDisplay().getText());
        if (multiplier == 1) {
            model.addToMemory(currentResult);
        } else {
            model.subtractFromMemory(currentResult);
        }
    }

    private void recallMemory() {
        double memoryValue = model.getMemory();
        view.updateDisplay(String.valueOf(memoryValue));
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
    }
}
