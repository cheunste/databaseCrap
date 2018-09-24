package com.company.pcvue.fields;

import java.util.List;

public class VarexpReconstructor {


    /**
     * This function reconstructs a Varexp Array from the database. It takes three Strings for each piece of the varexp.
     *
     * @param commonVariable  The common varexp String from the database
     * @param sourceVariable  The source String. Can vary in length depending on variable
     * @param commandVariable The command String. Can vary in length depending on variable
     */
    public static List<String> reconstructVarexpArray(String commonVariable, String sourceVariable, String commandVariable) {


        /*
        TODO:
        1) Populate the reconstructedVarexpArray with empty Strings. Remember the FIELD_NUM member variable
        2) Fetch the position array (varexpPositionList) from the common Varexp subclass and then stuff the common
        variables into it
        3) Determine the source and command that was set up in Coomon. Remember that Source and Command can be empty
        if it is a new variable and user haven't implemented it yet
        4)
         */

        Common common = new Common();

        common.initializeVarexpArray();
        //Create an empty Varexp array
        List<String> reconstructedVarexpArray = common.getVarexpList();

        //Get the positino list of the Common Variable
        List<Integer> commonPositionList = common.getVarexpPositionList();

        //Get the Common Variable String called from the DB and split it to a list
        String[] commonFieldArray = commonVariable.split(",");

        int tempIndexCounter = 0;

        //Traverse the common variables pulled from the database
        for (String commonField : commonFieldArray) {
            //And insert them into the reconstructedVarexpArray
            reconstructedVarexpArray.set(commonPositionList.get(tempIndexCounter), commonField);
            //Increment the temp counter
            tempIndexCounter++;
        }

        //In the reconstructed Variable
        return reconstructedVarexpArray;
    }
}
