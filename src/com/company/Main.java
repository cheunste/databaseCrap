package com.company;

import com.company.pcvue.fields.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException, SQLException {

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


                //initiate the reference
                //Recall this only sets up the table, but you need to handle the variable elsewhere
                Common common_field = new Common();

                /*
                For each line in the file do the following:
                1) Separate the line from String to fields (List or Arraylist)
                2) Add the newly converted list to another list. You will have a 2D List of lists<String>
                3) For each list in the list, get the variable parameter
                4) With the parameter in 3), map it to the correct database and write into the DB
                 */

                ArrayList<List<String>> fileList = new ArrayList();
                while ((line = fileBR.readLine()) != null) {
                    String appendedString = line;
                    //System.out.println(appendedString);

                    //Common common_field = new Common(appendedString);
                    //1) This method should split the line from string to fields and store them in a list
                    common_field.setArrayList(appendedString);
                    //2) Add the newly converted list to another list. Will need a get method
                    fileList.add(common_field.getCommonList());
                    temp++;
                }

                System.out.println("rows handled: " + temp);

                //3) This is a simple case, let's assume this is for common

                //4) Doing the write to DB stuff
                //Open up a DB connection
                common_field.writeDB(fileList, "twin_buttes_2", "common");

                //close file
                fh.closeFile(fileBR);


            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Not enough arguments");
        }


    }


}
