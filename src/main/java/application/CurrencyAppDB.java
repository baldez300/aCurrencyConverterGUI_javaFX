package application;

import datasource.MariaDbConnectionDB;
import entity.*;
import dao.*;
import view.CurrencyConverterViewDB;

import java.util.*;

public class CurrencyAppDB {
    public static void main(String[] args) {

        // Create an instance of CurrencyDaoDB to work with the database
        CurrencyDaoDB currencyDao = new CurrencyDaoDB();

        // Retrieve and print all currencies from the database
        List<CurrenciesDB> currencies = currencyDao.getAllCurrencies();
        for (CurrenciesDB currency : currencies) {
            System.out.println(currency.getAbbreviation() + " " + currency.getCurrencyName());
        }

        // Retrieve and print a specific currency from the database
        CurrenciesDB specificCurrency = currencyDao.getCurrency(2);
        System.out.println(specificCurrency.getAbbreviation() + " " + specificCurrency.getCurrencyName());

        // Insert a new currency into the database
        currencyDao.persist(new CurrenciesDB("BTC", "Bitcoin ₿", 0.000038));

        // Launch the JavaFX application
        CurrencyConverterViewDB.launch(CurrencyConverterViewDB.class, args);

        // Terminate the database connection
        MariaDbConnectionDB.terminate();
    }
}
