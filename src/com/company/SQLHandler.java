package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the formatting of sql requests before passing them onto the dbConnector class.
 * <p>
 * This class is responsible for fetching table names,
 * joining tables before performming reads or updates
 * Handling basic CRUD operations
 */
public class SQLHandler {
    //Constructor
    public SQLHandler() {

    }

    public void mapTables() {

    }

    /*
    This function sets up insert command parameters before hadning it off to the dbConnector for the actually
    insert
     */
    public void writeDB(ArrayList<List<String>> fileList, String databaseName, String tableName) throws SQLException {
        dbConnector db = new dbConnector();
        Connection connection = db.openConnection(databaseName);
        Statement statement = db.getStatement(connection);

        //use a loop to iterate through arraylist and stuff them into a batch statement
        for (List<String> list : fileList) {

            //You need to insert other queries here as well
            //Recall the first 0 after VALUES is suppose to be the auto increment id for the common table
            String query;
            if (tableName.toLowerCase().equals("common")) {
                query = "INSERT INTO " + tableName + " VALUES (";
            } else {
                query = "INSERT INTO " + tableName + " VALUES (";
            }

            for (String item : list) {
                query += "'" + item + "',";
            }
            String finalQuery = query.substring(0, query.length() - 1) + ")";
            //System.out.println(finalQuery);
            try {
                statement.addBatch(finalQuery);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        try {
            //then execute said batch statement
            statement.executeBatch();
        } catch (Exception e) {
            e.getMessage();
        }

        //Close the DB Connection
        db.close(connection);
    }

    /*
    This function reads from the DB
     */
    public void read() {
    }
}
