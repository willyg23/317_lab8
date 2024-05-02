package calculator;

/**
 * Main application class for the Calculator.
 * This class serves as the entry point for the calculator application,
 * setting up the MVC (Model-View-Controller) components and initializing the application.
 */

//36,+,=
public class CalculatorApp {

    /**
     * Main method to launch the calculator application.
     * @param args Command line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        // Create an instance of CalculatorModel, which holds the logic and state of the calculator.
        CalculatorModel model = new CalculatorModel();
        
        // Create an instance of CalculatorView, which is responsible for displaying the user interface.
        CalculatorView view = new CalculatorView();
        
        // Create an instance of CalculatorController, which acts as the intermediary between the view and the model.
        // The controller processes user input from the view, updates the model, and updates the view to reflect changes.
        CalculatorController controller = new CalculatorController(view, model);
        
        // Note: The controller now has references to both the model and the view, enabling it to mediate interactions between them.
        // The application is now set up and ready to run. The view is typically made visible within its constructor or through a separate method call that could be added here.
    }
}
