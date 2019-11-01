package com.soapsnake.pattern.structurals.decorator;

public class SeniorCitizen extends AccountDecorator {
    Account account;

    public SeniorCitizen(Account account) {
        super();
        this.account = account;
    }

    @Override
    String applyOtherBenefits() {
        //如果是SeniorCitizen那么有额外的1000美元商业保险
        return "an medical insurance of up to $1,000 for Senior Citizen";
    }

    @Override
    public String getTotalBenefits() {
        return account.getTotalBenefits() + " other benefits are" +
                this.applyOtherBenefits();
    }
}
