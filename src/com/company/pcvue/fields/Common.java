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
        List<String> varexpArraySplit = this.getVarexpList();
    }

    public Common() {
        setTableName("Common");
    }

    //Constructor, I guess
    @Override
    void VarexpVariable() {
    }

    @Override
    void insertToDB() {
        String query = this.getInsertSQLCmd() + "";

    }

    @Override
    public void setArrayList(String varexpString) {
        setvarexpArrayList(varexpString);
        List<String> commonArrayList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        /*
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
        */
        commonArrayList.add(varexpArraySplit.get(0).toString());
        commonArrayList.add(varexpArraySplit.get(1).toString());
        commonArrayList.add(varexpArraySplit.get(2).toString());
        commonArrayList.add(varexpArraySplit.get(3).toString());
        commonArrayList.add(varexpArraySplit.get(4).toString());
        commonArrayList.add(varexpArraySplit.get(5).toString());
        commonArrayList.add(varexpArraySplit.get(6).toString());
        commonArrayList.add(varexpArraySplit.get(7).toString());
        commonArrayList.add(varexpArraySplit.get(8).toString());
        commonArrayList.add(varexpArraySplit.get(9).toString());
        commonArrayList.add(varexpArraySplit.get(10).toString());
        commonArrayList.add(varexpArraySplit.get(11).toString());
        commonArrayList.add(varexpArraySplit.get(12).toString());
        commonArrayList.add(varexpArraySplit.get(13).toString());
        commonArrayList.add(varexpArraySplit.get(14).toString());
        commonArrayList.add(varexpArraySplit.get(15).toString());
        commonArrayList.add(varexpArraySplit.get(16).toString());
        commonArrayList.add(varexpArraySplit.get(17).toString());
        commonArrayList.add(varexpArraySplit.get(18).toString());
        commonArrayList.add(varexpArraySplit.get(19).toString());
        commonArrayList.add(varexpArraySplit.get(20).toString());
        commonArrayList.add(varexpArraySplit.get(21).toString());
        commonArrayList.add(varexpArraySplit.get(22).toString());
        commonArrayList.add(varexpArraySplit.get(23).toString());
        commonArrayList.add(varexpArraySplit.get(24).toString());
        commonArrayList.add(varexpArraySplit.get(25).toString());
        commonArrayList.add(varexpArraySplit.get(26).toString());
        commonArrayList.add(varexpArraySplit.get(27).toString());
        commonArrayList.add(varexpArraySplit.get(28).toString());
        commonArrayList.add(varexpArraySplit.get(39).toString());
        commonArrayList.add(varexpArraySplit.get(129).toString());
        commonArrayList.add(varexpArraySplit.get(130).toString());
        commonArrayList.add(varexpArraySplit.get(131).toString());
        commonArrayList.add(varexpArraySplit.get(132).toString());
        commonArrayList.add(varexpArraySplit.get(133).toString());
        commonArrayList.add(varexpArraySplit.get(134).toString());
        commonArrayList.add(varexpArraySplit.get(135).toString());
        commonArrayList.add(varexpArraySplit.get(136).toString());
        commonArrayList.add(varexpArraySplit.get(137).toString());
        commonArrayList.add(varexpArraySplit.get(138).toString());
        commonArrayList.add(varexpArraySplit.get(139).toString());
        commonArrayList.add(varexpArraySplit.get(140).toString());
        commonArrayList.add(varexpArraySplit.get(141).toString());
        commonArrayList.add(varexpArraySplit.get(142).toString());
        commonArrayList.add(varexpArraySplit.get(143).toString());
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
