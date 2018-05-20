package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class REG extends VarexpVariable {
    public ArrayList<List<String>> regList;

    public REG() {
        this.regList = new ArrayList<>();
        setTableName("reg");
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
        emptyString += ",";
        emptyString += ",";
        return emptyString;
    }

    @Override
    String getJoinCmd() {
        return "RIGHT JOIN reg on common.variable_id = reg.reg_variable_id";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.regList;
    }
    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> acmList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        acmList.add("" + dbIndex);
        for (int i = 59; i <= 66; i++) {
            acmList.add(varexpArraySplit.get(i).toString());
        }

        acmList.add(varexpArraySplit.get(155).toString());
        acmList.add(varexpArraySplit.get(156).toString());

        this.regList.add(acmList);
    }
}
