package com.soapsnake.algorithms.structures.stack;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by soapsnake on 2018/1/22.
 * 用两个队列实现一个栈
 */
public class MakeStack {

    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public MakeStack() {
        this.queue1 = new ArrayBlockingQueue<Integer>(100);
        this.queue2 = new ArrayBlockingQueue<Integer>(100);
    }

    public static void main(String[] args) {

        MakeStack makeStack = new MakeStack();
        for (int i = 0; i < 30; i++) {
            makeStack.push(i, makeStack.queue1);
        }
        System.out.println(makeStack);
    }

    //
    public void push(Integer num, Queue<Integer> queue) {
        queue.add(num);
    }

    //出栈,把队列1的size -1 个数据灌入队列2,则队列1剩余的一个元素就是最后一个元素,把这个元素弹出,就实现了弹栈
    //出完栈之后,队列1就是空的了,这时候,新入栈元素放入队列2,弹栈的时候弹队列2的size -1个元素移入队列1,最后一个就是要弹的
    //如此循环往复,就实现了要求的功能
    public void pop() {
        for (int i = 0; i < queue1.size() - 1; i++) {
            queue2.add(queue1.poll());
        }
        System.out.println(queue1.toString());
        queue1.poll();
    }

    @Override
    public String toString() {
        return this.queue1.toString() + this.queue2.toString();
    }

}
