package com.company.Database;

import com.company.pcvue.fields.VarexpFactory;
import com.company.pcvue.fields.VarexpVariable;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Stephen on 6/23/2018.
 */
/*
The purpose of this class is to export the entire database from the user when the user gives an export command
 */
public class Export {
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

    private String databaseName;
    private PrintWriter outputFile;

    //Constructor
    public Export(String databaseName) {
        this.databaseName = databaseName;
    }

    public void exportDB(String databaseName) throws Exception {

        dbConnector db = new dbConnector();
        createFile();
        VarexpFactory factory = new VarexpFactory();

        String sqlCmd;


        try {
            sqlCmd = "SELECT * FROM common";
            db.readDatabase(databaseName, sqlCmd);
        } catch (Exception e) {
            System.out.println("Database does not exist");
            e.printStackTrace();
        }

        //Closes file
        closeFile();


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

}
