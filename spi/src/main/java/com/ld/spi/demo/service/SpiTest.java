package com.ld.spi.demo.service;

import java.util.ServiceLoader;

/**
 * @author soapsnake
 * @date 2018/10/26
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<ISayName> loader = ServiceLoader.load(ISayName.class);  //从配置文件里面读取实现类并实例化然后加载

        for (ISayName sayName: loader){
            sayName.say();
        }

    }
}
