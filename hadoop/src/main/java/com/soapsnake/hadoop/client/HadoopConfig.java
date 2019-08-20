package com.soapsnake.hadoop.client;


import org.apache.hadoop.conf.Configuration;

public class HadoopConfig {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.addResource("configuration-1.xml");
        System.out.println(configuration.get("size"));

        System.out.println(configuration.get("weight"));

    }

}
