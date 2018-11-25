package com.soapsnake.thinkinjava.generics;

/**
 * Created by liudun on 2017/7/9.
 */
public class SomeClass<T> {

    private String name;

    private Object x;

    SomeClass() {
    }

    public SomeClass(T t) {
        this.x = t;       //泛型类是Son类,Son类中有一个f()方法
    }

    public void dealWithT() {
        System.out.println("someclass has method f()");

//        x.f();   //x现在是一个Son对象,Son对象有f()方法,然而这里不能编译
        System.out.println(">>>>>>>>>>>>>>>>:" + x.getClass().getSimpleName());

//        x.f();   //无论x是什么类型的,都不能直接调用其方法,因为泛型会被擦除

    }

    public T creatT(Class<T> kind) {
        T obj = null;
        try {
            obj = kind.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
