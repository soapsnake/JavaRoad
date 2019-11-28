package com.soapsnake.algorithms.structures.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 设计一个栈,要求min()得到最小元素,push, pop,o1复杂度
 */
public class O1Stack {
    private List<Integer> items;
    private List<Integer> minList;

    public O1Stack() {
        this.items = new ArrayList<>();
        this.minList = new ArrayList<>();
    }

    /**
     * @return min of stack
     */
    public Integer min() {
        if (this.minList.size() > 0) {
            return this.minList.get(0);
        } else {
            return null;
        }
    }

    /**
     *
     */
    public void push(Integer o) {
        if (o == null) {
            return;
        }
        if (this.minList.size() == 0) {
            this.minList.add(o);
        } else {
            for (int i = minList.size() - 1; i >= 0; i--) {
                if (minList.get(i) <= o) {
                    minList.add(i + 1, o);
                    break;
                }
            }
        }
        items.add(o);
    }

    /**
     * pop from stack
     *
     * @return
     */
    public Integer pop() {
        if (items.size() == 0) {
            return null;
        }
        Integer res = items.remove(items.size() - 1);
        minList.remove(res);
        return res;
    }

    public static void main(String[] args) {
        O1Stack stack = new O1Stack();
        stack.push(1);
        stack.push(2);
        stack.push(123);
        stack.push(17);
        stack.push(10);
        System.out.println("最小" + stack.minList);


        stack.pop();
        stack.pop();
        stack.printStack();
        System.out.println(stack.min());

        System.out.println("最小" + stack.minList);
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println("最小" + stack.minList);
        System.out.println(stack.min());
        stack.printStack();
    }

    public void printStack() {
        System.out.println(this.items);
    }
}
