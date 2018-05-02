package com.company.pcvue.fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 5/2/2018.
 */
public class ACM extends VarexpVariable {
    public List<String> acmList;

    @Override
    void VarexpVariable() {

    }

    @Override
    void insertToDB() {

    }

    @Override
    public void setArrayList(String varexpString) {
        setvarexpArrayList(varexpString);
        List<String> commonArrayList = new ArrayList<>();
        List<String> varexpArraySplit = this.getVarexpList();

        for (int i = 40; i <= 46; i++) {
            commonArrayList.add(varexpArraySplit.get(i).toString());
        }
        commonArrayList.add(varexpArraySplit.get(156).toString());

        this.acmList = commonArrayList;

    }
}
