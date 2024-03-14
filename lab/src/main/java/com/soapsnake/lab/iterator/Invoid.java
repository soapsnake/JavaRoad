package com.soapsnake.lab.iterator;

import java.text.DateFormat;

/**
 * Created on 2023-12-17
 */
public class Invoid {

    static String _oldId = "0";
    protected static String formatId(String oldId) {

        _oldId = oldId;
        return _oldId + "_Invoice";
    }
}

class SalesINvoice extends  Invoid {

    public static String formatId(String oldId) {
        return _oldId + "_salesInvoice";
    }
}

class Test {
    public static void main(String[] args) {
        System.out.println(DateFormat.getDateInstance().toString());
    }
}


