package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class Internal extends VarexpVariable {
    private ArrayList<List<String>> internalList;

    public Internal() {
        this.internalList = new ArrayList<>();
        setTableName("internal");
        setPositionList();
    }

    @Override
    void setPositionList() {
        varexpPositionList.add(17);
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

        return "RIGHT JOIN  internal on common.variable_id=internal.internal_variable_id";
    }

    @Override
    public String createTableCmd() {

        return "CREATE TABLE internal(" +
                "internal_variable_id int unsigned  primary key," +
                "internal_indication TEXT(50) NULL);";

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
        for (int i : varexpPositionList) {
            internalList.add(varexpArraySplit.get(i));
        }

        this.internalList.add(internalList);

    }
}
