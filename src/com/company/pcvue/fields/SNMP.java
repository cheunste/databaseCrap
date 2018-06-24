package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class SNMP extends VarexpVariable {
    public ArrayList<List<String>> snmpList;

    public SNMP() {
        this.snmpList = new ArrayList<>();
        setTableName("snmp");
        setPositionList();

    }


    @Override
    void setPositionList() {
        for (int i = 229; i <= 238; i++) {
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

        return "RIGHT JOIN  snmp on common.variable_id=snmp.snmp_variable_id";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.snmpList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> snmpList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        snmpList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            snmpList.add(varexpArraySplit.get(i));
        }

        this.snmpList.add(snmpList);

    }
}
