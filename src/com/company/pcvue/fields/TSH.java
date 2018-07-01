package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class TSH extends VarexpVariable {
    private ArrayList<List<String>> tshList;

    public TSH() {
        this.tshList = new ArrayList<>();
        setTableName("tsh");
        setPositionList();
    }


    @Override
    void setPositionList() {
        for (int i = 40; i <= 42; i++) {
            varexpPositionList.add(i);
        }
        for (int i = 49; i <= 54; i++) {
            varexpPositionList.add(i);
        }
        varexpPositionList.add(156);
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

        return "RIGHT JOIN  tsh on common.variable_id=tsh.tsh_variable_id";
    }

    @Override
    public String createTableCmd() {

        return "CREATE TABLE tsh(" +
                "tsh_variable_id int unsigned  primary key," +
                "	tsh_Log_bit_0_to_1 TEXT(50) NULL," +
                "	tsh_Log_bit_1_to_0 TEXT(50) NULL," +
                "	tsh_reserved TEXT(50) NULL," +
                "	tsh_Hysteresis TEXT(50) NULL," +
                "	tsh_Value TEXT(50) NULL," +
                "	tsh_Threshold TEXT(50) NULL," +
                "	tsh_Name_of_REG TEXT(50) NULL," +
                "	tsh_Threshold_system TEXT(50) NULL," +
                "	tsh_Threshold_type TEXT(50) NULL," +
                "	tsh_VCR TEXT(50) NULL ," +
                "foreign key fk_equipment(tsh_variable_id) references common(variable_id));";
    }

    public ArrayList<List<String>> getArrayList() {
        return this.tshList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> tshList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        tshList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            tshList.add(varexpArraySplit.get(i));
        }
        this.tshList.add(tshList);

    }
}
