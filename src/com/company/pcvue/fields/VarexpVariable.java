package com.company.pcvue.fields;

import com.company.dbConnector;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 4/20/2018.
 * <p>
 * IMPORTANT: This is an abstract class that is meant to be inheritaned from other class
 * <p>
 * This abstract class handles anything relating ot the varexp file
 * <p>
 * This means anything include separating a varexp variable to its respective fields. If this doesn't make
 * any sense, then you need to read the varexp manual.
 */

abstract class VarexpVariable {
    //Member variable. This represents the maximum amount of fields that PcVue will have.
    //IMPORTANT: If you do by chance need to add additional functionality, you MUST expand this FIELD_NUM field
    private int FIELD_NUM = 250;

    //This is the same varexpArrayList, but splitted based on ','
    private ArrayList<String> varexpArrayList = new ArrayList<>(FIELD_NUM);

    //The name of the table. This is limited to 12 options (BIT, CMD, ALA, ACM, TSH, ATS, REG, CTV, CNT, CHR, TXT ,CXT), etc. Refer to the SQL file for details
    private String tableName;

    //This is the command you use to insert into the database
    //TODO: You need to implement this so that you can insert the values as well
    private String insertToDBCmd = "INSERT INTO ";

    //This is going to be the command you use to delete from the DB.
    //TODO: Implement the delete function
    private String deleteFromDBCmd = "";

    //This is the update functino you use to update the MySQL DB.
    //TODO: IMplment the update function
    private String updateFromDBCmd = "";

    protected VarexpVariable() {
    }

    //return FIELD_NUM
    public int getFieldNum() {
        return this.FIELD_NUM;
    }

    public List<String> getVarexpList() {
        return this.varexpArrayList;
    }

    abstract void VarexpVariable();

    abstract String insertToDB();

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    //abstract method for returning empty fields. This is needed as not all fields are used
    abstract String empty();

    //abstract void empty();
    //abstract void empty();

    // abstract method for joining. This is so you can insert a variable in a joined table
    abstract String getJoinCmd();

    public abstract void setArrayList(String varexpString, int dbIndex);

    //This function splits a varexp variable into its respective fields and then
    //set the varexpArrayPlist variable
    /*
        Todo:
        - split string based on fields
        - append more fields if the number of fields is less than FIELD_NUM
        - Create a tempList arraylist<String> and copy all the crap from above to it
        - set varexpLIst to the new temp list
     */
    public void setvarexpArrayList(String varexpString) {

        String[] temp = varexpString.split(",");
        this.varexpArrayList.clear();
        if (temp.length < FIELD_NUM) {
            try {
                for (int i = 0; i < temp.length; i++) {
                    this.varexpArrayList.add(i, temp[i]);
                }
                for (int j = temp.length + 1; j <= FIELD_NUM; j++) {
                    this.varexpArrayList.add(" ");
                }
            } catch (Exception e) {
                throw e;
            } finally {
            }
        }
    }

    public void print(String string) {
        System.out.println(string);
    }

    public String getInsertSQLCmd() {
        return this.insertToDBCmd;
    }

    /*
    This method checks to see if an array is empty. You use this to check if a sub array have fields or not
     */
    public boolean isEmpty(String subArray) {
        Boolean isEmpty = true;

        //Traverse throguh the array, if everything in the array is of length 90, then that means it is empty
        String[] tempList = subArray.split(",");
        for (String s : tempList) {
            if (s.length() != 0) {
                isEmpty = !isEmpty;
                return isEmpty;
            }
        }
        return isEmpty;
    }

    /*
    This function is to read EVERYTHING in a DB. This is what I would call 'a bad idea'
     */
    public void read() {
        dbConnector db = new dbConnector();
        try {
            db.readDatabase();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //This method is to add the query to the database queue. However, ti does NOT execute the batch itesm.
    public void addToDatabaseQueue(ArrayList<List<String>> fileList, String databaseName, String tableName) throws SQLException {

    }
}
