package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class CHR extends VarexpVariable {

    private ArrayList<List<String>> chrList;

    public CHR() {
        this.chrList = new ArrayList<>();
        setTableName("chr");
        setPositionList();
    }


    @Override
    void setPositionList() {

        for (int i = 59; i <= 66; i++) {
            varexpPositionList.add(i);
        }
        for (int i = 89; i <= 94; i++) {
            varexpPositionList.add(i);
        }
        varexpPositionList.add(155);
        varexpPositionList.add(156);

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
        return "RIGHT JOIN chr on common.variable_id = chr.chr_variable_id";
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> chrList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        chrList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            chrList.add(varexpArraySplit.get(i).toString());
        }
        this.chrList.add(chrList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.chrList;
    }
}
