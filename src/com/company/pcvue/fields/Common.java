package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 4/27/2018.
 */
public class Common extends VarexpVariable {

    List<String> stringList;
    private ArrayList<List<String>> commonList;
    private String tableName;

    //Constructor, I guess
    public Common() {
        this.commonList = new ArrayList<>();
        setTableName("Common");
        setPositionList();
    }

    @Override
    void setPositionList() {
        for (int i = 0; i <= 28; i++) {
            varexpPositionList.add(i);
        }
        varexpPositionList.add(39);
        for (int i = 129; i <= 143; i++) {
            varexpPositionList.add(i);
        }
        varexpPositionList.add(173);
        varexpPositionList.add(177);
        varexpPositionList.add(178);
        varexpPositionList.add(240);
    }

    //This returns a list of all fields that are combo boxes
    public ArrayList<String> getComboBoxItems() {
        ArrayList<String> comboBoxItems = new ArrayList();
        comboBoxItems.add("Inhibit flag");
        comboBoxItems.add("Simulated flag");
        comboBoxItems.add("Permanent flag");
        comboBoxItems.add("Source of Variable");
        comboBoxItems.add("Diffusion Indicator");
        comboBoxItems.add("Permanent subscription for mimics");
        comboBoxItems.add("Object Root");
        comboBoxItems.add("Variable with Extended Attributes");
        comboBoxItems.add("Remote Access");
        comboBoxItems.add("Is Initial Value");
        return comboBoxItems;
    }

    //This returns a map of all text field items, along with their varexp Array position
    public Map<String, Integer> getFieldMap() {
        Map<String, Integer> thisMap = new LinkedHashMap<>();
        thisMap.put("Variable Type", 2);
        thisMap.put("Variable Internal ID", 3);
        thisMap.put("1st element of Variable", 4);
        thisMap.put("2nd element of Variable", 5);
        thisMap.put("3rd element of Variable", 6);
        thisMap.put("4th element of Variable", 7);
        thisMap.put("5th element of Variable", 8);
        thisMap.put("6th element of Variable", 9);
        thisMap.put("7th-12th element of Variable", 10);
        thisMap.put("Variable description (1st lang)", 11);
        thisMap.put("Varialbe description (2nd lang)", 12);
        thisMap.put("Domain", 13);
        thisMap.put("Nature", 14);
        thisMap.put("Inhibit flag", 15);
        thisMap.put("Simulated flag", 16);
        thisMap.put("Permanent flag", 17);
        thisMap.put("Source of Variable", 18);
        thisMap.put("Diffusion Indicator", 19);
        thisMap.put("Number of station", 20);
        thisMap.put("reserved", 21);
        thisMap.put("Permanent subscription for mimics", 22);
        thisMap.put("Object Root", 23);
        thisMap.put("Variable with Extended Attributes", 24);
        thisMap.put("reserved2", 25);
        thisMap.put("Remote Access", 26);
        thisMap.put("reserved3", 27);
        thisMap.put("reserved4", 28);
        thisMap.put("Topology: Server list name", 29);
        thisMap.put("Topology: Client list name", 30);
        thisMap.put("Name of associated label", 41);
        thisMap.put("Binary attributes", 131);
        thisMap.put("Text Attribute 3", 132);
        thisMap.put("Text Attribute 4", 133);
        thisMap.put("Text Attribute 5", 134);
        thisMap.put("Text Attribute 6", 135);
        thisMap.put("Text Attribute 7", 136);
        thisMap.put("Text Attribute 8", 137);
        thisMap.put("Text Attribute 9", 138);
        thisMap.put("Text Attribute 10", 139);
        thisMap.put("Text Attribute 11", 140);
        thisMap.put("Text Attribute 12", 141);
        thisMap.put("Text Attribute 13", 142);
        thisMap.put("Text Attribute 14", 143);
        thisMap.put("Text Attribute 15", 144);
        thisMap.put("Text Attribute 16", 145);
        thisMap.put("Visualization Level", 175);
        thisMap.put("Is Initial Value", 179);
        thisMap.put("Initial Value", 180);
        thisMap.put("reserved5", 242);
        return thisMap;
    }

    public LinkedHashMap<String, VarexpTuple> getComboBoxChoices() {
        LinkedHashMap<String, VarexpTuple> comboBoxChoicesMap = new LinkedHashMap<>();
        comboBoxChoicesMap.put("Inhibit flag", new VarexpTuple(14, new String[]{"Yes", "NA"}, new String[]{"I", ""}));
        comboBoxChoicesMap.put("Simulated flag", new VarexpTuple(15, new String[]{"Yes", "NA"}, new String[]{"S", ""}));
        comboBoxChoicesMap.put("Permanent flag", new VarexpTuple(16, new String[]{"Yes", "NA"}, new String[]{"P", ""}));
        comboBoxChoicesMap.put("Source of Variable",
                new VarexpTuple(17,
                        new String[]{"Equipment", "Internal", "External",
                                "DDE", "OPC", "LonWorks", "BACnet", "60870-5-104", "61850", "DNP3", "SNMP"},
                        new String[]{"E", "I", "X", "D", "O", "L", "B", "4", "8", "3", "S"}));
        comboBoxChoicesMap.put("Diffusion Indicator",
                new VarexpTuple(18,
                        new String[]{"N", "Y"},
                        new String[]{"0", "1"}));
        comboBoxChoicesMap.put("Permanent subscription for mimics",
                new VarexpTuple(21,
                        new String[]{"0: All Stations", "1: None", "2: Server station"},
                        new String[]{"0", "1", "2"}
                ));
        comboBoxChoicesMap.put("Object Root",
                new VarexpTuple(22,
                        new String[]{"Local", "Customized Layer"},
                        new String[]{"0", "1"}));

        comboBoxChoicesMap.put("Variable with Extended Attributes",
                new VarexpTuple(23,
                        new String[]{"Columns 130 to 140", "Columns 137 to 146"},
                        new String[]{"0", "1"}));
        comboBoxChoicesMap.put("Remote Access",
                new VarexpTuple(25,
                        new String[]{"N", "Y"},
                        new String[]{"0", "1"}));
        comboBoxChoicesMap.put("Is Initial Value",
                new VarexpTuple(178,
                        new String[]{"N", "Y"},
                        new String[]{"0", "1"}));
        return comboBoxChoicesMap;
    }

