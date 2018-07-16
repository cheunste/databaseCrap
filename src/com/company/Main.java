package com.company;

import com.company.Database.*;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void importFile(String fileLocation, String databaseName) throws IOException, SQLException {

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

        //String command = args[0];
        //String filePath = args[1];
        //String dbName = args[2];

        //importFile(filePath,dbName);

        final Map<String, List<String>> params = new HashMap<>();

        List<String> options = null;
        ;
        for (int i = 0; i < args.length; i++) {
            final String a = args[i];

            if (a.charAt(0) == '-') {
                if (a.length() < 2) {
                    System.err.println("Error at argument " + a);
                    return;
                }

                options = new ArrayList<>();
                params.put(a.substring(1), options);
            } else if (options != null) {
                options.add(a);
            } else {
                System.err.println("Illegal parameter usage");
                System.out.println("Usage: [Command] [path to file] [databaseName]");
            }
        }

        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();

            System.out.println(key);
            System.out.println(value);
            switch (key) {

                //Import
                case "I":
                    if (value.size() != 2) {
                        System.err.println("Error: Import Command only accepts two arguments. Please try again");
                        System.out.println("Usage: -I [file path] [database Name]");

                    } else {
                        String databaseName = value.get(1);
                        String filePath = value.get(0);
                        deleteDB(databaseName);
                        createDB(databaseName);
                        importFile(filePath, databaseName);
                    }

                    break;
                case "E":
                    if (value.size() != 2) {
                        System.err.println("Error: Export Command only accepts two arguments. Please try again");
                        System.out.println("Usage: -E [Output File Path ] [Export DB Name]");
                    } else {
                        String databaseName = value.get(1);
                        String filePath = value.get(0);
                        exportFile(databaseName, filePath);
                    }
                    break;
                default:
                    System.err.println("Unknown Error");
                    break;
            }
        }

    }


    public static void exportFile(String databaseName, String outputFilePath) {

        //Buffer buffer = new Buffer();
        //ExecutorService executor = Executors.newFixedThreadPool(2);

        //This is the consumer. It consumes data in the queue
        //executor.execute(new ImportHandler(buffer, databaseName));
        //This will be the producer (see producer-consumer problem if you're not familiar with hte term)
        //executor.execute(new Import(args, buffer));

        //Shtudown
        Buffer buffer = new Buffer();
        Export exp = new Export(databaseName, buffer, outputFilePath);
        exp.exportDB();
    }

}
