package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class ATS extends VarexpVariable {

    public ArrayList<List<String>> atsList;

    public ATS() {
        this.atsList = new ArrayList<>();
        setTableName("ats");
        setPositionList();
    }


    @Override
    void setPositionList() {
        for (int i = 40; i <= 42; i++) {
            this.varexpPositionList.add(i);
        }
        for (int i = 44; i <= 46; i++) {
            this.varexpPositionList.add(i);
        }
        for (int i = 49; i <= 54; i++) {
            this.varexpPositionList.add(i);
        }
        this.varexpPositionList.add(156);
    }

    @Override
    String insertToDB() {
        return null;
    }

    @Override
    String empty() {
        String emptyString = "";
        for (int i = 40; i <= 46; i++) {
            emptyString += ",";
        }
        for (int i = 49; i <= 54; i++) {
            emptyString += ",";
        }
        emptyString += ",";
        return emptyString;
    }

    public ArrayList<List<String>> getArrayList() {
        return this.atsList;
    }
    @Override
    String getJoinCmd() {
        return "RIGHT JOIN ats on common.variable_id = ats.ats_variable_id";
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> atsList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        atsList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            atsList.add(varexpArraySplit.get(i));
        }
        this.atsList.add(atsList);
    }
}
