package com.soapsnake.lambda.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

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
