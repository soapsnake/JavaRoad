package com.soapsnake.pattern.structurals.mediatorpattern;

public class Client {

    public static void main(String[] args) {
        SellerColleague colleagure = new SellerColleague("normal");
        colleagure.showType();
        SellerColleague colleague = new SellerColleague("test2");
        colleague.showType();
    }
}
