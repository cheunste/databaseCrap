package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class TXT extends VarexpVariable {
    public ArrayList<List<String>> txtList;

    public TXT() {
        this.txtList = new ArrayList<>();
        setTableName("txt");
    }

    @Override
    void VarexpVariable() {

    }

    @Override
    String empty() {
        return null;
    }

    @Override
    String getJoinCmd() {
        return "RIGHT JOIN txt on common.variable_id = txt.txt_variable_id";
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> txtList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        txtList.add("" + dbIndex);
        txtList.add(varexpArraySplit.get(99).toString());
        txtList.add(varexpArraySplit.get(100).toString());
        txtList.add(varexpArraySplit.get(102).toString());
        txtList.add(varexpArraySplit.get(156).toString());

        this.txtList.add(txtList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.txtList;
    }
}
