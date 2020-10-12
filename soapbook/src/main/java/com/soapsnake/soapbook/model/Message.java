package com.soapsnake.soapbook.model;

/**
 *
 * Created on 2020-06-01
 */
public class Message {
    private Integer messageId;
    private Member[] sentTo;
    private String messageBody;
    private byte[] media;

    public boolean addMember(Member member) {
        return false;
    }
}
