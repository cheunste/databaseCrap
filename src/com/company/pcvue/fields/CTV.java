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
        setPositionList();
    }


    @Override
    void setPositionList() {
        for (int i = 59; i <= 66; i++) {
            varexpPositionList.add(i);
        }
        for (int i = 69; i <= 71; i++) {
            varexpPositionList.add(i);
        }
        varexpPositionList.add(155);
        varexpPositionList.add(156);
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
        for (int i : varexpPositionList) {
            ctvList.add(varexpArraySplit.get(i));
        }

        this.ctvList.add(ctvList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.ctvList;
    }
}
