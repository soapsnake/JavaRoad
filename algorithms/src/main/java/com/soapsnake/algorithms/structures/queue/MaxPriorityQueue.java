package com.soapsnake.algorithms.structures.queue;

import com.soapsnake.algorithms.utils.NumberUtils;

import java.util.Arrays;
import java.util.Collection;

/**
 * 大顶堆, 大顶堆可以用来解决最小k个数的问题
 * 在运算的过程中,大顶堆的顶部一定是算到目前为止的最大数字,比如我大顶堆大小为10,如果有10000个数字,如果
 * 从头开始运算,如果第一个数字就是这10000个数字里面最大的数字,那么大顶堆的顶部将永远是这个数字
 *
 * @param <K>
 */
@SuppressWarnings("unchecked")
public class MaxPriorityQueue<K extends Comparable<K>> implements PriorityQueue<K> {

    private int n = 0;   //指针,如果指向第0位,表明pq是空的,因为0位留空

    private int arrLength = 0;   //pq数组的实际大小

    private K[] pq;  //优先级队列的底层其实是一个数组,这个数组保存的是一颗树

    //不允许用无参构造
    private MaxPriorityQueue() {

    }

    /**
     * create a priority queue of initial capacity max
     *
     * @param max
     */
    public MaxPriorityQueue(int max) {  //max应该是大顶堆的最大容量
        //学着点泛型数组的创建方式
        //数组元素从1开始,0位留空

        this.pq = (K[]) new Comparable[max + 1];
        this.arrLength = max + 1;
    }

    /**
     * create a priority queue from the keys in a[]
     *
     * @param a
     */
    MaxPriorityQueue(K[] a) {
        //数组元素从1开始,0位留空

        this.pq = (K[]) new Comparable[a.length + 1];
        System.arraycopy(a, 0, pq, 1, pq.length - 1);
        this.n = pq.length;
    }

    //工厂方法
    public static <K> MaxPriorityQueue buildMaxPQ(int size) {
        return new MaxPriorityQueue(size);
    }

    public static void main(String[] args) {
        MaxPriorityQueue<Integer> queue = new MaxPriorityQueue<>(10);
        Collection<Integer> numbers = NumberUtils.batchGenNonDup(20);
        for (Integer i : numbers) {
            System.out.println("will insert = " + i);
            queue.insert(i);
        }

        //堆有序而不是pq有序!!!!!!!!!!!!
        System.out.println(Arrays.toString(queue.pq));
        System.out.println(queue.max());

//        while (!queue.isEmpty()) {
//            System.out.println(queue.delMax());
//        }

        //排序
        System.out.println(Arrays.toString(queue.sort()));
        System.out.println(Arrays.toString(queue.pq));


        /**
         * todo 如何利用maxpq来做大数据的topN问题?(数据量远大于pq的容量)
         * 假设数据量为m,我们的pq的容量是n,有 n <<< m
         * 思路:目前的pq只是实现了delmax,这是因为pq[1]一定是最大值,但是最小值并不容易取到(绝不是pq[n]!!!!)
         * 所以假设我们的pq为了腾容量删数据,那么删掉的有可能就是最终的最大值!!!
         */


    }

    K max() {
        return this.pq[1];   //堆顶的元素永远最大
    }

    /**
     * return and remove the largest key
     *
     * 这个接口到底干嘛用的: 当insert插入完待排序的元素后,我们的大顶堆就已经是堆有序的了,此时调这个delMax挨个把元素从
     * 大顶堆当中取出来比如放进一个ArrayList,那么这个ArrayList当中的数字就是有序的了
     *
     */
    public K delMax() {
        K max = pq[1];   // 堆顶的元素永远是最大的,这里用max缓存起来,方便后面返回
        this.exch(1, n--);  //堆顶元素和堆底元素互换,为后面的操作做准备,注意这里是n--,也就是交换之后n指针才会后移
        pq[n + 1] = null;   //最后一位置空方便GC,非常nice(此时n+1是指向原来的堆顶的也就是max)
        this.sink(1);    //现在的堆顶其实是原来的堆底,肯定不是最大数,需要下沉,下沉完了堆顶还是最大的
        return max;
    }

    private void exch(int i1, int i2) {
        K temp = pq[i1];
        pq[i1] = pq[i2];
        pq[i2] = temp;
    }

    @Override
    //新插入的元素是插入到数组的最后的,也就是处于树的最低端,如果是比较大的数字可能需要上浮
    public void insert(K k) {
        this.pq[++n] = k;   //如果插入元素后超过了最大容量了????
        this.swim(n);
    }

    //这个数据结构最核心的其实就是这个swim和下面的sink函数
    //新插入的堆底元素上浮
    private void swim(int n) {
        while (n > 0 && this.lessThan(n / 2, n)) { //如果父节点比子节点要小
            this.exch(n / 2, n); //那么交换父节点和该子节点
            n /= 2;   //n / 2后指向其父节点,这里其实并不关心兄弟节点的值得情况
        }
    }

    private boolean lessThan(int i1, int i2) {
        if (null == pq[i1]) {
            return false;
        }
        int res = pq[i1].compareTo(pq[i2]);
        return res < 0;
    }

    //堆中的第i个元素下沉
    private void sink(int i) {
        while (2 * i <= n) {
            int j = 2 * i;    //i元素的左子节点
            if (j < n && this.lessThan(j, j + 1)) {  //我们的j指针应该指向比较大的那个子节点
                j++;  //j在两个子节点上移动,指向比较大的那个
            }
            if (!this.lessThan(i, j)) { //父节点的值已经比子节点的值大的情况下就没必要继续算了
                break;
            }
            this.exch(i, j);  //父节点比子节点小那么需要交换
            i = j;
        }
    }

    //这个排序算法,由于要用到del方法,排序结束会清空pq,所以这里用了点技巧把pq缓存了起来,排序结束后再赋值回来
    public K[] sort() {
        K[] backup = (K[]) new Comparable[arrLength];
        System.arraycopy(pq, 0, backup, 0, arrLength);
        K[] res = (K[]) new Comparable[n];
        for (int i = 0; i < res.length; i++) {
            res[i] = this.delMax();
        }
        this.pq = backup;
        return res;
    }

    @Override
    public boolean isEmpty() {
        //如果指针移动到了第0位,那么表明pq已经空了
        return this.n == 0;
    }

    @Override
    public int size() {
        return this.n;
    }

    //新加元素与堆顶比较,如果比堆顶小,那么交换堆顶,然后下沉堆顶
    @Override
    public void addMore(K k) {

    }
}
