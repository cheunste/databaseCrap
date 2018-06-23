package com.company;

import com.company.pcvue.fields.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by Stephen on 6/23/2018.
 * <p>
 * This class imports the varexp.dat file into the Mysql database
 */
public class Import {
    public void importFile(String[] args) throws IOException, ArrayIndexOutOfBoundsException, SQLException {
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
                for (List<String> subList : fullList) {
                    System.out.println(subList);
                    switch (subList.get(0).toUpperCase()) {
                        case "ACM":
                            System.out.println("Do acm Stuff");
                            acm.setArrayList(subList.toString(), temp);
                            break;
                        case "ALA":
                            System.out.println("Do ala Stuff");
                            ala.setArrayList(subList.toString(), temp);
                            break;
                        case "ATS":
                            System.out.println("Do ats Stuff");
                            ats.setArrayList(subList.toString(), temp);
                            break;
                        case "BIT":
                            System.out.println("Do bit Stuff");
                            bit.setArrayList(subList.toString(), temp);
                            break;
                        case "CHR":
                            System.out.println("Do ctr Stuff");
                            chr.setArrayList(subList.toString(), temp);
                            break;
                        case "CMD":
                            System.out.println("Do cmd Stuff");
                            cmd.setArrayList(subList.toString(), temp);
                            break;
                        case "CNT":
                            System.out.println("Do cnt stuff");
                            cnt.setArrayList(subList.toString(), temp);
                        case "CTV":
                            System.out.println("Do ctv Stuff");
                            ctv.setArrayList(subList.toString(), temp);
                            break;
                        case "CXT":
                            System.out.println("Do cxt Stuff");
                            cxt.setArrayList(subList.toString(), temp);
                            break;
                        case "REG":
                            System.out.println("Do reg Stuff");
                            reg.setArrayList(subList.toString(), temp);
                            break;
                        case "TXT":
                            System.out.println("Do txt Stuff");
                            txt.setArrayList(subList.toString(), temp);
                            break;
                        case "TSH":
                            System.out.println("Do tsh Stuff");
                            tsh.setArrayList(subList.toString(), temp);
                            break;
                    }
                    allAlarms.setArrayList(subList.toString(), temp);
                    switch (subList.get(16).toUpperCase()) {
                        //For equpiment
                        case "E":
                            equipment.setArrayList(subList.toString(), temp);
                            break;
                        //For Internal
                        case "I":
                            internal.setArrayList(subList.toString(), temp);
                            break;
                        //For external
                        case "X":
                            external.setArrayList(subList.toString(), temp);
                            break;
                        //For DDE
                        case "D":
                            dde.setArrayList(subList.toString(), temp);
                            break;
                        //For OPC
                        case "O":
                            opc.setArrayList(subList.toString(), temp);
                            break;
                        //For Lonwork
                        case "L":
                            lonWork.setArrayList(subList.toString(), temp);
                            break;
                        //For BACnet
                        case "B":
                            bac.setArrayList(subList.toString(), temp);
                            break;
                        //For 60870-5-104
                        case "4":
                            iec60870Master.setArrayList(subList.toString(), temp);
                            break;
                        //For 61850
                        case "8":
                            iec61850_master.setArrayList(subList.toString(), temp);
                            break;
                        //For DNP3
                        case "3":
                            dnp3.setArrayList(subList.toString(), temp);
                            break;
                        //For SNMP Manager
                        case "S":
                            smnp.setArrayList(subList.toString(), temp);
                            break;
                        default:
                            //Handle other unexpect cases here
                            break;
                    }
                    //This following case statemnets determine the "source"  of the tag and then assign it to their
                    //respective table. See page 11 of the varexp format manual for detail

                    temp++;
                }

                System.out.println("rows handled: " + temp);
                //acm.getArrayList();

                //3) This is a simple case, let's assume this is for common

                //4) Doing the write to DB stuff
                //Open up a DB connection

                common_field.writeDB(fileList, "twin_buttes_2", common_field.getTableName());
                //System.out.println("Done with wrting common to DB");
                acm.writeDB(acm.getArrayList(), "twin_buttes_2", acm.getTableName());
                ala.writeDB(ala.getArrayList(), "twin_buttes_2", ala.getTableName());
                ats.writeDB(ats.getArrayList(), "twin_buttes_2", ats.getTableName());
                bit.writeDB(bit.getArrayList(), "twin_buttes_2", bit.getTableName());
                chr.writeDB(chr.getArrayList(), "twin_buttes_2", chr.getTableName());
                cmd.writeDB(cmd.getArrayList(), "twin_buttes_2", cmd.getTableName());
                ctv.writeDB(ctv.getArrayList(), "twin_buttes_2", ctv.getTableName());
                cxt.writeDB(cxt.getArrayList(), "twin_buttes_2", cxt.getTableName());
                reg.writeDB(reg.getArrayList(), "twin_buttes_2", reg.getTableName());
                txt.writeDB(txt.getArrayList(), "twin_buttes_2", txt.getTableName());
                allAlarms.writeDB(allAlarms.getArrayList(), "twin_buttes_2", allAlarms.getTableName());
                //close file
                fh.closeFile(fileBR);


            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Not enough arguments");
        }
    }
}
