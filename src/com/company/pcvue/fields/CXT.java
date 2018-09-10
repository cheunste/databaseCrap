package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class CXT extends VarexpVariable {

    private ArrayList<List<String>> cxtList;

    public CXT() {
        this.cxtList = new ArrayList<>();
        setTableName("cxt");
        setPositionList();
    }


    @Override
    void setPositionList() {
        for (int i = 99; i <= 102; i++) {
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
        return "RIGHT JOIN cxt on common.variable_id = cxt.cxt_variable_id";
    }

    @Override
    public String createTableCmd() {
        return "CREATE TABLE cxt(" +
                "	cxt_variable_id int unsigned  primary key," +
                "	cxt_Maximum_number_of_characters TEXT(50) NULL," +
                "	cxt_write_authorised TEXT(50) NULL," +
                "	cxt_authorisation_level TEXT(50) NULL," +
                "	cxt_display_format TEXT(50) NULL, cxt_VCR TEXT(50) NULL);";

    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> cxtList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        cxtList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            cxtList.add(varexpArraySplit.get(i).trim().toString());
        }
        this.cxtList.add(cxtList);
    }


    public ArrayList<List<String>> getArrayList() {
        return this.cxtList;
    }
}
