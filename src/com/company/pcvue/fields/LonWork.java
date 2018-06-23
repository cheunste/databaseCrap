package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class LonWork extends VarexpVariable {
    public ArrayList<List<String>> lonworkList;

    public LonWork() {
        this.lonworkList = new ArrayList<>();
        setTableName("lonwork");

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
        for (int i = 109; i <= 117; i++) {
            emptyString += ",";
        }

        return emptyString;
    }

    @Override
    String getJoinCmd() {

        return "RIGHT JOIN  lonwork on common.variable_id=lonwork.lonwork_variable_id";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.lonworkList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> lonworkList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        lonworkList.add("" + dbIndex);
        for (int i = 109; i <= 117; i++) {
            lonworkList.add(varexpArraySplit.get(i).toString());
        }

        this.lonworkList.add(lonworkList);

    }
}
