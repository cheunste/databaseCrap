package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class IEC60870_Master extends VarexpVariable {
    public ArrayList<List<String>> iec60870_masterList;

    public IEC60870_Master() {
        this.iec60870_masterList = new ArrayList<>();
        setTableName("iec60870_master");

    }


    @Override
    String insertToDB() {

        return "";
    }

    @Override
    String empty() {
        String emptyString = "";

        for (int i = 179; i <= 188; i++) {
            emptyString += ",";
        }
        emptyString += ",";
        return emptyString;
    }

    @Override
    String getJoinCmd() {

        return "RIGHT JOIN  iec60870_master on common.variable_id=iec60870_master.iec60870_master_variable_id";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.iec60870_masterList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> iec60870_masterList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        iec60870_masterList.add("" + dbIndex);
        for (int i = 179; i <= 188; i++) {
            iec60870_masterList.add(varexpArraySplit.get(i).toString());
        }
        iec60870_masterList.add(varexpArraySplit.get(205).toString());

        this.iec60870_masterList.add(iec60870_masterList);

    }
}
