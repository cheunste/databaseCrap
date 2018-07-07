package com.company;

import com.company.Database.Buffer;
import com.company.Database.Import;
import com.company.Database.ImportHandler;
import com.company.Database.dbConnector;

import java.io.*;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException, SQLException {

        dbConnector db = new dbConnector();
        db.deleteDB("twin_buttes_2");

        db.createDB("twin_buttes_2");

        /*
        TODO: Add the linkedconcurrentqueue to both the Import() and ImportHandler classes
        while you're at it, create a buffer class
         */
        Buffer buffer = new Buffer();
        ExecutorService executor = Executors.newFixedThreadPool(2);

         //This is the consumer. It consumes data in the queue
        executor.execute(new ImportHandler(buffer, args[1]));
        //This will be the producer (see producer-consumer problem if you're not familiar with hte term)
        executor.execute(new Import(args, buffer));

        //Shtudown
        executor.shutdown();
    }

}
