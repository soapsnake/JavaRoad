package com.soapsnake.springboot.domain;

import lombok.Data;

import java.util.Date;

@Data
public class KafkaMessage {

    private Long id;

    private String msg;

    private Date sendTime;
}
