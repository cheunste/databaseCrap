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
    }


    @Override
    void setPositionList() {

    }

    @Override
    String insertToDB() {

        return "";
    }

    @Override
    String empty() {
        String emptyString = "";

        for (int i = 206; i <= 210; i++) {
            emptyString += ",";
        }
        for (int i = 212; i <= 214; i++) {
            emptyString += ",";
        }
        for (int i = 29; i <= 228; i++) {
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
        for (int i = 206; i <= 210; i++) {
            dnp3_masterList.add(varexpArraySplit.get(i).toString());
        }
        for (int i = 212; i <= 214; i++) {
            dnp3_masterList.add(varexpArraySplit.get(i).toString());
        }
        for (int i = 29; i <= 228; i++) {
            dnp3_masterList.add(varexpArraySplit.get(i).toString());
        }

        this.dnp3_masterList.add(dnp3_masterList);

    }
}
