package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/20/2018.
 */
public class CHR extends VarexpVariable {

    private ArrayList<List<String>> chrList;

    public CHR() {
        this.chrList = new ArrayList<>();
        setTableName("chr");
        setPositionList();
    }


    @Override
    void setPositionList() {

        for (int i = 59; i <= 66; i++) {
            varexpPositionList.add(i);
        }
        for (int i = 89; i <= 94; i++) {
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
        return "RIGHT JOIN chr on common.variable_id = chr.chr_variable_id";
    }

    @Override
    public String createTableCmd() {
        return
                "CREATE TABLE chr(" +
                        "chr_variable_id int unsigned  primary key," +
                        "	chr_Measurement_Units TEXT(50) NULL," +
                        "	chr_Deadband TEXT(50) NULL," +
                        "	chr_Minimium_display_value TEXT(50) NULL," +
                        "	chr_Maximum_display_value TEXT(50) NULL," +
                        "	chr_Scaling TEXT(50) NULL," +
                        "	chr_Minimum_equipment_value TEXT(50) NULL," +
                        "	chr_Maximum_equipment_value TEXT(50) NULL," +
                        "	chr_Display_format TEXT(50) NULL," +
                        "	chr_Chronometer_period TEXT(50) NULL," +
                        "	chr_Chronometer_increment TEXT(50) NULL," +
                        "	chr_chrono_enable_bit_variable TEXT(50) NULL," +
                        "	chr_Enable_on TEXT(50) NULL," +
                        "	chr_chrono_reset_bit_variable TEXT(50) NULL," +
                        "	chr_reset_on_transition TEXT(50) NULL," +
                        "	chr_deadband_type TEXT(50) NULL," +
                        "	chr_VCR TEXT(50) NULL);";

    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> chrList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        chrList.add("" + dbIndex);
        for (int i : varexpPositionList) {
            chrList.add(varexpArraySplit.get(i).trim().toString());
        }
        this.chrList.add(chrList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.chrList;
    }
}
