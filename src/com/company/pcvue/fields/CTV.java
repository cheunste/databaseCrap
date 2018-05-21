package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class CTV extends VarexpVariable {
    public ArrayList<List<String>> ctvList;

    public CTV() {
        this.ctvList = new ArrayList<>();
        setTableName("ctv");
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
        return null;
    }

    @Override
    String getJoinCmd() {
        return "RIGHT JOIN ctv on common.variable_id = ctv.ctv_variable_id";
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> ctvList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        ctvList.add("" + dbIndex);
        for (int i = 59; i <= 66; i++) {
            ctvList.add(varexpArraySplit.get(i).toString());
        }
        for (int i = 69; i <= 71; i++) {
            ctvList.add(varexpArraySplit.get(i).toString());
        }
        ctvList.add(varexpArraySplit.get(155).toString());
        ctvList.add(varexpArraySplit.get(156).toString());

        this.ctvList.add(ctvList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.ctvList;
    }
}
