package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class CMD extends VarexpVariable {
    public ArrayList<List<String>> cmdList;

    public CMD() {
        this.cmdList = new ArrayList<>();
        setTableName("cmd");
    }


    @Override
    String insertToDB() {
        return null;
    }

    @Override
    String empty() {
        String emptyString = "";
        for (int i = 40; i <= 42; i++) {
            emptyString += ",";
        }

        emptyString += ",";
        return emptyString;
    }

    @Override
    String getJoinCmd() {
        return "RIGHT JOIN cmd on common.variable_id = cmd.cmd_variable_id";
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> cmdList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        cmdList.add("" + dbIndex);
        for (int i = 40; i <= 43; i++) {
            cmdList.add(varexpArraySplit.get(i).toString());
        }

        cmdList.add(varexpArraySplit.get(156).toString());

        this.cmdList.add(cmdList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.cmdList;
    }
}
