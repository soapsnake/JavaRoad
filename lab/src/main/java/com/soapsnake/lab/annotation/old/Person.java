package com.soapsnake.lab.annotation.old;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang3.RandomStringUtils;

public class Person {

    static {
        System.out.println("static has been called");
    }

    private String name;

    private String password;

    private Integer age;

    //serialize = true则进行json序列化时就会调到该get方法,其json字符串里面就会多出一个名叫params的字段,哪怕该类没有定义这个字段
    //deserilaize = true 则进行json反序列化时会调到该get方法
    @JSONField(serialize = true, deserialize = true)  //进行序列化
    public String[] getParams() {
        String[] strings = new String[10];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = RandomStringUtils.randomAlphanumeric(20);
        }
        return strings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
