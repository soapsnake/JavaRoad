package com.soapsnake.lab.lambda.stream;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * Created by soapsnake on 2017/6/1.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    private long id;

    private Integer age;

    private String name;

    private List<Person> friends;

}
