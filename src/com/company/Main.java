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
                ACM acm = new ACM();

                /*
                For each line in the file do the following:
                1) Separate the line from String to fields (List or Arraylist)
                2) Add the newly converted list to another list. You will have a 2D List of lists<String>
                3) For each list in the list, get the variable parameter
                4) With the parameter in 3), map it to the correct database and write into the DB
                 */

                //Create a fileLIst arrayList to hold all varexp variables
                ArrayList<List<String>> fileList = new ArrayList();
                while ((line = fileBR.readLine()) != null) {
                    String appendedString = line;
                    //System.out.println(appendedString);

                    //Common common_field = new Common(appendedString);
                    //1) This method should split the line from string to fields and store them in a list
                    common_field.setArrayList(appendedString, temp);

                    //2) Add the newly converted list to another list. Will need a get method
                    fileList.add(common_field.getCommonList());
                    //For other non-common variables

                    temp++;
                }

                //Consider handling the other varexp elements here
                //TODO: Research to see if you can handle other varexp elements here and then
                // stuff them into their respective tables

                temp = 0;
                for (List<String> subList : fileList) {
                    System.out.println(subList);
                    switch (subList.get(1).toUpperCase()) {
                        case "ACM":
                            System.out.println("Do acm Stuff");
                            acm.setArrayList(subList.toString(), temp);
                            break;
                        case "ALA":
                            System.out.println("Do ala Stuff");
                            break;
                        case "ATS":
                            System.out.println("Do ats Stuff");
                            break;
                        case "BIT":
                            System.out.println("Do bit Stuff");
                            break;
                        case "CHR":
                            System.out.println("Do ctr Stuff");
                            break;
                        case "CMD":
                            System.out.println("Do cmd Stuff");
                            break;
                        case "CTV":
                            System.out.println("Do ctv Stuff");
                            break;
                        case "CXT":
                            System.out.println("Do cxt Stuff");
                            break;
                        case "REG":
                            System.out.println("Do reg Stuff");
                            break;
                        case "TXT":
                            System.out.println("Do txt Stuff");
                            break;
                    }
                    temp++;
                }

                System.out.println("rows handled: " + temp);
                //acm.getArrayList();

                //3) This is a simple case, let's assume this is for common

                //4) Doing the write to DB stuff
                //Open up a DB connection

                common_field.writeDB(fileList, "twin_buttes_2", common_field.getTableName());
                System.out.println("Done with wrting common to DB");
                acm.writeDB(acm.getArrayList(), "twin_buttes_2", acm.getTableName());

                //close file
                fh.closeFile(fileBR);


            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Not enough arguments");
        }


    }

}
