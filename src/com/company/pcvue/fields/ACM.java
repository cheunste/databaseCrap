package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/2/2018.
 */
public class ACM extends VarexpVariable {
    private ArrayList<List<String>> acmList;

    public ACM() {
        this.acmList = new ArrayList<>();
        setPositionList();
        setTableName("acm");
    }



    @Override
    void setPositionList() {
        for (int i = 40; i <= 46; i++) {
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

        return "RIGHT JOIN  acm on common.variable_id=acm.acm_variable_id";
    }

    @Override
    public ArrayList<List<String>> getArrayList() {
        return this.acmList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> acmList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        acmList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            acmList.add(varexpArraySplit.get(i));
        }
        this.acmList.add(acmList);

    }

}
