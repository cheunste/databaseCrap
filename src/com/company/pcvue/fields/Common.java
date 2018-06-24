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

    //Constructor, I guess
    public Common() {
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

        for (int i : varexpPositionList) {
            commonArrayList.add(varexpArraySplit.get(i));
        }

        this.commonList = commonArrayList;
        //this.acmList.add(acmList);
    }

    public List<String> getCommonList() {
        return this.commonList;

    }

}
