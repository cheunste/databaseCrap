package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class BIT extends VarexpVariable {
    private ArrayList<List<String>> bitList;

    public BIT() {
        this.bitList = new ArrayList<>();
        setTableName("bit");
        setPositionList();
    }


    @Override
    void setPositionList() {
        for (int i = 40; i <= 42; i++) {
            varexpPositionList.add(i);
        }
        varexpPositionList.add(156);
    }

    @Override
    String empty() {
        String emptyString = "";

        for (int i : varexpPositionList) {

            emptyString += "";
        }
        return emptyString;
    }

    @Override
    String getJoinCmd() {
        return "RIGHT JOIN bit on common.variable_id = bit.bit_variable_id";
    }

    @Override
    public String createTableCmd() {
        return
                "CREATE TABLE bit(" +
                        "bit_variable_id int unsigned  primary key," +
                        "bit_Log_bit_0_to_1 TEXT(50) NULL," +
                        "bit_Log_bit_1_to_0 TEXT(50) NULL," +
                        "bit_reserved TEXT(50) NULL," +
                        "bit_VCR TEXT(50) NULL);";

    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> bitList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        bitList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            bitList.add(varexpArraySplit.get(i).trim().toString());
        }
        this.bitList.add(bitList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.bitList;
    }
}
