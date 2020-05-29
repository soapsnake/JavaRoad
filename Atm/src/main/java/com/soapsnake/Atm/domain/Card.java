package com.soapsnake.Atm.domain;

import java.util.Date;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-28
 */
public abstract class Card {

    private String cardNumber;
    private String customerName;
    private Date cardExpiry;
    private int pin;

    public abstract Address getBillingAddress();
}
