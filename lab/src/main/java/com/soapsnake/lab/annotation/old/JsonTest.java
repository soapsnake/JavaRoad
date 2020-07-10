//package com.soapsnake.annotations;
//
//import com.alibaba.fastjson.JSON;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class JsonTest {
//
//
//    public static void main(String[] args) {
//
//        Person person = new Person();
//        person.setName("soapsnake");
//        person.setPassword("1234");
//        person.setAge(19);
//
//        String persJson = JSON.toJSONString(person);
//        System.out.println("will serialize person -> " + persJson);
//
//        Person person1 = JSON.parseObject(persJson , Person.class);
//
//        System.out.println("will deserialize person1 ->" + person1);
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        //加了这个后,mapper遇到A类中有而B类中没有的字段不会再报错
////        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//
//        System.out.println(person);
//        FakePerson fakePerson = mapper.convertValue(person, FakePerson.class);
//
//        System.out.println(fakePerson);
//
//
//    }
//
//
//
//}
