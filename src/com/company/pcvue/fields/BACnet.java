package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class BACnet extends VarexpVariable {

    public ArrayList<List<String>> bacList;

    public BACnet() {
        this.bacList = new ArrayList<>();
        setTableName("bac");
    }

    @Override
    void VarexpVariable() {

    }

    @Override
    String empty() {
        String emptyString = "";
        for (int i = 162; i <= 172; i++) {
            emptyString += ",";
        }

        emptyString += ",";
        emptyString += ",";
        emptyString += ",";
        emptyString += ",";

        return emptyString;
    }

    @Override
    String getJoinCmd() {
        return "RIGHT JOIN  bac on common.variable_id=bac.bac_variable_id";
    }

    ;

    public ArrayList<List<String>> getArrayList() {
        return this.bacList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> bacList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        bacList.add("" + dbIndex);
        for (int i = 162; i <= 172; i++) {
            bacList.add(varexpArraySplit.get(i).toString());
        }
        bacList.add(varexpArraySplit.get(203).toString());
        bacList.add(varexpArraySplit.get(204).toString());
        bacList.add(varexpArraySplit.get(217).toString());
        bacList.add(varexpArraySplit.get(218).toString());

        this.bacList.add(bacList);
    }
}
