package dao;

import entity.CurrenciesDB;
import java.sql.*;
import datasource.MariaDbConnectionDB;
import java.util.*;

public class CurrencyDaoDB {

    public List<CurrenciesDB> getAllCurrencies() {
        Connection conn = MariaDbConnectionDB.getInstance();
        String sql = "SELECT abbreviation, name, conversion_rate FROM currencies";
        List<CurrenciesDB> currencies = new ArrayList<CurrenciesDB>();

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                String abbreviation = rs.getString(1);
                String name = rs.getString(2);
                double conversionRate = rs.getDouble(3);
                CurrenciesDB emp = new CurrenciesDB(abbreviation, name, conversionRate);
                currencies.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currencies;
    }


    public CurrenciesDB getCurrency(int id) {
        Connection conn = MariaDbConnectionDB.getInstance();
        String sql = "SELECT abbreviation, name, conversion_rate FROM currencies WHERE id=?";

        String abbreviation = null;
        String name = null;
        double conversionRate = 0.0;
        int count = 0;

        try {
            Statement s = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count++;
                abbreviation = rs.getString(1);
                name = rs.getString(2);
                conversionRate = rs.getDouble(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (count==1) {
            return new CurrenciesDB(abbreviation, name, conversionRate);
        }
        else {
            return null;
        }
    }

    public void persist(CurrenciesDB emp) {
        Connection conn = MariaDbConnectionDB.getInstance();
        String sql = "INSERT INTO currencies (abbreviation, name, conversion_rate) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getAbbreviation());
            ps.setString(2, emp.getCurrencyName());
            ps.setDouble(3, emp.getConversionRate());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
