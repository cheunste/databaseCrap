package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class SNMP extends VarexpVariable {
    private ArrayList<List<String>> snmpList;

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

    @Override
    public String createTableCmd() {
        return
                "CREATE TABLE snmp(" +
                        "snmp_variable_id int unsigned  primary key," +
                        "snmp_Network_Name TEXT(50) NULL," +
                        "snmp_Device_name TEXT(50) NULL," +
                        "snmp_polling_group TEXT(50) NULL," +
                        "snmp_data_type TEXT(50) NULL," +
                        "snmp_OID TEXT(50) NULL," +
                        "snmp_Disable_reading TEXT(50) NULL," +
                        "snmp_with_initial_value TEXT(50) NULL," +
                        "snmp_initial_value TEXT(50) NULL," +
                        "snmp_offset TEXT(50) NULL," +
                        "snmp_extraction_field TEXT(50) NULL);";

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
            snmpList.add(varexpArraySplit.get(i).trim());
        }

        this.snmpList.add(snmpList);

    }
}
