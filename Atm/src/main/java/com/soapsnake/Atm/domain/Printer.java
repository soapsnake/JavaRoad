package com.soapsnake.Atm.domain;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-28
 */
public abstract class Printer {

    public abstract boolean printReceipt(Transaction transaction);
}
