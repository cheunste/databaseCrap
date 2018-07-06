package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class ALA extends VarexpVariable {
    private ArrayList<List<String>> alaList;

    public ALA() {
        this.alaList = new ArrayList<>();
        setTableName("ala");
        setPositionList();
    }

    @Override
    void setPositionList() {
        for (int i = 40; i <= 42; i++) {
            this.varexpPositionList.add(i);
        }
        for (int i = 44; i <= 47; i++) {
            this.varexpPositionList.add(i);
        }
        this.varexpPositionList.add(156);
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
        return "RIGHT JOIN ala on common.variable_id = ala.ala_variable_id";
    }

    @Override
    public String createTableCmd() {
        return "CREATE TABLE ala(" +
                "ala_variable_id int unsigned  primary key," +
                "	ala_Log_bit_0_to_1 TEXT(50) NULL," +
                "	ala_Log_bit_1_to_0 TEXT(50) NULL," +
                "	ala_reserved TEXT(50) NULL," +
                "	ala_Alarm_Level TEXT(50) NULL," +
                "	ala_acm_Alarm TEXT(50) NULL," +
                "	ala_Name_of_mask_bit TEXT(50) NULL," +
                "	alarm_temporization TEXT(50) NULL," +
                "	ala_VCR TEXT(50) NULL);";
                
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {

        setvarexpArrayList(varexpString);
        List<String> alaList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        alaList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            alaList.add(varexpArraySplit.get(i));
        }
        this.alaList.add(alaList);

    }

    public ArrayList<List<String>> getArrayList() {
        return this.alaList;
    }
}
