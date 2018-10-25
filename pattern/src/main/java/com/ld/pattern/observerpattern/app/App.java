package com.ld.pattern.observerpattern.app;


import com.ld.pattern.observerpattern.object.ObjectConcreateImpl;
import com.ld.pattern.observerpattern.observer.ObserverConcreateImpl;

/**
 * 观察者模式!!!!!!!!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        /**
         * 初始化一个目标对象
         */
        ObjectConcreateImpl object = new ObjectConcreateImpl();

        /**
         * 创建两个观察者对象:小明和小刚
         */
        ObserverConcreateImpl obsever1 = new ObserverConcreateImpl();
        obsever1.setObserverName("小明");
        ObserverConcreateImpl obsever2 = new ObserverConcreateImpl();
        obsever2.setObserverName("小刚");

        /**
         * 注册观察者
         */
        object.registorObserver(obsever1);
        object.registorObserver(obsever2);


        /**
         * 设置目标内容
         */
        object.setObjectContent("300");

    }
}
