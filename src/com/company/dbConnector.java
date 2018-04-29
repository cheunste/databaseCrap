package com.company;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


/**
 * Created by Stephen on 4/20/2018.
 * <p>
 * This class is responsible for connecting to mySQL
 */
public class dbConnector {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDatabase() throws Exception {
        try {
            //This will load the MYSQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/twin_buttes_2?"
                            + "user=root&password=Gundam7seed");

            // Statements allow to isuse SQl qwuesries to the DB
            statement = connect.createStatement();

            //Result set the result of  the     SQL query
            resultSet = statement.executeQuery("select * from common limit 1000");
            writeResultSet(resultSet);
            System.out.println("Done with 'reading'");
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    public void writeDatabase(String insertToDBCmd) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/twin_buttes_2?"
                            + "user=root&password=Gundam7seed");
            preparedStatement = connect.prepareStatement(insertToDBCmd);
        } catch (Exception e) {

        }

    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnNum = rsmd.getColumnCount();
        int temp = 0;
        while (resultSet.next()) {
            for (int i = 1; i <= columnNum; i++) {
                //   System.out.print(columnValue+ " "+rsmd.getColumnName(i));
            }
            //System.out.println("");
            temp++;
        }

    }

    public Connection openConnection(String site) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/twin_buttes_2?"
                            + "user=root&password=Gundam7seed");
        } catch (Exception e) {

        }
        return this.connect;
    }

    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

    public void close(Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connection.close();
            }
        } catch (Exception e) {

        }
    }
}
