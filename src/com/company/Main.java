package com.company;

import com.company.Database.*;
import com.company.pcvue.fields.VarexpFactory;
import com.company.pcvue.fields.VarexpVariable;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void importFile(String fileLocation, String databaseName) throws IOException, SQLException {
            deleteDB(databaseName);
            createDB(databaseName);


            Buffer buffer = new Buffer();
            ExecutorService executor = Executors.newFixedThreadPool(2);

            //This is the consumer. It consumes data in the queue
            executor.execute(new ImportHandler(buffer, databaseName));
            //This will be the producer (see producer-consumer problem if you're not familiar with hte term)
        executor.execute(new Import(fileLocation, databaseName, buffer));

            //Shtudown
            executor.shutdown();
    }

    public static void deleteDB(String databaseName) {
        dbConnector db = new dbConnector();
        db.deleteDB(databaseName);
        db.close();
    }

    public static void createDB(String databaseName) {
        dbConnector db = new dbConnector();
        db.createDB(databaseName);
        db.close();
    }

    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException, SQLException {

        String filePath = args[0];
        String dbName = args[1];

        //importFile(filePath,dbName);
        Buffer buffer = new Buffer();
        Export exp = new Export(dbName, buffer);
        exp.exportDB();

    }

    //Removes '[', ']' and removes all empty spaces after a single comma
    private static String removeUnwantedCharacters(String varexpVariable) {
        return varexpVariable.replace("[", "").replace("]", "").replaceAll(",\\ *", ",");
    }

    private void exportFile(String databaseName) {

        //Buffer buffer = new Buffer();
        //ExecutorService executor = Executors.newFixedThreadPool(2);

        //This is the consumer. It consumes data in the queue
        //executor.execute(new ImportHandler(buffer, databaseName));
        //This will be the producer (see producer-consumer problem if you're not familiar with hte term)
        //executor.execute(new Import(args, buffer));

        //Shtudown
    }

}
