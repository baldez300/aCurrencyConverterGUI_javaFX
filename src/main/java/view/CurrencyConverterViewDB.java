package view;

import controller.CurrencyConverterControllerDB;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Currency;

public class CurrencyConverterViewDB extends Application {
    private CurrencyConverterControllerDB controller;

    // JavaFX components variables defined at class level
    private TextField amountField;
    private ChoiceBox<Currency> sourceCurrencyChoice;
    private ChoiceBox<Currency> targetCurrencyChoice;
    private Label resultLabel;

    // Launch the application
    @Override
    public void start(Stage window) {
        // Create the controller
        controller = new CurrencyConverterControllerDB();

        // Initialize JavaFX components
        // Create the amount field with a numeric input filter
        amountField = new TextField();
        amountField.addEventFilter(KeyEvent.KEY_TYPED, numericInputFilter());
        sourceCurrencyChoice = new ChoiceBox<>(controller.getCurrencies());
        targetCurrencyChoice = new ChoiceBox<>(controller.getCurrencies());
        Button convertButton = new Button("Convert");
        resultLabel = new Label();

        // Set up choice box converters for displaying currency names
        sourceCurrencyChoice.setConverter(new CurrencyStringConverter());
        targetCurrencyChoice.setConverter(new CurrencyStringConverter());

        // Create the layout (VBox)
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(10));
        //layout.getStyleClass().add("container"); // Add a container style class
        layout.getChildren().addAll(
                new Label("Amount to convert (data from DB):"),
                amountField,
                new Label("From Currency:"),
                sourceCurrencyChoice,
                new Label("To Currency:"),
                targetCurrencyChoice,
                convertButton,
                new Label("Result:"),
                resultLabel
        );

        // Set up event handling
        convertButton.setOnAction(event -> convertCurrency());

        // Set up the scene and show the stage
        Scene view = new Scene(layout, 500, 450);
        view.getStylesheets().add("style.css"); // Link the CSS file
        window.setTitle("Currency Converter from DB");
        window.setScene(view);
        window.show();
    }

    // Event handler for filtering numeric input
    private EventHandler<KeyEvent> numericInputFilter() {
        return event -> {
            String input = event.getCharacter();
            if (!input.matches("[0-9]*\\.?[0-9]*")) { // Allow digits and the decimal point
                resultLabel.setText("Only digits and a decimal point are allowed.");
                resultLabel.getStyleClass().add("error-message"); // Add the error style class
                event.consume(); // Prevent non-numeric input
            } else {
                // Remove the error style class if input is valid
                resultLabel.getStyleClass().remove("error-message");
                resultLabel.setText(""); // Clear the error message
            }
        };
    }

    // Event handler for converting the currency
    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            Currency sourceCurrency = sourceCurrencyChoice.getValue();
            Currency targetCurrency = targetCurrencyChoice.getValue();

            // Check if source and target currencies are selected
            if (sourceCurrency == null || targetCurrency == null) {
                resultLabel.setText("Select both: From Currency and To Currency");
                resultLabel.getStyleClass().add("error-message"); // Add the error style class
            } else {
                double result = controller.convertCurrency(amount, sourceCurrency, targetCurrency);
                resultLabel.setText(String.format("%.2f %s", result, targetCurrency.getAbbreviation()));

                // Add the success style class
                resultLabel.getStyleClass().add("success-message");
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid amount.");
            resultLabel.getStyleClass().add("error-message"); // Add the error style class
        }
    }

    // Custom converter for ChoiceBox to display currency names
    private static class CurrencyStringConverter extends StringConverter<Currency> {
        @Override
        public String toString(Currency currency) {
            // Return the currency name if it is not null
            return currency != null ? currency.getName() : "";
        }

        @Override
        public Currency fromString(String string) {
            // Not needed for this application
            return null;
        }
    }
}
