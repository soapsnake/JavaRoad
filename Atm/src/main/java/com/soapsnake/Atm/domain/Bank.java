package com.soapsnake.Atm.domain;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-28
 */
public abstract class Bank {

    private String name;
    private String bankCode;

    public abstract String getBankCode();
    public abstract boolean addATM();
}
