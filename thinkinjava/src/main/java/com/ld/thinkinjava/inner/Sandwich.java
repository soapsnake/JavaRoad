package com.ld.thinkinjava.inner;

/**
 * Created by liudun on 2017/6/18.
 */
public class Sandwich extends PortableLunch{

    Bread bread  = new Bread();
    Cheese cheese = new Cheese();
    Lettuce lettuce = new Lettuce();

    Sandwich(){
        System.out.println("Sandwich()");
    }

    /**
     *1.父父父类Meal构造方法
     * 2.父父类Lunch构造方法
     * 3.父类portableLunch构造方法
     * 4.字段bread的构造方法
     * 5.字段cheese的构造方法
     * 6.字段lettuce的构造方法
     * 7.自己的构造方法
     * @param args
     */
    public static void main(String[] args) {
        new Sandwich();


    }



}
