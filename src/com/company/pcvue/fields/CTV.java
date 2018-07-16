package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class CTV extends VarexpVariable {
    private ArrayList<List<String>> ctvList;

    public CTV() {
        this.ctvList = new ArrayList<>();
        setTableName("ctv");
        setPositionList();
    }


    @Override
    void setPositionList() {
        for (int i = 59; i <= 66; i++) {
            varexpPositionList.add(i);
        }
        for (int i = 69; i <= 71; i++) {
            varexpPositionList.add(i);
        }
        varexpPositionList.add(155);
        varexpPositionList.add(156);
    }

    @Override
    String empty() {
        return "";
    }

    @Override
    String getJoinCmd() {
        return "RIGHT JOIN ctv on common.variable_id = ctv.ctv_variable_id";
    }

    @Override
    public String createTableCmd() {
        return "CREATE TABLE ctv(" +
                "	ctv_variable_id int unsigned  primary key," +
                "	ctv_Measurement_Units TEXT(50) NULL," +
                "	ctv_Deadband TEXT(50) NULL," +
                "	ctv_Minimium_display_value TEXT(50) NULL," +
                "	ctv_Maximum_display_value TEXT(50) NULL," +
                "	ctv_Scaling TEXT(50) NULL," +
                "	ctv_Minimum_equipment_value TEXT(50) NULL," +
                "	ctv_Maximum_equipment_value TEXT(50) NULL," +
                "	ctv_Display_format TEXT(50) NULL," +
                "	ctv_Minimum_control_value TEXT(50) NULL," +
                "	ctv_maximum_control_value TEXT(50) NULL," +
                "	ctv_authorisation_level TEXT(50) NULL," +
                "	ctv_deadband_type TEXT(50) NULL," +
                "	ctv_VCR TEXT(50) NULL);";

    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> ctvList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        ctvList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            ctvList.add(varexpArraySplit.get(i));
        }

        this.ctvList.add(ctvList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.ctvList;
    }
}
