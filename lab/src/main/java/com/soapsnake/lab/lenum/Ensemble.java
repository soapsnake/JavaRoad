package com.soapsnake.lab.lenum;

/**
 * Created by soapsnake on 2017/5/16.
 */
public enum Ensemble {

    SOLO, DUET, TRIO, QUARTET, QUINTET,
    SEXTET, SEPTET, OCTET, NONET, DECTET;

    public int numberOfMusicians() {
        //ordinal()是指枚举的序号
        return ordinal() + 1;
    }

}
