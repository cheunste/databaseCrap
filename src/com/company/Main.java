package com.company;

import com.company.pcvue.fields.*;
import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import java.io.*;
import java.sql.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException {

        try {
            if (args.length == 2) {
                String path = args[0];
                String dbName = args[1];

                System.out.println(path);
                System.out.println(dbName);

                String line;
                fileHandler fh = new fileHandler();
                //open file
                BufferedReader fileBR = fh.readInput(path);
                int temp = 0;


                //Open up a DB connection
                dbConnector db = new dbConnector();
                Connection connection = db.openConnection("twin_buttes_2");

                while ((line = fileBR.readLine()) != null) {
                    //System.out.println(line);
                    //This is where you push the line to the varexp class
                    //String appendedString=fh.appendFiles(line,250);
                    String appendedString = line;
                    //System.out.println(appendedString);

                    common common_field = new common(appendedString);
                    temp++;
                }

                System.out.println("rows handled: " + temp);

                //Close the DB Connection
                db.close(connection);
                //close file
                fh.closeFile(fileBR);


            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Not enough arguments");
        }


    }


}
