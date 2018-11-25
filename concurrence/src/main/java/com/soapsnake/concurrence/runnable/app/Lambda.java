package com.soapsnake.concurrence.runnable.app;

public class Lambda {

    public static void main(String[] args) {
        // Auto-generated method stub

        Lambda lambda = new Lambda();

        //jiafa是一个LambdaInterface类型的变量，该变量指向一个Lambda表达式，也就是一个匿名函数
        LambdaInterface jiafa = (a, b) -> a + b;
        System.out.println(lambda.doSomeThing(3, 1, jiafa));


        LambdaInterface2 print = () -> System.out.println("this is a lambda expression");

        lambda.toPrint(print);
        ;
    }

    private int doSomeThing(int a, int b, LambdaInterface lambdaInterface) {
        return lambdaInterface.operation(a, b);
    }

    private void toPrint(LambdaInterface2 print) {
        print.print();
    }

}
