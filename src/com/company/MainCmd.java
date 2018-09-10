package com.company;

import com.company.Database.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class MainCmd {

    //Function to import the varexp file. Ask a user if they want to overwrite a DB first and then actually import it
    private static void importFile(String fileLocation, String databaseName) throws IOException, SQLException {

        dbConnector dbc = new dbConnector();

        boolean dbExists = dbc.verifyDBExists(databaseName);

        if (dbExists) {

            System.out.println("Database already exists. Overwrite? (Y/N)");
            Scanner sc = new Scanner(System.in);
            String choice = sc.next().toString().toLowerCase();
            if (choice.equals("y")) {
                deleteDB(databaseName);
                createDB(databaseName);
                importHelper(fileLocation, databaseName);
            } else {
                System.out.println("No change will be made. Exiting tool");
            }
        } else {
            importHelper(fileLocation, databaseName);
        }


    }

    //An assistant method to ImportHandler. This Does the actual work of importing the DB Really needs a better name
    private static void importHelper(String fileLocation, String databaseName) throws IOException, SQLException {

        Buffer buffer = new Buffer();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        //This is the consumer. It consumes data in the queue
        executor.execute(new ImportHandler(buffer, databaseName));
        //This will be the producer (see producer-consumer problem if you're not familiar with hte term)
        executor.execute(new Import(fileLocation, databaseName, buffer));
        //Shtudown
        executor.shutdown();
    }

    private static void deleteDB(String databaseName) {
        dbConnector db = new dbConnector();
        db.deleteDB(databaseName);
        db.close();
    }

    private static void createDB(String databaseName) {
        dbConnector db = new dbConnector();
        db.createDB(databaseName);
        db.close();
    }

    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException, SQLException {

        //A map is used to store params
        final Map<String, List<String>> params = new HashMap<>();

        //For logging
        Logger logger = Logger.getLogger(MainCmd.class.getName());


        List<String> options = null;
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

            String databaseName = value.get(1);
            String filePath = value.get(0);
            switch (key.toLowerCase()) {

                //Import
                case "i":
                    if (value.size() != 2) {
                        logger.severe("Erro: Not enough arugments in Import");
                        logger.severe("Params given: " + value);
                        System.err.println("Error: Import Command only accepts two arguments. Please try again");
                        System.out.println("Usage: -I [file path] [database Name]");

                    } else {
                        deleteDB(databaseName);
                        createDB(databaseName);
                        importFile(filePath, databaseName);
                    }
                    break;
                case "e":
                    if (value.size() != 2) {
                        logger.severe("Erro: Not enough arugments in Export");
                        logger.severe("Params given: " + value);
                        System.err.println("Error: Export Command only accepts two arguments. Please try again");
                        System.out.println("Usage: -E [Output File Path ] [Export DB Name]");
                    } else {

                        exportFile(databaseName, filePath);
                    }
                    break;
                case "h":
                    System.out.println("Usage: ");
                    System.out.println("Import: -I [Input File path] [database Name]");
                    System.out.println("Export: -E [Output File Path ] [Export DB Name]");
                    System.out.println("Delete: -D [database Name]");
                    break;

                case "d":
                    System.out.println("Deleteing DB");
                    deleteDB(databaseName);
                    break;
                default:
                    System.err.println("Unknown Error");
                    break;
            }
        }

    }


    public static void exportFile(String databaseName, String outputFilePath) {
        //Shtudown
        Buffer buffer = new Buffer();
        Export exp = new Export(databaseName, buffer, outputFilePath);
        exp.exportDB();
    }

}
