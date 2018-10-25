package com.ld.thinkinjava.generics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liudun on 2017/7/8.
 */
public class GenericsTest {

    GenericsTest() {
    }

    public <T> void testGen(T fuck) {
        System.out.println("void method but generics method!!!!!" + fuck);

    }

    public <T> List<T> get0() {


        //事实证明,泛型类变量T作用与整个方法的范围级别,可以在入参处使用T,也可以在方法内使用T
        return new ArrayList<T>();
    }

    public <U> U get(U fuck) {  //给任意类型参数,但是返回值类型必须和参数类型一致
        System.out.println("has return value method but generic:>>>> " + fuck.getClass().getSimpleName());

        return fuck;
    }

    public <V> String get2(V fuck) {   //入参是泛型类型,但是返回值必须是String


        return fuck.toString();
    }

    public <K> Date get3(K fuck) {   //入参是泛型类,但是返回值必须是Date


        return new Date();
    }

    public <X> List<X> get4(X fuck) {  //入参是泛型类,但是返回值是List<Integer>

        return new ArrayList<>();

    }

    //返回值类型一般不能泛化,即使是想泛化也只能泛华成和入参一样的类型
    public <Z> Z get5(Z fuck) {

        return fuck;
    }


    //超级泛型方法:1.入参个数不固定,2.入参类型不固定,3.每个入参的类型不固定;4.返回值类型不固定
    public <V> V get6(V... fuck) {


        return null;
    }

    public <T> T newObj(Class<T> t) {

        T obj = null;
        try {
            obj = t.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
