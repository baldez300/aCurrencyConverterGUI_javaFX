package model;

import java.util.ArrayList;
import java.util.List;

public class CurrencyModel {
    private final List<Currency> currencies;

    public CurrencyModel() {
        currencies = new ArrayList<>();

        // Hardcode some currencies (temporary)
        currencies.add(new Currency("USD", "United States Dollar", 1.0));
        currencies.add(new Currency("EUR", "Euro", 0.94));
        currencies.add(new Currency("GBP", "British Pound Sterling", 0.8166));
        currencies.add(new Currency("BTC", "Bitcoin ₿", 0.000038));
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }
}
