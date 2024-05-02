package calculator;
import javax.swing.*;
import java.awt.*;

/**
 * The CalculatorView class represents the graphical user interface (GUI) of the calculator application.
 * It extends the JFrame class to create a window for the calculator.
 */
public class CalculatorView extends JFrame {
	private JTextField display = new JTextField();
    private JButton[] numberButtons = new JButton[10];
    private JButton addButton, subtractButton, multiplyButton, divideButton, equalsButton;
    private JButton decimalButton; // Declare the decimal button
    private JButton sqrtButton, squareButton, memoryAddButton, memorySubtractButton, memoryRecallButton, memoryClearButton, deleteButton, clearButton;
    /**
     * Constructor for the CalculatorView class.
     * Sets up the layout and components of the calculator GUI.
     */
    public CalculatorView() {
        // Setup the display
        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        display.setEditable(false);

        // Setup the number buttons
        JPanel numberPanel = new JPanel(new GridLayout(4, 3));
        for (int i = 1; i < numberButtons.length; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberPanel.add(numberButtons[i]);
        }
        
        // Decimal and zero button
        decimalButton = new JButton("."); // Initialize the decimal button
        numberButtons[0] = new JButton("0");
        numberPanel.add(numberButtons[0]);
        numberPanel.add(decimalButton);
        JButton dummyButton = new JButton();
        dummyButton.setEnabled(false); // Placeholder to fill the grid
        numberPanel.add(dummyButton);
        
        // Setup operation buttons
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        equalsButton = new JButton("=");  // Initialize equalsButton
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

    /**
     * Retrieves the decimal button.
     *
     * @return The decimal button.
     */
    public JButton getDecimalButton() {
        return decimalButton;
    }
    /**
     * Retrieves the equals button.
     *
     * @return The equals button.
     */
    public JButton getEqualsButton() {
        return equalsButton;
    }
    /**
     * Retrieves an array containing all number buttons.
     *
     * @return An array of number buttons.
     */
    public JButton[] getNumberButtons() {
        return numberButtons;
    }
    /**
     * Retrieves the addition button.
     *
     * @return The addition button.
     */
    public JButton getAddButton() {
        return addButton;
    }
    /**
     * Retrieves the subtraction button.
     *
     * @return The subtraction button.
     */
    public JButton getSubtractButton() {
        return subtractButton;
    }
    /**
     * Retrieves the multiplication button.
     *
     * @return The multiplication button.
     */
    public JButton getMultiplyButton() {
        return multiplyButton;
    }
    /**
     * Retrieves the division button.
     *
     * @return The division button.
     */
    public JButton getDivideButton() {
        return divideButton;
    }
    /**
     * Retrieves the square root button.
     *
     * @return The square root button.
     */
    public JButton getSqrtButton() {
        return sqrtButton;
    }
    /**
     * Retrieves the square button.
     *
     * @return The square button.
     */
    public JButton getSquareButton() {
        return squareButton;
    }
    /**
     * Retrieves the memory addition button.
     *
     * @return The memory addition button.
     */
    public JButton getMemoryAddButton() {
        return memoryAddButton;
    }
    /**
     * Retrieves the memory subtraction button.
     *
     * @return The memory subtraction button.
     */
    public JButton getMemorySubtractButton() {
        return memorySubtractButton;
    }
    /**
     * Retrieves the memory recall button.
     *
     * @return The memory recall button.
     */
    public JButton getMemoryRecallButton() {
        return memoryRecallButton;
    }
    /**
     * Retrieves the memory clear button.
     *
     * @return The memory clear button.
     */
    public JButton getMemoryClearButton() {
        return memoryClearButton;
    }
    /**
     * Retrieves the delete button.
     *
     * @return The delete button.
     */
    public JButton getDeleteButton() {
        return deleteButton;
    }
    /**
     * Retrieves the clear button.
     *
     * @return The clear button.
     */
    public JButton getClearButton() {
        return clearButton;
    }
    /**
     * Retrieves the display JTextField.
     *
     * @return The display JTextField.
     */
    public JTextField getDisplay() {
        return display;
    }

    /**
     * Updates the display JTextField with the specified value.
     *
     * @param value The value to display.
     */
    public void updateDisplay(String value) {
        display.setText(value);
    }
}

