package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */

public class TXT extends VarexpVariable {
    private ArrayList<List<String>> txtList;

    public TXT() {
        this.txtList = new ArrayList<>();
        setTableName("txt");
        setPositionList();
    }


    @Override
    void setPositionList() {
        varexpPositionList.add(99);
        varexpPositionList.add(100);
        varexpPositionList.add(102);
        varexpPositionList.add(156);

    }

    @Override
    String empty() {
        return "";
    }

    @Override
    String getJoinCmd() {
        return "RIGHT JOIN txt on common.variable_id = txt.txt_variable_id";
    }

    @Override
    public String createTableCmd() {
        return
                "CREATE TABLE txt(" +
                        "txt_variable_id int unsigned  primary key," +
                        "	txt_Maximum_number_of_characters TEXT(50) NULL," +
                        "	txt_write_authorised TEXT(50) NULL," +
                        "	txt_display_format TEXT(50) NULL," +
                        "	txt_VCR TEXT(50) NULL);";

    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> txtList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        txtList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            txtList.add(varexpArraySplit.get(i).trim());
        }
        this.txtList.add(txtList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.txtList;
    }
}
