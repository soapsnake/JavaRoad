package com.soapsnake.algorithms.structures.queue;

public abstract class AbstractPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
    protected T[] pq;

    protected Integer tail;   //尾指针,始终指向最后一个元素

    protected Integer LEN;  //长度(会比实际容量大1,因为pq[0]不放元素)

    final public boolean lessThan(int i1, int i2) {
        if (null == pq[i1]) {
            return false;
        }
        if (null == pq[i2]) {
            return false;
        }
//        System.out.println("will compare i1 = "+ i1+ " i2 = "+ i2 +" pq =  " + Arrays.toString(pq));
        int res = pq[i1].compareTo(pq[i2]);
        return res < 0;
    }

    final public void exchange(int i1, int i2) {
        T temp = pq[i1];
        pq[i1] = pq[i2];
        pq[i2] = temp;
    }
}
