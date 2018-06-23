package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class TSH extends VarexpVariable {
    public ArrayList<List<String>> tshList;

    public TSH() {
        this.tshList = new ArrayList<>();
        setTableName("tsh");

    }


    @Override
    String insertToDB() {

        return "";
    }

    @Override
    String empty() {
        String emptyString = "";

        for (int i = 40; i <= 42; i++) {
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

        return "RIGHT JOIN  tsh on common.variable_id=tsh.tsh_variable_id";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.tshList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> tshList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        tshList.add("" + dbIndex);
        for (int i = 40; i <= 42; i++) {
            tshList.add(varexpArraySplit.get(i).toString());
        }
        for (int i = 49; i <= 54; i++) {
            tshList.add(varexpArraySplit.get(i).toString());
        }
        tshList.add(varexpArraySplit.get(156).toString());
        this.tshList.add(tshList);

    }
}
