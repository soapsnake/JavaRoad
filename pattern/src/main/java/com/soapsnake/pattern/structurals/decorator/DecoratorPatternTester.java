package com.soapsnake.pattern.structurals.decorator;

public class DecoratorPatternTester {


    public static void main(String[] args) {
        Account basic = new SavingAccount();
        System.out.println(basic.getTotalBenefits());

        Account seniorAccount = new SeniorCitizen(basic);
        //seniorAccount除了具有basic的功能,还将附带额外的功能
        System.out.println(seniorAccount.getTotalBenefits());

        Account privilegeAccount = new PrivilegeCitizen(basic);
        System.out.println(privilegeAccount.getTotalBenefits());
    }
}
