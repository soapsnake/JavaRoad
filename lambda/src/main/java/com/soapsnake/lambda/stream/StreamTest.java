package com.soapsnake.lambda.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liudun on 2017/5/27.
 */
public class StreamTest {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("liudun", "zhangliting", "maohongyan");

        //计算list中每个单词的hashcode,并且输出到控制台,map为中间操作,forEach为终结操作常用来做遍历
        list.stream().map(String::hashCode).forEach(System.out::println);

        //计算list中每个单词的长度,并且输出到一个新的list,map是个中间操作,collect是终结操作专门用来把stream转成集合
        List<?> list1 = list.stream().map(String::length).collect(Collectors.toList());
        System.out.println(list1.toString());


    }
}
