package com.soapsnake.lab.npe;

import lombok.Data;
import lombok.ToString;

/**
 *
 * Created on 2020-05-06
 */
public class TestNpe {

    public static void main(String[] args) {
        Person person1 = new Person();

        Person person = new Person();

        //这里会报出npe,原因是在三目运算符中,赋值会进行先拆箱再赋值,而不是直接赋值
        //比如这里的person1.getAge()  实际上调用的是Integer.intValue()在其中会进行integer的拆箱
        //根本原因是:根据java三目运算符的规范,第二和第三位的数据类型应该一致,要么都是原始类型,要么都是对象
        person.setAge(false ? 0 : person1.getAge());   //❌写法

        person.setAge(false ? Integer.valueOf(0) : person1.getAge());  //✅写法1,把0包装成对象类型

        AnotherPerson anotherPerson = new AnotherPerson();
        person.setAge(false ? 0 : anotherPerson.getAge());   //✅写法2,改age的类型为int
        System.out.println(person);
    }

    @Data
    @ToString
    static class Person {
        private String name;
        private Integer age;
    }

    @Data
    static class AnotherPerson {
        private String name;
        private int age;
    }
}
