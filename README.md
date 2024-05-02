# 317_lab8
Calculator Application

Description

The Calculator Application is a simple Java program that provides basic arithmetic calculations along with memory functions. It consists of three main components: the model, the view, and the controller.

Classes

CalculatorModel
Responsible for performing arithmetic calculations and managing memory operations.
Contains methods for addition, subtraction, multiplication, division, square root, and memory operations.
Ensures proper handling of errors such as divide by zero and square root of negative numbers.

CalculatorView
Provides the graphical user interface (GUI) for the calculator application.
Contains buttons for digits, arithmetic operations, memory functions, and other functionalities.
Displays the current input and results on a text field.

CalculatorController
Acts as the intermediary between the model and the view.
Handles user input from the GUI components and invokes corresponding methods in the model.
Updates the view based on the model's state and operations performed.

ChimmyT
Contains JUnit tests for testing the functionality of the calculator application.
Tests various scenarios including arithmetic operations, memory operations, error handling, and boundary cases.
Utilizes reflection to access private fields and test internal states of the controller.

Authors: 
Jonathan Duron NetID: jduron24
Will Griner NetID: willyg
