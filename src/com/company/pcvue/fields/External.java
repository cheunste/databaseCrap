package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class External extends VarexpVariable {
    private ArrayList<List<String>> externalList;

    public External() {
        this.externalList = new ArrayList<>();
        setTableName("external");
        setPositionList();
    }

    @Override
    void setPositionList() {
        varexpPositionList.add(18);
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

    @Override
    public String createTableCmd() {
        return
                "CREATE TABLE external(" +
                        "external_variable_id int unsigned  primary key," +
                        "external_Description TEXT(50) NOT NULL);";

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
        for (int i : varexpPositionList) {
            externalList.add(varexpArraySplit.get(i).trim().toString());
        }

        this.externalList.add(externalList);

    }
}
