package com.ld.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by liudun on 2017/5/27.
 */
public class StreamTest {

    public static void main(String[] args){

        List<String> list = Arrays.asList("liudun","zhangliting","maohongyan");

        //计算list中每个单词的hashcode,并且输出到控制台
       list.stream().map(String::hashCode).forEach(System.out::println);

        //计算list中每个单词的长度,并且输出到一个新的list
       List<?> list1 = list.stream().map(String::length).collect(Collectors.toList());
        System.out.println(list1.toString());



    }
}
