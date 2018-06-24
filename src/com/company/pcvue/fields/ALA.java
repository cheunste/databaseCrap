package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class ALA extends VarexpVariable {
    private ArrayList<List<String>> alaList;

    public ALA() {
        this.alaList = new ArrayList<>();
        setTableName("ala");
        setPositionList();
    }

    @Override
    void setPositionList() {
        for (int i = 40; i <= 42; i++) {
            this.varexpPositionList.add(i);
        }
        for (int i = 44; i <= 47; i++) {
            this.varexpPositionList.add(i);
        }
        this.varexpPositionList.add(156);
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
        return "RIGHT JOIN ala on common.variable_id = ala.ala_variable_id";
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {

        setvarexpArrayList(varexpString);
        List<String> alaList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        alaList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            alaList.add(varexpArraySplit.get(i));
        }
        this.alaList.add(alaList);

    }

    public ArrayList<List<String>> getArrayList() {
        return this.alaList;
    }
}
