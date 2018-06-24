package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class DNP3_Master extends VarexpVariable {
    public ArrayList<List<String>> dnp3_masterList;

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
        for (int i = 220; i <= 228; i++) {
            varexpPositionList.add(i);
        }
    }

    @Override
    String insertToDB() {

        return "";
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
            varexpPositionList.add(i);
        }

        this.dnp3_masterList.add(dnp3_masterList);

    }
}
