package com.soapsnake.lab.lenum;

/**
 *
 * Created on 2020-03-11
 */
public enum TestEnum {

    CREATE("创建"),
    UPDATE("修改");

    String desc;

    TestEnum(String desc) {
        this.desc = desc;
    }


}
