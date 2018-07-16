package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class REG extends VarexpVariable {
    private ArrayList<List<String>> regList;

    public REG() {
        this.regList = new ArrayList<>();
        setTableName("reg");
        setPositionList();
    }

    @Override
    void setPositionList() {
        for (int i = 59; i <= 66; i++) {
            varexpPositionList.add(i);
        }
        varexpPositionList.add(155);
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
        return "RIGHT JOIN reg on common.variable_id = reg.reg_variable_id";
    }

    @Override
    public String createTableCmd() {

        return "CREATE TABLE reg(" +
                "	reg_variable_id int unsigned  primary key," +
                "	reg_Measurement_Units TEXT(50) NULL," +
                "	reg_Deadband TEXT(50) NULL," +
                "	reg_Minimium_display_value TEXT(50) NULL," +
                "	reg_Maximum_display_value TEXT(50) NULL," +
                "	reg_Scaling TEXT(50) NULL," +
                "	reg_Minimum_equipment_value TEXT(50) NULL," +
                "	reg_Maximum_equipment_value TEXT(50) NULL," +
                "	reg_Display_format TEXT(50) NULL," +
                "	reg_Deadband_type TEXT(50) NULL," +
                "	reg_VCR TEXT(50) NULL);";

    }

    public ArrayList<List<String>> getArrayList() {
        return this.regList;
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

        this.regList.add(acmList);
    }
}
