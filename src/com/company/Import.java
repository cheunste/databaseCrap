package com.company;

import com.company.pcvue.fields.*;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Stephen on 6/23/2018.
 * <p>
 * This class imports the varexp.dat file into the Mysql database
 */
public class Import implements Runnable {
    private String[] args;
    private Buffer varQueue;

    public Import(String[] args, Buffer buffer) throws IOException, SQLException {
        this.args = args;
        this.varQueue = buffer;
    }


    public void importFile() throws IOException, ArrayIndexOutOfBoundsException, SQLException {
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

                //Reference to the sqlHandler
                SQLHandler sqlHandler = new SQLHandler();


                /*
                For each line in the file do the following:
                1) Separate the line from String to fields (List or Arraylist)
                2) Add the newly converted list to another list. You will have a 2D List of lists<String>
                3) For each list in the list, get the variable parameter
                4) With the parameter in 3), map it to the correct database and write into the DB
                 */

                //Create a fileLIst arrayList to hold all varexp variables
                ArrayList<List<String>> fileList = new ArrayList();
                ArrayList<List<String>> fullList = new ArrayList();
                VarexpFactory common = new VarexpFactory();
                while ((line = fileBR.readLine()) != null) {
                    String appendedString = line;
                    //For other non-common variables
                    List<String> tempItem = Arrays.asList(appendedString.split(","));
                    fullList.add(tempItem);

                    temp++;
                }
                //close file
                fh.closeFile(fileBR);

                //Consider handling the other varexp elements here
                //TODO: Research to see if you can handle other varexp elements here and then
                // stuff them into their respective tables
                temp = 0;
                VarexpFactory factoryVariable = new VarexpFactory();

                /*
                TODO: Review
                ok. So there are a few issues here
                1) You essentually have to handle three to four cases
                    1) The common table
                    2) The source table
                    3) The alarm table ( iff ALA, ACM, ATS)
                    4) The variable table (you know, ALA, CHM, CMD, etc). There's six of these

                 */
                for (List<String> subList : fullList) {
                    VarexpVariable commonType = factoryVariable.declareNewVariable("COMMON");
                    commonType.setArrayList(subList.toString(), temp);
                    varQueue.put(commonType);
                    temp++;
                }
                temp = 0;
                for (List<String> subList : fullList) {
                    //System.out.println(subList);

                    String type = subList.get(0).toUpperCase();
                    String source = subList.get(16).toUpperCase();

                    VarexpVariable variableType = factoryVariable.declareNewVariable(type);
                    VarexpVariable sourceType = factoryVariable.declareNewVariable(source);

                    variableType.setArrayList(subList.toString(), temp);

                    if (type.equals("ALA") || type.equals("ACM") || type.equals("ATS")) {
                        VarexpVariable varexpSource = factoryVariable.declareNewVariable("ALL");
                        varexpSource.setArrayList(subList.toString(), temp);
                        //make a call to store in queue
                    }

                    sourceType = factoryVariable.declareNewVariable(source);
                    sourceType.setArrayList(subList.toString(), temp);

                    /*
                    At this point, you might want to consider pushing all of this shit into a queue.
                    However, what type of queeu is still up for debate. Because idieally, after the queue reaches
                    a certain size, I want to start a thread to another funciton that will gather up all the varibles
                    in a batch and then submit it to mysql
                     */
                    varQueue.put(variableType);
                    varQueue.put(sourceType);
                    if (type.equals("ALA") || type.equals("ACM") || type.equals("ATS")) {
                        VarexpVariable allAlarmType = factoryVariable.declareNewVariable("ALL");
                        allAlarmType.setArrayList(subList.toString(), temp);
                        varQueue.put(allAlarmType);
                        //make a call to store in queue
                    }
                    temp++;
                }

                System.out.println("rows handled: " + temp);
                varQueue.setDoneFlag();
                //sqlHandler.writeDB(fileList, "twin_buttes_2", common_field.getTableName());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Not enough arguments");
        }
    }

    @Override
    public void run() {
        try {
            importFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


