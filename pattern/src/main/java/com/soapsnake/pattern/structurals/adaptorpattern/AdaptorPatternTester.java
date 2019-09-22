package com.soapsnake.pattern.structurals.adaptorpattern;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-01-02 11:25
 */
public class AdaptorPatternTester {

    public static void main(String[] args) {

        //adaptor 使残疾人TobeApate能够满足TargetInterface的要求
        //Aaptor对象实现了目标接口,又继承了残疾人TobeApate原有的接口,
        // 因此只需要在Adaptor中补足残疾人的缺陷就可以了

        TargetInterface adaptor = new Adaptor();
        adaptor.method1();
        adaptor.method2();
        adaptor.method3();
    }
}
