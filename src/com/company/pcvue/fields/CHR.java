package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class CHR extends VarexpVariable {

    public ArrayList<List<String>> chrList;

    public CHR() {
        this.chrList = new ArrayList<>();
        setTableName("chr");
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
        for (int i = 59; i <= 66; i++) {
            emptyString += ",";
        }
        for (int i = 89; i <= 94; i++) {
            emptyString += ",";
        }
        emptyString += ",";
        emptyString += ",";
        return emptyString;
    }

    @Override
    String getJoinCmd() {
        return "RIGHT JOIN chr on common.variable_id = chr.chr_variable_id";
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> chrList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        chrList.add("" + dbIndex);
        for (int i = 59; i <= 66; i++) {
            chrList.add(varexpArraySplit.get(i).toString());
        }
        for (int i = 89; i <= 94; i++) {
            chrList.add(varexpArraySplit.get(i).toString());
        }
        chrList.add(varexpArraySplit.get(155).toString());
        chrList.add(varexpArraySplit.get(156).toString());

        this.chrList.add(chrList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.chrList;
    }
}
