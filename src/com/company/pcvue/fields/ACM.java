package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/2/2018.
 */
public class ACM extends VarexpVariable {
    public List<List<String>> acmList;

    public ACM() {
        this.acmList = new ArrayList<>();
        setTableName("acm");

    }

    @Override
    void VarexpVariable() {

    }

    @Override
    String insertToDB() {

        return "";
    }

    @Override
    String empty() {
        String emptyString = "";
        for (int i = 40; i <= 46; i++) {
            emptyString += ",";
        }

        emptyString += ",";

        return emptyString;
    }

    @Override
    String getJoinCmd() {

        return "LEFT JOIN  acm on common.variable_id=acm.acm_variable_id";
    }

    public List<List<String>> getArrayList() {
        return this.acmList;
    }

    @Override
    public void setArrayList(String varexpString) {
        setvarexpArrayList(varexpString);
        List<String> acmList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        for (int i = 40; i <= 46; i++) {
            acmList.add(varexpArraySplit.get(i).toString());
        }
        acmList.add(varexpArraySplit.get(156).toString());

        this.acmList.add(acmList);

    }
}
