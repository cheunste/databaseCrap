package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class OPC extends VarexpVariable {
    public ArrayList<List<String>> opcList;

    public OPC() {
        this.opcList = new ArrayList<>();
        setTableName("opc");

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
        for (int i = 144; i <= 150; i++) {
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
        for (int i = 144; i <= 150; i++) {
            opcList.add(varexpArraySplit.get(i).toString());
        }

        this.opcList.add(opcList);

    }
}
