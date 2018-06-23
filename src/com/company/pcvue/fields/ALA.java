package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class ALA extends VarexpVariable {
    public ArrayList<List<String>> alaList;

    public ALA() {
        this.alaList = new ArrayList<>();
        setTableName("ala");
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
        for (int i = 40; i <= 42; i++) {
            emptyString += ",";
        }
        for (int i = 44; i <= 47; i++) {
            emptyString += ",";
        }
        emptyString += ",";
        return emptyString;
    }

    @Override
    String getJoinCmd() {
        return "RIGHT JOIN ala on common.variable_id = ala.ala_variable_id";
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {

        setvarexpArrayList(varexpString);
        List<String> alaList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        alaList.add("" + dbIndex);
        for (int i = 40; i <= 42; i++) {
            alaList.add(varexpArraySplit.get(i).toString());
        }
        for (int i = 44; i <= 47; i++) {
            alaList.add(varexpArraySplit.get(i).toString());
        }
        alaList.add(varexpArraySplit.get(156).toString());

        this.alaList.add(alaList);

    }

    public ArrayList<List<String>> getArrayList() {
        return this.alaList;
    }
}
