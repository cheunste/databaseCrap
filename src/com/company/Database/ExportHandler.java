package com.company.Database;

import com.company.pcvue.fields.VarexpFactory;
import com.company.pcvue.fields.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.sql.ResultSet;

/**
 * This class handles the formatting of sql requests before passing them onto the dbConnector class.
 * <p>
 * This class is responsible for fetching table names,
 * joining tables before performming reads or updates
 * Handling basic CRUD operations
 */
public class ExportHandler implements Runnable {

    //These are member variables that have to do with mysql connections
    dbConnector db;
    Connection connection;
    Statement statement;

    //These are used for Buffer class.
    private Buffer buffer;

    private String databaseName;
    //This is a 2 because Sql don't starts at one and you need to rebuild the array start form the second element
    private int ID_POSITION = 1;
    private int SQL_START_POSITION = 2;

    public ExportHandler() {

    }

    public ExportHandler(Buffer buffer, String databaseName) throws SQLException {
        //Open connection to the given DB
        this.buffer = buffer;
        this.db = new dbConnector();
        this.connection = db.openConnection(databaseName);
        this.connection.setAutoCommit(false);
        this.statement = db.getStatement(connection);
        this.databaseName = databaseName;
    }

    /*
    This function performs a read command given the DB name, formats the String, performs more reads and then
    stuff them into a buffer for Export to write to file
     */
    public void addToBatch(String databaseName) throws SQLException {

        //Create a temp place to store the read variables
        List<String> fileList = new ArrayList<>();

        //This is used as temp storage
        ArrayList<String> tempList = new ArrayList<>();
        //Get all the data from common;
        String sqlCmd = "SELECT * FROM common";
        try {
            ResultSet commonData = db.readDatabase(databaseName, sqlCmd);
            ResultSetMetaData rsmd = commonData.getMetaData();
            int columnNum = rsmd.getColumnCount();
            VarexpFactory factoryVariable = new VarexpFactory();

            while (commonData.next()) {

                //Declare an arrayList. This will be your new VarexpVariable and will be where you shove in fields from toher tables;
                ArrayList<String> newVarexpVariable = new ArrayList<String>();

                //This gets the basic type variable id.
                String id = commonData.getString(ID_POSITION);

                //Fill in the empty fields for the variable
                VarexpVariable varType = factoryVariable.declareNewVariable("COMMON");
                for (int fieldCounter = 0; fieldCounter <= varType.getFieldNum(); fieldCounter++) {
                    newVarexpVariable.add("");
                }

                //Loop through all elements and remove the first blank space character you see.
                //Note that because MySql refuses to accept empty characters, I ended up having to put in spaces for empty fields
                //This means that when you read from the DB, you'll get a space in all fields as the first character...unless it is the first field
                //The following creates a substring that removes the first character space and adds them into the
                //ArrayList

                for (int i = SQL_START_POSITION; i <= columnNum; i++) {
                    String tempString = commonData.getString(i);

                    if (i == SQL_START_POSITION) {
                        //Do nothing here
                    } else {
                        int length = tempString.length();
                        String correctedString = tempString.substring(1, length);
                        tempString = correctedString;
                    }
                    tempList.add(tempString);
                }
                System.out.println(tempList);


                //Store tempList fields into the newVarexpVariable ArrayList
                int tempCounter = 0;
                for (int position : varType.getVarexpPositionList()) {
                    newVarexpVariable.set(position, tempList.get(tempCounter));
                    tempCounter++;
                }


                //Get the respective table based on the variable_type (Common[1])
                rebuildVarexpVariable(newVarexpVariable, id, 0);

                //Get the respective table based on the source field (Common[17])
                rebuildVarexpVariable(newVarexpVariable, id, 16);

                //Stuff it in a queue
                //Clear the list,
                tempList.clear();
                System.out.println(newVarexpVariable);
            }
        } catch (Exception e) {
            //At this point, that means you can't read the data, so double check hte comand or the databaseName
            e.printStackTrace();

        }
        //Remove the first space char
        //Store into the temp List
    }

    private void rebuildVarexpVariable(ArrayList<String> newVarexpVariable, String commonID, int fieldNum) throws Exception {

        String id = newVarexpVariable.get(fieldNum);

        //Local variables
        VarexpFactory factoryVariable = new VarexpFactory();
        ArrayList<String> tempList = new ArrayList<>();
        //Fill the common ArrayList with empty variables from all the tables

        VarexpVariable varexpVariable = factoryVariable.declareNewVariable(id);

        String cmd = "SELECT * FROM " + varexpVariable.getTableName() + " WHERE " + varexpVariable.getTableName() + "_variable_id = " + commonID;
        ResultSet resultSet = db.readDatabase(databaseName, cmd);
        ResultSetMetaData rsmd = resultSet.getMetaData();

        int colNum = rsmd.getColumnCount();
        //Fetch the String from the table and store it in a tempList
        while (resultSet.next()) {
            for (int j = 1; j <= colNum; j++) {
                tempList.add(resultSet.getString(j));
            }
        }
        //Get the positionList of the varexpVariable and then map the
        //fields in tempList to the newVarexpVariable arraylist
        int counter = 0;
        for (int position : varexpVariable.getVarexpPositionList()) {
            newVarexpVariable.set(position, tempList.get(counter));
            counter++;
        }

    }


    private void executeBatch() {
    }

    @Override
    public void run() {
    }
}
