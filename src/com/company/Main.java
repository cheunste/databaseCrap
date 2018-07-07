package com.company;

import com.company.Database.*;

import java.io.*;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException, SQLException {

        dbConnector db = new dbConnector();
        db.deleteDB("twin_buttes_2");

        db.createDB("twin_buttes_2");

        Buffer buffer = new Buffer();
        ExecutorService executor = Executors.newFixedThreadPool(2);

         //This is the consumer. It consumes data in the queue
        executor.execute(new ImportHandler(buffer, args[1]));
        //This will be the producer (see producer-consumer problem if you're not familiar with hte term)
        executor.execute(new Import(args, buffer));

        //Shtudown
        executor.shutdown();

        //Export
        /*
        dbConnector db = new dbConnector();
        Buffer buffer = new Buffer();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //This will be the producer (see producer-consumer problem if you're not familiar with hte term)

        //This is the consumer. It consumes data in the queue
        executor.execute(new Export(args[1], buffer));

        //Shtudown
        executor.shutdown();
        */

    }

}
