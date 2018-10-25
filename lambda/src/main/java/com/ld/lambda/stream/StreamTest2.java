package com.ld.lambda.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudun on 2017/6/1.
 */
public class StreamTest2 {

    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();
        for (long i = 0; i < 101; i++) {
            Person person = new Person();
            person.setId(i);

            person.setAge(i * 2);
            list.add(person);
        }

        long res = list.stream().mapToLong(Person::getId).sum();

        long age = list.stream().mapToLong(Person::getAge).sum();

        System.out.println("size: " + list.size());

        System.out.println("res: " + res);

        System.out.println("age: " + age);

//        long totalDepositAmount = resps.stream().mapToLong(TotalReceiptsResp::getTotalDepositAmount).sum();
//        long totalCardAmount = resps.stream().mapToLong(TotalReceiptsResp::getTotalCardAmount).sum();

    }
}
