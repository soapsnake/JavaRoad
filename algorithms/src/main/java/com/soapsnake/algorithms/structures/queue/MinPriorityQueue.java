package com.soapsnake.algorithms.structures.queue;


import com.soapsnake.algorithms.utils.NumberUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 小顶堆,可以用来解决最大n个数的问题
 *
 *
 * 100个元素找10个最大思路:
 * 1. 前10个元素构造小顶堆
 * 2.从第11个开始,分别于堆顶比较,如果发现数字比堆顶要大,那么交换堆顶,然后下沉(因为新堆顶不一定是堆里面的最小数字)
 * 3.重复步骤2
 * 4.可能存在的问题:????????
 */
public class MinPriorityQueue<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private Integer degree = 0;   //树的度,定为2就是一个二叉树
    private Integer curIndex;
    private final Integer LEN;
    //不允许无参示例化
    private MinPriorityQueue() {
        LEN = 0;
    }

    public MinPriorityQueue(int size) {
        pq = (T[]) new Comparable[size + 1]; //0位留空不放数字,
        this.LEN = size + 1;
        curIndex = 1;  //0位留空
        this.degree = 2;  //二叉树
    }

    //上浮cur指针指向的元素
    private void swim(int i) {
        System.out.println("i = " + i);
        while (i > 1 && super.lessThan(i, i / this.degree)) { //如果比父节点更小,那么就交换
            super.exchange(i / this.degree, i);
            i /= this.degree;
        }
    }

    //插入元素小于堆容量时使用这个接口,构造初始堆时可以调用该接口
    @Override
    public void insert(T t) {
        pq[curIndex] = t;
        this.swim(curIndex);
        if (++curIndex > LEN) {
            throw new RuntimeException("个数超限!!!!");
        }
    }

    @Override
    public boolean isEmpty() {
        return curIndex == 0;
    }

    @Override
    public int size() {
        return curIndex;
    }

    /**
     * topK问题,最小堆用来解最大N个数的问题,因为堆顶最小,所以只需和堆顶比较,大于堆顶,那么交换堆顶然后下沉
     * @param t
     */
    @Override
    public void addMore(T t) {
        if (t == null) {
            return;
        }
        if (t.compareTo(pq[1]) <= 0) {   //新元素比堆顶小

        } else {
            pq[1] = t;
            this.sink(1);
        }
    }

    /**
     * 下沉:父节点与两个子节点比较,如果小于等于那么不动,如果大于那么交换
     * i * degree 会指向最左子节点,用指针指向它,然后指针可以在所有字节点上移动,这时候我们可以给这些节点拍下序
     * @param i
     */
    private void sink(int i) {
        while (i * degree <= LEN - 1) {
            int j = i * degree;  //最左子节点
            //这时候应该让j指向更小的节点,然后和父节点比较
            System.out.println(curIndex + "->" + Arrays.toString(pq));
            if (j + 1 <= curIndex - 2 && !super.lessThan(j,j + 1)) {
                ++j;
            }
            if (super.lessThan(i,j)) {  //如果父节点已经比两个子节点中最小的那个还小,这时候就表名节点已经到达准确位置
                break;
            }
            //到这里,说明,父节点比子节点中较小的那个大,那么交换这两个节点,继续往下走
            super.exchange(i,j);
            i *= degree;
        }
    }

    /**
     * 从最小堆中移除堆顶,通过调这个接口可以实现从小到大排序
     */
    public T removeMin() {
        T dest = pq[1];
        pq[1] = pq[curIndex - 1];   //把堆尾移到堆顶,然后下沉
        pq[(curIndex--) - 1] = null;    //堆尾置空防止重复计算,尾指针减1因为有一个元素被移除了
        this.sink(1);
        return dest;
    }

    public static void main(String[] args) {
        MinPriorityQueue<Integer> minPriorityQueue = new MinPriorityQueue<>(10);
        List<Integer> collection = NumberUtils.batchGenNonDup(20);
        Integer[] arr = {96, 65, 66, 36, 5, 69, 8, 40, 43, 44, 45, 16, 82, 52, 57, 91, 59, 92, 94, 31};
        collection = Arrays.asList(arr);
        System.out.println("原始arr: " + Arrays.toString(arr));
        System.out.println("最大10个数字为: 57, 59, 65, 66, 69, 82, 91, 92, 94, 96");
        for (int i = 0; i < collection.size(); i++) {
            if (i < 10) {
                minPriorityQueue.insert(collection.get(i));
                if (i == 9) {
                    System.out.println("pq构造完成: " + Arrays.toString(minPriorityQueue.pq));
                }
            } else {
                minPriorityQueue.addMore(collection.get(i));
            }
        }
        System.out.println("最终结果:" + Arrays.toString(minPriorityQueue.pq));
        System.out.println(minPriorityQueue.LEN);

        while (minPriorityQueue.LEN >= 1) {
            System.out.print(minPriorityQueue.removeMin() + ",");
        }
    }
}