    @Override
        //Should never be used for common;
    String empty() {
        return "";
    }

    @Override
        //Shouldn't ever be used for common
    String getJoinCmd() {
        return "";
    }

    @Override
    public String createTableCmd() {
        return
                "CREATE TABLE common(" +
                        "variable_id	 			INT UNSIGNED  PRIMARY KEY," +
                        "variable_type	 		    TEXT(50) NOT NULL	," +
                        "internal_id	 			TEXT(50) NOT NULL	," +
                        "1st_element	 			TEXT(50) NOT NULL	," +
                        "2nd_element	 			TEXT(50) NOT NULL	," +
                        "3rd_element	 			TEXT(50) NULL	," +
                        "4th_element	 			TEXT(50) NULL	," +
                        "5th_element	 			TEXT(50) NULL	," +
                        "6th_element	 			TEXT(50) NULL	," +
                        "7th_to_12th	 			TEXT(50) NULL	," +
                        "desc_1st_lang	 		    TEXT(50) NOT NULL	," +
                        "desc_2nd_lang	 		    TEXT(50) NOT NULL	," +
                        "domain	 				    TEXT(50) NOT NULL	," +
                        "nature	 				    TEXT(50) NOT NULL	," +
                        "inhibit_flag	 		    TEXT(50)  NULL	," +
                        "simulated_flag	 		    TEXT(50)  NULL	," +
                        "permanent_flag	 		    TEXT(50)  NULL	," +
                        "source	 				    TEXT(50) NOT  NULL	," +
                        "diffusion_indicator	 	TEXT(50) NOT NULL	," +
                        "station	 				TEXT(50) NOT NULL	," +
                        "reserved	 			    TEXT(50) NOT NULL	," +
                        "subscription	 		    TEXT(50) NOT NULL	," +
                        "object_root	 			TEXT(50) NOT NULL	," +
                        "extended_attribute	 	    TEXT(50) NOT NULL	," +
                        "reserved2	 			    TEXT(50) NOT NULL	," +
                        "remote_access	 		    TEXT(50) NOT NULL	," +
                        "reserved3	 			    TEXT(50) NOT NULL	," +
                        "reserved4	 			    TEXT(50) NOT NULL	," +
                        "topology_server	 		TEXT(50) NOT NULL	," +
                        "topology_client	 		TEXT(50) NULL	," +
                        "name_of_associated_label	TEXT(50) NULL	," +
                        "binary_attributes	 	    TEXT(50) NULL	," +
                        "Text_attribute_3	 	    TEXT(50) NULL	," +
                        "Text_attribute_4	 	    TEXT(50) NULL	," +
                        "Text_attribute_5	 	    TEXT(50) NULL	," +
                        "Text_attribute_6	 	    TEXT(50) NULL	," +
                        "Text_attribute_7	 	    TEXT(50) NULL	," +
                        "Text_attribute_8	 	    TEXT(50) NULL	," +
                        "Text_attribute_9	 	    TEXT(50) NULL	," +
                        "Text_attribute_10	 	    TEXT(5) NOT NULL	," +
                        "Text_attribute_11	 	    TEXT(5) NOT NULL	," +
                        "Text_attribute_12	 	    TEXT(5) NOT NULL	," +
                        "Text_attribute_13	 	    TEXT(5) NOT NULL	," +
                        "Text_attribute_14	 	    TEXT(5) NOT NULL	," +
                        "Text_attribute_15	 	    TEXT(5) NOT NULL	," +
                        "Text_attribute_16	 	    TEXT(5) NOT NULL	," +
                        "Visualization_level	 	TEXT(50) NULL	," +
                        "is_initial_value	 	    TEXT(50) NULL	," +
                        "initial_value	 		    TEXT(50) NULL	," +
                        "reserved5	 			    TEXT(50) NULL	" +
                        ");";


    }

    @Override
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> commonArrayList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        //Add the index Number. This will be used as the ID reference to the DB
        commonArrayList.add("" + dbIndex);

        for (int i : varexpPositionList) {
            commonArrayList.add(varexpArraySplit.get(i).trim());
        }

        this.commonList.add(commonArrayList);
        //this.acmList.add(acmList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.commonList;
    }

}
