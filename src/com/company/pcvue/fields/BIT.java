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
        return "RIGHT JOIN bit on common.variable_id = bit.bit_variable_id";
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> bitList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        bitList.add("" + dbIndex);
        for (int i = 40; i <= 42; i++) {
            bitList.add(varexpArraySplit.get(i).toString());
        }
        bitList.add(varexpArraySplit.get(156).toString());

        this.bitList.add(bitList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.bitList;
    }
}
