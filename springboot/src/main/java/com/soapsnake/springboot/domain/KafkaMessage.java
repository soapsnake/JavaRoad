package com.soapsnake.springboot.domain;

import java.util.Date;

import lombok.Data;

@Data
public class KafkaMessage {

    private Long id;

    private String msg;

    private Date sendTime;
}
