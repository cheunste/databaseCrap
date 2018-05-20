package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class BIT extends VarexpVariable {
    public ArrayList<List<String>> bitList;

    public BIT() {
        this.bitList = new ArrayList<>();
        setTableName("bit");
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
        for (int i = 40; i <= 42; i++) {
            emptyString += "";
        }
        emptyString += "";
        return emptyString;
    }

    @Override
    String getJoinCmd() {
        return null;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> acmList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        acmList.add("" + dbIndex);
        for (int i = 40; i <= 42; i++) {
            acmList.add(varexpArraySplit.get(i).toString());
        }
        acmList.add(varexpArraySplit.get(156).toString());

        this.bitList.add(acmList);
    }
}
