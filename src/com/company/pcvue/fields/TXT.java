package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */

public class TXT extends VarexpVariable {
    private ArrayList<List<String>> txtList;

    public TXT() {
        this.txtList = new ArrayList<>();
        setTableName("txt");
        setPositionList();
    }


    @Override
    void setPositionList() {
        varexpPositionList.add(99);
        varexpPositionList.add(100);
        varexpPositionList.add(102);
        varexpPositionList.add(156);

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
        for (int i : varexpPositionList) {
            txtList.add(varexpArraySplit.get(i));
        }
        this.txtList.add(txtList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.txtList;
    }
}
