package com.soapsnake.algorithms.systemdesign.oo.deckofcards.pojo;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2018/12/9 21:58
 */
public enum Suit {
    HEART(0, "HEART", "黑桃"),
    DIAMOND(1, "DIAMOND", "红桃"),
    CLUBS(2, "CLUBS", "方片"),
    SPADE(3, "SPADE", "梅花");

    private int code;
    private String name;
    private String desc;

    Suit(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

}
