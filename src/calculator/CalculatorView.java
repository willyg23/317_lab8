package calculator;
import javax.swing.*;
import java.awt.*;

public class CalculatorView extends JFrame {
    private JTextField display = new JTextField();
    private JButton[] numberButtons = new JButton[10];
    private JButton addButton, subtractButton, multiplyButton, divideButton, equalsButton;
    private JButton decimalButton; // Decimal button declaration
    private JButton sqrtButton, squareButton, memoryAddButton, memorySubtractButton, memoryRecallButton, memoryClearButton, deleteButton, clearButton;

    public CalculatorView() {
        // Setup the display
        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        display.setEditable(false);

        // Setup the number buttons
        JPanel numberPanel = new JPanel(new GridLayout(4, 3));
        for (int i = 0; i < 9; i++) { // 1-9 buttons
            numberButtons[i] = new JButton(String.valueOf(i + 1));
            numberPanel.add(numberButtons[i]);
        }

        // Additional buttons
        decimalButton = new JButton("."); // Initialize decimal button
        numberButtons[0] = new JButton("0"); // 0 button initialization
        JButton dummyButton = new JButton(); // Dummy button to fill the grid
        dummyButton.setEnabled(false);

        // Adding buttons to the panel
        numberPanel.add(decimalButton);
        numberPanel.add(numberButtons[0]);
        numberPanel.add(dummyButton);

        // Operation buttons
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        equalsButton = new JButton("=");
        sqrtButton = new JButton("√");
        squareButton = new JButton("sq");
        memoryAddButton = new JButton("M+");
        memorySubtractButton = new JButton("M-");
        memoryRecallButton = new JButton("MR");
        memoryClearButton = new JButton("MC");
        deleteButton = new JButton("Del");
        clearButton = new JButton("C");

        // Layout for operation buttons
        JPanel operationPanel = new JPanel(new GridLayout(6, 2));
        operationPanel.add(addButton);
        operationPanel.add(subtractButton);
        operationPanel.add(multiplyButton);
        operationPanel.add(divideButton);
        operationPanel.add(equalsButton);
        operationPanel.add(sqrtButton);
        operationPanel.add(squareButton);
        operationPanel.add(memoryAddButton);
        operationPanel.add(memorySubtractButton);
        operationPanel.add(memoryRecallButton);
        operationPanel.add(memoryClearButton);
        operationPanel.add(deleteButton);
        operationPanel.add(clearButton);

        // Add panels to frame
        add(numberPanel, BorderLayout.CENTER);
        add(operationPanel, BorderLayout.EAST);

        // Finalize the frame
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    
    public JButton getDecimalButton() {
        return decimalButton;
    }
    
    
    public JButton getEqualsButton() {
        return equalsButton;
    }

    public JButton[] getNumberButtons() {
        return numberButtons;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getSubtractButton() {
        return subtractButton;
    }

    public JButton getMultiplyButton() {
        return multiplyButton;
    }

    public JButton getDivideButton() {
        return divideButton;
    }

    public JButton getSqrtButton() {
        return sqrtButton;
    }

    public JButton getSquareButton() {
        return squareButton;
    }

    public JButton getMemoryAddButton() {
        return memoryAddButton;
    }

    public JButton getMemorySubtractButton() {
        return memorySubtractButton;
    }

    public JButton getMemoryRecallButton() {
        return memoryRecallButton;
    }

    public JButton getMemoryClearButton() {
        return memoryClearButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JTextField getDisplay() {
        return display;
    }

    // Update display method
    public void updateDisplay(String value) {
        display.setText(value);
    }
}

