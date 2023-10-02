/*
    Once the database is established, write the following SQL queries to test it:

    A query that retrieves all the currencies from the database.
    A query that retrieves the currency with the abbreviation EUR (or other abbreviation, if you don't have EUR in your database).
    A query that retrieves the number of currencies in the database.
    A query that retrieves the currency with the highest exchange rate.
    Do not include the queries in the database script. Instead, write them in a separate file, e.g., queries.sql. You can run the queries either from HeidiSQL or from the command line with the command mysql -u root -p < queries.sql.
*/


-- Query 1: Retrieve all currencies from the database
SELECT * FROM currencies;

-- Query 2: Retrieve the currency with the abbreviation 'EUR'
SELECT * FROM currencies WHERE abbreviation = 'EUR';

-- Query 3: Retrieve the number of currencies in the database
SELECT COUNT(*) AS currency_count FROM currencies;

-- Query 4: Retrieve the currency with the highest conversion rate
SELECT * FROM currencies ORDER BY conversion_rate DESC LIMIT 1;
