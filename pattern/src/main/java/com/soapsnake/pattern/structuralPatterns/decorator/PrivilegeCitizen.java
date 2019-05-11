package com.soapsnake.pattern.structuralPatterns.decorator;

public class PrivilegeCitizen extends AccountDecorator {

    Account account;

    public PrivilegeCitizen(Account account) {
        super();
        this.account = account;
    }

    @Override
    String applyOtherBenefits() {
        //PrivilegeCitizen可以享受额外的1600商业保险和84块的透支余额
        return " an accident insurance of up to $1,600 and " +
                " an overdraft facility of $84";    }

    @Override
    public String getTotalBenefits() {
        return account.getTotalBenefits() + " other benefits are"
                +applyOtherBenefits();
    }
}
