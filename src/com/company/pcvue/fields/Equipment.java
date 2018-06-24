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
    void setPositionList() {
        for (int i = 29; i <= 36; i++) {
            varexpPositionList.add(i);
        }
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
        for (int i : varexpPositionList) {
            equipmentList.add(varexpArraySplit.get(i));
        }

        this.equipmentList.add(equipmentList);

    }
}
