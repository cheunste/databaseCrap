package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class CNT extends VarexpVariable {
    private ArrayList<List<String>> cntList;

    public CNT() {
        this.cntList = new ArrayList<>();
        setTableName("cnt");
        setPositionList();
    }

    @Override
    void setPositionList() {
        for (int i = 59; i <= 66; i++) {
            varexpPositionList.add(i);
        }
        for (int i = 79; i <= 84; i++) {
            varexpPositionList.add(i);
        }
        varexpPositionList.add(155);
        varexpPositionList.add(156);
    }

    @Override
    String insertToDB() {

        return "";
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

        return "RIGHT JOIN  cnt on common.variable_id=cnt.cnt_variable_id";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.cntList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> cntList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        cntList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            cntList.add(varexpArraySplit.get(i).toString());
        }

        this.cntList.add(cntList);

    }
}

