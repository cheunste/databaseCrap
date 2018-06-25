package com.company;

import com.company.pcvue.fields.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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


                //initiate the reference to the Type variables
                //Recall this only sets up the table, but you need to handle the variable elsewhere
                Common common_field = new Common();
                ACM acm = new ACM();
                ALA ala = new ALA();
                ATS ats = new ATS();
                BIT bit = new BIT();
                CHR chr = new CHR();
                CMD cmd = new CMD();
                CNT cnt = new CNT();
                CTV ctv = new CTV();
                CXT cxt = new CXT();
                REG reg = new REG();
                TXT txt = new TXT();
                TSH tsh = new TSH();

                //References to the source variables
                All_Alarms allAlarms = new All_Alarms();

                Equipment equipment = new Equipment();
                External external = new External();
                Internal internal = new Internal();
                External ext = new External();
                DDE dde = new DDE();
                OPC opc = new OPC();

                LonWork lonWork = new LonWork();
                BACnet bac = new BACnet();

                IEC61850_Master iec61850_master = new IEC61850_Master();
                IEC60870_Master iec60870Master = new IEC60870_Master();
                DNP3_Master dnp3 = new DNP3_Master();
                SNMP smnp = new SNMP();

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
                    //System.out.println(appendedString);

                    //Common common_field = new Common(appendedString);
                    //1) This method should split the line from string to fields and store them in a list
                    common_field.setArrayList(appendedString, temp);

                    //2) Add the newly converted list to another list. Will need a get method
                    fileList.add(common_field.getCommonList());
                    //For other non-common variables
                    List<String> tempItem = Arrays.asList(appendedString.split(","));
                    fullList.add(tempItem);

                    temp++;
                }

                //Consider handling the other varexp elements here
                //TODO: Research to see if you can handle other varexp elements here and then
                // stuff them into their respective tables

                temp = 0;
                VarexpFactory factoryVariable = new VarexpFactory();
                VarexpVariable varexpType = null;
                VarexpVariable varexpSource = null;
                ArrayList<VarexpVariable> queue = new ArrayList<>();
                for (List<String> subList : fullList) {
                    System.out.println(subList);

                    String type = subList.get(0).toUpperCase();
                    String source = subList.get(16).toUpperCase();

                    varexpType = factoryVariable.declareNewVariable(type);
                    varexpType.setArrayList(subList.toString(), temp);

                    if (type.equals("ALA") || type.equals("ACM") || type.equals("ATS")) {
                        varexpSource = factoryVariable.declareNewVariable("ALL");
                        varexpSource.setArrayList(subList.toString(), temp);
                        //make a call to store in queue
                    }

                    varexpSource = factoryVariable.declareNewVariable(source);
                    varexpSource.setArrayList(subList.toString(), temp);

                    /*
                    At this point, you might want to consider pushing all of this shit into a queue.
                    Because by this point you're running into a tradeoff.
                    By makign your code more readable, you're going to have to add a queue or else things will get erased
                    (due to you calling a new instance every time) before things get inserted into the DB.

                    so you can store the varexpType and varexpSource, along with the temp, and the type/source table name

                    Also, you need to take care of the all_alarms case
                     */

                    //This following case statemnets determine the "source"  of the tag and then assign it to their
                    //respective table. See page 11 of the varexp format manual for detail

                    temp++;
                }

                System.out.println("rows handled: " + temp);
                //acm.getArrayList();

                //3) This is a simple case, let's assume this is for common

                //4) Doing the write to DB stuff
                //Open up a DB connection

                //System.out.println("Done with wrting common to DB");
                //TODO: Rewrite the entire writeDB method in sqlHandler
                sqlHandler.writeDB(fileList, "twin_buttes_2", common_field.getTableName());
                //sqlHandler.writeDB(acm.getArrayList(), "twin_buttes_2", acm.getTableName());
                //sqlHandler.writeDB(ala.getArrayList(), "twin_buttes_2", ala.getTableName());
                //sqlHandler.writeDB(ats.getArrayList(), "twin_buttes_2", ats.getTableName());
                //sqlHandler.writeDB(bit.getArrayList(), "twin_buttes_2", bit.getTableName());
                //sqlHandler.writeDB(chr.getArrayList(), "twin_buttes_2", chr.getTableName());
                //sqlHandler.writeDB(cmd.getArrayList(), "twin_buttes_2", cmd.getTableName());
                //sqlHandler.writeDB(ctv.getArrayList(), "twin_buttes_2", ctv.getTableName());
                //sqlHandler.writeDB(cxt.getArrayList(), "twin_buttes_2", cxt.getTableName());
                //sqlHandler.writeDB(reg.getArrayList(), "twin_buttes_2", reg.getTableName());
                //sqlHandler.writeDB(txt.getArrayList(), "twin_buttes_2", txt.getTableName());
                //sqlHandler.writeDB(allAlarms.getArrayList(), "twin_buttes_2", allAlarms.getTableName());
                //close file
                fh.closeFile(fileBR);


            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Not enough arguments");
        }


    }

}
