package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class DDE extends VarexpVariable {
    private ArrayList<List<String>> ddeList;

    public DDE() {
        this.ddeList = new ArrayList<>();
        setTableName("dde");
        setPositionList();
    }

    @Override
    void setPositionList() {
        for (int i = 119; i <= 126; i++) {
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

        return "RIGHT JOIN  dde on common.variable_id=dde.dde_variable_id";
    }

    @Override
    public String createTableCmd() {

        return "CREATE TABLE dde(" +
                "dde_variable_id int unsigned  primary key," +
                "dde_Conversation_name TEXT(50) NOT NULL," +
                "dde_Item_Name TEXT(50) NOT NULL," +
                "dde_reserved6 TEXT(50) NOT NULL," +
                "dde_reserved7 TEXT(50) NOT NULL," +
                "dde_Item_format TEXT(50) NOT NULL," +
                "dde_reserved TEXT(50) NOT NULL," +
                "dde_Auto_Format_Flag TEXT(50) NOT NULL," +
                "dde_Label  TEXT(50) NOT NULL);"; 
               

    }

    public ArrayList<List<String>> getArrayList() {
        return this.ddeList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> ddeList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        ddeList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            ddeList.add(varexpArraySplit.get(i));
        }

        this.ddeList.add(ddeList);

    }
}
