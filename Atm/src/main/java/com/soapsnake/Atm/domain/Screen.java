package com.soapsnake.Atm.domain;

import com.soapsnake.Atm.enums.TransactionType;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-28
 */
public abstract class Screen {

    public abstract boolean showMessage(String message);
    public abstract TransactionType getInput();
}
