package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CalculatorController {
    private CalculatorView view;
    private CalculatorModel model;

    public CalculatorController(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;
        this.initController();
    }

    private void initController() {
        // Add action listeners for all number buttons
        for (JButton button : view.getNumberButtons()) {
            button.addActionListener(e -> appendNumber(e.getActionCommand()));
        }

        // Operation button listeners
        view.getAddButton().addActionListener(e -> performOperation("+"));
        view.getSubtractButton().addActionListener(e -> performOperation("-"));
        view.getMultiplyButton().addActionListener(e -> performOperation("*"));
        view.getDivideButton().addActionListener(e -> performOperation("/"));
        view.getSqrtButton().addActionListener(e -> performSingleOperandOperation("sqrt"));
        view.getSquareButton().addActionListener(e -> performSingleOperandOperation("sq"));

        // Memory buttons
        view.getMemoryAddButton().addActionListener(e -> updateMemory(1));
        view.getMemorySubtractButton().addActionListener(e -> updateMemory(-1));
        view.getMemoryRecallButton().addActionListener(e -> recallMemory());
        view.getMemoryClearButton().addActionListener(e -> clearMemory());

        // Misc
        view.getDeleteButton().addActionListener(e -> deleteLastCharacter());
        view.getClearButton().addActionListener(e -> clearAll());
    }

    private void appendNumber(String number) {
        view.updateDisplay(view.getDisplay().getText() + number);
    }

    private void performOperation(String operator) {
        // Implementation for operation execution
    }

    private void performSingleOperandOperation(String operation) {
        // Implementation for single operand operations like square and square root
    }

    private void updateMemory(double multiplier) {
        // Add or subtract from memory
    }

    private void recallMemory() {
        // Recall the value in memory
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

