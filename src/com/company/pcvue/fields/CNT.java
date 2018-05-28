package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class CNT extends VarexpVariable {
    public ArrayList<List<String>> cntList;

    public CNT() {
        this.cntList = new ArrayList<>();
        setTableName("cnt");

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
        for (int i = 59; i <= 66; i++) {
            emptyString += ",";
        }
        for (int i = 79; i <= 84; i++) {
            emptyString += ",";
        }
        emptyString += ",";
        emptyString += ",";

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
        for (int i = 59; i <= 66; i++) {
            cntList.add(varexpArraySplit.get(i).toString());
        }
        for (int i = 79; i <= 84; i++) {
            cntList.add(varexpArraySplit.get(i).toString());
        }
        cntList.add(varexpArraySplit.get(155).toString());
        cntList.add(varexpArraySplit.get(156).toString());

        this.cntList.add(cntList);

    }
}

