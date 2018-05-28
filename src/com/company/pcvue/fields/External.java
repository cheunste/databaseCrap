package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class External extends VarexpVariable {
    public ArrayList<List<String>> externalList;

    public External() {
        this.externalList = new ArrayList<>();
        setTableName("external");

    }


    @Override
    void VarexpVariable() {

    }

    @Override
    String insertToDB() {

        return "";
    }

    @Override
    String empty() {
        String emptyString = "";
        emptyString += ",";

        return emptyString;
    }

    @Override
    String getJoinCmd() {

        return "RIGHT JOIN  external on common.variable_id=external.external_variable_id";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.externalList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> externalList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        externalList.add("" + dbIndex);
        externalList.add(varexpArraySplit.get(59).toString());

        this.externalList.add(externalList);

    }
}
