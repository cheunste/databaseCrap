package com.company;

import com.company.pcvue.fields.VarexpVariable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * This class handles the formatting of sql requests before passing them onto the dbConnector class.
 * <p>
 * This class is responsible for fetching table names,
 * joining tables before performming reads or updates
 * Handling basic CRUD operations
 */
public class SQLHandler implements Runnable {

    //These are member variables that have to do with mysql connections
    dbConnector db;
    Connection connection;
    Statement statement;
    //These are used for Buffer class.
    private Buffer buffer;
    private int BUFFER_LIMIT = 3000;

    public SQLHandler() {

    }

    public SQLHandler(Buffer buffer, String databaseName) throws SQLException {
        this.buffer = buffer;
        this.db = new dbConnector();
        this.connection = db.openConnection(databaseName);
        this.connection.setAutoCommit(false);
        this.statement = db.getStatement(connection);
    }

    /*
    This function sets up insert command parameters before hadning it off to the dbConnector for the actually
    insert
     */
    public void addToBatch(ArrayList<List<String>> fileList, String databaseName, String tableName) throws SQLException {
        /*
        db = new dbConnector();
        connection = db.openConnection(databaseName);
        connection.setAutoCommit(false);
        statement = db.getStatement(connection);
        */

        //You need to insert other queries here as well
        //Recall the first 0 after VALUES is suppose to be the auto increment id for the common table
        String query;
        if (tableName.toLowerCase().equals("common")) {
            query = "INSERT INTO " + tableName + " VALUES ('";
        } else {
            query = "INSERT INTO " + tableName + " VALUES ('";
        }

        for (List<String> item : fileList) {
            for (String temp : item) {

                query += "" + temp + "','";
            }
            //query += "" + item + "','";

        }
        String finalQuery = query.substring(0, query.length() - 2) + ")";
        finalQuery.replace('[', ' ').replace(']', ' ');
        try {
            statement.addBatch(finalQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        try {
            //then execute said batch statement
            statement.executeBatch();
        } catch (Exception e) {
            e.getMessage();
        }

        //Close the DB Connection
        db.close(connection);
        */
    }
    public void writeDB(ArrayList<List<String>> fileList, String databaseName, String tableName) throws SQLException {
        db = new dbConnector();
        connection = db.openConnection(databaseName);
        statement = db.getStatement(connection);


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
                this.statement.addBatch(finalQuery);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        /*
        try {
            //then execute said batch statement
            statement.executeBatch();
        } catch (Exception e) {
            e.getMessage();
        }

        //Close the DB Connection
        db.close(connection);
        */
    }

    /*
    This function reads from the DB
     */
    public void read() {
    }

    private void executeBatch() {
        try {
            //then execute said batch statement
            this.statement.executeBatch();
            this.connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }

    @Override
    public void run() {
        try {
            while (buffer.isDone != true || buffer.getSize() > 0) {
                if (buffer.isReady()) {
                    for (int i = 0; i <= buffer.getQueueLimit(); i++) {
                        VarexpVariable var = buffer.get();

                        //Add to batch
                        //writeDB(var.getArrayList(),"twin_buttes_2",var.getTableName());
                        addToBatch(var.getArrayList(), "twin_buttes_2", var.getTableName());
                    }
                    //Then execute batch
                    executeBatch();
                    //Then close the connection
                }
            }
            db.close(connection);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
