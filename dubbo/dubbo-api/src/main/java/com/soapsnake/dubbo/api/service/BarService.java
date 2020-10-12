package com.soapsnake.dubbo.api.service;

import com.soapsnake.dubbo.api.domain.Person;

/**
 * @author soapsnake
 * @date 2018/10/30
 */
public interface BarService {

    /**
     * * 泛化引用, 调用方不需要引入api所在jar包即可调到该接口
     *
     * @param name
     * @param age
     * @return
     */
    Person testBar(String name, Integer age);

}
