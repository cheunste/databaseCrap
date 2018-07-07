package com.company.Database;

import com.company.fileHandler;
import com.company.pcvue.fields.*;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Created by Stephen on 6/23/2018.
 * <p>
 * This class reads the varexp.dat file and creates different Arraylists based on the fields.
 * Afterwards, it then pushes the ArrayLists into a queue that ImportHandler will read and write to the MySQL DB.
 * This class is used in conjuncture with Import Hanlder and Buffer
 */
public class Import implements Runnable {
    private String[] args;
    private Buffer varQueue;
    private int THREAD_LIMIT = 4;

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

                //Reference to the importHandler
                ImportHandler importHandler = new ImportHandler();


                /*
                For each line in the file do the following:
                1) Separate the line from String to fields (List or Arraylist)
                2) Add the newly converted list to another list. You will have a 2D List of lists<String>
                3) For each list in the list, get the variable parameter
                4) With the parameter in 3), map it to the correct database and write into the DB
                 */

                //Create a fileLIst arrayList to hold all varexp variables
                ArrayList<List<String>> fullList = new ArrayList();

                while ((line = fileBR.readLine()) != null) {
                    String appendedString = line;
                    List<String> tempItem = Arrays.asList(appendedString.split(","));
                    fullList.add(tempItem);
                    temp++;
                }
                //close file
                fh.closeFile(fileBR);
                System.out.println("Done with Reading file");

                //Consider handling the other varexp elements here
                //TODO: Research to see if you can handle other varexp elements here and then
                // stuff them into their respective tables
                VarexpFactory factoryVariable = new VarexpFactory();

                /*
                 Traverse through the entire arraylist, handle each List<String> appropriately and throw them into a
                 queue for the Import Handler to pick up
                  */
                for (List<String> subList : fullList) {
                    //For Common
                    VarexpVariable commonType = factoryVariable.declareNewVariable("COMMON");
                    commonType.setArrayList(subList.toString(), temp);
                    varQueue.put(commonType);
                    //new Thread(new commonHandler(subList,commonType,temp)).start();

                    /* For Source */
                    String source = subList.get(16).toUpperCase();
                    VarexpVariable sourceType = factoryVariable.declareNewVariable(source);
                    sourceType.setArrayList(subList.toString(), temp);
                    varQueue.put(sourceType);
                    // new Thread(new sourceHanlder(subList,sourceType,temp)).start();

                    //For Type and alarms
                    String type = subList.get(0).toUpperCase();
                    VarexpVariable variableType = factoryVariable.declareNewVariable(type);
                    variableType.setArrayList(subList.toString(), temp);
                    varQueue.put(variableType);
                    if (type.equals("ALA") || type.equals("ACM") || type.equals("ATS")) {
                        VarexpVariable allAlarmType = factoryVariable.declareNewVariable("ALL");
                        allAlarmType.setArrayList(subList.toString(), temp);
                        //make a call to store in queue
                        varQueue.put(allAlarmType);
                    }
                    temp++;

                    if (temp >= varQueue.getQueueLimit()) {
                        varQueue.ready();
                        //System.out.println("Queue is ready to read. Size: "+varQueue.getSize());
                    }
                }

                System.out.println("rows handled: " + temp);

                varQueue.setDoneFlag();
                System.out.println("Done flag raised");
                //importHandler.writeDB(fileList, "twin_buttes_2", common_field.getTableName());
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


