package com.soapsnake.soapbook.model;

import com.soapsnake.Atm.domain.Customer;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-01
 */
public class Admin extends Person {
    public boolean blockUser(Customer customer) {
        return false;
    }

    public boolean unblockUser(Customer customer) {
        return false;
    }

    public boolean enablePage(Page page) {
        return false;
    }

    public boolean disablePage(Page page) {
        return false;
    }
}
