
/*
    Assignment: A database for the Currency Converter application
    In submodule 6.2., you designed an application for the currency converter that had a graphical user interface. In this assignment, the goal is to design a database for the application. Later, we will make the application use the database.

    The Model of the application contains the Currency class, which stores for each currency the following data: the abbreviation, name, and conversion rate to a fixed currency (e.g., USD). In addition, you need to store the list of currencies somewhere, either in a Model class or in the Controller class.

    Now, design a database that stores the data of the Currency objects. Make a database script that contains the following things:

    A statement for dropping the previous version of the database, if it exists.
    A statement for creating the database.
    A statement for creating a table for storing the Currency objects.
    Statements for populating the table with data. You should include at least eight currencies with up-to-date exchange rates in the table.
    A statement for dropping the user account appuser, if it exists.
    A statement for creating the user account appuser.
    Statements for granting the privileges to the user account appuser. Think of your application: what privileges does it need? The user account should have only the privileges it needs, and no more.
    Save the database script and run it to create the MariaDB database. Verify that it works, even if you run it more than once.

    Once the database is established, write the following SQL queries to test it:

    A query that retrieves all the currencies from the database.
    A query that retrieves the currency with the abbreviation EUR (or other abbreviation, if you don't have EUR in your database).
    A query that retrieves the number of currencies in the database.
    A query that retrieves the currency with the highest exchange rate.
    Do not include the queries in the database script. Instead, write them in a separate file, e.g., queries.sql. You can run the queries either from HeidiSQL or from the command line with the command mysql -u root -p < queries.sql.
*/

-- Statement to drop the previous version of the database, if it exists
DROP DATABASE IF EXISTS currency_converter;

-- Statement to create the database
CREATE DATABASE currency_converter;

-- Use the newly created database
USE currency_converter;

-- Statement to create a table for storing Currency objects
CREATE TABLE currencies (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            abbreviation VARCHAR(3) NOT NULL,
                            name VARCHAR(255) NOT NULL,
                            conversion_rate DECIMAL(10, 6) NOT NULL
);

-- Statements for populating the table with data (example data)
INSERT INTO currencies (abbreviation, name, conversion_rate) VALUES
                                                                 ('USD', 'United States Dollar', 1.000000),
                                                                 ('EUR', 'Euro', 0.942540),
                                                                 ('GBP', 'British Pound Sterling', 0.818047),
                                                                 ('JPY', 'Japanese Yen', 108.450000),
                                                                 -- More currencies for at least eight currencies
                                                                 ('AUD', 'Australian Dollar', 1.403230),
                                                                 ('CAD', 'Canadian Dollar', 1.362080),
                                                                 ('CHF', 'Swiss Franc', 0.974388),
                                                                 ('CNY', 'Chinese Yuan', 6.524000);

-- Statement to drop the user account appuser, if it exists
DROP USER IF EXISTS 'appuser'@'localhost';

-- Statement to create the user account appuser
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'password'; -- Replace 'password' with a strong password

-- Statements for granting the privileges to the user account appuser
GRANT SELECT, INSERT, UPDATE, DELETE ON currency_converter.* TO 'appuser'@'localhost';
