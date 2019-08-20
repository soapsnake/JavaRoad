package com.soapsnake.algorithms.leetcode.concurrency;

public class Question1114 {

    static class Foo {


        /**
         * Example 1:
         * Input: [1,2,3]
         * Output: "firstsecondthird"
         * Explanation: There are three threads being fired asynchronously.
         * The input [1,2,3] means thread A calls first(), thread B calls second(),
         * and thread C calls third(). "firstsecondthird" is the correct output.
         */
        private volatile boolean firstrun;
        private volatile boolean secondrun;
        public Foo() {
            this.firstrun = false;
            this.secondrun = false;
        }

        public synchronized void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            this.firstrun = true;
            notifyAll();  //唤醒其他的等锁线程
        }

        public synchronized void second(Runnable printSecond) throws InterruptedException {

            // printSecond.run() outputs "second". Do not change or remove this line.
            while (!this.firstrun) {
                wait();  //如果第一次打印还没有执行过,那么先等锁
            }
            this.secondrun = true;
            notifyAll();
            printSecond.run();
        }

        public synchronized void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            while (!this.secondrun) {
                wait();
            }
            notifyAll();
            printThird.run();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String name = "";
        int[] ints = {1,3,2};
        Foo foo = new Foo();

        System.out.println(name);
        Runnable first = () -> System.out.println("this is first");
        Runnable second = () -> System.out.println("this is second");
        Runnable third = () -> System.out.println("this is third");
        for (int i : ints) {
            if (i == 1) {
                foo.first(first);
            } else if (i == 2) {
                foo.second(second);
            } else {
                foo.third(third);
            }
        }
    }
}
