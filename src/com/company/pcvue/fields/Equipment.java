package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class Equipment extends VarexpVariable {
    private ArrayList<List<String>> equipmentList;

    public Equipment() {
        this.equipmentList = new ArrayList<>();
        setTableName("equipment");
        setPositionList();
    }

    @Override
    void setPositionList() {
        for (int i = 29; i <= 36; i++) {
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

        return "RIGHT JOIN  equipment on common.variable_id=equipment.equipment_variable_id";
    }

    @Override
    public String createTableCmd() {
        return
                "CREATE TABLE equipment(" +
                        "equipment_variable_id int unsigned  primary key," +
                        "equipment_Card_Number TEXT(50) NOT NULL," +
                        "Equipment_network_Name TEXT(50) NOT NULL," +
                        "Equipment_equipment_Name TEXT(50) NOT NULL," +
                        "equipment_Frame_Name TEXT(50) NOT NULL," +
                        "equipment_Format TEXT(50) NOT NULL," +
                        "equipment_Equipment_Index TEXT(50) NOT NULL," +
                        "equipment_Complementary_Index TEXT(50) NOT NULL," +
                        "equipment_Size TEXT(50) NOT NULL);";
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
