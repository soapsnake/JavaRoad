package com.ld.mq.kafka.pojos;

import java.util.UUID;

public class Company {

    private String name;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();

        System.out.println("uuid: "+ uuid + ">>>>" + uuid.length());

    }
}
