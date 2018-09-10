package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class DNP3_Master extends VarexpVariable {
    private ArrayList<List<String>> dnp3_masterList;

    public DNP3_Master() {
        this.dnp3_masterList = new ArrayList<>();
        setTableName("dnp3_master");
        setPositionList();
    }

    @Override
    void setPositionList() {
        for (int i = 206; i <= 210; i++) {
            varexpPositionList.add(i);
        }
        varexpPositionList.add(212);
        varexpPositionList.add(214);
        for (int i = 219; i <= 228; i++) {
            varexpPositionList.add(i);
        }
    }

    @Override
    String empty() {
        String emptyString = "";

        for (int i : varexpPositionList) {
            emptyString += ",";
        }

        return emptyString;
    }

    @Override
    String getJoinCmd() {

        return "RIGHT JOIN  dnp3_master on common.variable_id=dnp3_master.dnp3_master_variable_id";
    }

    @Override
    public String createTableCmd() {
        return
                "CREATE TABLE dnp3_master(" +
                        "dnp3_master_variable_id int unsigned  primary key," +
                        "dnp3_master_Network_name TEXT(50) NULL," +
                        "dnp3_master_device_name TEXT(50) NULL," +
                        "dnp3_master_type TEXT(50) NULL," +
                        "dnp3_master_point_address TEXT(50) NULL," +
                        "dnp3_master_AOB_PointVariation TEXT(50) NULL," +
                        "dnp3_master_Time_Tagging TEXT(50) NULL," +
                        "dnp3_master_Select_before_operate TEXT(50) NULL," +
                        "dnp3_master_enable_writing_add_val0 TEXT(50) NULL," +
                        "dnp3_master_add_val_0 TEXT(50) NULL," +
                        "dnp3_master_options_val0 TEXT(50) NULL," +
                        "dnp3_master_onTimeMs_val0 TEXT(50) NULL," +
                        "dnp3_master_EnableWriting_add_val_1 TEXT(50) NULL," +
                        "dnp3_master_add_val1 TEXT(50) NULL," +
                        "dnp3_master_options_val1 TEXT(50) NULL," +
                        "dnp3_master_onTimeMs_val1 TEXT(50) NULL," +
                        "dnp3_master_EnableWritingAdd_AOB TEXT(50) NULL," +
                        "dnp3_master_Add_AOB TEXT(50) NULL);";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.dnp3_masterList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> dnp3_masterList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        dnp3_masterList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            dnp3_masterList.add(varexpArraySplit.get(i).trim());
        }

        this.dnp3_masterList.add(dnp3_masterList);

    }
}
