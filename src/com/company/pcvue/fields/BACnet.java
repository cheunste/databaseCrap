package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class BACnet extends VarexpVariable {

    private ArrayList<List<String>> bacList;

    public BACnet() {
        this.bacList = new ArrayList<>();
        setTableName("bac");
        setPositionList();
    }


    @Override
    void setPositionList() {
        for (int i = 162; i <= 172; i++) {
            getVarexpPositionList().add(i);
        }
        getVarexpPositionList().add(203);
        getVarexpPositionList().add(204);
        getVarexpPositionList().add(217);
        getVarexpPositionList().add(218);
    }

    @Override
    String empty() {
        String emptyString = "";
        for (int i : getVarexpPositionList()) {
            emptyString += ",";
        }

        return emptyString;
    }

    @Override
    String getJoinCmd() {
        return "RIGHT JOIN  bac on common.variable_id=bac.bac_variable_id";
    }

    ;

    public ArrayList<List<String>> getArrayList() {
        return this.bacList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> bacList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        bacList.add("" + dbIndex);

        for (int i : getVarexpPositionList()) {
            bacList.add(varexpArraySplit.get(i));
        }

        this.bacList.add(bacList);
    }
}
