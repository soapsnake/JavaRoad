package com.soapsnake.lab.functioninterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntConsumer;

public class FunctionInterfaceTester {

    public static void main(String[] args) {

        FunctionInterfaceTester tester = new FunctionInterfaceTester();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //这个value -> System.out.println(value + 1)就是一个function,也是IntConsumer接口的一个匿名实现
        //所以这里第二个参数其实是一个函数,类似回调的思想,简化代码,仅此而已
        tester.doSome(list, value -> System.out.println(value + 1));
    }

    private void doSome(List<Integer> list, IntConsumer consumer) {
        for (Integer integer : list) {
            consumer.accept(integer);
        }
    }

}
