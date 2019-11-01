package com.soapsnake.pattern.creationals.builder;

public class ConstructorTester {

    /**
     * 实践发现初始化的顺序:
     * 1. 父类static
     * 2. 子类static
     * 3. 父类constructor(有参构造方法需要子类构造方法调用super(参数)来调用)
     * 4. 子类constrcutor
     *
     * @param args
     */
    public static void main(String[] args) {
        Son son = new Son();
    }

}
