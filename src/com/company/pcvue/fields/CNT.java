package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/28/2018.
 */
public class CNT extends VarexpVariable {
    private ArrayList<List<String>> cntList;

    public CNT() {
        this.cntList = new ArrayList<>();
        setTableName("cnt");
        setPositionList();
    }

    @Override
    void setPositionList() {
        for (int i = 59; i <= 66; i++) {
            varexpPositionList.add(i);
        }
        for (int i = 79; i <= 84; i++) {
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

        return "RIGHT JOIN  cnt on common.variable_id=cnt.cnt_variable_id";
    }

    @Override
    public String createTableCmd() {
        return
                "CREATE TABLE cnt(" +
                        "	cnt_variable_id int unsigned  primary key," +
                        "	cnt_Measurement_Units TEXT(50) NULL," +
                        "	cnt_Deadband TEXT(50) NULL," +
                        "	cnt_Minimium_display_value TEXT(50) NULL," +
                        "	cnt_Maximum_display_value TEXT(50) NULL," +
                        "	cnt_Scaling TEXT(50) NULL," +
                        "	cnt_Minimum_equipment_value TEXT(50) NULL," +
                        "	cnt_Maximum_equipment_value TEXT(50) NULL," +
                        "	cnt_Display_format TEXT(50) NULL," +
                        "	cnt_Size_of_step TEXT(50) NULL," +
                        "	cnt_Decrement_increment_counter TEXT(50) NULL," +
                        "	cnt_Name_of_bit_on_which_to_count TEXT(50) NULL," +
                        "	cnt_Count_on_transition TEXT(50) NULL," +
                        "	cnt_Name_of_bit_on_which_to_reset_count TEXT(50) NULL," +
                        "	cnt_reset_on_transition TEXT(50) NULL," +
                        "	cnt_deadband_type TEXT(50) NULL," +
                        "	cnt_VCR TEXT(50) NULL);";
                        
    }

    public ArrayList<List<String>> getArrayList() {
        return this.cntList;
    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> cntList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        cntList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            cntList.add(varexpArraySplit.get(i).toString());
        }

        this.cntList.add(cntList);

    }
}

