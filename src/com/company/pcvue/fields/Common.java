package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

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
            commonArrayList.add(varexpArraySplit.get(i));
        }

        this.commonList.add(commonArrayList);
        //this.acmList.add(acmList);
    }

    public ArrayList<List<String>> getArrayList() {
        return this.commonList;
    }

}
