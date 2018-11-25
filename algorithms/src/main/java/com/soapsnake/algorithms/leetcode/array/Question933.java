package com.soapsnake.algorithms.leetcode.array;

import java.util.TreeMap;

/**
 * @author soapsnake
 * @date 2018/11/4
 *
 * 需求:实现一个类,该类可以记录每次访问的时间
 * 要求:该类有一个接口,入参为一个时间,返回值为传入时间正负3秒内访问系统的总次数
 *
 *
 * 思路1:访问时间存arraylist,取到两个边界时间的index,然后index相减即可取得访问总次数
 * 思路2:如下利用TreeMap,关键是其tailMap方法
 *
 */
class Question933 {

    //todo 利用上面的思路1自己实现一遍
    class RecentCounter {

        TreeMap<Integer, Integer> tm;   //TreeMap是有序的,底层由红黑树实现

        public RecentCounter() {
            tm = new TreeMap<>();
        }

        public int ping(int t) {
            tm.put(t, 1 + tm.size());   //<时间, 累计访问次数>
            return tm.tailMap(t - 3000).size();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Question933.RecentCounter recentCounter = new Question933().new RecentCounter();
        Thread.sleep(1000L);
        System.out.println(recentCounter.ping((int)System.currentTimeMillis()));
    }

}
