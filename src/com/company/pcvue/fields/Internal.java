package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class Internal extends VarexpVariable {
    public ArrayList<List<String>> internalList;

    public Internal() {
        this.internalList = new ArrayList<>();
        setTableName("internal");

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
        emptyString += ",";

        return emptyString;
    }

    @Override
    String getJoinCmd() {

        return "RIGHT JOIN  internal on common.variable_id=internal.internal_variable_id";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.internalList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> internalList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        internalList.add("" + dbIndex);
        internalList.add(varexpArraySplit.get(17).toString());

        this.internalList.add(internalList);

    }
}
