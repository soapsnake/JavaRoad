package com.soapsnake.lab.lambda.stream.flatmap;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;

public class FlatMapTest1 {

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("好,好,学", "习,天,天", "向,上");

        int[] re = new int[10];
        System.out.println(Arrays.toString(re));

        List<String[]> strArray = strs.stream().map(str -> str.split(",")).collect(Collectors.toList());

        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(strArray);

        System.out.println("strArray => " + jsonArray.toString());

        strs.stream().map(str -> str.split(",")).forEach(o -> System.out.println(Arrays.toString(o)));

        // flatMap与map的区别在于 flatMap是将一个流中的每个值都转成一个个流，然后再将这些流扁平化成为一个流 。
        List<String> strList = strs.stream().map(str -> str.split(",")).flatMap(Arrays::stream)
                .collect(Collectors.toList());

        System.out.println("strList => " + strList);

    }
}
