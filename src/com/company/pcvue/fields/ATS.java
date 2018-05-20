package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class ATS extends VarexpVariable {

    public ArrayList<List<String>> atsList;

    public ATS() {

    }

    @Override
    void VarexpVariable() {

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

    @Override
    String getJoinCmd() {
        return "RIGHT JOIN ats on common.variable_id = ats.ats_variable_id";
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> acmList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        acmList.add("" + dbIndex);
        for (int i = 40; i <= 46; i++) {
            acmList.add(varexpArraySplit.get(i).toString());
        }
        for (int i = 49; i <= 54; i++) {
            acmList.add(varexpArraySplit.get(i).toString());
        }
        acmList.add(varexpArraySplit.get(156).toString());
        this.atsList.add(acmList);
    }
}
