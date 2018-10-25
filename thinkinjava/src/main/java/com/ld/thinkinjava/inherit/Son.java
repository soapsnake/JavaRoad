package com.ld.thinkinjava.inherit;

/**
 * Created by liudun on 2017/7/27.
 */
public class Son extends Parent {

    private String son1;

    private String son2;

    public Son() {

        System.out.println("子类构造方法被调用");
        System.out.println("this.parent1 被赋值前,this.parent1 = " + this.parent1);
        System.out.println("this.parent1 被赋值前,super.parent1 = " + super.parent1);

        this.son1 = "son1";
        this.son2 = "son2";
        this.parent1 = "son_paren1";
        this.parent2 = "son_paren2";


        //非公共字段不能被访问
//        this.priParent = "dsadsa";

        System.out.println("子类parent1被赋值后:this.parent1 =  " + this.parent1);
        System.out.println("子类parent1被赋值后:super.parent1 = " + super.parent1);

        super.parent1 = "父parent1被赋值";
        System.out.println(super.parent1);
        System.out.println(this.parent1);
    }

    public void printpar() {
        System.out.println("==================");
        System.out.println(super.parent1);
        System.out.println(this.parent1);
    }

    @Override
    public void parenShow1() {
        System.out.println("子类的parentShow1方法");
    }


}
