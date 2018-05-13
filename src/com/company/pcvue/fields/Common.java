package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 4/27/2018.
 */
public class Common extends VarexpVariable {

    public List<String> commonList;
    List<String> stringList;
    private String tableName;
    private String[] commonArray;

    //Constructor
    public Common(String varexpString) {
        setvarexpArrayList(varexpString);
        setTableName("Common");
    }

    public Common() {
        setTableName("Common");
    }

    //Constructor, I guess
    @Override
    void VarexpVariable() {
    }

    @Override
    String insertToDB() {
        String query = this.getInsertSQLCmd() + "";
        return query;

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
    public void setArrayList(String varexpString, int dbIndex) {
        setvarexpArrayList(varexpString);
        List<String> commonArrayList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        //Add the index Number. This will be used as the ID reference to the DB
        commonArrayList.add("" + dbIndex);

        for (int i = 0; i<=28; i++){
            commonArrayList.add(varexpArraySplit.get(i).toString());
        }
        commonArrayList.add(varexpArraySplit.get(39).toString());
        for (int i = 129; i<=143; i++){
            commonArrayList.add(varexpArraySplit.get(i).toString());
        }
        commonArrayList.add(varexpArraySplit.get(173).toString());
        commonArrayList.add(varexpArraySplit.get(177).toString());
        commonArrayList.add(varexpArraySplit.get(178).toString());
        commonArrayList.add(varexpArraySplit.get(240).toString());

        this.commonList = commonArrayList;
        //this.acmList.add(acmList);
    }

    public List<String> getCommonList() {
        return this.commonList;

    }

}
