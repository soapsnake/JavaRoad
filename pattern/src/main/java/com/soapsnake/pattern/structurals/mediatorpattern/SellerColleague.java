package com.soapsnake.pattern.structurals.mediatorpattern;

public class SellerColleague extends AbstractColleague {

    SellerColleague(String type) {
        super(type);
    }

    public void showType() {
        System.out.println(this.type);
    }

}
