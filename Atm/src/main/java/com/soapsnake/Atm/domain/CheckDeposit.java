package com.soapsnake.Atm.domain;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-28
 */
public abstract class CheckDeposit extends Deposit {
    private String checkNumber;
    private String bankCode;

    public abstract String getCheckNumber();
}
