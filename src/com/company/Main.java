package com.company;

import com.company.Database.*;

import java.io.*;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void importFile(String[] args) throws IOException, SQLException {
        if (args.length == 2) {
            String fileLocation = args[0];
            String databaseName = args[1];

            deleteDB(databaseName);
            createDB(databaseName);


            Buffer buffer = new Buffer();
            ExecutorService executor = Executors.newFixedThreadPool(2);

            //This is the consumer. It consumes data in the queue
            executor.execute(new ImportHandler(buffer, databaseName));
            //This will be the producer (see producer-consumer problem if you're not familiar with hte term)
            executor.execute(new Import(args, buffer));

            //Shtudown
            executor.shutdown();
        } else {
            System.out.println("Not enough parameters. Either the file path or the databaseName is missing");
        }
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

    public static void exportFile(String[] args) throws SQLException {

        String path = args[0];
        String databaseName = args[1];
        //Export
        dbConnector db = new dbConnector();
        Buffer buffer = new Buffer();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //This will be the producer (see producer-consumer problem if you're not familiar with hte term)
        ExportHandler exh = new ExportHandler(null, databaseName);
        exh.addToBatch(databaseName);

        //This is the consumer. It consumes data in the queue
        //executor.execute(new Export(args[1], buffer));

        //Shtudown
        executor.shutdown();
    }

    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException, SQLException {
        //importFile(args);
        exportFile(args);


    }

}
