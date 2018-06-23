package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class Equipment extends VarexpVariable {
    public ArrayList<List<String>> equipmentList;

    public Equipment() {
        this.equipmentList = new ArrayList<>();
        setTableName("equipment");

    }


    @Override
    String insertToDB() {

        return "";
    }

    @Override
    String empty() {
        String emptyString = "";
        for (int i = 29; i <= 36; i++) {
            emptyString += ",";
        }
        return emptyString;
    }

    @Override
    String getJoinCmd() {

        return "RIGHT JOIN  equipment on common.variable_id=equipment.equipment_variable_id";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.equipmentList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> equipmentList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        equipmentList.add("" + dbIndex);
        for (int i = 29; i <= 36; i++) {
            equipmentList.add(varexpArraySplit.get(i).toString());
        }

        this.equipmentList.add(equipmentList);

    }
}
