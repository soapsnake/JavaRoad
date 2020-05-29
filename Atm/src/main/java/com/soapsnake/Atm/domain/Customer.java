package com.soapsnake.Atm.domain;

import javax.smartcardio.Card;

import com.soapsnake.Atm.enums.CustomerStatus;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-28
 */
public abstract class Customer {

    private String name;
    private String email;
    private String phone;
    private Address address;
    private CustomerStatus status;

    private Card card;
    private Account account;

    public abstract boolean makeTransaction(Transaction transaction);
    public abstract Address getBillingAddress();
}
