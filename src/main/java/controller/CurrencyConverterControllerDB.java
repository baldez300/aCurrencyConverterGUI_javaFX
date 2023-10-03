package controller;

import entity.CurrenciesDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Currency;
import model.CurrencyModelDB; // Updated model

import java.util.List;

public class CurrencyConverterControllerDB {
    private final CurrencyModelDB currencyModelDB; // Updated model

    public CurrencyConverterControllerDB() {
        currencyModelDB = new CurrencyModelDB(); // Updated model
    }

    public ObservableList<Currency> getCurrencies() {
        List<CurrenciesDB> currencyList = currencyModelDB.getCurrencies(); // Updated model
        ObservableList<Currency> observableCurrencyList = FXCollections.observableArrayList();

        for (CurrenciesDB currencies : currencyList) {
            // Convert Currencies to Currency
            Currency currency = new Currency(
                    currencies.getAbbreviation(),
                    currencies.getCurrencyName(),
                    currencies.getConversionRate()
            );
            observableCurrencyList.add(currency);
        }

        return observableCurrencyList;
    }

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
