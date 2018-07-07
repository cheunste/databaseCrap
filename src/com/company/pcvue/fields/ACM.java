package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/2/2018.
 */
public class ACM extends VarexpVariable {
    private ArrayList<List<String>> acmList;

    public ACM() {
        this.acmList = new ArrayList<>();
        setPositionList();
        setTableName("acm");
    }



    @Override
    void setPositionList() {
        for (int i = 40; i <= 46; i++) {
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

        return "RIGHT JOIN  acm on common.variable_id=acm.acm_variable_id";
    }

    @Override
    public String createTableCmd() {
        return "CREATE TABLE acm(" +
                "acm_variable_id int unsigned  primary key," +
                "	acm_Log_bit_0_to_1 TEXT(50) NULL," +
                "	acm_Log_bit_1_to_0 TEXT(50) NULL," +
                "	acm_reserved TEXT(50) NULL," +
                "	acm_Authoisation_Level TEXT(50) NULL," +
                "	acm_acm_Alarm_Level TEXT(50) NULL," +
                "	acm_Alarm TEXT(50) NULL," +
                "	acm_Name_of_mask_bit TEXT(50) NULL," +
                "	acm_VCR TEXT(50) NULL);";
                
    }

    @Override
    public ArrayList<List<String>> getArrayList() {
        return this.acmList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> acmList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        acmList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            acmList.add(varexpArraySplit.get(i));
        }
        this.acmList.add(acmList);

    }

}
