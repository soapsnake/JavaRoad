package com.soapsnake.dubbo.provider.demo;

import com.soapsnake.dubbo.api.domain.Person;
import com.soapsnake.dubbo.api.service.BarService;

/**
 * @author soapsnake
 * @date 2018/10/30
 */
public class BarServiceImpl implements BarService {
    @Override
    public Person testBar(String name, Integer age) {
        Person person = new Person();
        person.setAge(age);
        person.setName(name);
        System.out.println("this is barservice impl!!!!" + person.toString());
        return person;
    }
}
