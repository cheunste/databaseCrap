package com.company.pcvue.fields;

/**
 * This is a class that is responsible for creating references to other varexp variables without the other
 * actually interfacting with any of them
 * <p>
 * This is a factory class
 */
public class VarexpFactory {

    public VarexpVariable declareNewClass(String newVarexpVariable) {
        VarexpVariable varexpVariable = null;

        switch (newVarexpVariable) {
            case "ACM":
                return new ACM();

            case "ALA":
                return new ALA();

            case "ATS":
                return new ATS();

            case "BIT":
                return new BIT();

            case "CHR":
                return new CHR();

            case "CMD":
                return new CMD();

            case "CNT":
                return new CNT();

            case "CTV":
                return new CTV();

            case "CXT":
                return new CXT();

            case "REG":
                return new REG();

            case "TXT":
                return new TXT();

            case "TSH":
                return new TSH();

            //For equpiment
            case "E":
                return new Equipment();

            //For Internal
            case "I":
                return new Internal();

            //For external
            case "X":
                return new External();

            //For DDE
            case "D":
                return new DDE();

            //For OPC
            case "O":
                return new OPC();

            //For Lonwork
            case "L":
                return new LonWork();

            //For BACnet
            case "B":
                return new BACnet();

            //For Common
            case "common":
                return new Common();

            //For 60870-5-104
            case "4":
                return new IEC60870_Master();

            //For 61850
            case "8":
                return new IEC61850_Master();

            //For DNP3
            case "3":
                return new DNP3_Master();

            //For SNMP Manager
            case "S":
                return new SNMP();

            default:
                //Handle other unexpect cases here
                return null;

        }
    }

}
