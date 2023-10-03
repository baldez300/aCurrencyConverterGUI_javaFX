package model;

/*
    Assignment: A database-enhanced currency converter
    In the previous assignment, you created a database for storing information about currencies. Now, you will expand your earlier currency converter application so that it uses the database.

    First, redesign your project structure so that you have separate packages for:

    the user interface (the view of the application)
    the application logic (the controller of the application)
    the data access layer (the DAO classes)
    the entity classes (the classes that represent the data in the database. These are probably one-to-one to your model classes.)
    the datasource class (the class that handles the connection to the database)
    You may modify the existing project, or create a new one. If you work on the existing project, consider creating a new branch for the new version.

    Make your application connect to the database. For inspiration, check the MariaDbConnection class in the example above.

    In the earlier application, your controller class is responsible for reacting to the button presses that initiate the conversion. Now, add a CurrencyDao class into the dao package in your project. The class is responsible for communicating with the database. Add a method for retrieving the exchange rate of a currency from the database, and embed the required SQL statement into the method. The method should take the abbreviation of the currency as a parameter, and return the exchange rate as a double value. Modify your controller method so that it uses the values fetched from the database instead of the hard-coded values. (By the way, now is a good time to delete the hard-coded currencies from your code, if you have them.)

    Prepare for errors. For instance, if the database is not available, your application should not crash. Instead, it should display an error message to the user. While testing, an easy way to simulate an error is to change the server name in the MariaDbConnection class to something that is not a valid server name.

    At this point, your application should work as before, but the exchange rates are fetched from the database.

    For this assignment, you get points as follows:

    You have refactored your code to reflect the new project structure. (1 point)
    Your application successfully connects to the database. (1 point)
    You have created a CurrencyDao class that contains a method for fetching the exchange rate of a currency from the database. (1 point)
    Your Controller class uses the aforementioned method to successfully fetch the exchange rate. (1 point)
    Your application works as before (meaning: as described in the earlier GUI assignment), except for the fact that the exchange rates are fetched from the database. (1 point)
    Your application displays an appropriate error message in the user interface if the database is not available. (1 point)
*/

import java.util.List;
import dao.CurrencyDaoDB;
import entity.CurrenciesDB;

public class CurrencyModelDB {
    private final CurrencyDaoDB currencyDao;

    public CurrencyModelDB() {
        currencyDao = new CurrencyDaoDB();
    }

    public List<CurrenciesDB> getCurrencies() {
        return currencyDao.getAllCurrencies();
    }
}
