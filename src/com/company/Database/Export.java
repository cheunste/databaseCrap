package com.company.Database;

import com.company.pcvue.fields.VarexpFactory;
import com.company.pcvue.fields.VarexpVariable;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Stephen on 6/23/2018.
 */
/*
The purpose of this class is to export the entire database from the user when the user gives an export command
 */
public class Export implements Runnable {
    /*
    General outline
    Take a DB name from the "user"
    Check if DB does exist
    If it does, create a varexp.dat file

    Create a new Varexp Variable object Temp
    select * from common
    remove
    Store the output from the above into a Temp object

    Select * from the Type Table (Common position 0)
    Store the output from the above into Temp

    Select * from the Soure table
    Store the output from the above into Temp

    if alarm
    Store the output from the above into Temp

    write to a csv file
     */

    //TODO: Use the BUffer class to pass on a ConcurrentLinkedQueue to this class and the dbConnector
    private String databaseName;
    private PrintWriter outputFile;
    private ArrayList<ArrayList<String>> outptuVarexpVariableList;

    //Constructor
    public Export(String databaseName, Buffer buffer) {
        this.databaseName = databaseName;
    }

    //Removes '[', ']' and removes all empty spaces after a single comma
    private static String removeUnwantedCharacters(String varexpVariable) {
        return varexpVariable.replace("[", "").replace("]", "").replaceAll(",\\ *", ",");
    }

    public void exportDB(String databaseName) throws Exception {

        //Verify that the DB exists first
        dbConnector dbConnector = new dbConnector();
        boolean exists = dbConnector.verifyDBExists(databaseName);

        if (exists) {
            fetch(databaseName);
        } else {
        }
    }

    //This function performs the actual fetching from MySQL.
    /*
    @param: String databaseName: Name of the database (ie twin_buttes_2)
    @param varexpVariableType: This is the type of varexp variable. acceptable items are things like "COMMON", "CNT", "I","O", etc.
    See the varexp factory on this
     */
    private void fetch(String databaseName) {
        dbConnector db = new dbConnector();
        String sqlCmd = "SELECT * FROM common";
        ArrayList<ArrayList<String>> resultList = db.readDatabase(databaseName, sqlCmd);
        ArrayList<ArrayList<String>> csvListOutput = new ArrayList<ArrayList<String>>();


        VarexpFactory newVariable = new VarexpFactory();
        VarexpVariable common = newVariable.declareNewVariable("COMMON");
        System.out.println("FIELD NUM: " + common.getFieldNum());


        for (ArrayList<String> innerList : resultList) {

            //Remove the id, which is always position 0
            //String id =innerList.remove(0);

            ArrayList<String> outputVarexpVariable = new ArrayList<String>();
            //sets up the new variable positions:
            for (int x = 1; x <= common.getFieldNum(); x++) {
                outputVarexpVariable.add("");
            }

            //Sets the item form the COMMON table to the outputVarexpVariable.
            int y = 1;
            for (int position : common.getVarexpPositionList()) {
                String temp = removeUnwantedCharacters(innerList.get(y).toString());
                outputVarexpVariable.set(position, temp);
                y++;
            }
            csvListOutput.add(outputVarexpVariable);
            //This removes all the unnecessary characters and strings. Probably should be moved to a separate function
            //String temp =removeUnwantedCharacters(outputVarexpVariable.toString());
            //System.out.println(temp);
        }

        for (String table : newVariable.listOfTables) {
            //Do nothing for the common table as it has already been taken care of
            if (table == "COMMON") {

            } else {
                System.out.println(table);
                VarexpVariable tempVar = newVariable.declareNewVariable(table);
                String tableSqlCmd = "SELECT * FROM " + tempVar.getTableName();

                ArrayList<ArrayList<String>> tableList = db.readDatabase(databaseName, tableSqlCmd);

                for (ArrayList<String> innerList : tableList) {
                    //System.out.println(innerList);
                    String id = innerList.remove(0);
                    ArrayList<String> commonTemp = csvListOutput.get(Integer.parseInt(id));
                    //System.out.println(commonTemp);

                    int a = 0;
                    for (int position : tempVar.getVarexpPositionList()) {
                        //System.out.println(commonTemp);
                        commonTemp.set(position, innerList.get(a));
                        a++;
                    }

                }
            }
        }
        for (ArrayList<String> outputList : csvListOutput) {
            System.out.println(removeUnwantedCharacters(outputList.toString()));
        }
    }

    private void createFile() {
        try {
            this.outputFile = new PrintWriter("Varexp.dat", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //This function writes a VarexpVariable line into the varexp file
    private void writeLine(String varexpLine) {
        outputFile.println(varexpLine);
    }

    private String formatVarexp(VarexpVariable varexp) {
        return null;
    }

    //Closes the file. After this call, you're done exporting the DB to file
    private void closeFile() {
        this.outputFile.close();
    }

    @Override
    public void run() {

    }
}
