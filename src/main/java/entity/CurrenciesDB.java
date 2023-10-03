package entity;

public class CurrenciesDB {

    private String abbreviation, currencyName;
    private double conversionRate;

    public CurrenciesDB(String abbreviation, String currencyName, double conversionRate) {
        super();
        this.abbreviation = abbreviation;
        this.currencyName = currencyName;
        this.conversionRate = conversionRate;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }
}
