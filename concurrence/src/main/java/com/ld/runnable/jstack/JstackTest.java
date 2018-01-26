package com.ld.runnable.jstack;

/**
 * Created by liudun on 2018/1/18.
 */
public class JstackTest {

    public static class Member{
        String name;
        Integer age;

        public Member(){

        }

        public synchronized void call()   {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("member method call()....");
            }
        }
    }

    /**
     * 打开Jsatack的命令为: jstack -l pid
     */
    public static void main(String[] args) throws InterruptedException {

        Member member = new Member();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("t1 is running.....");
                    member.call();
                }
            }
        });
        t1.setName("thread t1");
        t1.start();

//        Thread.sleep(1000);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    member.call();
                    System.out.println("t2 got the lock!!!!");
                }
            }
        });
        t2.setName("thread t2");
        t2.start();

    }
}
