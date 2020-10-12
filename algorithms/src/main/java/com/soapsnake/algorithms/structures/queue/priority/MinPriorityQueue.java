package com.soapsnake.algorithms.structures.queue.priority;


import com.soapsnake.algorithms.utils.NumberUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 小顶堆,可以用来解决最大n个数的问题
 * <p>
 * <p>
 * 100个元素找10个最大思路:
 * 1. 前10个元素构造小顶堆
 * 2.从第11个开始,分别于堆顶比较,如果发现数字比堆顶要大,那么交换堆顶,然后下沉(因为新堆顶不一定是堆里面的最小数字)
 * 3.重复步骤2
 * 4.可能存在的问题:????????
 */
public class MinPriorityQueue<T extends Comparable<T>> extends AbstractPriorityQueue<T> {
    private Integer degree = 0;   //树的度,定为2就是一个二叉树

    //不允许无参示例化
    private MinPriorityQueue() {
    }

    @SuppressWarnings("unchecked")
    public MinPriorityQueue(int size) {
        super.pq = (T[]) new Comparable[size + 1]; //0位留空不放数字,
        super.LEN = size + 1;
        super.tail = 0;
        this.degree = 2;  //二叉树,degree为3就是三叉树
    }

    public static void main(String[] args) {
        MinPriorityQueue<Integer> minPriorityQueue = new MinPriorityQueue<>(10);
        List<Integer> collection = NumberUtils.batchGenNonDup(20);
        Integer[] arr = {96, 65, 66, 36, 5, 69, 8, 40, 43, 44, 45, 16, 82, 52, 57, 91, 59, 92, 94, 31};
        collection = Arrays.asList(arr);
        System.out.println("原始arr: " + Arrays.toString(arr));
        System.out.println("最大10个数字为: 57, 59, 65, 66, 69, 82, 91, 92, 94, 96");
        for (int i = 0; i < collection.size(); i++) {
            minPriorityQueue.insert(collection.get(i));
        }
        System.out.println("最终结果:" + Arrays.toString(minPriorityQueue.pq));
        System.out.println("开始排序======================");
        System.out.println(Arrays.toString(minPriorityQueue.sort()));
    }

    //上浮cur指针指向的元素,哪种情况需要上浮了,父节点比子节点大的时候
    private void swim(int i) {
        while (i > 0 && super.lessThan(i, i / this.degree)) {
//            System.out.println("need change " + pq[i / this.degree] + "---->" + pq[i]);
            super.exchange(i / this.degree, i);
            i /= this.degree;
        }
    }

    //插入元素,如果pq没满直接插入,如果满了需要和堆顶比较决定是否需要入队或者丢弃
    @Override
    public void insert(T t) {
        if (t == null) {
            return;
        }
        if (tail + 1 < LEN) {               //如果pq没满那么直接插入
            pq[++tail] = t;
            this.swim(tail);
        } else {                             //如果pq满了,那么新元素需要视情况入队还是舍弃
            if (t.compareTo(pq[1]) <= 0) {

            } else {
                pq[1] = t;
                this.sinkTop();
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return super.tail == 0;
    }

    @Override
    public int size() {
        return super.tail;     //tail - 1 + 1 = tail
    }

    @Override
    public T[] sort() {
        T[] backup = (T[]) new Comparable[super.LEN];
        System.arraycopy(super.pq, 0, backup, 0, super.LEN);
        T[] res = (T[]) new Comparable[super.tail];
        for (int i = 0; i < res.length; i++) {
            res[i] = this.delMin();
        }
        super.pq = backup;
        return res;
    }

    /**
     * 下沉:父节点与两个子节点比较,如果小于等于那么不动,如果大于那么交换
     * i * degree 会指向最左子节点,用指针指向它,然后指针可以在所有字节点上移动,这时候我们可以给这些节点拍下序
     */
    private void sinkTop() {
        int i = 1;  //下沉必然是堆顶的下沉,其他节点不可能下沉
        while (i * degree <= super.tail) {
            int j = i * degree;  //最左子节点
            //这时候应该让j指向更小的节点,然后和父节点比较
            if (j < super.tail && super.lessThan(j + 1, j)) {
                ++j;
            }
            if (super.lessThan(i, j)) {  //如果父节点已经比两个子节点中最小的那个还小,这时候就表名节点已经到达准确位置
                break;
            }
            //到这里,说明,父节点比子节点中较小的那个大,那么交换这两个节点,继续往下走
            super.exchange(i, j);
            i = j;   //这个地方很容易错,把j赋给i实现指针的向下层移动,而不是i * degree
        }
    }

    /**
     * 从最小堆中移除堆顶,通过调这个接口可以实现从小到大排序
     */
    public T delMin() {
        T dest = pq[1];
        pq[1] = pq[tail];   //把堆尾移到堆顶,然后下沉
        pq[tail--] = null;    //堆尾置空防止重复计算,尾指针减1因为有一个元素被移除了
        this.sinkTop();
        return dest;
    }
}
