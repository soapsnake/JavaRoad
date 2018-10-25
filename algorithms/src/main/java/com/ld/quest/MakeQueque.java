package com.ld.quest;

import java.util.Stack;

/**
 * Created by liudun on 2018/1/22.
 * 用两个栈实现一个队列
 */
public class MakeQueque {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MakeQueque() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    public static void main(String[] args) {
        MakeQueque makeQueque = new MakeQueque();
        for (int i = 0; i < 50; i++) {
            makeQueque.add(i);
        }

        System.out.println(makeQueque.stack1);

        makeQueque.out();

    }

    //入队
    public void add(int num) {
        stack1.push(num);
    }

    //出队
    public void out() {
        for (int i = 0; i < stack1.size(); i++) {
            Integer temp = stack1.pop();
            stack2.push(temp);
        }

        System.out.println(stack2.pop());
    }


}
