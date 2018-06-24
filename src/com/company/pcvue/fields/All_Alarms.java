package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/21/2018.
 */
public class All_Alarms extends VarexpVariable {
    public ArrayList<List<String>> allAlarmsList;

    public All_Alarms() {
        this.allAlarmsList = new ArrayList<>();
        setTableName("all_alarms");
        setPositionList();

    }


    @Override
    void setPositionList() {
        for (int i = 158; i <= 160; i++) {
            varexpPositionList.add(i);
        }
        for (int i = 175; i <= 177; i++) {
            varexpPositionList.add(i);
        }
    }

    @Override
    String insertToDB() {
        return null;
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
        return "RIGHT JOIN  all_alarms on common.variable_id=all_alarms.all_alarms_variable_id";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.allAlarmsList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> all_alarmsList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        all_alarmsList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            all_alarmsList.add(varexpArraySplit.get(i));
        }
        this.allAlarmsList.add(all_alarmsList);
    }
}
