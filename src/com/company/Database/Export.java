package com.company.Database;

import com.company.pcvue.fields.VarexpFactory;
import com.company.pcvue.fields.VarexpVariable;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.util.StringUtil;

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

    //TODO: Use the BUffer class to pass on a ConcurrentLinkedQueue to this class and the dbConnector
    private String databaseName;
    private PrintWriter outputFile;
    private ArrayList<ArrayList<String>> outptuVarexpVariableList;
    private String encoding = "UTF-8";

    //Constructor
    public Export(String databaseName, Buffer buffer) {
        this.databaseName = databaseName;
    }

    //Removes '[', ']' and removes all empty spaces after a single comma
    private static String removeUnwantedCharacters(String varexpVariable) {
        return varexpVariable.replace("[", "").replace("]", "").replaceAll(",\\ *", ",").replace("\"", "");
    }

    public void exportDB() {

        //Verify that the DB exists first
        dbConnector dbConnector = new dbConnector();
        boolean exists = dbConnector.verifyDBExists(databaseName);

        if (exists) {
            createFile();
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

        String numVarexpVariable = db.getTableSize(databaseName);
        String sqlCmd = "SELECT * FROM common ";
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
                        String difference = StringUtils.difference(commonTemp.get(position), innerList.get(a));
                        //Only set value at commonTemp if and only if innerList.get is NOT empty
                        if (!innerList.get(a).equals("")) {
                            commonTemp.set(position, innerList.get(a));
                        }
                        /*
                        if (!difference.equals("")){
                            System.out.println("CommonTemp["+position+"]: "+commonTemp.get(position));
                            System.out.println("innerList["+a+"]: "+innerList.get(a));
                            System.out.println("difference: "+StringUtils.difference(commonTemp.get(position),innerList.get(a)));
                        }
                        */
                        //commonTemp.set(position, innerList.get(a));
                        a++;
                    }

                }
            }
        }
        for (ArrayList<String> outputList : csvListOutput) {
            writeLine(removeUnwantedCharacters(outputList.toString()));
            //System.out.println(removeUnwantedCharacters(outputList.toString()));
        }
        closeFile();
    }

    private void createFile() {
        try {
            this.outputFile = new PrintWriter("C:\\Users\\Stephen\\Desktop\\varexp tool project\\Test-Varexp.csv", encoding);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //This function writes a VarexpVariable line into the varexp file
    private void writeLine(String varexpLine) {
        this.outputFile.println(varexpLine);
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
