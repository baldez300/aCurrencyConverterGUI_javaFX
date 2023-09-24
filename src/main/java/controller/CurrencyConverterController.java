package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Currency;
import model.CurrencyModel;
import java.util.List;

public class CurrencyConverterController {
    private final CurrencyModel currencyModel;

    public CurrencyConverterController() {
        currencyModel = new CurrencyModel();
    }

    // Return an observable list of currencies
    public ObservableList<Currency> getCurrencies() {
        List<Currency> currencyList = currencyModel.getCurrencies();
        return FXCollections.observableArrayList(currencyList);
    }

    // Convert the given amount from the source currency to the target currency
    public double convertCurrency(double amount, Currency sourceCurrency, Currency targetCurrency) {
        if (sourceCurrency == null || targetCurrency == null) {
            return 0.0; // Handle invalid input gracefully
        }

        // Get the conversion rates
        double sourceRate = sourceCurrency.getConversionRate();
        double targetRate = targetCurrency.getConversionRate();

        // Calculate the converted amount
        return (amount / sourceRate) * targetRate;
    }
}
