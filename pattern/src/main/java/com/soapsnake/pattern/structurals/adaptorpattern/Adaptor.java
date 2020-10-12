package com.soapsnake.pattern.structurals.adaptorpattern;

/**
 * 适配类
 *
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-02 11:24
 */
public class Adaptor extends TobeAdapte implements TargetInterface {


    //Adaptor由于继承残疾人TobeAdapte而自动获取了其两个接口,这个叫做类适配器
    //如果在Adaptor中保存一个残疾人TobeAdapte的引用,那么就是对象适配器

    //现在只需要加一个新的接口补足残疾人的不足就行了
    @Override
    public void method3() {
        System.out.println("TargetObj -> method3");

    }
}
