package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class OPC extends VarexpVariable {
    private ArrayList<List<String>> opcList;

    public OPC() {
        this.opcList = new ArrayList<>();
        setTableName("opc");
        setPositionList();

    }


    @Override
    void setPositionList() {
        for (int i = 144; i <= 150; i++) {
            varexpPositionList.add(i);
        }

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

        return "RIGHT JOIN  opc on common.variable_id=opc.opc_variable_id";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.opcList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> opcList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        opcList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            opcList.add(varexpArraySplit.get(i));
        }

        this.opcList.add(opcList);

    }
}
